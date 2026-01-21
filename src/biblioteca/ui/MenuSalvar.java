package biblioteca.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import biblioteca.core.Biblioteca;
import biblioteca.domainException.DadosException;
import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import biblioteca.util.CSVUtil;

public class MenuSalvar {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Biblioteca biblioteca = new Biblioteca();
	
	public MenuSalvar(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public void exibeMenuSalvar() {
		try {
			while(true) {//O while(true) mantém o usuário dentro do menu de salvar e carregar.
				System.out.println("=".repeat(15) + " SALVAR E CARREGAR " + "=".repeat(15));
				
				System.out.println("1 - Salvar ");
				System.out.println("2 - Carregar ");
				System.out.println("0 - Voltar");
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao4 = Integer.parseInt(br.readLine());
				System.out.println();
				switch (opcao4) {// switch interno (menu salvar e carregar)
				case 1:
					/*salvar todas as listas*/ //ESTUDAR.....
					  try {
						  	if(Livro.getContador() > 0) {// só vai escrever no arquivo se for instanciado
						  		CSVUtil.salvarCSV(biblioteca.getLivros(), "livros.csv");
						  		Livro.setContador(0);// salvou no arquivo, zera o contador
						  	}
					        if(Leitor.getContador() > 0) {// só vai escrever no arquivo se for instanciado
					        	CSVUtil.salvarCSV(biblioteca.getLeitores(), "leitores.csv");
					        	Leitor.setContador(0);// salvou no arquivo, zera o contador
					        }
					        if(Emprestimo.getContador() > 0) {// só vai escrever no arquivo se for instanciado
					        	CSVUtil.salvarCSV(biblioteca.getEmprestimos(), "emprestimos.csv");
					        	Emprestimo.setContador(0);// salvou no arquivo, zera o contador
					        }
					        System.out.println("Dados salvos com sucesso!");
					  }
					  catch (IOException e) {
					       System.out.println("Erro ao salvar: " + e.getMessage());
					  }
					
					break; //case 1
				case 2:
					List<Livro> livros = CSVUtil.lerLivroCSV("/home/leandro/eclipse-workspace/Biblioteca/livros.csv");
					for(Livro livro: livros) {
						System.out.println(livro);
					}
					System.out.println();
					System.out.println("=".repeat(25));
					System.out.println();
					
					List<Leitor> leitores = CSVUtil.lerLeitorCSV("/home/leandro/eclipse-workspace/Biblioteca/leitores.csv");
					for(Leitor leitor: leitores) {
						System.out.println(leitor);
					}
					System.out.println();
					System.out.println("=".repeat(25));
					System.out.println();
					List<Emprestimo> emprestimos = CSVUtil.lerEmprestimoCSV("/home/leandro/eclipse-workspace/Biblioteca/emprestimos.csv",
							leitores, livros);
					for(Emprestimo emprestimo: emprestimos) {
						System.out.println(emprestimo.toStringEmprestimo());// mostra o emprestimo sem a data de devolucao
					}
					System.out.println();
					System.out.println("=".repeat(25));
					System.out.println();
					break;
				
				default:
					if(opcao4 != 0) System.out.println("Digito invalido.");
				}//switch interno(menu salvar e carregar)
				
				//O if (opcao2 == 0) break; faz sair do loop (e voltar pro menu principal).
				if(opcao4 == 0) {
					System.err.println("Voltando ao menu anterior..");
					break;
				}
			}
		}
		catch(DadosException e) {
			System.out.println(e.getMessage());
		}
		catch(NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
