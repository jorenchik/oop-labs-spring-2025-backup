package jtm.activity18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class JettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JettyApplication.class, args);
    }
}
