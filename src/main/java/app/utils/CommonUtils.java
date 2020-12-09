package app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {

    public static final String  JWT_KEY_USER_ID = "uid";


    public static String encoderPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean verifyPassword(String password,String encoderPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password,encoderPassword);
    }
}
