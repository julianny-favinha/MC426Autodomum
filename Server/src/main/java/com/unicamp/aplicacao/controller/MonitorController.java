package com.unicamp.aplicacao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sabrina on 16/05/16.
 */
@RestController
public class MonitorController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "PONG";
    }

}
