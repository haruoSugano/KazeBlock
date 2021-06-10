package model.dao;

import db.DB;
import model.dao.impl.PessoaDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
//Classe auxiliar para instanciar Dao
public class DaoFactory {
	
	public static PessoaDao createPessoaDao() {
		return new PessoaDaoJDBC(DB.getConnection());
	}

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
}
