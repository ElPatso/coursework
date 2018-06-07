package ua.lemekh.mailEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.lemekh.model.User;

import javax.mail.SendFailedException;
import java.util.logging.Logger;

@Service
public class MailSernderImpl implements MailSernder {

    private static final String NEW_PUBLICATION = "New publication";
    @Autowired
    private JavaMailSender mailSender;


    @Override
    @Async
    public void sendToUsers(User user) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject(NEW_PUBLICATION);
            email.setText("New publication for you group");
            mailSender.send(email);
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }

    }
}
