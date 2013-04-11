package br.com.software.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.software.dao.CartaoDao;
import br.com.software.dao.TransacaoDao;
import br.com.software.modelos.Cartao;
import br.com.software.modelos.Transacao;

@Controller
public class GrudController {
	@Autowired
	private TransacaoDao transacaoDao;

	public TransacaoDao getCartaoDao() {
		return transacaoDao;
	}

	public void setTransacao(TransacaoDao dao) {
		transacaoDao = dao;
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
	public String habilitaIndex() {
		return "index";

	}

	@RequestMapping(value = "/grud/cad", method = RequestMethod.GET)
	public String habilitaCCartao(ModelMap modelMap) {
		modelMap.addAttribute("cadCartao", new Cartao());
		return "grud/cadCartao";
	}

	@RequestMapping(value = "/grud/cadCartao", method = RequestMethod.POST)
	public String create(@ModelAttribute("cadCartao") Cartao cartao) {
		cartaodao.persistir(cartao);
		return "/index";
	}

	@RequestMapping(value = "/grud/transacao", method = RequestMethod.GET)
	public ModelAndView habilitaCTransacao(ModelMap modelmap) {
		modelmap.addAttribute("transacao", new Transacao());
		ModelAndView mav = new ModelAndView();
		List<Cartao> cartoes = cartaodao.lista(0, 10);
		mav.getModel().put("cartoes", cartoes);
		mav.setViewName("grud/cadTransacao");
		return mav;

	}

	@RequestMapping(value = "/grud/transacao/", method = RequestMethod.POST)
	public String adionarTransacao(@ModelAttribute("transacao") Transacao transacao,@RequestParam(value = "cartao.id") Long idcartao ) {
		transacao.setCartao(cartaodao.getCartao(idcartao));
		Transacao t = transacao;
		transacaoDao.persistir(t);
		return "/index";
	}
	@RequestMapping(value = "/grud/show/")
	public ModelAndView showTransacoes(){
		ModelAndView mav = new ModelAndView();
		List<Transacao> showTransacao = transacaoDao.list(0, 10);
		mav.getModel().put("transacoes",showTransacao);
		mav.setViewName("grud/showTransacoes");
		return mav;

	}
}
