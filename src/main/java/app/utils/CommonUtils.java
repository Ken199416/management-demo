package app.utils;

import app.entity.User;
import app.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.nio.cs.US_ASCII;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.plaf.PanelUI;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    public static String getMethodName(String fieldName) throws Exception{
        byte[] items = fieldName.getBytes();
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

    public static String getEmailMode(User user,String code){
        return "<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                "    <table cellpadding=\"0\" align=\"center\" style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <th valign=\"middle\" style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #42a3d3; background-color: #49bcff; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                "                <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">注册验证码 ！（黑凤梨）</font>\n" +
                "            </th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                "                    <h2 style=\"margin: 5px 0px; \">\n" +
                "                        <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
                "                            <font style=\"line-height: 22px; \" size=\"4\">\n" +
                "                                亲爱的 " + user.getEmail() + "</font>\n" +
                "                        </font>\n" +
                "                    </h2>\n" +
                "                    <p>首先感谢您加入本站！下面是您的账号注册信息<br>\n" +
                "                        您的账号：<b>" + user.getUserName() + "</b><br>\n" +
                "                        您的邮箱：<b> " + user.getEmail() + " </b><br>\n" +
                "                        验证码：<b>" + code + "</b><br>\n" +
                "                        当您在使用本网站时，遵守当地法律法规。<br>\n" +
                "                        如果您有什么疑问可以联系管理员，Email: ft_ken@163.com</p>\n" +
                "                    <p align=\"right\">黑凤梨</p>\n" +
                "                    <p align=\"right\"> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " </p>\n" +
                "                    <div style=\"width:700px;margin:0 auto;\">\n" +
                "                        <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                "                            <p>此为系统邮件，请勿直接回复！<br>\n" +
                "                                请保管好您的邮箱地址，避免账号被他人盗用\n" +
                "                            </p>\n" +
                "                            <p>© Black Pineapple</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "      </tbody>" +
                "    </table/>" +
                "  </div>";
    }

    /**
     * @Author : haomj
     * @Date : 14:54 2021/1/12
     * @param num 几位数的验证码
     * return : java.lang.String
     * @Description :
     */
    public static String getEmailCode(int num){
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//保存数字0-9 和 大小写字母
        StringBuffer sb = new StringBuffer(); //声明一个StringBuffer对象sb 保存 验证码
        for (int i = 0; i < num; i++) {
            Random random = new Random();//创建一个新的随机数生成器
            int index = random.nextInt(string.length());//返回[0,string.length)范围的int值    作用：保存下标
            char ch = string.charAt(index);//charAt() : 返回指定索引处的 char 值   ==》赋值给char字符对象ch
            sb.append(ch);// append(char c) :将 char 参数的字符串表示形式追加到此序列  ==》即将每次获取的ch值作拼接
        }
        return sb.toString();//toString() : 返回此序列中数据的字符串表示形式   ==》即返回一个String类型的数据
    }
}
