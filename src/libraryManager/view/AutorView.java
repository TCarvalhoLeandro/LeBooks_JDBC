package libraryManager.view;

import java.util.List;
import java.util.Scanner;

import libraryManager.controllers.AutorController;
import libraryManager.entities.Autor;
import libraryManager.repositories.dao.AutorDAO;
import libraryManager.repositories.dao.DaoFactory;
import libraryManager.services.AutorService;

public class AutorView {
	
	// Reusa o Scanner do Main
	private Scanner sc;
	
	public AutorView(Scanner sc) {
		this.sc = sc;
	}

	public void telaAutor() {
		// 1. A Fábrica cria o DAO já com a conexão do banco injetada
		AutorDAO autorDao = DaoFactory.createAutorDao();
		// 2. Injetamos o DAO no Service (Regra de Negócio)
		AutorService autorService = new AutorService(autorDao);
		// 3. Injetamos o Service no Controller (Porta de entrada)
		AutorController autorController = new AutorController(autorService);

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  AUTOR  ===============");
			System.out.println("1 - Cadastrar Autor");
			System.out.println("2 - Pesquisar Autor");
			System.out.println("3 - Listar Autores");
			System.out.println("4 - Atualizar Autor");
			//System.out.println("5 - Excluir Autor");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				try {
					sc.nextLine();
					autorController.insert(telaCadastro());
					System.out.println("Autor inserido com sucesso!");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2: 
				System.out.print("Digite o ID do Autor: ");
				Long idAutor = sc.nextLong();
				try {
					Autor autor = autorController.findById(idAutor);
					System.out.println();
					System.out.println("--------- Autor ---------");
					System.out.println(autor);
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println();
					System.out.println("--------- Autores ---------");
					List<Autor> autores = autorController.findAll();
					System.out.println();
					for(Autor obj: autores) {
						System.out.println(obj);
					}
					System.out.println();
				}
				catch(RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					// pega o id do livro a ser atualizado
					System.out.println("Digite o ID do Autor: ");
					idAutor = sc.nextLong();
					System.out.println(autorController.findById(idAutor));
					
					System.out.println();
					sc.nextLine();
					Autor updateLivro = telaCadastro();
					updateLivro.setId(idAutor);
					
					// chama o metodo para atualizar
					autorController.update(idAutor, updateLivro);
					System.out.println();
					System.out.println("Autor atualizado com sucesso!");
					System.out.println();
				}
				catch(RuntimeException e) {
					System.out.println("Erro: " +  e.getMessage());
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}
	
	public Autor telaCadastro() {
		System.out.println("===============  AUTOR  ===============");
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("Nacionalidade: ");
		String nacionalidade = sc.nextLine();
		
		return new Autor(nome, nacionalidade);
		
	}
}
