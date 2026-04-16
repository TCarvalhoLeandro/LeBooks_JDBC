package biblioteca.ui;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.List;

import biblioteca.db.DbException;

import biblioteca.entities.Leitor;
import biblioteca.repository.LeitorDao;

public class MenuLeitor {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	//1 - Fazer a injeção de depencia com a interface LeitorDao 
	private LeitorDao leitorDao;
	
	public MenuLeitor(LeitorDao leitorDao) {
		this.leitorDao = leitorDao;
	}

	public void exibeMenuLeitor() {
		try {
			while (true) {// O while(true) mantém o usuário dentro do menu de leitores.
				System.out.println("=".repeat(15) + " LEITORES " + "=".repeat(15));
				
				System.out.println("1 - Cadastrar ");
				System.out.println("2 - Listar ");
				System.out.println("3 - Remover ");
				System.out.println("4 - Buscar ");
				System.out.println("5 - Atualizar Dados ");
				System.out.println("0 - Voltar");
				System.out.println();
				System.out.print("Digite a opcao desejada: ");
				int opcao2 = Integer.parseInt(br.readLine());
				System.out.println();
				switch (opcao2) {// switch interno (menu leitores)
				
				
				
				case 1:
					// Retirado a leitura do Id do Leitor que vai ser atribuida autmaticamente pelo banco de dados
					System.out.print("Nome: ");
					String nome = br.readLine();
					System.out.print("CPF: ");
					String cpf = br.readLine();
					System.out.print("Email: ");
					String email = br.readLine();
					
					Leitor leitor = new Leitor(null, nome, cpf, email);//  Instanciar o leitor com null no id 
					
					leitorDao.insert(leitor); // Chamar o metodo insert de leitorDao
					
					System.out.println();
					System.out.println("Leitor cadastrado com sucesso.");
					System.out.println();
					break; // case 1
					
				case 2:
					System.out.println("Lista de leitores: ");
					System.out.println();
					
					List<Leitor> list = leitorDao.findAll();// Instanciar uma lista que recebe o metodo findAll do leitor Dao
					
					if(list.isEmpty()) {
						throw new DbException("Lista vazia");
					}
					else {
						for(Leitor obj: list) {
							System.out.println(obj);
						}
					}
					System.out.println();
					break;// case 2
					
					
				case 3:
					System.out.print("Digite o id para remover: ");
					int remove = Integer.parseInt(br.readLine());
					
					leitorDao.deleteById(remove);
					
					break;// case 3
				case 4:
					System.out.print("Digite o id do leitor para buscar:");
					int IDBusca = Integer.parseInt(br.readLine());
					
					Leitor leitorById = leitorDao.findById(IDBusca);
					
					System.out.println(leitorById);
					
					System.out.println();
					break;// case 4
					
				case 5:
					System.out.println("Digite o id do leitor para atualizar: ");
					int idatualizar = Integer.parseInt(br.readLine());
					
					Leitor leitorAtualizar = leitorDao.findById(idatualizar);
					System.out.println(leitorAtualizar);
					
					System.out.println();
					System.out.println("Qual campo deseja atualizar: ");
					System.out.println("1 - Nome ");
					System.out.println("2 - Cpf ");
					System.out.println("3 - Email ");
					int opcaoAtualizar = Integer.parseInt(br.readLine());
					switch(opcaoAtualizar) {
					case 1:
						System.out.println("Nome atual: " + leitorAtualizar.getNome());
						System.out.print("Digite o novo nome: ");
						String novoNome = br.readLine();
						leitorAtualizar.setNome(novoNome);
						break;
					
					case 2:
						System.out.println("CPF atual: " + leitorAtualizar.getCpf());
						System.out.print("Digite o novo CPF: ");
						String novoCPF = br.readLine();
						leitorAtualizar.setCpf(novoCPF);
						break;
						
					case 3:
						System.out.println("Email Atual: " + leitorAtualizar.getEmail());
						System.out.print("Digite o novo email: ");
						String novoEmail = br.readLine();
						leitorAtualizar.setEmail(novoEmail);
						break;
						
					default:
						System.out.println("Opção inválida. Nenhuma alteração local feita.");
			            return; // Sai do case sem executar o update abaixo
					}
					
					leitorDao.update(leitorAtualizar);
					System.out.println("Banco de dados atualizado com sucesso!");
				    break;
					
				default:
					if (opcao2 != 0)
						System.out.println("Digito invalido.");
				}// switch interno
					// O if (opcao2 == 0) break; faz sair do loop (e voltar pro menu principal).
				if (opcao2 == 0) {
					System.err.println("Voltando ao menu anterior..");
					break;
				}
			}
		} 
		
		catch(NumberFormatException e) {
			System.err.println("Valor invalido. Digite somente numeros.");
		}
		catch (DbException e) {
			System.err.println("Erro no banco de dados: " + e.getMessage());
		}
		catch(Exception e) {
			System.err.println("Erro inesperado.");
		}
	}
}
