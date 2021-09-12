package mt.spacewebapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login(Model model){
        Boolean loggedIn = isLoggedIn();
        model.addAttribute("isLoggedIn", loggedIn);
        return "login";
    }

    public boolean isLoggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean loggedIn = true;
        if (authentication.getPrincipal().equals("anonymousUser")){
            loggedIn = false;
        }
        return loggedIn;
    }


}











