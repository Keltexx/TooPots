package es.uji.ei102718cln.TooPots.dao;

import java.util.Collection;

import es.uji.ei102718cln.TooPots.model.UserDetails;


public interface UserDao {
	UserDetails loadUserByUsername(String username, String password);
 	Collection<UserDetails> listAllUsers();
}