package libraryManager.view;

import java.util.List;
import java.util.Scanner;

import libraryManager.controllers.AutorController;
import libraryManager.controllers.LivroController;
import libraryManager.entities.Autor;
import libraryManager.entities.Livro;
import libraryManager.repositories.dao.AutorDAO;
import libraryManager.repositories.dao.DaoFactory;
import libraryManager.repositories.dao.LivroDAO;
import libraryManager.services.AutorService;
import libraryManager.services.LivroService;

public class LivroView {

	// Reusa o Scanner do Main
	private Scanner sc;

	public LivroView(Scanner sc) {
		this.sc = sc;
	}

	// 1. A Fábrica cria o DAO já com a conexão do banco injetada
	LivroDAO livroDao = DaoFactory.createLivroDao();
	AutorDAO autorDao = DaoFactory.createAutorDao();

	// 2. Injetamos o DAO no Service (Regra de Negócio)
	LivroService livroService = new LivroService(livroDao);
	AutorService autorService = new AutorService(autorDao);

	// 3. Injetamos o Service no Controller (Porta de entrada)
	LivroController livroController = new LivroController(livroService);
	AutorController autorController = new AutorController(autorService);

	public void telaLivro() {

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  LIVRO  ===============");
			System.out.println("1 - Cadastrar Livro");
			System.out.println("2 - Pesquisar Livro");
			System.out.println("3 - Listar Livros");
			System.out.println("4 - Atualizar Livro");
			System.out.println("5 - Excluir Livro");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				try {
					sc.nextLine();
					livroController.insert(telaCadastro());
					System.out.println("Livro inserido com sucesso!");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.print("Digite o ID do livro: ");
				Long idLivro = sc.nextLong();
				try {
					Livro livro = livroController.findById(idLivro);
					System.out.println();
					System.out.println("--------- Livro ---------");
					System.out.println(livro);
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println();
					System.out.println("--------- Livros ---------");
					List<Livro> livros = livroController.findAll();
					System.out.println();
					for (Livro obj : livros) {
						System.out.println(obj);
					}
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					// pega o id do livro a ser atualizado
					System.out.println("Digite o ID do Livro: ");
					idLivro = sc.nextLong();
					System.out.println(livroController.findById(idLivro));

					System.out.println();
					sc.nextLine();
					Livro updateLivro = telaCadastro();
					updateLivro.setId(idLivro);

					// chama o metodo para atualizar
					livroController.update(idLivro, updateLivro);
					System.out.println();
					System.out.println("Livro atualizado com sucesso!");
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 5:
				System.out.println("Digite o ID do Livro: ");
				idLivro = sc.nextLong();

				livroController.delete(idLivro);
				System.out.println("Livro deletado com sucesso!");

				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	public Livro telaCadastro() {
		System.out.println("===============  LIVRO  ===============");
		System.out.print("Titulo: ");
		String titulo = sc.nextLine();

		// lista os autores
		Autor autor = new Autor();
		List<Autor> autores = autorController.findAll();
		for (Autor obj : autores) {
			System.out.println(obj);
		}
		// le o Autor da lista ou zero se não existir
		System.out.print("Id do autor ou 0(zero) para cadastrar : ");
		Long autor_id = sc.nextLong();
		// se o autor nao existir na lista inseri um novo Autor
		if (autor_id == 0) {
			sc.nextLine();
			AutorView autorView = new AutorView(sc);
			autor = autorView.telaCadastro();
			autorController.insert(autor);
			
			// lista os autores
			autores = autorController.findAll();
			for (Autor obj : autores) {
				System.out.println(obj);
			}
			
			// le o Autor da lista 
			System.out.print("Id do autor: ");
			autor_id = sc.nextLong();
			autor = autorController.findById(autor_id);
		}
		else {
			autor = autorController.findById(autor_id);
			System.out.println("Autor cadastrado");
		}

		System.out.print("Ano: ");
		Integer ano = sc.nextInt();

		return new Livro(titulo, autor, ano, true);
	}
}
