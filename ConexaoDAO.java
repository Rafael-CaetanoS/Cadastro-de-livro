package appfinal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO {
	static String url= "jdbc:mysql://localhost/atividadejdbc";
	static Connection objCon=null;

	public static Connection getConnection() {
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			objCon = DriverManager.getConnection(url, "root", "");
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro com a conexao!");
			e.printStackTrace();
		}
		
		return objCon;
	}
	
	public static void closeConnection(Connection con){
		
		try {
			
			if (con!=null){
				con.close();
			}
						
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na hora de fechar a conexão!");
			e.printStackTrace();
		}
	
	}
}
