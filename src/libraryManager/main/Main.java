package libraryManager.main;

import java.util.Scanner;

import libraryManager.view.AutorView;
import libraryManager.view.EmprestimoView;
import libraryManager.view.LeitorView;
import libraryManager.view.LivroView;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		AutorView autorView = new AutorView(sc);
		LivroView livroView = new LivroView(sc);
		LeitorView leitorView = new LeitorView(sc);
		EmprestimoView empView = new EmprestimoView(sc);
		
		int opcao = -1;
		while (opcao != 0) {
			System.out.println("===============  BIBLIOTECA  ===============");
			System.out.println("1 - Gerenciar Autor");
			System.out.println("2 - Gerenciar Livro");
			System.out.println("3 - Gerenciar Leitor");
			System.out.println("4 - Gerenciar Empréstimo");
			System.out.println("0 - Sair");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				sc.nextLine();
				autorView.telaAutor();
				break;
			case 2:
				sc.nextLine();
				livroView.telaLivro();
				break;
			case 3:
				leitorView.telaLeitor();
				break;
			case 4:
				empView.telaEmprestimo();
				break;
			case 0:
				System.out.println("Encerrando o sistema...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
		sc.close();
	}
}
