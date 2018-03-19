package ru.randscheduler.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dimaMJ on 30.01.2018
 */
@Controller
@RequestMapping(value = {"/", "/list"})
public class SchedulerListCtrl {

    @GetMapping
    public String getPage() {
        return "/list";
    }
}
