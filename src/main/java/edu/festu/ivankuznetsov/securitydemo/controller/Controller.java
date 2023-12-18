package edu.festu.ivankuznetsov.securitydemo.controller;

import edu.festu.ivankuznetsov.securitydemo.config.JwtService;
import edu.festu.ivankuznetsov.securitydemo.entity.Account;
import edu.festu.ivankuznetsov.securitydemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    private String userName = "Vlad";

    // Конструктор, инициализирующий userService
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/product")
    public ModelAndView product(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product");
        return mv;
    }
    @RequestMapping("/whome")
    public ModelAndView whome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("whome");
        return mv;
    }
    @RequestMapping("/contact")
    public ModelAndView contact(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contact");
        return mv;
    }
    @RequestMapping("/stock")
    public ModelAndView stock(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("stock");
        return mv;
    }
    @RequestMapping("/catalog")
    public ModelAndView catalog(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("catalog");
        return mv;
    }
    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        System.out.println(jwtService.generateToken(userName));
        return mv;
    }
    /*@PostMapping("/add")
public String authenticateAndGetToken() {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
    );
    if (authentication.isAuthenticated()) {
        return jwtService.generateToken(username);
    } else {
        throw new UsernameNotFoundException("Invalid user request!");
    }
}
*/
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/lc")
    public ModelAndView lc(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lc");
        return mv;
    }
    @GetMapping("/users/{targetID}")
    public Optional<Account> getTargetUser(@PathVariable Long targetID) {
        return accountService.get(targetID);
    }
    @GetMapping("/users")
    public Iterable<Account> getTargetUser() {
        return accountService.all();
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUser(
            @RequestParam String username,
            @RequestParam String password) {
        Account user = accountService.add(new Account(username, new BCryptPasswordEncoder().encode(password)));
        return ResponseEntity.ok("Пользователь с логином: " + user.getUsername() + " добавлен");
    }
    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestParam Long id) {
        accountService.remove(id);
        return true;
    }

    @PatchMapping("/edit")
    public boolean editUser(
            @RequestParam Long id,
            @RequestParam String username,
            @RequestParam String password) {
        Account account = new Account(username, new BCryptPasswordEncoder().encode(password));
        accountService.edit(id, account);
        return true;
    }
}