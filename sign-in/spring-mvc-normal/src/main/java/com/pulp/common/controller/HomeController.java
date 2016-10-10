package com.pulp.common.controller;

import com.google.appengine.repackaged.com.google.gson.annotations.Since;
import com.pulp.user.model.Site;
import com.pulp.user.repository.SitesRepository;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private SitesRepository sitesRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private static final String VIEW_NAME_HOMEPAGE = "/users/index.html";

    @Transactional
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String showHomePage(Principal principal,Model model) {
        List<Site> list = new ArrayList<>();
        final PersistentDateTime dateTime = new PersistentDateTime();
        list.addAll(sitesRepository.findAll());

        list.sort(new Comparator<Site>() {
            @Override
            public int compare(Site site, Site t1) {
                return dateTime.compare(t1.getModificationTime(),site.getModificationTime());
            }
        });

        model.addAttribute("sites",list);
        LOGGER.debug("Rendering homepage.");
        return VIEW_NAME_HOMEPAGE;
    }


}
