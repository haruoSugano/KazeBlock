package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{
	
	private Connection conn;
	

	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
	/**
	 * Verificar se o usuario existe 
	 * Validacao de login Implementar isto!!!
	 * @param usuario
	 * @return
	 */
	@Override
	public boolean existe(Usuario usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				ps = conn.prepareStatement("SELECT * FROM tb_usuario WHERE cpf = ? AND senha = ? ");
				
				ps.setString(1, usuario.getCpf());
				ps.setString(2, usuario.getSenha());
			
				rs = ps.executeQuery();
				if(rs.next()) {
					usuario = new Usuario();
					usuario.setCpf(rs.getString("cpf"));
					usuario.setSenha(rs.getString("senha"));
					return true;
				}	
				
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(ps);
				DB.closeResultSet(rs);
			}
			return false;
		}
	
	/**
	 * Inserir/Cadastrar usuario:
	 */
	@Override
	public void insert(Usuario usuario) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO tb_usuario "
					+ "(nome, cpf, senha, tipoUsuario ) "
					+"VALUES "
					+"(?, ?, ?, ? )",
					Statement.RETURN_GENERATED_KEYS);
		
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getTipoUsuario());
			
			int rowAffected = ps.executeUpdate();
			
			if(rowAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					usuario.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}
	/**
	 * Deletar o usuario no banco de dados:
	 */
	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM tb_usuario WHERE id = ? ");
			
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}
/**
 * Buscar usuario pelo id no banco de dados:
 */
	@Override
	public Usuario UsuarioById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_usuario "
					+"WHERE id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("id"));
				return user;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	/**
	 * Buscar o usuario pelo cpf no bd
	 */
	@Override
	public Usuario UsuarioByCpf(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_usuario "
					+"WHERE cpf = ?");
			
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				Usuario user = new Usuario();
				user.setCpf(rs.getString("cpf"));
				return user;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
/**
 * Listar o usuários na tabela:
 */
	@Override
	public List<Usuario> listUsuario() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> listUsuario = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_usuario ");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipoUsuario(rs.getString("tipoUsuario"));
				listUsuario.add(usuario);
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		return listUsuario;
	}

	/**
	 * Lista usado para listar os usuarios e autenticar para realizar o login:
	 */
	@Override
	public List<Usuario> autenticar() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> autenticarUsuario = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_usuario ");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setTipoUsuario(rs.getString("tipoUsuario"));
				autenticarUsuario.add(usuario);
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		return autenticarUsuario;
	}
}
