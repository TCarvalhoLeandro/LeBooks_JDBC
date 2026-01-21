package biblioteca.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import biblioteca.core.Biblioteca;
import biblioteca.domainException.DadosException;
import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;

public class MenuEmprestimo {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");// formato de data 
	
	Biblioteca biblioteca = new Biblioteca();
	
	public MenuEmprestimo(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public void exibeMenuEmprestimo() {
		try {
			while(true) {
				System.out.println("=".repeat(15) + " EMPRESTIMOS E DEVOLUCOES " + "=".repeat(15));
				
				System.out.println("1 - Emprestimo ");
				System.out.println("2 - Listar emprestimo por leitor");
				System.out.println("3 - Listar emprestimo por livro ");
				System.out.println("4 - Lista todos os emprestimos ");
				System.out.println("5 - Devolucao ");
				System.out.println("0 - Voltar");
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao3 = Integer.parseInt(br.readLine());
				System.out.println();
				switch(opcao3) {// switch externo (Emprestimo e Devolucoes) case 3
				case 1:
					System.out.print("ID: ");
					int id = Integer.parseInt(br.readLine());
					
					System.out.print("ID Leitor: ");
					int idLeitor = Integer.parseInt(br.readLine());
					Leitor leitorEmp = biblioteca.buscarLeitor(idLeitor);
					
					System.out.print("ID Livro: ");
					int idLivro = Integer.parseInt(br.readLine());
					Livro livroEmp = biblioteca.buscarLivro(idLivro);
					
					System.out.print("Data emprestimo (dd/MM/yyyy): ");
					String entrada = br.readLine();
					LocalDate dataEmprestimo = LocalDate.parse(entrada, fmt);
					//System.out.println("Data devolucao (dd/MM/yyyy): ");
					//entrada = br.readLine();
					//LocalDate dataDevolucao = LocalDate.parse(entrada, fmt);
					
					boolean disponivel = false;
					
					// Aqui o Leitor e o Livro sao associados no emprestimo
					Emprestimo emprestimo = new Emprestimo(id, leitorEmp, livroEmp, dataEmprestimo, disponivel);
					System.out.println();
					
					biblioteca.emprestimo(emprestimo);
					System.out.println();
					break;
				case 2:
					System.out.println("Lista de emprestimos por leitor: ");
					System.out.println("Leitor ID:  ");
					idLeitor = Integer.parseInt(br.readLine());// pega o id do leitor
					
					// chama o metodo passando o id do leitor
					List<Livro> livrosEmprestados = biblioteca.listarLivrosDoLeitor(idLeitor);
					
					if(livrosEmprestados.isEmpty()) {
						System.out.println("Nenhum livro encontado para o leitor " + idLeitor);
					}
					else {
						System.out.println("Livros emprestados: ");
						for(Livro livro: livrosEmprestados) {
							System.out.println(livro);
						}
					}
					break;
				case 3:
					System.out.println("Lista de emprestimo de livro: ");
					System.out.println("Livro ID:  ");
					idLivro = Integer.parseInt(br.readLine());// pega o id do livro
					
					// chama o metodo passando o id do livro
					Leitor LeitorComLivro = biblioteca.listarLeitorComLivro(idLivro);
					
					if(LeitorComLivro == null) {
						System.out.println("Nenhum emprestimo encontado para o livro " + idLivro);
					}
					else {
						System.out.println("O livro " + idLivro + " esta com: ");
						System.out.println(LeitorComLivro);
					}
					
					break;
				case 4:
					System.out.println("Lista todos os emprestimos ativos: ");
					System.out.println();
					biblioteca.listaEmprestimo();
					System.out.println();
					break;
				case 5:
					System.out.println();
					System.out.println("Devolucao: ");
					System.out.println();
					System.out.print("Titulo: ");
					String devolucao = br.readLine();
					biblioteca.devolucao(devolucao);
					System.out.println();
					break;
				default:
					if(opcao3 != 0) System.out.println("Digito invalido.");
				}//switch interno
				//O if (opcao3 == 0) break; faz sair do loop (e voltar pro menu principal).
				if(opcao3 == 0) {
					System.err.println("Voltando ao menu anterior...");
					break;
				}
			}
		}
		catch(IOException e) {
			System.err.println("Erro de leitura." + e.getMessage());
		}
		catch(NumberFormatException e) {
			System.err.println("Valor invalido. Digite somente numeros.");
		}
		catch(DadosException e) {
			System.err.println("Erro: " + e.getMessage());
		}
		catch(Exception e) {
			System.err.println("Erro inesperado.");
		}
	}
}
