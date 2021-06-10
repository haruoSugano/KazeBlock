package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDao {
	
	/**
	 * Cadastrando usuario no sistema:
	 * @param usuario
	 */
	void insert (Usuario usuario);
	/**
	 * Deletando usuario pelo ID:
	 * @param id
	 */
	void deleteById(Integer id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Usuario UsuarioById(Integer id);
	Usuario UsuarioByCpf(String cpf);
	List<Usuario> listUsuario();
	/**
	 * Verificar se o usuario existe 
	 * @param usuario
	 * @return
	 */
	boolean existe(Usuario usuario);
	List<Usuario> autenticar();
	

}
