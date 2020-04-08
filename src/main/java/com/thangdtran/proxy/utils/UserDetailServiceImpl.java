package com.thangdtran.proxy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ArrayList<Map<String, String>> getMapAccount() {
        ArrayList<Map<String, String>> mapAccount = new ArrayList<>();

        Map<String, String> userAccount = new HashMap<>();
        userAccount.put("username", "foo");
        userAccount.put("password", passwordEncoder.encode("1234"));
        userAccount.put("role", "MEMBER");
        mapAccount.add(userAccount);

        Map<String, String> adminAccount = new HashMap<>();
        userAccount.put("username", "admin");
        userAccount.put("password", passwordEncoder.encode("admin"));
        userAccount.put("role", "ADMIN");
        mapAccount.add(userAccount);

        return mapAccount;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<Map<String, String>> mapAccount = getMapAccount();
        for (Map<String, String> account : mapAccount) {
            if (username.equals(account.get("username"))) {
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(account.get("role")));
                User user = new User(username, account.get("password"), grantedAuthorities);
                return user;
            }
        };

        throw new UsernameNotFoundException("User not found");
    }
}
