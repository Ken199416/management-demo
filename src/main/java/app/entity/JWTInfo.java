package app.entity;

import lombok.Data;

/**
 * @program: jemter-testapp
 * @description: JWTInfo
 * @author: hmj
 * @create: 2020-04-13 10:49
 **/
@Data
public class JWTInfo {
    private String uid;

    public JWTInfo(String uid) {
        this.uid = uid;
    }
}
