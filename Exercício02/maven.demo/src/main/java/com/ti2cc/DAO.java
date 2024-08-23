package com.ti2cc;

// bibliotecas
import java.sql.*;

/*
	Class DAO -> Data Acess Project, classe de conexao entre o back-end e o banco de dados
*/
public class DAO {
	// atributos
	private Connection conexao; // conexao com o banco de dados
	
	/*
		Contrutor
	*/
	public DAO() {
		conexao = null;
	} // end DAO()
	
	/*
		conectar() -> metodo que verifica se a conexao com o banco de dados foi bem sucedida
		- retorna true se conexao bem sucedida
	*/
	public boolean conectar() {
		// definir dados
		String driverName = "org.postgresql.Driver"; // driver do postgre                    
		String serverName = "localhost"; // servidor
		String mydatabase = "exercicio02"; // nome da base de dados
		int porta = 5432; // porta do banco
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase; // url
		String username = "ti2cc"; // nome de usuario
		String password = "ti@cc"; // senha do usuario
		boolean status = false; // controlador de conexao

		// tentar conectar com o banco de dados, lancar excecao se der errado
		try {
			// carregar dinamicamente uma classe em tempo de execucao
			Class.forName(driverName);
			
			// estabelecer conexao com o banco de dados
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		// retorno
		return status;
	} // end conectar
	
	/*
	 	close() -> encerra a conexao com o banco de dados
	*/
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		// retorno
		return status;
	} // end close()
	
	/*
		inserirLivro() -> inserir livro no banco de dados
		- retorna true se a insercao foi bem sucedida
	*/
	public boolean inserirLivro(Livro livro) {
		// definir dados
		boolean status = false;
		
		// tentar inserir novo livro
		try {  
			// objeto usado para executar uma instrução SQL estática e retornar os resultados que produz
			Statement st = conexao.createStatement();     
			
			/* Executa a instrução SQL fornecida, que pode ser uma instrução INSERT, UPDATE ou DELETE ou uma 
			   instrução SQL que não retorna nada, como uma instrução SQL DDL.
			*/
			st.executeUpdate("INSERT INTO livro (id, autor, titulo, ano) "
					       + "VALUES ("+ livro.getId()+ ", '" + livro.getAutor() + "', '"  
					       + livro.getTitulo() + "', '" + livro.getAno() + "');");
			
			/*
				Libera o banco de dados deste objeto Statement e os recursos JDBC imediatamente, 
				em vez de esperar que isso aconteça quando ele for fechado automaticamente.
			*/
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		// retorno
		return status;
	} // end inserirLivro()
	
	/*
		exceluirLivro -> metodo para excluir um livro do banco de dados
		@param id -> identificador do livro que sera excluido do bd
		- retorna true se a exclusao foi realizada com sucesso
	*/
	public boolean excluirLivro(int id) {
		// definir dados
		boolean status = false;
		
		// tentar excluir livro
		try {  
			Statement st = conexao.createStatement();		
			// realizar mudanca no banco de dados
			st.executeUpdate("DELETE FROM livro WHERE id = " + id);
			// liberar banco de dados
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		
		// retorno
		return status;
	} // end excluirLivro()
	
	/*
		getLivros() -> metodo que retorna um array com todos os livros inseridos no BD naquele momento
	*/
	public Livro[] getLivros() {
		// definir dados
		Livro[] livros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			/*
				tabela de dados que representa um conjunto de resultados do banco de dados, 
				que geralmente é gerado pela execução de uma instrução que consulta o banco de dados.
			*/
			ResultSet rs = st.executeQuery("SELECT * FROM livro");	
			
			// verificar se possui elementos no BD
	        if(rs.next()){
	        	// move o cursor(ponteiro) para o ultimo livro do BD
	            rs.last();
	            
	            // cria um array com o tamanho do numero de colunas no banco de dados
	            livros = new Livro[rs.getRow()];
	            
	            // move o ponteiro de volta para antes da primeira linha de livro
	            rs.beforeFirst();

	            // preenche o array de livros
	            for(int i = 0; rs.next(); i++) {
	               livros[i] = new Livro(rs.getInt("id"), rs.getString("autor"), 
	                		                  rs.getString("titulo"), rs.getInt("ano"));
	            }
	         }
	        
	        // liberar banco de dados
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		// retorno
		return livros;
	}// end getlivros()
} // end DAO




