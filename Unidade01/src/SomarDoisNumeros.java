// Bibliotecas
import java.util.*;

class SomarDoisNumeros {
	// leitor para entrada de dados
	public static Scanner sc = new Scanner( System.in );
	public static void main( String[] args ) {
		// declaracao de variaveis
		// variaveis de emtrada
		int num1 = 0;
		int num2 = 0;
		// variaveis de saida
		int soma = 0;
		
		// leitura
		System.out.println( "Digite um numero: " );
		num1 = sc.nextInt( );
		System.out.println( "Digite outro numero: " );
		num2 = sc.nextInt( );
		
		// somar
		soma = num1 + num2;
		
		// mostrar na tela
		System.out.println( "Soma: " + soma );
	} // end main()
} // end SomarDoisNumeros
