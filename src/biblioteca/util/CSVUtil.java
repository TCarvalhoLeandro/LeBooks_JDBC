package biblioteca.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import biblioteca.service.Salvar;


public class CSVUtil {
	
	/*METODO PARA SALVAR EM ARQUIVO .CSV*/
	/*RESPONSAVEL POR SALVAR OS DADOS EM ARQUIVO*/
	/*Esse método público, que não retorna nada, recebe uma
	 * lista de qualquer tipo que implemente Exportavel e um 
	 * nome de arquivo, grava tudo em CSV, e pode lançar uma 
	 * IOException se der erro no processo.*/

	/*EXPLICACAO DO METODO
	 * 
	 * public: O método pode ser chamado de fora da classe.
	 * void: O método não retorna nada.
	 * salvarCSV: Nome do método.
	 Aqui está o ponto importante.
	 
	 List<? extends Salvar> lista
	Significa:
	“aceita qualquer lista de objetos que implementem a interface Salvar (ou subclasses, se fosse uma classe).”
	Exemplo:

	List<Livro> livros;        // ok (Livro implementa Salvar)
	List<Leitor> leitores;     // ok
	List<Emprestimo> emprestimos; // ok

	Se você colocasse List<Salvar> em vez de List<? extends Salvar>, o método não aceitaria List<Livro> direto 
	— teria que converter a lista.
	Então esse ? extends Salvar é um curinga genérico (“wildcard”) que diz:
	“qualquer tipo que seja Salvar serve”.

	 * String nomeArquivo - Nome do arquivo onde o CSV será salvo.

	 * throws IOException - Informa que o método pode lançar uma exceção de entrada/saída (erro ao gravar o arquivo).
		Quem chamar o método é obrigado a tratar ou propagar essa exceção.
	*/
	public static void salvarCSV(List<? extends Salvar> lista, String nomeArquivo) throws IOException{
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))){
			for(Salvar obj: lista) {
				bw.write(obj.toCSV());
				bw.newLine();
			}
		}
	}


	/*METODO PARA LER UM ARQUIVO DE LIVROS*/
	public static List<Livro> lerLivroCSV(String nomeArquivo) throws IOException{
		
		List<Livro> livros = new ArrayList<Livro>();// o arquivo pode ter varios livros entao criamos uma lista
		
		try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){
			String line;
			while((line = br.readLine()) != null) {
				String[] fields  = line.split(";");
				int id = Integer.parseInt(fields[0]);
				String nome = fields[1];
				String autor = fields[2];
				int ano = Integer.parseInt(fields[3]);
				boolean disponivel = Boolean.parseBoolean(fields[4]);
				
				livros.add(new Livro(id, nome, autor, ano, disponivel));
			}	
		}
		return livros;
	}
	
	/*METODO PARA LER UM ARQUIVO DE LEITORES*/
	public static List<Leitor> lerLeitorCSV(String nomeArquivo) throws IOException{
		
		List<Leitor> leitores = new ArrayList<Leitor>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){
			String line;
			while((line = br.readLine()) != null) {
				String[] fields = line.split(";");
				int id = Integer.parseInt(fields[0]);
				String nome = fields[1];
				String cpf = fields[2];
				String email = fields[3];
				
				leitores.add(new Leitor(id, nome, cpf, email));
			}
			 
		}
		return leitores;
	}
	

	/*METODO PARA LER UM ARQUIVO DE EMPRESTIMO*/  //estudar....
	public static List<Emprestimo> lerEmprestimoCSV(String nomeArquivo,List<Leitor> leitores, 
            List<Livro> livros) throws IOException{
		
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){
			String line;
			while((line = br.readLine()) != null) {
				String[] fields = line.split(";");
				int id = Integer.parseInt(fields[0]);
				int idLeitor = Integer.parseInt(fields[1]);
				int idLivro = Integer.parseInt(fields[2]);
				LocalDate dataEmprestimo = LocalDate.parse(fields[3]);
				LocalDate dataDevolucao;
				if(!fields[4].isEmpty()) {
					dataDevolucao = LocalDate.parse(fields[4]);
				}
				else { 
					dataDevolucao = null;
				}
				boolean devolvido = Boolean.parseBoolean(fields[5]);
				
				 // buscar objetos já carregados
	            Leitor leitor = leitores.stream().filter(l -> l.getId() == idLeitor).findFirst().orElse(null);
	            Livro livro = livros.stream().filter(l -> l.getId() == idLivro).findFirst().orElse(null);
	            
	            if(livro != null && dataDevolucao != null) {
	                livro.setDisponivel(true);
	            } else if(livro != null) {
	                livro.setDisponivel(false);
	            }
				
				emprestimos.add(new Emprestimo(id, leitor, livro, dataEmprestimo, dataDevolucao, devolvido));
			}
		}
		return emprestimos;
	}

}



