package net.petrikainulainen.spring.social.signinmvc.user.controller;

import net.petrikainulainen.spring.social.signinmvc.user.dto.SiteForm;
import net.petrikainulainen.spring.social.signinmvc.user.model.Site;
import net.petrikainulainen.spring.social.signinmvc.user.model.User;
import net.petrikainulainen.spring.social.signinmvc.user.repository.SitesRepository;
import net.petrikainulainen.spring.social.signinmvc.user.repository.UserRepository;
import net.petrikainulainen.spring.social.signinmvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@SessionAttributes("site")
public class SiteController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SitesRepository sitesRepository;
    protected static final String MODEL_NAME_REGISTRATION_DTO = "site";
    public SiteController(){};


    @RequestMapping(value = "/add_site",method = RequestMethod.GET)
    public String siteForm(Model model ){
        SiteForm siteform = new SiteForm();
        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, siteform);
        return "site.html";
    }
    @Transactional
    @RequestMapping(value = "/add_site",method = RequestMethod.POST)
    public String addSite(@Valid @ModelAttribute("site")SiteForm siteForm, BindingResult result, WebRequest request){

        User user =userRepository.findByEmail(request.getUserPrincipal().getName());

        Site site = new Site(siteForm.getName(),user);

        sitesRepository.save(site);
        Set set1 = new HashSet<Site>();
           set1.add(site);
        user.setSites(set1);
        userRepository.save(user);




        return "redirect:/";
    }



}
