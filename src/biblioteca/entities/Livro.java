package biblioteca.entities;

import biblioteca.service.Salvar;

public class Livro implements Salvar{// implemetando a interface Salvar
	
	private static int contador = 0;
	
	private int id;// id do livro
	private String titulo;//nome do livro
	private String autor;//nome do autor
	private int ano;//ano de publicação
	private boolean disponivel;//disponibilidade do livro
	
	//construtor padrao
	public Livro() {
		contador++;
	}
	
	//construtor com todos os atributos
	public Livro(int id, String titulo, String autor, int ano, boolean disponivel) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.disponivel = disponivel;
		contador++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public static int getContador() {
		return contador;
	}
	public static void setContador(int cont) {
		contador = cont;
	}
	
	/*METODO INTERFACE PRA SALVAR EM .CSV*/
	@Override
	public String toCSV() {
		return id + ";" 
				  + titulo 
				  + ";" 
				  + autor 
				  + ";" 
				  + ano 
				  + ";" 
				  + disponivel;
	}
	
	@Override
	public String toString(){
		 return id + " - " + titulo + " - "+ autor + " - " + ano + " - " + disponivel; 
	}
}
