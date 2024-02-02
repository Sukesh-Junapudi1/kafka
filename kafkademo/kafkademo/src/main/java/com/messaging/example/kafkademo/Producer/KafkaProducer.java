package com.messaging.example.kafkademo.Producer;

import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	@Value("${spring.kafka.topic.name}")
	private String topic;
	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String message) throws InterruptedException {
		int count=0;
		while (true) {
			int value =new Random().nextInt(9000) + 1000; 
			kafkaTemplate.send(topic, message+String.valueOf(value));
			Thread.sleep(5000);count++;
			if(count==30) {
				break;
			}
		}
	}
}
