package biblioteca.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import biblioteca.core.Biblioteca;
import biblioteca.domainException.DadosException;
import biblioteca.entities.Livro;
import biblioteca.util.CSVUtil;

public class MenuLivros {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Biblioteca biblioteca;// Declaramos a referencia

	// O construtor recebe a instância de Biblioteca
	public MenuLivros(Biblioteca biblioteca) {
		// Inicializa a dependência
		this.biblioteca = biblioteca;
	}
	
	// O código de interação deve estar dentro de um método
	public void exibeMenuLivro() {
		try {
			while (true) {// O while(true) mantém o usuário dentro do menu de livros.
				System.out.println("=".repeat(15) + " LIVROS " + "=".repeat(15));
				
				System.out.println("1 - Cadastrar ");
				System.out.println("2 - Listar");
				System.out.println("3 - Remover");
				System.out.println("4 - Buscar");
				System.out.println("5 - Salvar");
				System.out.println("0 - Voltar");
				
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao1 = Integer.parseInt(br.readLine());
				
				System.out.println();
				switch (opcao1) {// switch menu livros
				case 1:
					System.out.print("ID: ");
					int id = Integer.parseInt(br.readLine());
					
					System.out.print("Titulo: ");
					String titulo = br.readLine();
					
					System.out.print("Autor: ");
					String autor = br.readLine();
					
					System.out.print("Ano de publicação: ");
					int ano = Integer.parseInt(br.readLine());
					if(ano < 1800 || ano > LocalDate.now().getYear()) {
						//Apenas por motivos didaticos, pq um livro pode ter sido publicado antes de 1800 facilmente
						throw new DadosException("Ano invalido. Periodo permitido 1800 a " + LocalDate.now().getYear());
					}
					
					boolean disponivel = true;
	
					Livro livro = new Livro(id, titulo, autor, ano, disponivel);
	
					biblioteca.cadastrarLivro(livro);
	
					System.out.println();
					System.out.println("Livro cadastrado com sucesso.");
					System.out.println();
					break;
				case 2:
					System.out.println("Lista de Livros: ");
					System.out.println();
					
					if(biblioteca.getLivros().isEmpty()) {
						throw new DadosException("Lista vazia.");
					}
					biblioteca.listarLivro();
					System.out.println();
					break;
				case 3:
					System.out.print("Remover livro, titulo: ");
					String remove = br.readLine();
					
					biblioteca.removerLivro(remove);
					System.out.println();
					break;
				case 4:
					System.out.print("Busca ID: ");
					int busca = Integer.parseInt(br.readLine());
	
					System.out.println(biblioteca.buscarLivro(busca));
					break;
				case 5: 
					try {
						if(Livro.getContador() > 0) {// só vai escrever no arquivo se for instanciado
					  		CSVUtil.salvarCSV(biblioteca.getLivros(), "livros.csv");
					  		Livro.setContador(0);// salvou no arquivo, zera o contador
					  	}
					}
					catch(IOException e) {
						 System.out.println("Erro ao salvar: " + e.getMessage());
					}
					break;
				default:
					if (opcao1 != 0)
						System.out.println("Digito invalido.");
					break;
				}// fim switch 
	
				// O if (opcao1 == 0) break; faz sair do loop (e voltar pro menu principal).
				if (opcao1 == 0) {
					System.err.println("Voltando ao menu anterior.");
					break;
				}
			} // fim while
		}// fim try
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

/*Erros que cometi:
 * 
 * INSTANCIEI BIBLIOTECA DIRETO NO CODIGO ============================================================
 
Em classes como MenuLivros, que gerenciam a interação do usuário e 
precisam acessar serviços centrais do aplicativo, é melhor usar um 
construtor para obter a instância de Biblioteca.

Instanciar a Biblioteca diretamente na declaração 
(Biblioteca biblioteca = new Biblioteca();) é a abordagem mais simples, 
mas a abordagem usando o construtor (chamada de Injeção de Dependência por Construtor) 
é a melhor prática para projetos em Java.
 
Por Que Usar um Construtor (Injeção de Dependência)
Usar um construtor oferece vantagens significativas:

Flexibilidade e Testabilidade (Melhor Prática):

Se a classe Biblioteca for complexa e difícil de instanciar, ou se for uma interface com
várias implementações, o construtor permite que você passe a implementação correta de fora, 
sem que MenuLivros precise saber os detalhes.

Para testes unitários, você pode facilmente passar um objeto Biblioteca mock (falso) para 
o MenuLivros e testar a classe de menu isoladamente.

Transparência de Dependências:

O construtor deixa claro que a classe MenuLivros depende de uma instância de Biblioteca 
para funcionar. Qualquer desenvolvedor vendo o construtor saberá imediatamente do que a 
classe precisa.

NÃO FIZ UM METODO PARA O MENU ====================================================================

O erro principal neste código está na estrutura básica da classe Java. Você colocou o loop 
while(true) e todo o código de menu diretamente no corpo da classe MenuLivros, fora de 
qualquer método, construtor, ou bloco de inicialização.

Você deve encapsular toda a lógica do menu (do while(true) até o final do if (opcao1 == 0) break;) 
dentro de um método, que chamei de exibirMenu() no exemplo abaixo.

A classe que gerencia a aplicação principal (onde você instancia MenuLivros) será responsável 
por chamar este método.
 * */

 