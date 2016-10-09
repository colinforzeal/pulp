package com.pulp.user.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;

import com.pulp.user.dto.PageForm;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by colinforzeal on 8.10.16.
 */
@Controller
public class PageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PagesRepository pagesRepository;
    @Autowired
    private SitesRepository sitesRepository;
/*!
  !  //TODO when we have some pages and wonna add one more "sites/{siteName}/create"!!!!!!
!

*/
    @Transactional
    @RequestMapping(value="/sites/{siteName}/pages/{pageName}/create",method = RequestMethod.POST)
    public String savePage(@PathVariable(value = "siteName") String siteName, @PathVariable(value = "pageName") String pageName, @RequestBody String data) {
        Site site = sitesRepository.findByName(siteName);

        Page page = pagesRepository.findBySiteAndName(site, pageName);
        page.setData(data);


        pagesRepository.save(page);

        return "redirect:/sites/" + siteName;
    }

    @RequestMapping(value="/sites/{siteName}/pages/{pageName}",method = RequestMethod.GET)
    public String showPages(@PathVariable(value = "siteName") String siteName, @PathVariable(value="pageName") String pageName,Model model,Principal principal) {
        Site site = sitesRepository.findByName(siteName);
        Page page = pagesRepository.findBySiteAndName(site,pageName);

        User currentUser = userRepository.findByEmail(principal.getName());
        if (currentUser != null){
            model.addAttribute("isPrincipal",true);
            model.addAttribute("path","/sites/"+siteName+"/pages/"+pageName+"/edit");
        }

        model.addAttribute("page",page);

        return "/pages/index.html";
    }

    @RequestMapping(value="/sites/{siteName}/pages/{pageName}/create",method = RequestMethod.GET)
    public String createPage(@PathVariable(value = "siteName") String siteName, @PathVariable(value = "pageName") String pageName, Model model)
    {
        model.addAttribute("pageName", pageName);
        return "/pages/create.html";
    }

    @RequestMapping(value="/sites/{siteName}/pages/{pageName}/edit",method = RequestMethod.GET)
    public String editPage(@PathVariable(value = "siteName") String siteName, @PathVariable(value = "pageName") String pageName, Model model)
    {
        PageForm pageForm = new PageForm();
        pageForm.setPageName(pageName);
        model.addAttribute("page", pageForm);
        model.addAttribute("path","/sites/"+siteName+"/pages/"+pageName+"/edit");
        model.addAttribute("pageName",pageName);
        return "/pages/edit_page_name.html";
    }

    @Transactional
    @RequestMapping(value = "/sites/{siteName}/pages/{pageName}/edit",method = RequestMethod.POST)
    public String createPageName(@PathVariable(value = "siteName") String siteName, @PathVariable(value = "pageName") String pageName,@Valid @ModelAttribute("page") PageForm pageForm, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "redirect:/sites/"+siteName+"/pages/"+pageName+"/edit";
        }


        Site site = sitesRepository.findByName(siteName);
        if(site==null)return "redirect:/sites/create";

        if(pageNameExists(pageForm.getPageName(),site))
        {
            addFieldError("site","pageName",pageName,"Page with that name already exists",result);
            return "pages/edit_page_name.html";
        }

        Page page = pagesRepository.findBySiteAndName(site,pageName);
        page.setName(pageForm.getPageName());
        pagesRepository.save(page);

        return "redirect:/sites/"+site.getName();
    }

    private boolean pageNameExists(String name,Site site) {
        Page page = pagesRepository.findBySiteAndName(site,name);
        return page != null;


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
