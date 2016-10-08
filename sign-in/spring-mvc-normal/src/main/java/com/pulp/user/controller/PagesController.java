package com.pulp.user.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by colinforzeal on 8.10.16.
 */
@Controller
public class PagesController {
    @Autowired
    private PagesRepository pagesRepository;
    @Autowired
    private SitesRepository sitesRepository;

    @Transactional
    @RequestMapping(value="/pages",method = RequestMethod.POST)
    public String savePage(@RequestBody String data) {
        Page page = new Page();
        page.setName("LOH");
        page.setSite(sitesRepository.findByName("suede"));
        page.setData(data);
        pagesRepository.save(page);

        return "redirect:/pages";
    }

    @RequestMapping(value="/pages/{pageName}",method = RequestMethod.GET)
    public String showPages(@PathVariable(value="pageName") String pageName,Model model) {
        Page page = pagesRepository.findByName(pageName);
       model.addAttribute("page",page);

        return "/pages/index.html";
    }

    @RequestMapping(value="/pages/create",method = RequestMethod.GET)
    public String createPage() {
        return "/pages/create.html";
    }
}
