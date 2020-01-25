package com.daniel.rest.webservices.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Class for generating encoded password with BCrypt
public class BcryptEncoderTest {

    public static void main(String[] args){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        for(int i = 1; i <= 10; i++){
            String encodedPassword = bCryptPasswordEncoder.encode("DaniAhr!89");
            System.out.println(encodedPassword);
        }
    }
}
