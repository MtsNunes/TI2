package com.ti2cc;

// bibliotecas
import java.util.*;

public class Principal {
	
	public static void menu() {
		System.out.println("\nOpcoes: \n0 - Sair\n1 - Inserir \n2 - Remover \n3 - Listar");
	}
	
	public static void main(String[] args) {
		// definir dados
		DAO dao = new DAO();
		int opcao = -1;
		int id, ano;
		String autor, titulo;
		Scanner sc = new Scanner(System.in);
		
		// conectar com banco de dados
		dao.conectar();
		
		while(opcao != 0) {
			menu();
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
				case 0:
					System.out.println("Obrigado por usar o programa");
					break;
				case 1:
					System.out.println("Digite id do livro: ");
					id = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Digite autor do livro: ");
					autor = sc.nextLine();
					
					System.out.println("Digite titulo do livro: ");
					titulo = sc.nextLine();
					
					System.out.println("Digite ano do livro: ");
					ano = sc.nextInt();
					sc.nextLine();
					
					Livro livro = new Livro(id, autor, titulo, ano);
					
					dao.inserirLivro(livro);
					break;
				case 2:
					System.out.println("Digite o id do livro a ser removido: ");
					id = sc.nextInt();
					sc.nextLine();
					
					dao.excluirLivro(id);
					break;
				case 3:
					Livro[] livros = dao.getLivros();
					System.out.println("Livros: ");
					for(int i = 0; i < livros.length; i = i + 1) {
						System.out.println(livros[i].toString());
					}
					break;
				default:
					System.out.println("Opcao inválida");
					break;
			}
		}
		
		// desconectar do banco de dados
		dao.close();
		
	} // end main()
} // end Principal