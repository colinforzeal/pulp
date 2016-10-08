package com.pulp.user.controller;

import com.pulp.user.dto.SiteForm;
import com.pulp.user.model.Page;
import com.pulp.user.model.Site;
import com.pulp.user.model.User;
import com.pulp.user.repository.PagesRepository;
import com.pulp.user.repository.SitesRepository;
import com.pulp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Controller

public class SiteController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private SitesRepository sitesRepository;
    protected static final String MODEL_NAME_REGISTRATION_DTO = "site";
    public SiteController(){};


    @RequestMapping(value = "/users/add_site",method = RequestMethod.GET)
    public String siteForm(Model model, Principal principal){
        if(principal==null)
        {return "redirect:/login";}
        SiteForm siteform = new SiteForm();
        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, siteform);
        return "sites/create.html";
    }
    @Transactional
    @RequestMapping(value = "/users/add_site",method = RequestMethod.POST)
    public String addSite(@Valid @ModelAttribute("site")SiteForm siteForm, BindingResult result,Principal principal){


        System.out.println(siteForm.getName());
        User user =userRepository.findByEmail(principal.getName());

        String name = siteForm.getName();
        if(!emailExist(name)) {
            Site site = new Site(name, user);

            sitesRepository.save(site);
            Set set1 = new HashSet<Site>();
            set1.add(site);
            user.setSites(set1);
            userRepository.save(user);
        }
        else return "redirect:/users/"+user.getId();



        return "redirect:/users/"+user.getId();
    }
    @RequestMapping(value = "/users/sites/{site_name}",method = RequestMethod.GET)
    public String showSite(@PathVariable(value="site_name") String siteName,Model model, Principal principal){

        Site site = sitesRepository.findOneByName(siteName);
        if(site!=null)
        {
            User user = site.getUser();
            model.addAttribute(user);
            model.addAttribute(site);
        }
        if(site==null){
            return "redirect:/";
        }
        else if(site.getPages()==null){
            return "pages/create.html";
        }

       if (pagesRepository.findBySite(site)!=null)
       {ArrayList<Page> pages =pagesRepository.findBySite(site); System.out.println(pages);}
        else System.out.println("NULL");

        return "pages/index.html";
//        else
//            return "sites/"+siteName+pagesRepository.findBySiteOrderByPageidDesc(site.getId()).get(0).getName()+".html";
    }


    private boolean emailExist(String name) {

        Site site = sitesRepository.findOneByName(name);


        return site != null;


    }



}
