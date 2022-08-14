package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class Mail {
    public void send() {

        //세션 세팅
        Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {
            MailInfo mailInfo = new MailInfo();
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailInfo.getSenderMail(), mailInfo.getSenderMailPassword());
            }
        });
        session.setDebug(true);

        //메일 보낼 정보 세팅
        MimeMessage mail = new MimeMessage(session);
        settingMail(mail);

        //메일 전송
        transport(mail);
    }

    private void settingMail(MimeMessage mail) {
        MailInfo mailInfo = new MailInfo();
        try {
            mail.setFrom(new InternetAddress(mailInfo.getSenderMail()));
            mail.setRecipients(Message.RecipientType.TO, mailInfo.getRecipientMail());
            mail.setSubject(new MailDetail().subject());
            mail.setContent(new MailDetail().body(), "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private Properties getProperties() {
        MailInfo mailInfo = new MailInfo();

        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailInfo.getHost());
        props.put("mail.smtp.port", mailInfo.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", mailInfo.getHost());

        return props;
    }

    private void transport(MimeMessage mail) {
        try {
            Transport.send(mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
