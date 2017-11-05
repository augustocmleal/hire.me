package com.bemobi.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages = "com.bemobi.shortener")
@ImportResource("classpath:applicationcontext.xml")
public class App 
{
    public static void main( String[] args )throws InterruptedException {
    	SpringApplication.run(App.class, args);
    	
    }
}
