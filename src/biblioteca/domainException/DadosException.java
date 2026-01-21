package biblioteca.domainException;

public class DadosException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DadosException(String msg) {
		super(msg);
	}
}
