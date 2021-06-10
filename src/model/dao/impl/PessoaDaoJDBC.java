package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PessoaDao;
import model.entities.Pessoa;
public class PessoaDaoJDBC implements PessoaDao {

	//Dependencia
	private Connection conn;
	
	
	public PessoaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	//-----------
	/**
	 * Inserindo os dados do paciente ao banco de dados:
	 * ok
	 */
	@Override
	public void insert(Pessoa pessoa) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO tb_pessoa "
					+"(nome, idade, profissao, cpf, nascimento, areaSaude, logradouro, numero, cidade, uf, cep, nivel_prioridade, vacinado) "
					+"VALUES "
					+"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getProfissao());
			ps.setString(4, pessoa.getCpf());
			ps.setDate(5,new java.sql.Date(pessoa.getNascimento().getTime()));
			ps.setBoolean(6, pessoa.getAreaSaude());
			ps.setString(7, pessoa.getLogradouro());
			ps.setString(8, pessoa.getNumero());
			ps.setString(9, pessoa.getCidade());
			ps.setString(10, pessoa.getUf());
			ps.setString(11, pessoa.getCep());
			ps.setInt(12, pessoa.getNivel());
			ps.setBoolean(13, false);
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					pessoa.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada.");
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
	 * Removendo paciente do banco de dados:
	 */
	@Override
	public void deleteByCpf(String cpf) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM tb_pessoa WHERE cpf = ? ");
			
			ps.setString(1, cpf);
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
 * Enviando ao banco de dados a confirmação de pessoas vacinadas e a data de vacinação:
 */
	@Override
	public void confirmarVacina(Pessoa pessoa) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"UPDATE tb_pessoa SET vacinado = ?, dataVacinacao = ? WHERE cpf = ? ");
			
			ps.setBoolean(1, pessoa.getVacinado());
			ps.setDate(2, new java.sql.Date(pessoa.getDataVacinado().getTime()));
			ps.setString(3, pessoa.getCpf());
			
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
	 * Pesquisando um paciente cadastrado no banco de dados:
	 * ok
	 */
	@Override
	public Pessoa findByCpf(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_pessoa "
					+"WHERE cpf = ?");
			
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCpf(rs.getString("cpf"));
				return pessoa;
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
	 * Listando um paciente cadastrado em um banco de dados:
	 * ok
	 */
	@Override
	public List<Pessoa> listarPessoas() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pessoa> listPessoa = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_pessoa ");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setProfissao(rs.getString("profissao"));
				pessoa.setAreaSaude(rs.getBoolean("areaSaude"));
				pessoa.setNascimento(rs.getDate("nascimento"));
				pessoa.setNivel(rs.getInt("nivel_prioridade"));
				pessoa.setVacinado(rs.getBoolean("vacinado"));
				pessoa.setDataVacinado(rs.getDate("dataVacinacao"));
				listPessoa.add(pessoa);
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		return listPessoa;
	}

	/**
	 * Ordenando a fila de acordo com nivel de prioridade:
	 */
	@Override
	public List<Integer> ordenarFila() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> listNivel = new ArrayList<>();
		try {
			
			ps = conn.prepareStatement("SELECT * FROM tb_pessoa ");
			Pessoa pessoa = new Pessoa();
			
			rs = ps.executeQuery();
			while(rs.next()) {
				pessoa.setNivel(rs.getInt("nivel_prioridade"));
				listNivel.add(pessoa.getNivel());
				
			}
			return listNivel;
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
	 * Buscar data:
	 */
	@Override
	public List<Date> buscarData() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Date> listData = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_pessoa ");
			Pessoa pessoa = new Pessoa();
			rs = ps.executeQuery();
			if(rs.next()) {
				pessoa.setDataVacinado(rs.getDate("dataVacinacao"));
				listData.add(pessoa.getDataVacinado());
				return listData ;
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
}
