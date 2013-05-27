package br.com.software.web;

import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String adionarTransacao(@Valid @ModelAttribute("transacao")Transacao transacao ,BindingResult result,@RequestParam(value = "cartao.id") Long idcartao) {
		if(result.hasFieldErrors()){
			return "grud/cadTransacao";
		}else{
			transacao.setCartao(cartaodao.getCartao(idcartao));
			Transacao t = transacao;
			transacaoDao.persistir(t);
			return "/index";
		}
	}
	 
	/* Inicio da parte da visualizacao */
	
	@RequestMapping(value = "/grud/show/*", method = RequestMethod.GET)
	public String showCad() {
		return "grud/showTransacoes";
	}

	@RequestMapping(value = "/grud/show/pagMax", method = RequestMethod.GET)
	public @ResponseBody int totalPag(){
		int size = 10;
		float numeroPaginas = (float) transacaoDao.getCount().intValue() / size;
		int maxPages = (int) (numeroPaginas + 1);
		return maxPages;
		
	}
	
	@RequestMapping(value = "/grud/show/lista/", method = RequestMethod.GET)
	public @ResponseBody List<Transacao> list(@RequestParam(defaultValue = "1", value = "page", required = false) String page, @RequestParam(defaultValue = "10", value = "numero", required = false) Integer size) {
		Long pageN = Long.valueOf(page)  ;
		int firstResult = (int) ((page == null) ? 0 : (pageN - 1) * size);
		List<Transacao> lista = transacaoDao.list(firstResult, size);
		return lista;
	} 

	/* Fim da parte da visualizacao */
	
	@RequestMapping(value = "/updateControl")
	public @ResponseBody String update(@RequestParam String id){
		Transacao transacao = transacaoDao.get(Long.valueOf(id));
		transacao.setCartao(null);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(transacao);
		} catch (Exception e) {
			e.printStackTrace();
			return "fuuuu";
		}
		
	}
}
