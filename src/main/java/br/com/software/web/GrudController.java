package br.com.software.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.software.dao.Cartao;
import br.com.software.dao.CartaoDao;

@Controller
public class GrudController {
	
	@Autowired
	private CartaoDao cartaodao;

	public CartaoDao getUsuarioDao() {
		return cartaodao;
	}

	public void setUsuarioDao(CartaoDao dao) {
		cartaodao = dao;
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	@RequestMapping(value = "/grud/cad", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		modelMap.addAttribute("cadCartao", new Cartao());
		return "grud/cadCartao";
	}
	
	@RequestMapping(value = "/grud/cadCartao")
	public String create (@ModelAttribute ("cadCartao") Cartao cartao){
		System.out.println("kkk7");
		cartaodao.persistir(cartao);
		return "/index";
	}
}
