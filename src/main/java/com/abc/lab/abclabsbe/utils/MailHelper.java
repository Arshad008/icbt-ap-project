package com.abc.lab.abclabsbe.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailHelper {
  private final JavaMailSender mailSender;
  public static MailHelper instance;

  public MailHelper(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public static void init(JavaMailSender mailSender) {
    instance = new MailHelper(mailSender);
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
