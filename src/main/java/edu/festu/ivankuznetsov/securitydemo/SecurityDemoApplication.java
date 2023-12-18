package edu.festu.ivankuznetsov.securitydemo;

import edu.festu.ivankuznetsov.securitydemo.entity.Account;
import edu.festu.ivankuznetsov.securitydemo.entity.Role;
import edu.festu.ivankuznetsov.securitydemo.repository.AccountRepository;
import edu.festu.ivankuznetsov.securitydemo.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SecurityDemoApplication {
    private static AccountRepository userRepository = null;
    private static RoleRepository repository = null;

    public SecurityDemoApplication(AccountRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }
}
