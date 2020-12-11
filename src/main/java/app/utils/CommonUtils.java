package app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {

    public static final String  JWT_KEY_USER_ID = "uid";


    /**
     * 加密密码
     * @param password
     * @return
     */
    public static String encoderPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /**
     * 效验密码
     * @param password
     * @param encoderPassword
     * @return
     */
    public static boolean verifyPassword(String password,String encoderPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password,encoderPassword);
    }
}
