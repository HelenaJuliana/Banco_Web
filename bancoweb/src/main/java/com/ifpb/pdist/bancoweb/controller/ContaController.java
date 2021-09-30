package com.ifpb.pdist.bancoweb.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifpb.pdist.bancoweb.exception.BancoException;
import com.ifpb.pdist.bancoweb.model.Conta;
import com.ifpb.pdist.bancoweb.model.ContaForm;
import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.model.Transacao;
import com.ifpb.pdist.bancoweb.service.ContaService;
import com.ifpb.pdist.bancoweb.service.CorrentistaService;

@Controller
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@Autowired
	private CorrentistaService correntistaService;

	@RequestMapping("/form")
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.setViewName("contas/form");
		modelAndView.addObject("contaForm", new ContaForm());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView adicioneConta(ContaForm contaForm, ModelAndView modelAndView, RedirectAttributes attr) {
		Correntista correntista = correntistaService.findById(contaForm.getCorrentistaId());
		contaForm.getConta().setCorrentista(correntista);
		contaService.saveConta(contaForm.getConta());
		modelAndView.setViewName("redirect:/contas");
		attr.addFlashAttribute("mensagem", "Conta salva com sucesso!");
		return modelAndView;
	}

	@ModelAttribute("correntistaItems")
	public List<Correntista> getCorrentistas() {
		return correntistaService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listeContas(ModelAndView modelAndView) {
		modelAndView.setViewName("/contas/list");
		try {
			List<Conta> contas = contaService.findAll();
			modelAndView.addObject("contas", contas);
		} catch (BancoException e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping("/{id}")
	public String busquePorId(@PathVariable("id") Integer id, Model model, RedirectAttributes attr) {
		Conta conta = contaService.findById(id);
		if (conta != null) {
			ContaForm contaForm = new ContaForm(conta, conta.getCorrentista().getId());
			model.addAttribute("contaForm", contaForm);
		} else {
			attr.addFlashAttribute("mensagem", "Conta não encontrada!");
			model.addAttribute("contaForm", new ContaForm());
		}
		return "contas/form";
	}

	@RequestMapping(value = "/{id}/delete")
	public ModelAndView deletePorId(@PathVariable("id") Integer id, ModelAndView modelAndView,
			RedirectAttributes attr) {
		contaService.deleteById(id);
		modelAndView.setViewName("redirect:/contas/list");
		attr.addFlashAttribute("mensagem", "Conta e transações excluídas!");
		return modelAndView;
	}

	@RequestMapping("/{id}/transacoes")
	public String addTransacaoConta(@PathVariable("id") Integer idConta, Model model) {
		Conta conta = contaService.findByIdWithTransacoes(idConta);
		model.addAttribute("conta", conta);
		return "contas/transacoes";
	}

	@RequestMapping("/operacao")
	public ModelAndView operacaoConta(String nuConta, HttpSession session, Transacao transacao, ModelAndView model) {
		String proxPagina = "";
		Correntista correntista = (Correntista) session.getAttribute("usuario");
		if (!correntista.isAdmin()) {
			nuConta = contaService.findByCorrentista(correntista).getNumero();
		}
		if (nuConta == null && correntista.isAdmin()) {
			model.addObject("transacao", new Transacao());
			proxPagina = "contas/operacao";
		} else {
			if (nuConta != null && transacao.getValor() == null) {
				Conta conta = contaService.findByNumeroWithTransacoes(nuConta);
				if (conta != null) {
					model.addObject("conta", conta);
					proxPagina = "contas/operacao";
				} else {
					model.addObject("mensagem", "Conta inexistente!");
					proxPagina = "contas/operacao";
				}
			} else {
				Conta conta = contaService.findByNumeroWithTransacoes(nuConta);
				contaService.addTransacao(conta, transacao);
				session.setAttribute("conta", conta);
				proxPagina = "redirect:/contas/"+conta.getId()+"/transacoes";
			}
		}
		model.setViewName(proxPagina);
		return model;
	}

}
