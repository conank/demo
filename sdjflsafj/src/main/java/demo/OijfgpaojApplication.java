package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Configuration
@ComponentScan (basePackages = "model")
public class OijfgpaojApplication {

    public static void main(String[] args) {
        SpringApplication.run(OijfgpaojApplication.class, args);
    }
}
