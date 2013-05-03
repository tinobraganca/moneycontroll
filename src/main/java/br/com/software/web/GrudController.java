package br.com.software.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	private Object messageSource;

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
	public String adionarTransacao(@ModelAttribute("transacao")Transacao transacao ,BindingResult result,@RequestParam(value = "cartao.id") Long idcartao) {
		if(result.hasFieldErrors()){
//			result.reject("Failed to convert property" , "teste");
//			result.rejectValue("valor", "typeMismatch.valor","test");
//			result.reject("typeMismatch.transacao.valor,typeMismatch.valor,typeMismatch.java.math.BigDecimal,typeMismatch", "erro");
			return "grud/cadTransacao";
		}else{
			transacao.setCartao(cartaodao.getCartao(idcartao));
			Transacao t = transacao;
			transacaoDao.persistir(t);
			return "/index";
		}
	}
//	@RequestMapping(value = "pagina/{page}", method = RequestMethod.GET)  
//    public String posts(@PathVariable Integer page, Model model) {        
//        Criteria = new cr
//		Sort sort = new Sort();       
//        Pageable pageRequest = new PageRequest(page-1, 5 , sort);         
//        Page<Post> posts = postRepository.findAll(pageRequest);             
//        model.addAttribute("posts", posts.getContent());  
//        model.addAttribute("pagina", page);  
//          
//        Integer proximo = 0;  
//        Integer anterior = 0;  
//        if (posts.hasNextPage()){  
//            proximo = page + 1;           
//        }  
//        if (posts.hasPreviousPage()){  
//            anterior = page - 1;  
//        }  
//        model.addAttribute("proximo", proximo);  
//        model.addAttribute("anterior", anterior);  
//  
//        return "index/posts";         
//    }  
//}  
//	@RequestMapping(value = "/grud/show/")
//	public ModelAndView showTransacoes() {
//		ModelAndView mav = new ModelAndView();
//		List<Transacao> showTransacao = transacaoDao.list(0, 10);
//		mav.getModel().put("transacoes", showTransacao);
//		mav.setViewName("grud/showTransacoes");
//		return mav;
//
//	}

	@RequestMapping(value = "/grud/show/", method = RequestMethod.GET)
	public String list(
			@RequestParam(defaultValue = "1", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "10", value = "page.size", required = false) Integer size,
			Model mav) {
		int firstResult = (page == null) ? 0 : (page - 1) * size;
//		Map<String, Object> m = mav.getModel();
//		mav.add
//		m.put("transacoes", transacaoDao.list(firstResult, size));
		mav.addAttribute("transacoes", transacaoDao.list(firstResult, size));
		float numeroPaginas = (float) transacaoDao.getCount().intValue() / size;
		int maxPages = (int) (numeroPaginas + 1);
		mav.addAttribute("maxPages", maxPages);
//		m.put("maxPages", maxPages);
//		mav.setViewName("grud/showTransacoes");
		return "grud/showTransacoes";
	}

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
