package app.service.impl;

import app.dao.EmailCodeDao;
import app.entity.EmailCode;
import app.entity.User;
import app.service.EmailService;
import app.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @ClassName EmailServiceImpl
 * @Description
 * @Author haomj
 * @Date 2021/1/12 14:42
 * @Version 1.0
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmailCodeDao emailCodeDao;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmail(User user) {
        String code = CommonUtils.getEmailCode(4);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("ft_ken@163.com");
            helper.setTo(user.getEmail());
            helper.setSubject("黑凤梨系统注册");
            helper.setText(CommonUtils.getEmailMode(user, code), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setUserId(user.getId());
        emailCode.setStatus(1);
        emailCode.setType(1);
        emailCodeDao.insert(emailCode);
    }
}
