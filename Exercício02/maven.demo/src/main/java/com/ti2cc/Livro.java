package com.ti2cc;

/*
	Class Livro -> definicao do tipo abstrato Livro
*/
public class Livro {
	// atributos da classe
	private int id; // codigo de identificacao de um livro
	private String autor; // escritor do livro
	private String titulo; // titulo do livro
	private int ano; // ano de lancamento do livro
	
	/*
	 	Contrutores
	*/
	public Livro() {
		this.id = -1;
		this.autor = "";
		this.titulo = "";
		this.ano = 0;
	} // end Livro()
	
	public Livro(int id, String autor, String titulo, int ano) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.ano = ano;
	} // end Livro
	
	/*
	 	Metodos Set e Get -> selecao de valores e recuperacao de valores
	*/
	public void setId(int id) {
		this.id = id;
	} // end setId
	
	public int getId() {
		return this.id;
	} // end getId
	
	public void setAutor(String autor) {
		this.autor = autor;
	} // end setAutor
	
	public String getAutor() {
		return this.autor;
	} // end getAutor
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	} // end setTitulo
	
	public String getTitulo() {
		return this.titulo;
	} // end getTitulo
	
	public void setAno(int ano) {
		this.ano = ano;
	} // end setAno
	
	public int getAno() {
		return this.ano;
	} // end getAno
	
	/*
		toString() -> converter dados para String
	*/
	public String toString() {
		return "Id: " + id + "; Autor: " + autor + "; Titulo: " + titulo + "; Ano: " + ano;
	} // end toString()
} // end Livro










