package bibliotecaSpark.exception;


public class DuracaoAtendimentoException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuracaoAtendimentoException(String mensagem) {
		super("[ERRO] " + mensagem);
	}
	
}
