package biblioteca.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import biblioteca.core.Biblioteca;
import biblioteca.domainException.DadosException;
import biblioteca.entities.Leitor;

public class MenuLeitor {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	Biblioteca biblioteca = new Biblioteca();

	public MenuLeitor(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public void exibeMenuLeitor() {
		try {
			while (true) {// O while(true) mantém o usuário dentro do menu de leitores.
				System.out.println("=".repeat(15) + " LEITORES " + "=".repeat(15));
				
				System.out.println("1 - Cadastrar ");
				System.out.println("2 - Listar ");
				System.out.println("3 - Remover ");
				System.out.println("4 - Buscar ");
				System.out.println("0 - Voltar");
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao2 = Integer.parseInt(br.readLine());
				System.out.println();
				switch (opcao2) {// switch interno (menu leitores)
				case 1:
					System.out.print("ID: ");
					int id = Integer.parseInt(br.readLine());
					System.out.print("Nome: ");
					String nome = br.readLine();
					System.out.print("CPF: ");
					String cpf = br.readLine();
					System.out.print("Email: ");
					String email = br.readLine();
					Leitor leitor = new Leitor(id, nome, cpf, email);
					biblioteca.cadastrarLeitor(leitor);
					System.out.println();
					System.out.println("Leitor cadastrado com sucesso.");
					System.out.println();
					break; // case 1
				case 2:
					System.out.println("Lista de leitores: ");
					System.out.println();
					biblioteca.listarLeitores();
					System.out.println();
					break;// case 2
				case 3:
					System.out.print("Remover leitor, ID: ");
					int remove = Integer.parseInt(br.readLine());
					biblioteca.removerLeitor(remove);
					break;// case 3
				case 4:
					System.out.print("Buscar leitor, ID: ");
					int IDBusca = Integer.parseInt(br.readLine());
					System.out.println(biblioteca.buscarLeitor(IDBusca));
					System.out.println();
					break;// case 4
				default:
					if (opcao2 != 0)
						System.out.println("Digito invalido.");
				}// switch interno
					// O if (opcao2 == 0) break; faz sair do loop (e voltar pro menu principal).
				if (opcao2 == 0) {
					System.err.println("Voltando ao menu anterior..");
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
