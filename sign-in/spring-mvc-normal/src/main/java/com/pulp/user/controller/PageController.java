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

    @Transactional
    @RequestMapping(value="/sites/{siteName}/pages/create",method = RequestMethod.POST)
    public String savePage(@PathVariable(value = "siteName") String siteName, @RequestBody String data, @Valid @ModelAttribute("form") PageForm pageForm, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/sites/" + siteName+ "/pages/create";
        }
        String name = pageForm.getName();

        Site site = sitesRepository.findByName(siteName);
        if(pageNameExists(site,name))
        {
            addFieldError("form","name",name,"Page with that name already exists",result);
            return "pages/create.html";
        }

        Page page = new Page();
        page.setName(name);
        page.setSite(site);
        page.setData(data);
        page.setMainPage(true);
        pagesRepository.save(page);

        return "redirect:/sites/" + siteName;
    }

    @RequestMapping(value="/sites/{siteName}/pages/{pageName}",method = RequestMethod.GET)
    public String showPages(@PathVariable(value = "siteName") String siteName, @PathVariable(value="pageName") String pageName,Model model) {
        Page page = pagesRepository.findByName(pageName);
        model.addAttribute("page",page);

        return "/pages/index.html";
    }

    @RequestMapping(value="/sites/{siteName}/pages/create",method = RequestMethod.GET)
    public String createPage(@PathVariable(value = "siteName") String siteName, Model model)
    {
        SiteForm siteform = new SiteForm();
        model.addAttribute("form", siteform);
        model.addAttribute("siteName", siteName);
        return "/pages/create.html";
    }

    private boolean pageNameExists(Site site, String name) {
        Page page = pagesRepository.findBySiteAndName(site, name);

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
