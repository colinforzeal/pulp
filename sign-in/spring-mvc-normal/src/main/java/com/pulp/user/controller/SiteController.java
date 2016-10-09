package com.pulp.user.controller;

import com.pulp.user.dto.PageForm;
import com.pulp.user.dto.RegistrationForm;
import com.pulp.user.dto.SiteEditForm;
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
    public SiteController(){}


    @RequestMapping(value = "/sites/create",method = RequestMethod.GET)
    public String siteForm(Model model, Principal principal){
        if(principal==null)
        {return "redirect:/login";}
        SiteForm siteform = new SiteForm();
        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, siteform);
        return "sites/create.html";
    }
    @Transactional
    @RequestMapping(value = "/sites/create",method = RequestMethod.POST)
    public String addSite(@Valid @ModelAttribute("site")SiteForm siteForm, BindingResult result, Principal principal){

        if(result.hasErrors()){
            return "redirect:/sites/create";
        }
        String name = siteForm.getName();
        String pageName = siteForm.getPageName();

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

        if(pageNameExists(pageName,site))
        {
            addFieldError("site","pageName",pageName,"Page with that name already exists",result);
            return "sites/create.html";
        }
        Page page = new Page(pageName,site);
        pagesRepository.save(page);

        return "redirect:/sites/"+name+"/pages/"+pageName+"/create";
    }

    @RequestMapping(value = "/sites/{site_name}",method = RequestMethod.GET)
    public String showSite(@PathVariable(value="site_name") String siteName, Principal principal){

        Site site = sitesRepository.findOneByName(siteName);
        if(site != null)
        {
            List<Page> pages = pagesRepository.findBySite(site);

            if (pages == null || pages.isEmpty()){
                return "redirect:/sites/" + siteName + "/create";
            }
            else{
                return "redirect:/sites/" + siteName + "/pages/" + pages.get(0).getName();
            }
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/sites/{site_name}/create", method = RequestMethod.GET)
    public String showPageForm(@PathVariable(value = "site_name") String siteName,Principal principal,Model model){
        if(principal==null)
        {return "redirect:/login";}
        model.addAttribute("path","/sites/"+siteName+"/create");
        PageForm pageForm = new PageForm();
        model.addAttribute("page", pageForm);
        return "pages/create_page_name.html";
    }


    @Transactional
    @RequestMapping(value = "/sites/{site_name}/create",method = RequestMethod.POST)
    public String createPageName(@PathVariable(value = "site_name") String siteName,@Valid @ModelAttribute("page") PageForm pageForm, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "redirect:/sites/"+siteName+"/create";
        }

        String pageName = pageForm.getPageName();

        Site site = sitesRepository.findByName(siteName);
        if(site==null)return "redirect:/sites/create";

        if(pageNameExists(pageName,site))
        {
            addFieldError("site","pageName",pageName,"Page with that name already exists",result);
            return "pages/create_page_name.html";
        }

        Page page = new Page(pageName,site);
        pagesRepository.save(page);

        return "redirect:/sites/"+site.getName()+"/pages/"+pageName+"/create";
    }
    @RequestMapping(value = "/sites/{site_name}/edit", method = RequestMethod.GET)
    public String showEditSiteForm(@PathVariable(value = "site_name") String siteName,Principal principal,Model model){
        Site site = sitesRepository.findByName(siteName);
        if(site==null)
            return "redirect:/sites/create";

        if(!(site.getUser().getId().equals(userRepository.findByEmail(principal.getName()).getId())))
        {
            return "redirect:/";
        }
        SiteEditForm siteForm = new SiteEditForm();
        siteForm.setName(siteName);
        model.addAttribute("site", siteForm);
        model.addAttribute("path","/sites/"+siteName+"/edit");
        return "sites/edit_site_name.html";

    }
    @Transactional
    @RequestMapping(value = "/sites/{site_name}/edit",method = RequestMethod.POST)
    public String editSiteName(@PathVariable(value = "site_name") String siteName,@Valid @ModelAttribute("site") SiteEditForm siteForm, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "redirect:/sites/"+siteName+"/edit";
        }


        Site site = sitesRepository.findByName(siteName);

        if(site==null)return "redirect:/sites/create";

        String editedSiteName = siteForm.getName();
        if(siteNameExists(editedSiteName))
        {
            addFieldError("site","name",siteName,"Site with that name already exists",result);
            return "sites/edit_site_name.html";
        }
        site.setName(editedSiteName);
        sitesRepository.save(site);

        return "redirect:/sites/"+site.getName();
    }

    private boolean siteNameExists(String name) {

        Site site = sitesRepository.findOneByName(name);


        return site != null;


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
