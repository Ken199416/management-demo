package app.utils;

import app.entity.JWTInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * @program: jemter-testapp
 * @description: JWTTokne验证
 * @author: hmj
 * @create: 2020-04-13 10:50
 **/
public class JwtTokenUtils {
//    TOKEN 7天过期
    public static final int EXPIRE = 7;
    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary("autoTest-user");
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    /**
     * 生成token的方法
     * @param jwtInfo    plusSeconds(EXPIRE)
     * @return
     */
    public static String generatorToken(JWTInfo jwtInfo){
        return Jwts.builder().claim(CommonUtils.JWT_KEY_USER_ID,jwtInfo.getUid())
                .setExpiration(DateTime.now().plusDays(EXPIRE).toDate())
//                .setExpiration(DateTime.now().plusSeconds(10).toDate())
                .signWith(SignatureAlgorithm.HS256,getKeyInstance()).compact();
    }

    /**
     * 根据token获取token中的信息
     * @param token
     * @return
     */
    public static JWTInfo getTokenInfo(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return new JWTInfo(claims.get(CommonUtils.JWT_KEY_USER_ID).toString());
    }
}
