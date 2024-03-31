package bibliotecaSpark.exception;


public class ClienteInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClienteInvalidoException(String mensagem) {
		super("[ERRO] " + mensagem);
	}
	
}
