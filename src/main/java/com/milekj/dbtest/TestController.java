package com.milekj.dbtest;

import com.milekj.dbtest.entity.Authority;
import com.milekj.dbtest.entity.DbUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class TestController {
    @GetMapping("/pizza")
    public String home(Model model, Authentication authentication) {
        DbUserDetails details = (DbUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("email", details.getEmail());
        model.addAttribute("roles", details.getAuthorities());

        return "index";
    }
}
