package br.com.software.dao;


import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.software.modelos.User;
import br.com.software.modelos.UserConnection;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("usuarioDao")
public class UserDaoImpl extends HibernateDao<User> implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Transactional
	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	@Transactional
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Transactional
	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}
	
	@Transactional
    protected Class getClazz() {
        return User.class;
    }
//    @Transactional
//    public User getUsuario(String login, String senha) {
//        Query query = getSession().createQuery("from Usuario u where u.login = ? and u.hashSenha = ?");
//        query.setString(0, login);
//        query.setString(1, DigestUtils.sha256Hex(senha));
//        return (User) query.uniqueResult();
//    }
    @Transactional
    public User getUsuario(String login) {
        Query query = getSession().createQuery("from User u where u.login = ?");
        query.setString(0, login);
        return (User) query.uniqueResult();
    }
    
	@Override
	@Transactional
	public List<User> list(int offset, int max) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional
	public UserConnection getUsuarioConnection(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
