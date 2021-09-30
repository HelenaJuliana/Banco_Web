package com.ifpb.pdist.bancoweb.producers;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifpb.pdist.bancoweb.model.dtos.EmailDto;

@Component
public class CorrentistaProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	public void send(EmailDto emailDto) {
		rabbitTemplate.convertAndSend(this.queue.getName(), emailDto);
	}

}