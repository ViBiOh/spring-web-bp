package org.vibioh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vibioh.model.Hello;
import org.vibioh.service.DateService;

@RestController
@RequestMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"*"}, allowedHeaders = {"Content-Type"}, methods = {RequestMethod.GET, RequestMethod.POST})
public class HelloWorldController {
    @Value("${app.name}")
    private String appName;

    private final DateService dateService;

    @Autowired
    public HelloWorldController(final DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping
    @ResponseBody
    public Hello get() {
        return new Hello(appName, dateService.now().toEpochMilli());
    }

    @GetMapping("{name}")
    @ResponseBody
    public Hello name(final @PathVariable String name) {
        return new Hello(name, dateService.now().toEpochMilli());
    }
}
