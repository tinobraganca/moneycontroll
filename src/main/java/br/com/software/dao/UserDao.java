package br.com.software.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.software.modelos.User;
import br.com.software.modelos.UserConnection;



public interface UserDao {
//	@Transactional
//    public User getUsuario(String login, String senha);
	@Transactional
    public User getUsuario(String login);
	@Transactional
    public User get(Long id);
	@Transactional
    public void excluir(User usuario);
	@Transactional
    public List<User> list(int offset, int max);
	@Transactional
    public void persistir(User usuario);
	@Transactional
	public UserConnection getUsuarioConnection(Long id);

}
