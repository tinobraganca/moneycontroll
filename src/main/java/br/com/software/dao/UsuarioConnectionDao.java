package br.com.software.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.software.modelos.User;
import br.com.software.modelos.UserConnection;

@Repository("UserConnection")
@Transactional
public class UsuarioConnectionDao extends HibernateDao<UserConnection> {
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}
	
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return UserConnection.class;
	}
	
	@Override
	public UserConnection get(Long userId) {
		// TODO Auto-generated method stub
		return super.get(userId);
	}
	public UserConnection getUsuario(Long userId) {
        Query query = getSession().createQuery("from UserConnection u where u.userId = ?");
        query.setLong(0, userId);
        return (UserConnection) query.uniqueResult();
    }
}
