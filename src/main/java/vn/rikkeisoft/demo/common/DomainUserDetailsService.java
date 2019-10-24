package vn.rikkeisoft.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.rikkeisoft.demo.model.entity.Account;
import vn.rikkeisoft.demo.model.entity.Role;
import vn.rikkeisoft.demo.repositories.AccountRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

//Lay thong tin User account tu Database len
@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    //Lay User tu Database bang username
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User" + username + "was not found in the database");
        }

        //Thiet lap Role cho Account
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = account.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        //return ra 1 implementation của UserDetails, co chua Username va Password va Quyen cua Account
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), grantedAuthorities);
    }
}
