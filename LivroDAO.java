package appfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
	public void cadastrar(Livro objLivro) {
		Connection con = ConexaoDAO.getConnection();
		String sqlInsert = "insert into tbl_livro values(?,?,?,?,?)";
		
		try {
			PreparedStatement pstm = con.prepareStatement(sqlInsert);
			pstm.setInt(1, objLivro.getIsbn());
			pstm.setString(3, objLivro.getTitulo());
			pstm.setString(2, objLivro.getAutor());
			pstm.setInt(4, objLivro.getAno());
			pstm.setString(5, objLivro.getEditora());
			pstm.executeUpdate();
			System.out.println("Livro cadastrado");
		
		}catch(SQLException e) {
			System.out.println("Falha no cadastro");
			e.printStackTrace();	
		}
		finally {
			ConexaoDAO.closeConnection(con);
		}
	}
	
	public Livro consultar(int isbn) {
		Connection con = ConexaoDAO.getConnection();
		String sqlConsultaLivro = "select * from tbl_livro where isbn=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sqlConsultaLivro);
			pstm.setInt(1,isbn);
			ResultSet rs = pstm.executeQuery();
			Livro livro = new Livro();
			rs.next();
		             
			livro.setIsbn(rs.getInt("isbn"));
			livro.setTitulo(rs.getString("titulo"));
			livro.setAutor(rs.getString("autor"));
			livro.setAno(rs.getInt("ano"));
			livro.setEditora(rs.getString("editora"));
		         
			return livro;
		
		}catch(Exception e){
			return null;
		}
		finally {
			ConexaoDAO.closeConnection(con);
		}
	}
	
	public void excluirLivro(int isbn){
		Connection con = ConexaoDAO.getConnection();
		
		String sqlExclusao = "delete from tbl_livro where isbn=?";
		
		try {
			PreparedStatement pstm = con.prepareStatement(sqlExclusao);
			pstm.setInt(1, isbn);
			pstm.executeUpdate();
			System.out.println("livro excluido");
		} 
		catch (SQLException e) {
			System.out.println("livro nao encontrado");
			e.printStackTrace();
		}
		finally {
			ConexaoDAO.closeConnection(con);
		}			
	}
	
	public void Atulizar (Livro livro) {
		Connection con = ConexaoDAO.getConnection();
		String sql = "UPDATE tbl_livro SET titulo=?, autor=?, ano=?, editora=? WHERE isbn=?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getAutor());
			pstm.setInt(3, livro.getAno());
			pstm.setString(4, livro.getEditora());
			pstm.setInt(5, livro.getIsbn());
			pstm.executeUpdate();
		}catch(Exception e) {
			System.out.println("Erro ao editar curso: " + e.getMessage());
		}
		finally {
			ConexaoDAO.closeConnection(con);
		}				
	}
	
	public List<Livro> getLivro() {
		Connection con = ConexaoDAO.getConnection();
		String sqlConsultaLivro = "select * from tbl_livro";
		
	 try {
         PreparedStatement stm = con.prepareStatement(sqlConsultaLivro);
         ResultSet rs = stm.executeQuery();
         List<Livro> listaLivro = new ArrayList<>();

         while(rs.next()) {
             Livro livro = new Livro(rs.getInt("isbn"),rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"), rs.getString("editora"));
             livro.setIsbn(rs.getInt("isbn"));
             livro.setTitulo(rs.getString("titulo"));
             livro.setAutor(rs.getString("autor"));
             livro.setAno(rs.getInt("ano"));
             livro.setEditora(rs.getString("editora"));
             listaLivro.add(livro);
         }
         return listaLivro;

	 	}catch (SQLException e) {
			System.out.println("livro nao encontrado");
			e.printStackTrace();
			return null;
		}
		finally {
			ConexaoDAO.closeConnection(con);
		}
		
	}
}
