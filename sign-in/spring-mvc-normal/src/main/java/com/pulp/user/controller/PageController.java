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
    @RequestMapping(value="/sites/{siteName}/pages/{pageName}/create",method = RequestMethod.POST)
    public String savePage(@PathVariable(value = "siteName") String siteName, @PathVariable(value = "pageName") String pageName, @RequestBody String data, @Valid @ModelAttribute("form") PageForm pageForm, BindingResult result) {
        Site site = sitesRepository.findByName(siteName);

        Page page = pagesRepository.findBySiteAndName(site, pageName);
        page.setData(data);
        pagesRepository.save(page);

        return "redirect:/sites/" + siteName;
    }

    @RequestMapping(value="/sites/{siteName}/pages/{pageName}",method = RequestMethod.GET)
    public String showPages(@PathVariable(value = "siteName") String siteName, @PathVariable(value="pageName") String pageName,Model model) {
        Site site = sitesRepository.findByName(siteName);
        Page page = pagesRepository.findBySiteAndName(site,pageName);
        model.addAttribute("page",page);

        return "/pages/index.html";
    }

    @RequestMapping(value="/sites/{siteName}/pages/{pageName}/create",method = RequestMethod.GET)
    public String createPage(@PathVariable(value = "siteName") String siteName, @PathVariable(value = "pageName") String pageName, Model model)
    {
        model.addAttribute("siteName", siteName);
        model.addAttribute("pageName", pageName);
        return "/pages/create.html";
    }
}
