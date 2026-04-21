package libraryManager.entities;

import java.io.Serializable;
import java.time.LocalDate;

//JDBC
public class Emprestimo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer leitor_id;
	private Integer livro_id;
	private boolean devolvido;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	
	public Emprestimo() {
	
	}
	
	public Emprestimo(Integer id, Integer leitor_id, Integer livro_id, boolean devolvido, LocalDate dataEmprestimo,
			LocalDate dataDevolucao) {
		this.id = id;
		this.leitor_id = leitor_id;
		this.livro_id = livro_id;
		this.devolvido = devolvido;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeitor_id() {
		return leitor_id;
	}

	public void setLeitor_id(Integer leitor_id) {
		this.leitor_id = leitor_id;
	}

	public Integer getLivro_id() {
		return livro_id;
	}

	public void setLivro_id(Integer livro_id) {
		this.livro_id = livro_id;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", leitor_id=" + leitor_id + ", livro_id=" + livro_id + ", devolvido="
				+ devolvido + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + "]";
	}

}