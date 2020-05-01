package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;/*
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import nz.net.ultraq.thymeleaf.LayoutDialect;*/


@SpringBootApplication
public class OsloBySykkelApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(OsloBySykkelApplication.class, args);
	}
	
	/*
	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.addDialect(new LayoutDialect());
	    return templateEngine;
	}
	*/
}


