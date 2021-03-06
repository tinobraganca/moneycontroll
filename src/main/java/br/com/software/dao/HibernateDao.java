package br.com.software.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.software.modelos.User;

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
    public void  updateT(T objeto){
        getSession().merge(objeto);
        
    }
    public void excluir(T objeto) {
        getSession().delete(objeto);
    }

    public T get(Long id) {
        return (T) getSession().get(getClazz(), id);
    }

    public List<T> list(int offset, int max,String userIdCookie) {
    	return (List<T>) getSession().createCriteria(getClazz()).setMaxResults(max).setFirstResult(offset).add(Restrictions.eq("user", new User(userIdCookie))).list();
    }
    
	public Number getCount() {
		return (Number)getSession().createCriteria(getClazz()).setProjection(Projections.rowCount()).uniqueResult();
	}
	public Number getValorReceita(String userIdCookie){
		Number retorno = (Number)getSession().createCriteria(getClazz()).setProjection(Projections.sum("valor")).add(Restrictions.eq("tipo", 1)).add(Restrictions.eq("user", new User(userIdCookie))).uniqueResult();
		if(retorno == null){
			retorno = 0;
		}
		return retorno;
	}
	public Number getValorDespesas(String userIdCookie){
		Number retorno =(Number)getSession().createCriteria(getClazz()).setProjection(Projections.sum("valor")).add(Restrictions.eq("tipo", 2)).add(Restrictions.eq("user", new User(userIdCookie))).uniqueResult();
		if (retorno==null){
			retorno = 0;
		}
		return retorno;
	}
	
}
