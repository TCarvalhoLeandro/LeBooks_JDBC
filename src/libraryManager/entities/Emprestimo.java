package libraryManager.entities;

import java.io.Serializable;
import java.time.LocalDate;

//JDBC
public class Emprestimo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Leitor leitor;
	private Livro livro;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private LocalDate dataDevolucaoEfetiva;
	
	public Emprestimo() {
	
	}

	public Emprestimo(Long id, Leitor leitor, Livro livro, LocalDate dataEmprestimo,
			LocalDate dataDevolucao, LocalDate dataDevolucaoEfetiva) {
		this.id = id;
		this.leitor = leitor;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
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

	public LocalDate getDataDevolucaoEfetiva() {
		return dataDevolucaoEfetiva;
	}

	public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) {
		this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
	}
	
	public boolean isPendente() {
		return this.dataDevolucaoEfetiva == null;
	}

	@Override
	public String toString() {
		return id + " - " + leitor.getNome() + " - " + livro.getTitulo()
				+ " - " + dataEmprestimo + " - " + dataDevolucao + " - " + dataDevolucaoEfetiva;
	}
}