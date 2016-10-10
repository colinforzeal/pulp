package com.pulp.user.controller;

import com.pulp.user.model.Role;
import com.pulp.user.model.User;
import com.pulp.user.repository.SitesRepository;
import com.pulp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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
        List<User> userList = userRepository.findByRole(Role.ROLE_USER);
        model.addAttribute("userList",userList);

        return "/admin/admin.html";
    }
    @RequestMapping(value = "admin/delete/{id}")
    public String deleteUser(@PathVariable("id")Long id){
        userRepository.delete(id);
        return "redirect: /admin";
    }
//    @RequestMapping(value = "admin/delete/{site_name}")
//    public String deleteSite(@PathVariable("site_name") String site_name){
//        sitesRepository.deleteByName(site_name);
//        return "redirect: /admin";
//    }
//    @RequestMapping(value = "admin/delete/{site_name}/{page_name}")
//    public String deletePage(@PathVariable("site_name") String site_name,@PathVariable("page_name") String page){
//        sitesRepository.deleteByName(site_name);
//        return "redirect: /admin";
//    }

}
