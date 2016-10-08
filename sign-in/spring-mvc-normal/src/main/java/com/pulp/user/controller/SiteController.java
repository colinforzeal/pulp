package com.pulp.user.controller;

import com.pulp.user.dto.RegistrationForm;
import com.pulp.user.dto.SiteForm;
import com.pulp.user.model.Page;
import com.pulp.user.model.Site;
import com.pulp.user.model.User;
import com.pulp.user.repository.PagesRepository;
import com.pulp.user.repository.SitesRepository;
import com.pulp.user.repository.UserRepository;
import com.pulp.user.service.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


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


    @RequestMapping(value = "/users/create",method = RequestMethod.GET)
    public String siteForm(Model model, Principal principal){
        if(principal==null)
        {return "redirect:/login";}
        SiteForm siteform = new SiteForm();
        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, siteform);
        return "sites/create.html";
    }
    @Transactional
    @RequestMapping(value = "/users/create",method = RequestMethod.POST)
    public String addSite(@Valid @ModelAttribute("site")SiteForm siteForm, BindingResult result, WebRequest request, Principal principal){

        if(result.hasErrors()){
            return "redirect:/users/create";
        }
        String name = siteForm.getName();

        Site registered = null;
        if(siteNameExists(name))
        {
            addFieldError("site","name",name,"Site with that name already exists",result);
            return "sites/create.html";
        }



        System.out.println(siteForm.getName());
        User user =userRepository.findByEmail(principal.getName());



            Site site = new Site(name, user);
            sitesRepository.save(site);
//            ArrayList set1 = new ArrayList<Site>();
//            set1.add(site);
//            user.setSites(set1);
//            userRepository.save(user);




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
       {List<Page> pages =pagesRepository.findBySite(site); System.out.println(pages);}
        else System.out.println("NULL");

        return "pages/index.html";
//        else
//            return "sites/"+siteName+pagesRepository.findBySiteOrderByPageidDesc(site.getId()).get(0).getName()+".html";
    }


    private boolean siteNameExists(String name) {

        Site site = sitesRepository.findOneByName(name);


        return site != null;


    }
    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );

        result.addError(error);
    }



}
