package service;

import EntityDTO.UserAuthDTO;
import entity.Order;
import entity.OrderStatus;
import entity.Product;
import entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductRepository;
import repository.UserRepository;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addProductToCart(Long userId, Long productPropertyId, double size) {
        Optional<User> user = userRepository.findById(userId);
        System.out.println("Пользователь с ID " + userId + " не найден");

      Order order = getCartingOrder(user.get());
      order.getProducts().add(productRepository.getProductBySizeAndProductPropertyId(size, productPropertyId));


    }

    public Order getCartingOrder(User user){
        //если у юзера есть заказ в статусе картинг, то мы его возвращаем, иначе создаем
        for (Order order: user.getOrders()){
            if(order.getOrderStatus().equals(OrderStatus.CARTING)){
                return order;
            }
        }
        List<Product> products = new ArrayList<>();
        Order order =  Order.builder().orderStatus(OrderStatus.CARTING).products(products).build();
        user.getOrders().add(order);
        return order;
    }

    public void authorizeUser(UserAuthDTO userAuthDTO) throws AuthenticationException {
        String username = userAuthDTO.getUsername();
        String password = userAuthDTO.getPassword();

        // Найдем пользователя по его имени пользователя
        User user = userRepository.findByUsername(username);
        System.out.println("Пользователь с именем пользователя " + username + " не найден");

        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Неправильный пароль для пользователя " + username);
        }

       /* String token = generateToken(user);
        authenticateWithToken(token);*/
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getName());
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, String.valueOf(SECRET_KEY))
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(String.valueOf(SECRET_KEY)).parseClaimsJws(token);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

/*    public UsernamePasswordAuthenticationToken authenticateWithToken(String token) {
        if (validateToken(token)) {
            Claims claims = Jwts.parser().setSigningKey(String.valueOf(SECRET_KEY)).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            UserDetails userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            return authenticationToken;
        }
        return null;
    }*/
}
