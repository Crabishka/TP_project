package service;

import EntityDTO.UserAuthDTO;
import entity.Product;
import entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.naming.AuthenticationException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addProductToCart(Long userId, Long productId, int size) {
        User user = userRepository.findById(userId);
        System.out.println("Пользователь с ID " + userId + " не найден");

        Product product = new Product();
        product.setId(Math.toIntExact(productId));
        product.setSize(size);

        user.getOrders().add(product);

        userRepository.save(user);
    }

    public void authorizeUser(UserAuthDTO userAuthDTO) throws AuthenticationException {
        String username = userAuthDTO.getUsername();
        String password = userAuthDTO.getPassword();

        // Найдем пользователя по его имени пользователя
        User user = userRepository.findByUsername(username);
        System.out.println("Пользователь с именем пользователя " + username + " не найден");//try catch не получился, но что-то вставить надо

        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Неправильный пароль для пользователя " + username);
        }

        String token = generateToken(user);
        authenticateWithToken(token);
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

    public UsernamePasswordAuthenticationToken authenticateWithToken(String token) {
        if (validateToken(token)) {
            Claims claims = Jwts.parser().setSigningKey(String.valueOf(SECRET_KEY)).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            UserDetails userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            return authenticationToken;
        }
        return null;
    }
}
