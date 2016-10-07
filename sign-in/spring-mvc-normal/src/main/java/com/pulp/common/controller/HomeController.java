package com.pulp.common.controller;

import com.pulp.user.model.User;
import com.pulp.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final String VIEW_NAME_HOMEPAGE = "index.html";

    @Transactional
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String showHomePage(Principal principal,Model model) {

        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("sites",user.getSites().toString());
        System.out.println(principal.getName());









//
//        User user
//        System.out.println(user.getFirstName());
//        User user= userRepository.findByEmail("pashakozik3123736@gmail.com");
//        Set set = new HashSet<Page>();
//        Site site = new Site("my shite");
//        set.add(new Page("lolsdsxcvf","lsdfosdfsdfsdf","sdfsdfsdfsdfsdf",site));
//        set.add(new Page("sdsdfxcvf","sdsdsdfsdfff","sdsddsfsdfsdff",site));
//        site.setPages(set);
//        sitesRepository.save(site);
//        Site newsite = new Site("another shit");
//        Set set1 = new HashSet<Site>();
//        set1.add(site);
//        set1.add(newsite);
//
//
//        user.setSites(set1);
//        userRepository.save(user);
//        System.out.println(user.getId());
//
//        System.out.println(user.showSites());

        LOGGER.debug("Rendering homepage.");
        return VIEW_NAME_HOMEPAGE;
    }
}
