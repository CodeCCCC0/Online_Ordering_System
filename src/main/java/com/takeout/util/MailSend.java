package com.takeout.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSend {
    private static final Logger logger = LoggerFactory.getLogger(MailSend.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    //邮件发送工具类，通过JavaMail实现邮件的发送
    public void sendMail(String to, String subject, String content){
        try {
            //创建MimeMessage对象用于装载发送邮件的信息
            MimeMessage message = mailSender.createMimeMessage();
            //MimeMessageHelper帮助类，方便配置JavaMail相关参数
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);//发信人
            helper.setTo(to);//收信人
            helper.setSubject(subject);//邮件主题
            //邮件内容，参数二设置为true，则带有html格式的邮件内容将被解析为html页面显示
            helper.setText(content, true);

            mailSender.send(helper.getMimeMessage());//调用send方法发送邮件
        }catch (MessagingException e){
            logger.error("发送失败: " + e.getMessage());
        }
    }
}
