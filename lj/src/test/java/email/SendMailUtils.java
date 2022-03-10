package email;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2020/5/12
 */

public class SendMailUtils {

    public static void sendMessage(String toEmail,String title,String content) {
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号
        props.put("mail.user", "1077387582@qq.com");
 //       props.put("mail.user", "2095014352@qq.com");
        // 此处填写16位STMP口令
        props.put("mail.password", "ijmktaeqvdlwhgae");
 //       props.put("mail.password", "blukohafwlsjegfa");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(toEmail);
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置邮件标题

            message.setSubject(title);


        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        sendMessage("598448084@qq.com","保证书","我李鲨鱼保证决不再对王葡萄女士说任何关于滚的话 ");
    }

}
