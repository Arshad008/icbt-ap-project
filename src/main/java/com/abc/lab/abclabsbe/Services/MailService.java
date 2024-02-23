package com.abc.lab.abclabsbe.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
  @Autowired
  private final JavaMailSender mailSender;


  public MailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendMail(String to, String subject, String body) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();

      message.setTo(to);
      message.setFrom("arshadrauff008@gmail.com");
      message.setText(body);
      message.setSubject(subject);

      mailSender.send(message);
    } catch (Exception e) {
      System.out.println("e" + e);
      throw new Error("Error while Sending Mail");
    }
  }
}
