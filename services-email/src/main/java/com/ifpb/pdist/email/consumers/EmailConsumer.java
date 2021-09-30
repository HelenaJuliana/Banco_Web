package com.ifpb.pdist.email.consumers;

import com.ifpb.pdist.email.services.EmailService;
import com.ifpb.pdist.email.dtos.EmailDto;
import com.ifpb.pdist.email.models.EmailModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        emailDto.setOwnerRef("Helena");
        emailDto.setEmailFrom("helenabarros245@gmail.com");
        emailDto.setSubject("Banco Web");
        emailDto.setText("Bem vindo ao banco Web plataforma que faz transações rápidas e seguras! *-*");
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
    }
}
