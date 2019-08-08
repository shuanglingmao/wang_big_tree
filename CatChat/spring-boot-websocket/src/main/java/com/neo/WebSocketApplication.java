package com.neo;

import com.neo.test.statemachine.Event;
import com.neo.test.statemachine.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@EnableWebSocket
@SpringBootApplication
@EnableAspectJAutoProxy
public class WebSocketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketApplication.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Autowired
	private StateMachine<Status, Event> stateMachine;


	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		stateMachine.sendEvent(Event.PICKUP_EVENT);
		stateMachine.sendEvent(Event.RETURN_EVENT);
	}

}
