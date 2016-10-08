package com.pulp.user.controller;

import com.pulp.user.model.Site;
import com.pulp.user.model.User;
import com.pulp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 08.10.2016.
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @RequestMapping(value="/users/{id}",method = RequestMethod.GET)
    public String showPersonalPage(@PathVariable(value="id") Long id,Principal principal, Model model) {
        User user = userRepository.findById(id);
        System.out.println(user.getFirstName());
        if(principal == null || !principal.getName().equals(user.getEmail())) {
            return "redirect:/";
        }
        model.addAttribute("user",user);
        if(user.getSites()!=null){
            List<Site> sites=new ArrayList<>();
            sites.addAll(user.getSites());
            model.addAttribute("sites",sites);
            System.out.println(showSite(user.getSites()));
        }



        return "users/personal.html";
    }

    private String showSite(Set<Site> sites){
        String result="";
        for (Site site :sites) {
            result+="Site name : "+ site.getName()+ "\n";
        }
            return result;
    }
}

