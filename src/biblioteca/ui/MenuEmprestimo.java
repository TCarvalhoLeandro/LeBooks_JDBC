package biblioteca.ui;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import biblioteca.db.DB;
import biblioteca.db.DbException;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import biblioteca.repository.DaoFactory;
import biblioteca.repository.EmprestimoDao;
import biblioteca.repository.LeitorDao;
import biblioteca.repository.LivroDao;

public class MenuEmprestimo {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");// formato de data 
	
	//Biblioteca biblioteca = new Biblioteca();
	private EmprestimoDao emprestimoDao;
	public MenuEmprestimo(EmprestimoDao emprestimoDao) {
		this.emprestimoDao = emprestimoDao;
	}

	/*
	public MenuEmprestimo(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	*/
	
	public void exibeMenuEmprestimo() {
		try {
			while(true) {
				System.out.println("=".repeat(15) + " EMPRESTIMOS E DEVOLUCOES " + "=".repeat(15));
				
				System.out.println("1 - Registrar Empréstimo ");
				System.out.println("2 - Registrar Devolução");
				System.out.println("3 - Listar Empréstimos Ativos ");
				System.out.println("4 - Buscar Empréstimo por Id ");
				System.out.println("5 - Listar Todos Empréstimos ");
				System.out.println("0 - Voltar");
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao3 = Integer.parseInt(br.readLine());
				
				System.out.println();
				
				switch(opcao3) {// switch externo (Emprestimo e Devolucoes) case 3
				
				case 1:// insert
					
					System.out.print("ID Leitor: ");
					int idLeitor = Integer.parseInt(br.readLine());
					
					LeitorDao leitorDao = DaoFactory.createLeitorDao();// Conexao com o banco??
					Leitor leitorEmp = leitorDao.findById(idLeitor);
					
					System.out.print("ID Livro: ");
					int idLivro = Integer.parseInt(br.readLine());
					
					LivroDao livroDao = DaoFactory.createLivroDao();
					Livro livroEmp = livroDao.findById(idLivro);
					
					/*System.out.print("Data emprestimo (dd/MM/yyyy): ");
					String entrada = br.readLine();
					LocalDate dataEmprestimo = LocalDate.parse(entrada, fmt);
					//System.out.println("Data devolucao (dd/MM/yyyy): ");
					//entrada = br.readLine();
					//LocalDate dataDevolucao = LocalDate.parse(entrada, fmt);*/
					
					// Aqui o Leitor e o Livro sao associados no emprestimo
					Emprestimo emprestimo = new Emprestimo(null, leitorEmp, livroEmp,LocalDate.now(),false);
					
					emprestimoDao.insert(emprestimo);
					System.out.println("Sucesso!!");
					System.out.println();
					break;
					
				case 2:// registerReturn
					System.out.println("Digite o id do emprestimo para registrar a devolução: ");
					int idDevolucao = Integer.parseInt(br.readLine());// pega o id do emprestimo
					
					Emprestimo devolucao = emprestimoDao.findById(idDevolucao);
					emprestimoDao.registerReturn(devolucao);
					
					System.out.println("Sucesso!!");
					System.out.println();
					break;
					
				case 3:// findAtivos
					System.out.println("Lista de Emprestimos Ativos: ");
					
					List<Emprestimo> listEmp = new ArrayList<Emprestimo>();
					
					listEmp = emprestimoDao.findAtivos();
					
					for(Emprestimo obj: listEmp) {
						System.out.println(obj);
					}
					
					System.out.println();
					
					break;
					
				case 4:// findById
					System.out.println("Digite o id para buscar um emprestimo: ");
					int empById = Integer.parseInt(br.readLine());
					
					Emprestimo buscaEmp = emprestimoDao.findById(empById);
					System.out.println(buscaEmp);
					
					System.out.println();
					break;
					
				case 5:// findAll
					System.out.println("Lista de emprestimos: ");

					List<Emprestimo> listTodosEmp = new ArrayList<Emprestimo>();
					listTodosEmp = emprestimoDao.findAll();
					
					for(Emprestimo obj: listTodosEmp) {
						System.out.println(obj);
					}
					System.out.println();
					break;
				case 0:
					System.err.println("Encerrando e fechando a conexão....");
					DB.closseConnection();
					return;
				}
			}
		}
		catch(NumberFormatException e) {
			System.err.println("Valor invalido. Digite somente numeros.");
		}
		catch(DbException e) {
			System.err.println("Erro: " + e.getMessage());
		}
		catch(Exception e) {
			System.err.println("Erro inesperado.");
		}
	}
}
