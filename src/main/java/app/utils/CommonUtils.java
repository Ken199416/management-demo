package app.utils;

import app.vo.ResponseData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 把实体的所有属性put一个map中
     * @param object
     * @param map
     * @return
     */
    public static Map<String,Object> bean2Map(Object object,Map map) {
        Class clazz = object.getClass();
        Field [] fields = clazz.getDeclaredFields();
        Method method = null;
        for (Field field : fields) {
            try {
                method = clazz.getMethod("get" + getMethodName(field.getName()));
                map.put(field.getName(),method.invoke(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }


//    public static void main(String[] args) throws Exception {
//        ResponseData responseData = new ResponseData(200,"SUCCESS","TEST");
////        Class responseClazz = responseData.getClass();
////        Field[] fields = responseClazz.getDeclaredFields();
////        for (Field f:fields
////             ) {
////            System.out.println(f.getName());
////        }
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("test","这就是一个测试");
//        Map<String,Object> result = bean2Map(responseData,map);
//        for (String key : result.keySet()
//             ) {
//            System.out.println(key + " : " + result.get(key));
//        }
//    }
}
