package br.com.software.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import br.com.software.dao.UserDao;
import br.com.software.dao.UsuarioConnectionDao;
import br.com.software.modelos.Cartao;
import br.com.software.modelos.Transacao;
import br.com.software.modelos.User;
import br.com.software.modelos.UserConnection;
import br.com.software.social.UserCookieGenerator;


@Controller
public class GrudController  {
	@Autowired
	private UsuarioConnectionDao userConnectionDao;
	
	public UsuarioConnectionDao getUserConnectionDao() {
		return userConnectionDao;
	}

	public void setUserConnectionDao(UsuarioConnectionDao userConnectionDao) {
		this.userConnectionDao = userConnectionDao;
	}

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

	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String conectado(){
//		SocialConfig conexao = new SocialConfig();
//		Facebook face = conexao.facebook();
//		if (face.isAuthorized()){
//			FacebookProfile faceprofile = face.userOperations().getUserProfile();
//			User user = new User();
//			user.setLogin(faceprofile.getName());
//			user.setName(faceprofile.getName());
//			
//		}
//		return "redirect:/index";
//	}

	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String habilitaIndex() {
		return "index";

	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String habilitaTelaDeLogin() {
		return "telaLogin";

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
	public String adionarTransacao(@Valid @ModelAttribute("transacao")Transacao transacao ,BindingResult result,@RequestParam(value = "cartao.id") Long idcartao,HttpServletRequest request) {
		String status = "fail";
		Long UserIdCookie = Long.valueOf(userCookieGenerator.readCookieValue(request));
		System.out.println(UserIdCookie);
		UserConnection userConnection = userConnectionDao.getUsuario(UserIdCookie);
		User user = userDao.get(Long.valueOf(userConnection.getProviderUserId()));
		if(result.hasFieldErrors()){
			status="fail";
		}
			if (user!=null) {
				System.out.println(user.getId());
				System.out.println(userConnection.getProviderUserId());
				if(user.getId().equals(Long.valueOf(userConnection.getProviderUserId()))){
				transacao.setCartao(cartaodao.getCartao(idcartao));
				transacao.setUser(user);
				transacaoDao.persistir(transacao);
				System.out.println("incluido");
				status = "ok";
				}
			}
		
		return "redirect:/grud/transacao/?status="+status;
	}
	 
	/* Inicio da parte da visualizacao */
	
	@RequestMapping(value = "/grud/show*", method = RequestMethod.GET)
	public ModelAndView showCad(ModelMap modelmap){
	    modelmap.addAttribute("alteraTransacao", new Transacao());
	    ModelAndView mav = new ModelAndView();
	    List<Cartao> cartoes = cartaodao.lista(0, 10);
        mav.getModel().put("cartoes", cartoes);
	    mav.setViewName("grud/showTransacoes");
		return mav;
	}

	@RequestMapping(value = "/grud/show/pagMax", method = RequestMethod.GET)
	public @ResponseBody int totalPag(){
		int size = 10;
		float numeroPaginas = (float) transacaoDao.getCount().intValue() / size;
		int maxPages = (int) (numeroPaginas + 1);
		return maxPages;
		
	}
	
	@RequestMapping(value = "/grud/show/lista/", method = RequestMethod.GET)
	public @ResponseBody List<Transacao> list(@RequestParam(defaultValue = "1", value = "page", required = false) String page, @RequestParam(defaultValue = "10", value = "numero", required = false) Integer size,HttpServletRequest request) {
		Long pageN = Long.valueOf(page)  ;
		int firstResult = (int) ((page == null) ? 0 : (pageN - 1) * size);
		String userIdCookie = userCookieGenerator.readCookieValue(request);
		UserConnection userConnection = userConnectionDao.getUsuario(Long.valueOf(userIdCookie));
		userIdCookie=userConnection.getProviderUserId();
		List<Transacao> lista = transacaoDao.list(firstResult, size,userIdCookie);
		return lista;
	} 
	
	
	@RequestMapping(value = "/grud/abrastractSomaTotal", method = RequestMethod.GET)
	public @ResponseBody Map<String,Double> somasGerais(HttpServletRequest request){
		String userIdCookie = userCookieGenerator.readCookieValue(request);
		UserConnection userConnection = userConnectionDao.getUsuario(Long.valueOf(userIdCookie));
		userIdCookie=userConnection.getProviderUserId();
		Double valorReceita =transacaoDao.getValorReceita(userIdCookie).doubleValue();
		Double valorDespesa =transacaoDao.getValorDespesas(userIdCookie).doubleValue();
		Double valorTotal =valorReceita-valorDespesa;
		Map<String,Double> resultado = new HashMap<String, Double>();
		resultado.put("receitas", valorReceita);
		resultado.put("despesas", valorDespesa);
		resultado.put("total",valorTotal);
		return resultado;
	}
	
	/* Fim da parte da visualizacao */
	
	/*Começo da parte de alteraçao de transacao */
	
    @RequestMapping(value = "/grud/show/updateControl/", method = RequestMethod.GET)
    public @ResponseBody
    Transacao update(@RequestParam String id) {
        Transacao transacao = transacaoDao.get(Long.valueOf(id));
        return transacao;
    }

    @RequestMapping(value = "/grud/show/alteraTransacao/", method = RequestMethod.POST)
    public String alteraTransacao(@Valid @ModelAttribute("alteraTransacao") Transacao transacao,BindingResult result,@RequestParam(defaultValue = "1", value = "cartao.id", required = false) Long idcartao,@RequestParam String id) {
        if (result.hasFieldErrors()) {
            return "grud/cadTransacao";
        }
        else {
            transacao.setCartao(cartaodao.getCartao(idcartao));
            Transacao t = transacao;
            transacaoDao.updateT(transacao);
            return "/index";
        }
    }
    /* Fim da parte de alteraçao de transacao */

}
