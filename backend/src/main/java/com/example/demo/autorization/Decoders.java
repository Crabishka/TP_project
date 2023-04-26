package com.example.demo.autorization;

import java.util.Base64;

public class Decoders {
    public static byte[] BASE64_decode(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
