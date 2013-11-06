package br.com.software.social;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import br.com.software.dao.UserDao;
import br.com.software.modelos.User;

@Configuration
public class SocialConfig {
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

    @Value("${facebook.clientId}")
    private String facebookClientId;
    
    @Value("${facebook.clientSecret}")
    private String facebookClientSecret;
    
    @Inject
    private Environment environment;
    
    @Inject
    private DataSource dataSource;
    
    
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookClientSecret));
        return registry;
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), Encryptors.noOpText());
        repository.setConnectionSignUp(new SimpleConnectionSignUp());
        return repository;
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        User user = SecurityContext.getCurrentUser();
        return usersConnectionRepository().createConnectionRepository(user.getStringId());
    }

    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)   
    public Facebook facebook() {
//    	return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
    	Facebook Fb =  connectionRepository().getPrimaryConnection(Facebook.class).getApi();
        if (Fb.isAuthorized()){
        	FacebookProfile faceprofile = Fb.userOperations().getUserProfile();
			User user = new User();
			user.setLogin(faceprofile.getName());
			user.setName(faceprofile.getName());
			user.setEmail(faceprofile.getEmail());
			user.setId(Long.valueOf(faceprofile.getId()));
			System.out.println(faceprofile.getId());
			User user2 = userDao.getUsuario(faceprofile.getId());
			if(user2==null || user2.getId()==0){
				userDao.persistir(user);
			}
        }
        return Fb;
        
    }
    
}
