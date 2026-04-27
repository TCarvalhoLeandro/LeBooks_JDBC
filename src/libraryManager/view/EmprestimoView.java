package libraryManager.view;

import java.util.List;
import java.util.Scanner;

import libraryManager.controllers.EmprestimoController;
import libraryManager.entities.Emprestimo;
import libraryManager.repositories.dao.DaoFactory;
import libraryManager.repositories.dao.EmprestimoDAO;
import libraryManager.services.EmprestimoService;

public class EmprestimoView {

	// Reusa o Scanner do Main
	private Scanner sc;

	public EmprestimoView(Scanner sc) {
		this.sc = sc;
	}

	// 1. A Fábrica cria o DAO já com a conexão do banco injetada
	EmprestimoDAO emprestimoDao = DaoFactory.createEmprestimoDao();

	// 2. Injetamos o DAO no Service (Regra de Negócio)
	EmprestimoService emprestimoService = new EmprestimoService(emprestimoDao);

	// 3. Injetamos o Service no Controller (Porta de entrada)
	EmprestimoController emprestimoController = new EmprestimoController(emprestimoService);

	public void telaEmprestimo() {

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  EMPRÉSTIMO  ===============");
			System.out.println("1 - Cadastrar Empréstimo");
			System.out.println("2 - Pesquisar Empréstimo");
			System.out.println("3 - Listar Empréstimos");
			System.out.println("4 - Registrar Devolução");
			System.out.println("0 - Voltar ao Menu Anterior");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				try {
					sc.nextLine();
					System.out.println("===============  EMPRÉSTIMO  ===============");
					System.out.print("ID Leitor: ");
					Long leitor_id = sc.nextLong();
					System.out.print("ID Livro: ");
					Long livro_id = sc.nextLong();
					emprestimoController.insert(leitor_id, livro_id);
					System.out.println("Empréstimo inserido com sucesso!");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.print("Digite o ID do empréstimo: ");
				Long idEmp = sc.nextLong();
				try {
					Emprestimo emp = emprestimoController.findById(idEmp);
					System.out.println();
					System.out.println("--------- Empréstimo ---------");
					System.out.println(emp);
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println();
					System.out.println("--------- Empréstimos ---------");
					List<Emprestimo> emps = emprestimoController.findAll();
					System.out.println();
					for (Emprestimo obj : emps) {
						System.out.println(obj);
					}
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					// pega o id do empréstimo a ser atualizado
					System.out.println("Digite o ID do empréstimo: ");
					idEmp = sc.nextLong();
					Emprestimo emp = emprestimoController.findById(idEmp);
					System.out.println(emp);
					System.out.println();
					// chama o metodo para atualizar
					emprestimoController.returnBook(idEmp);
					System.out.println();
					System.out.println("Empréstimo atualizado com sucesso!");
					System.out.println();
				} catch (RuntimeException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}
}
