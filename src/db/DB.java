package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//Gerar uma conexão com banco de dados:
public class DB {

	private static Connection conn = null;//Objeto de conexão
	
	public static Connection getConnection() {

		if(conn == null) {
			try {
				Properties props = loadProperties();//Recebendo o loadProperties
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);//Obter conexão com banco de dados
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
//Metodo para fechar a conexão:
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
//Metodo auxiliar para carregar db_properties, e guardar em um objeto especifico:
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties ();
			props.load(fs);//leitura do arquivo db_properties 
			return props;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
//Metodo auxiliar para fechar o objeto Statement:    (para nao ficar colocando try no programa principal )
	public static void closeStatement(Statement ps) {
		if(ps != null) {
			try {
				ps.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
//Metodo auxiliar para fechar o objeto ResultSet:
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
