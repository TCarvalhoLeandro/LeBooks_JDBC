package libraryManager.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import libraryManager.controllers.LeitorController;
import libraryManager.entities.Leitor;
import libraryManager.repositories.dao.DaoFactory;
import libraryManager.repositories.dao.LeitorDAO;
import libraryManager.services.LeitorService;

public class LeitorView {

	// Reusa o Scanner do Main
	private Scanner sc;

	public LeitorView(Scanner sc) {
		this.sc = sc;
	}

	LeitorDAO leitorDao = DaoFactory.createLeitorDao();

	LeitorService leitorService = new LeitorService(leitorDao);

	LeitorController leitorController = new LeitorController(leitorService);

	public void telaLeitor() {

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  LEITOR  ===============");
			System.out.println("1 - Cadastrar Leitor");
			System.out.println("2 - Pesquisar Leitor");
			System.out.println("3 - Listar Leitores");
			System.out.println("4 - Atualizar Leitor");
			System.out.println("5 - Excluir Leitor");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				try {
					sc.nextLine();
					leitorController.insert(telaCadastro());
					System.out.println("Leitor inserido com sucesso!");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.print("Digite o ID do leitor: ");
				Long idLeitor = sc.nextLong();
				try {
					Leitor leitor = leitorController.findById(idLeitor);
					System.out.println();
					System.out.println("--------- Leitor ---------");
					System.out.println(leitor);
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println();
					System.out.println("--------- Leitores ---------");
					List<Leitor> leitores = leitorController.findAll();
					System.out.println();
					for (Leitor obj : leitores) {
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
					System.out.println("Digite o ID do Leitor: ");
					idLeitor = sc.nextLong();
					System.out.println(leitorController.findById(idLeitor));

					System.out.println();
					sc.nextLine();
					Leitor updateLeitor = telaCadastro();
					updateLeitor.setId(idLeitor);

					// chama o metodo para atualizar
					leitorController.update(idLeitor, updateLeitor);
					System.out.println();
					System.out.println("Leitor atualizado com sucesso!");
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 5:
				System.out.println("Digite o ID do Leitor: ");
				idLeitor = sc.nextLong();

				leitorController.delete(idLeitor);
				System.out.println("Leitor deletado com sucesso!");

				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	public Leitor telaCadastro() {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("===============  LEITOR  ===============");
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("Cpf: ");
		String cpf = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Data de Nascimento [dd/mm/yyyy]: ");
		String nascimento = sc.nextLine();
		LocalDate dataNascimento = LocalDate.parse(nascimento, fmt);
		
		return new Leitor(nome, cpf, email, dataNascimento);
		
	}	
}

	










