package com.ndtm.passwordmanager;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailTest {
    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("noreply", "password"))
            .withPerMethodLifecycle(false);

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void sendTest() {
        String verificationLink = UUID.randomUUID().toString();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("noreplyTest@gmail.com");
        mail.setSubject("Continue registration");
        mail.setText(verificationLink);
        mail.setTo("test@gmail.com");

        javaMailSender.send(mail);

        MimeMessage receivedMessage = greenMail.getReceivedMessages()[0];
        assertEquals(verificationLink, GreenMailUtil.getBody(receivedMessage));
    }

}
