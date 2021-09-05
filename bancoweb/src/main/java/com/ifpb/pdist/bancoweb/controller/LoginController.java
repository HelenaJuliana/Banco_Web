package com.ifpb.pdist.bancoweb.controller;

import javax.servlet.http.HttpSession;

import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new Correntista());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView valide(Correntista correntista, HttpSession session, ModelAndView modelAndView, RedirectAttributes redirectAttts) {
        if ((correntista = loginService.isValido(correntista)) != null) {
            session.setAttribute("usuario", correntista);
            modelAndView.setViewName("redirect:/home");
        } else {
            redirectAttts.addFlashAttribute("mensagem", "Login e/ou senha inválidos!");
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping("/out")
    public ModelAndView logout(ModelAndView mav, HttpSession session) {
        session.invalidate();
        mav.setViewName("redirect:/login");
        return mav;
    }

}
