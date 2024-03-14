package com.abc.lab.abclabsbe.Services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.abc.lab.abclabsbe.utils.MailHelper;

@Service
public class MailService {
  
  public MailService(JavaMailSender mailSender) {
    MailHelper.init(mailSender);
  }
  
  public void sendMail(String to, String subject, String body) {
    MailHelper.instance.sendMail(to, subject, body);
  }
}
