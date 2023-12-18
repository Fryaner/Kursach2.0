package edu.festu.ivankuznetsov.securitydemo.service;

import edu.festu.ivankuznetsov.securitydemo.entity.Account;
import edu.festu.ivankuznetsov.securitydemo.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {


    private final AccountRepository accountRepository;

    public Iterable<Account> all() {
        return accountRepository.findAll();
    }

    public Optional<Account> get(Long id) {
        return accountRepository.findById(id);
    }

    public Account add(Account user) {

        return accountRepository.save(user);
    }

    public Account edit(Long id, Account user) {
        // Добавим ID к пользователю перед сохранением
        user.setId(id);
        return accountRepository.save(user);
    }

    public void remove(Long id) {
        accountRepository.deleteById(id);
    }

    AccountService(AccountRepository repository){
        accountRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = accountRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User does not exist");}
        else
            return user.get();
    }
}
