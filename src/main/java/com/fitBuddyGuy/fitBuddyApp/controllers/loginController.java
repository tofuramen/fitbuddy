package com.fitBuddyGuy.fitBuddyApp.controllers;


import com.fitBuddyGuy.fitBuddyApp.model.User;
import com.fitBuddyGuy.fitBuddyApp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class loginController {


    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    private HttpServletRequest request;
    private HttpServletResponse response;

    PasswordEncoder bcrypt = new BCryptPasswordEncoder();

    UserRepository userRepository;




    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @PostMapping("/login_success")
    public String processLogin(@RequestParam String username, @RequestParam String password,
    HttpServletRequest request, HttpServletResponse response) {


        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER");
        User user = new User();
        user.setusername(username);
        userRepository.findByUsername(username);
        user.setPassword(password);


        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.authenticated(
                username, password, authorities);


        SecurityContext securityContext = this.securityContextHolderStrategy.createEmptyContext();
        securityContext.setAuthentication(token);
        this.securityContextHolderStrategy.setContext(securityContext);
        this.securityContextRepository.saveContext(securityContext, request, response);

        return "redirect:/login_success";
    }

}
