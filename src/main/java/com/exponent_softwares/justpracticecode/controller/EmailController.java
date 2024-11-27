package com.exponent_softwares.justpracticecode.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping
public class EmailController {

    @Value("${spring.mail.username}")
    private String emailSender;
    private final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @GetMapping(path = "/sendSimpleEmail")
    public String sendEmail() {
        // Code to send email goes here
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailSender);
        simpleMailMessage.setTo(emailSender);
        simpleMailMessage.setSubject("Testing Email Sender Application");
        simpleMailMessage.setText("Hi There, I'm testing my springboot email sender application. I hope you dont mind. Thanks for your cooperation");
        simpleMailMessage.setCc("auspiciousmakawa03@gmail.com");
        simpleMailMessage.setCc("bed-com-36-18@unima.ac.mw");
        simpleMailMessage.setSentDate(new Date());
        javaMailSender.send(simpleMailMessage);
        System.out.println("simpleMailMessage = " + simpleMailMessage);
        return "Email sent successfully";
    }
    @RequestMapping(path = "/sendEmailWithAttachment")
    public String sendEmailWithAttachment() throws MessagingException {
        // Code to send email goes here
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(emailSender);
        mimeMessageHelper.setTo(emailSender);
        mimeMessageHelper.setSubject("Testing Email Sender Application with Attachment");
        mimeMessageHelper.setText("Hi There, I'm testing my springboot email sender application with attachment. I hope you dont mind. Thanks for your cooperation");
        mimeMessageHelper.setCc("auspiciousmakawa03@gmail.com");
        mimeMessageHelper.setBcc("bed-com-36-18@unima.ac.mw");
//        mimeMessageHelper.setReplyTo(emailSender);
        mimeMessageHelper.addAttachment("Picture : ", new File("D:\\.Geoffrey\\2527.jpg"));
        mimeMessageHelper.addAttachment("Presentation : ", new File("D:\\Geoffrey refresh folder\\P\\Presentation Structure.pptx"));

        javaMailSender.send(mimeMailMessage);
        System.out.println("simpleMailMessage\n " + mimeMailMessage);
        return "Email sent successfully";
    }

}
