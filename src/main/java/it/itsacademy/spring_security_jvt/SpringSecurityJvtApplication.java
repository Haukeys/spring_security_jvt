package it.itsacademy.spring_security_jvt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class SpringSecurityJvtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJvtApplication.class, args);
    }

}
