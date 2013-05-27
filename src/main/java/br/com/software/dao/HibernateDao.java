package br.com.software.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
@Component("hibernate")
public abstract class HibernateDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
    
    protected abstract Class getClazz();
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sf) {
        sessionFactory = sf;
    }

    public void persistir(T objeto) {
        getSession().saveOrUpdate(objeto);
    }

    public void excluir(T objeto) {
        getSession().delete(objeto);
    }

    public T get(Long id) {
        return (T) getSession().get(getClazz(), id);
    }

    public List<T> list(int offset, int max) {
    	return (List<T>) getSession().createCriteria(getClazz()).setMaxResults(max).setFirstResult(offset).list();
    }

	public Number getCount() {
		return (Number)getSession().createCriteria(getClazz()).setProjection(Projections.rowCount()).uniqueResult();
	}
	
}
