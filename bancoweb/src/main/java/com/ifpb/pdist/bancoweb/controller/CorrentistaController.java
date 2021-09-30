package com.ifpb.pdist.bancoweb.controller;

import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.service.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @RequestMapping("/insert")
    public ModelAndView insertBD(ModelAndView modelAndView) {
        correntistaService.insertBD();
        modelAndView.setViewName("correntistas/list");
        modelAndView.addObject("correntistas", correntistaService.findAll());
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView listar(ModelAndView modelAndView) {
        modelAndView.setViewName("correntistas/list");
        modelAndView.addObject("correntistas", correntistaService.findAll());
        return modelAndView;
    }



}