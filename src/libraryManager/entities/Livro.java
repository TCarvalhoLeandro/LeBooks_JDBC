package libraryManager.entities;

import java.io.Serializable;
import java.util.Objects;

//JDBC

public class Livro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String titulo;
	private Autor autor;
	private Integer ano;
	private boolean disponivel;
	
	
	public Livro() {
	
	}


	public Livro(Integer id, String titulo, Autor autor, Integer ano, boolean disponivel) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.disponivel = disponivel;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}


	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public boolean isDisponivel() {
		return disponivel;
	}


	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", ano=" + ano + ", disponivel="
				+ disponivel + "]";
	}
	
	
}
