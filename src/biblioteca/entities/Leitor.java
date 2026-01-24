package biblioteca.entities;

import java.io.Serializable;

//import biblioteca.service.Salvar;

public class Leitor implements Serializable{// implemetando o serializable
	
	private static final long serialVersionUID = 1L;

	//private static int contador = 0;
	
	private Integer id;//id do leitor
	private String nome;// nome do leitor
	private String cpf;//cpf do leitor
	private String email;//email do leitor
	
	//construtor padrão
	public Leitor() {
		//contador++;
	}

	//construtor com todos os argumentos
	public Leitor(Integer id, String nome, String cpf, String email) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		//contador++;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
	public static int getContador() {
		return contador;
	}
	public static void setContador(int cont) {
		contador = cont;
	}
	
	/*METODO INTERFACE PRA SALVAR EM .CSV
	@Override
	public String toCSV() {
		return id + ";" 
				  + nome 
				  + ";" 
				  + cpf 
				  + ";" 
				  + email;
	}
	*/
	
	@Override
	public String toString() {
		return id + " - " + nome + " - (" + cpf + ") - " + email;
	}
}
