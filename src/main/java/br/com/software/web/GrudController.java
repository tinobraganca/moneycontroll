package br.com.software.web;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.software.dao.*;
import br.com.software.modelos.Cartao;
import br.com.software.modelos.Transacao;

@Controller
public class GrudController {
	@Autowired
	private TransacaoDao transacaoDao;
	
	public TransacaoDao getCartaoDao() {
		return transacaoDao;
	}

	public void setTransacao(TransacaoDao dao1) {
		transacaoDao = dao1;
	}
	
	@Autowired
	private CartaoDao cartaodao;

	public CartaoDao getUsuarioDao() {
		return cartaodao;
	}

	public void setUsuarioDao(CartaoDao dao) {
		cartaodao = dao;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String habilitaIndex(){
	    return "index";
	}
	
	@RequestMapping(value = "/grud/cad", method = RequestMethod.GET)
	public String habilitaCCartao(ModelMap modelMap) {
		modelMap.addAttribute("cadCartao", new Cartao());
		return "grud/cadCartao";
	}
	
	@RequestMapping(value = "/grud/cadCartao",method = RequestMethod.POST)
	public String create (@ModelAttribute ("cadCartao") Cartao cartao){
		cartaodao.persistir(cartao);
		return "/index";
	}

	@RequestMapping(value = "/grud/transacao", method = RequestMethod.GET)
	public String habilitaCTransacao (ModelMap modelmap ){
		modelmap.addAttribute("Transacao", new Transacao());
		return "/grud/cadTransacao";
	}
	@RequestMapping(value = "/grud/transacao", method = RequestMethod.POST)
	public String AdionarTransacao(@ModelAttribute("Transacao") Transacao transacao){
		transacaoDao.persistir(transacao);
		return "/index";
		
	}
}
