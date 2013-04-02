package br.com.software.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.software.modelos.Cartao;



@Repository("cartao")
public class CartaoDao {

	protected Class getClazz() {
		return Cartao.class;
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

	public Cartao getCartao(Long id) {
		return (Cartao) getSession().get(getClazz(), id);
	}
	public void delete (Cartao cartao){
		getSession().delete(cartao);
	}
	public void persistir (Cartao cartao){
		getSession().saveOrUpdate(cartao);
	}
	public List<Cartao> lista (int offset, int max){
		return (List<Cartao>) getSession().createCriteria(getClazz()).setMaxResults(max).setFirstResult(offset).list();
	}
}

