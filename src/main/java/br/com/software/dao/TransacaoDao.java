package br.com.software.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.software.modelos.Transacao;

@Component("transacaoDao")
@Transactional
public class TransacaoDao extends HibernateDao<Transacao>{
	
	@Override
	protected Class getClazz() {
		return Transacao.class;
	}
	
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
	


}
