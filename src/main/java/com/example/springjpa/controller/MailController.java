package com.example.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springjpa.model.Mail;
import com.example.springjpa.service.MailService;

@Controller
public class MailController {

	@Autowired
	MailService mailService;
	@GetMapping("/mail/{mailAddr}")
	@ResponseBody
	public String sendMail(@PathVariable String mailAddr){
		Mail mail = new Mail();
		mail.setMailFrom("lidytalk@gmail.com");
		mail.setMailTo(mailAddr);
		mail.setMailSubject("Spring Boot - Email demo");
		mail.setMailContent("테스트 메일입니다.");
		mailService.sendEmail(mail);
		return "sended";
	}
}
