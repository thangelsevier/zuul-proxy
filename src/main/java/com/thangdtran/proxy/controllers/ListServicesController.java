package com.thangdtran.proxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListServicesController {
    @GetMapping(path = "/services")
    public String listServices() {
        return "services";
    }
}
