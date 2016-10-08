package com.pulp.user.controller;

import com.pulp.user.model.User;
import com.pulp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String renderAdminPage(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute(userList);
        return "/users/admin.html";
    }
    @RequestMapping(value = "delete/{id}")
    public String remove(@PathVariable("id")Long id){
        userRepository.delete(id);
        return "redirect: /";
    }

}
