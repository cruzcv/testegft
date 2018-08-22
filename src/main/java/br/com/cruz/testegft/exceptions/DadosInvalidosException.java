package br.com.cruz.testegft.exceptions;

/**
 * The Class DadosInvalidosException.
 */
public class DadosInvalidosException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new dados invalidos exception.
	 *
	 * @param msg the msg
	 */
	public DadosInvalidosException(String msg){
        super(msg);
    }

}
