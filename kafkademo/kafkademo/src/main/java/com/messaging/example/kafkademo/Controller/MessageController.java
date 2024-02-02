package com.messaging.example.kafkademo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.example.kafkademo.Producer.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

	private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

	@GetMapping("/get")
	public String sendMessage(@RequestParam("message")  String message) {
		System.out.println(message);
		return "Helow guys";
	}
	@GetMapping("/producer")
	public ResponseEntity<String> publish(@RequestParam("message") String message) throws InterruptedException{
		kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to the topic");
    }
}
