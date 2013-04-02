package br.com.software.dao;

import org.springframework.stereotype.Repository;

import br.com.software.modelos.Transacao;

@Repository("transacao")
public class TransacaoDao extends HibernateDao<Transacao>{

	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
