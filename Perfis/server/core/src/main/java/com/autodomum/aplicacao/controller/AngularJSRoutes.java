package com.autodomum.aplicacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sabrina on 27/05/16.
 */
@Controller
public class AngularJSRoutes {

    @RequestMapping({
            "/",
            "/audio",
            "/clothes-line",
            "/garden",
            "/home",
            "/profile",
            "/profile/new",
            "/profiles"
    })
    public String index() {
        return "index.html";
    }

}
