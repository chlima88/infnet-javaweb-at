package bibliotecaSpark.exception;


public class TamanhoInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public TamanhoInvalidoException(String mensagem) {
		super("[ERRO] " + mensagem);
	}
	
}
