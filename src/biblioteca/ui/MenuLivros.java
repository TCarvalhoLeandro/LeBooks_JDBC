package biblioteca.ui;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;


import biblioteca.db.DbException;
import biblioteca.domainException.DadosException;
import biblioteca.entities.Livro;
import biblioteca.repository.LivroDao;


public class MenuLivros {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/*1. Alteração das Dependências
    Em vez de receber a classe Biblioteca no construtor, agora você deve receber a 
    interface LivroDao. Isso é o que chamamos de Injeção de Dependência.*/
	
	private LivroDao livroDao; // Referencia para a interface DAO
	
	public MenuLivros(LivroDao livroDao) {
		this.livroDao = livroDao;
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
				System.out.println("5 - Atualizar Dados");
				System.out.println("0 - Voltar");
				
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao1 = Integer.parseInt(br.readLine());
				
				System.out.println();
				switch (opcao1) {// switch menu livros
				case 1:
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
	
					Livro livro = new Livro(null, titulo, autor, ano, true);
	
					livroDao.insert(livro);
	
					System.out.println();
					System.out.println("Livro cadastrado com sucesso.");
					System.out.println();
					break;
					
				case 2:
					System.out.println("Lista de Livros: ");
					System.out.println();
					
					List<Livro> list = livroDao.findAll();
					
					if(list.isEmpty()) {
						throw new DadosException("Lista vazia.");
					}
					else {
						for(Livro obj: list) {
							System.out.println(obj);
						}
					}
					System.out.println();
					break;
					
				case 3:
					System.out.print("Digite o id para remover: ");
					int remove = Integer.parseInt(br.readLine());
					
					livroDao.deleteById(remove);
					System.out.println();
					break;
					
				case 4:
					System.out.print("Digite o id do livro para buscar: ");
					int busca = Integer.parseInt(br.readLine());
	
					Livro livroById = livroDao.findById(busca);
					System.out.println(livroById);
					
					System.out.println();
					break;
				
				case 5:
					System.out.println("Digite o id do livro para atualizar: ");
					int idatualizar = Integer.parseInt(br.readLine());
					
					Livro livroAtualizar = livroDao.findById(idatualizar);
					System.out.println(livroAtualizar);
					
					System.out.println();
					System.out.println("Qual campo deseja atualizar: ");
					System.out.println("1 - Título ");
					System.out.println("2 - Autor ");
					System.out.println("3 - Ano ");
					int opcaoAtualizar = Integer.parseInt(br.readLine());
					switch(opcaoAtualizar) {
					case 1:
						System.out.println("Título atual: " + livroAtualizar.getTitulo());
						System.out.print("Digite o novo título: ");
						String novoTitulo = br.readLine();
						livroAtualizar.setTitulo(novoTitulo);
						break;
					
					case 2:
						System.out.println("Autor atual: " + livroAtualizar.getAutor());
						System.out.print("Digite o novo autor: ");
						String novoAutor = br.readLine();
						livroAtualizar.setAutor(novoAutor);
						break;
						
					case 3:
						System.out.println("Ano Atual: " + livroAtualizar.getAno());
						System.out.print("Digite o novo ano: ");
						Integer novoAno = Integer.parseInt(br.readLine());
						livroAtualizar.setAno(novoAno);
						break;
						
					default:
						System.out.println("Opção inválida. Nenhuma alteração local feita.");
			            return; // Sai do case sem executar o update abaixo
					}
					
					livroDao.update(livroAtualizar);
					System.out.println("Banco de dados atualizado com sucesso!");
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
		
		catch(NumberFormatException e) {
			System.err.println("Valor invalido. Digite somente numeros.");
		}
		catch(DbException e) {
			System.err.println("Erro no banco de dados: " + e.getMessage());
		}
		catch(Exception e) {
			System.err.println("Erro inesperado.");
		}
		
		
	}
}
