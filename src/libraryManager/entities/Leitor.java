package libraryManager.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

//JDBC
public class Leitor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;
	
	public Leitor() {
		
	}

	public Leitor(String nome, String cpf, String email, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public Leitor(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leitor other = (Leitor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder sb = new StringBuilder();
		
		sb.append(id);
		sb.append(" - ");
		sb.append(nome);
		sb.append(" - ");
		sb.append(cpf);
		sb.append(" - ");
		sb.append(email);
		sb.append(" - ");
		String dataFmt = dataNascimento.format(fmt);
		sb.append(dataFmt);
		
		return sb.toString();
	}
	
	
}
