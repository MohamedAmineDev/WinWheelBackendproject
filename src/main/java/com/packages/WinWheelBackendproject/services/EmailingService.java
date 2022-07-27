package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.interfaces.IEmailingManagement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class EmailingService implements IEmailingManagement {
    @Autowired
    private JavaMailSender javaMailSender;
    //private String sender = "testenvoi61@gmail.com";
    private String sender = "aminosamineos54@gmail.com";

    @Override
    public boolean sendAnEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(toEmail);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
