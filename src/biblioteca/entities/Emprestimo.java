package biblioteca.entities;

import java.time.LocalDate;

import biblioteca.service.Salvar;

public class Emprestimo implements Salvar{// implementando a interface Salvar
	
	private static int contador = 0;
	
	private int id;// id do emprestimo
	private Leitor leitor;
	private Livro livro;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private boolean devolvido;
	
	//construtor padrão
	public Emprestimo() {
		contador++;
	}
	public Emprestimo(int id, Leitor leitor, Livro livro, LocalDate dataEmprestimo,
			boolean devolvido) {
		this.id = id;
		this.leitor = leitor;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.devolvido = devolvido;
		contador++;
	}

	public Emprestimo(int id, Leitor leitor, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao,
			boolean devolvido) {
		this.id = id;
		this.leitor = leitor;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.devolvido = devolvido;
		contador++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}
	
	public void registrarDevolucao() {
		this.dataDevolucao = LocalDate.now();
	}
	
	public static int getContador() {
		return contador;
	}
	public static void setContador(int cont) {
		contador = cont;
	}
	
	
	public long diasAtrasados() {
		return 1;
	}
	
	/*METODO INTERFACE PRA SALVAR EM .CSV*/
	@Override
	public String toCSV() {
		/*dataDevolucao diferente de null?
		 * SIM: dataDev = dataDevolucao.toString()
		 * NAO: dataDev = " "*/
		String dataDev = (dataDevolucao != null) ? dataDevolucao.toString() : "";
		return id + ";" 
				  + leitor.getId() 
				  + ";" 
				  + livro.getId() 
				  + ";" 
				  + dataEmprestimo 
				  + ";" 
				  + dataDev 
				  + ";" 
				  + devolvido;
	}
	
	/*=================================>> MELHORAR ESSA PARTE*/
	public String toStringEmprestimo() {
		return id + " - " + leitor.getNome() + " - " + livro.getTitulo() + " - " + dataEmprestimo ;
	}
	
	public String toStringDevolucao() {
		return id + " - " + leitor.getNome() + " - " + livro.getTitulo() + " - " + dataEmprestimo + " - " + dataDevolucao;
	}
	
	@Override
	public String toString() {
		return id + " - " + leitor.getNome() + " - " + livro.getTitulo() + " - " + dataEmprestimo + " - " + dataDevolucao;
	}
	
}