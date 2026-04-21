package libraryManager.main;

import java.util.Scanner;

import libraryManager.view.AutorView;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		AutorView autorView = new AutorView(sc);
		
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
				
				break;
			case 3:
			
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
