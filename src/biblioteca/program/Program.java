package biblioteca.program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import biblioteca.core.Biblioteca;
import biblioteca.ui.MenuEmprestimo;
import biblioteca.ui.MenuLeitor;
import biblioteca.ui.MenuLivros;
import biblioteca.ui.MenuSalvar;

/*FALTA FAZER


 * criar uma classe de validacao pra validar cpf e email
 * criar metodo para calcular dias atrasados
 * criar metodo para calcular a multa por atraso
 * criar metodo para criar um historico
 * adicionar no menu de gerenciamneto de livros, leitores e emprestimos a opcao de salvar*/

public class Program {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. Cria UMA ÚNICA INSTÂNCIA da Biblioteca (repositório central de dados)
		Biblioteca bibliotecaPrincipal = new Biblioteca();
		
		// 2. Instancia todos os menus, INJETANDO a mesma instância da Biblioteca
		// Menu para gerir os Livros
		MenuLivros menuLivros = new MenuLivros(bibliotecaPrincipal);
		// Menu para gerir os Leitores
		MenuLeitor menuLeitor = new MenuLeitor(bibliotecaPrincipal);
		// Menu para gerir os Emprestimos
		MenuEmprestimo menuEmprestimo = new MenuEmprestimo(bibliotecaPrincipal);
		// Menu para gerir os salvamentos
		MenuSalvar menuSalvar = new MenuSalvar(bibliotecaPrincipal);

		/* Os arquivos são carregados e só podem ser modificados atraves dos menus */
		bibliotecaPrincipal.carregarArquivo();// carrega os arquivos .csv

		/* Loop de interação com o usuario */
		while (true) {
			try {
				System.out.println("=".repeat(15) + " BIBLIOTECA - GERENCIAMENTO DE DADOS " + "=".repeat(15));
				
				System.out.println("1 - Livros");
				System.out.println("2 - Leitores");
				System.out.println("3 - Emprestimos e Devolucoes");
				System.out.println("4 - Salvar e Carregar");
				System.out.println("0 - Sair");
				System.out.println();
				System.out.print("Digite a opção desejada: ");
				
				int opcao = Integer.parseInt(br.readLine());

				switch (opcao) {// switch externo (menu principal)
				case 1:
					menuLivros.exibeMenuLivro();
					break;// switch externo (Gerenciar Livros) case 1
				case 2:
					menuLeitor.exibeMenuLeitor();
					break;// switch externo (Gerenciar Leitor) case 2
				case 3:
					menuEmprestimo.exibeMenuEmprestimo();
					break;// switch externo (Emprestimo e Devolucoes) case 3
				case 4:
					menuSalvar.exibeMenuSalvar();
					break;// switch externo (salvar e carregar) case 4
				default:
					if (opcao != 0)
						System.out.println("Digito invalido.");
					break;
				}// switch externo (menu principal)
					// O if (opcao == 0) break; faz encerrar o programa.
				if (opcao == 0) {
					System.err.println("Programa encerrado...");
					break;
				}
			} // fim try
			/*O essencial é que o código que pode falhar (br.readLine() e Integer.parseInt()) 
			 * esteja DENTRO do try para que os blocos catch possam agir.*/
			catch (IOException e) {
			    // Captura erros de leitura/escrita do BufferedReader
			    System.err.println("Erro de leitura. " + e.getMessage());
			}
			catch (NumberFormatException e) {
				//Captura o erro se o usuario digitar algo que nao e um numero (como letras)
				System.err.println("Valor invalido. Digite apenas numeros.");
			} 
			catch (Exception e) {
				//Captura qualquer outra excecao nao tratadas acima
				System.err.println("Erro inesperado. " + e.getMessage());
			}
		}
	}
}
