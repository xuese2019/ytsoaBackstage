package com.yts.ytsoa.business.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * email test
 * 带有附件方法
 */
@RestController
@RequestMapping("/emailtest")
public class EmailController {
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String forEmail;

    @RequestMapping("/toemail")
    public boolean toEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(forEmail);
            message.setTo("xuesemofa@126.com");
            // 邮件主题
            message.setSubject("逸云平台注册");
            message.setText("Hello Word!");
            mailSender.send(message);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
