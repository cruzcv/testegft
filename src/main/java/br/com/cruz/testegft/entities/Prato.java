package br.com.cruz.testegft.entities;

/**
 * The Class Prato.
 */
public class Prato {
 
	/** The descricao prato. */
	private String descricaoPrato;
	
	/** The tipo prato. */
	private Integer idTipoPrato;
	
	/** The periodo. */
	private String periodo;
	
	/** The quantidade. */
	private Integer quantidade;
	
	
	/**
	 * Instantiates a new prato.
	 *
	 * @param descricaoPrato the descricao prato
	 * @param idTipoPrato the id tipo prato
	 * @param periodo the periodo
	 */
	public Prato(String descricaoPrato, Integer idTipoPrato, String periodo) {
		this.descricaoPrato = descricaoPrato;
		this.idTipoPrato = idTipoPrato;
		this.periodo = periodo;
		this.quantidade = 1;
	}
	
	/**
	 * Gets the descricao prato.
	 *
	 * @return the descricao prato
	 */
	public String getDescricaoPrato() {
		return descricaoPrato;
	}

	/**
	 * Sets the descricao prato.
	 *
	 * @param descricaoPrato the new descricao prato
	 */
	public void setDescricaoPrato(String descricaoPrato) {
		this.descricaoPrato = descricaoPrato;
	}

	
	/**
	 * Gets the id tipo prato.
	 *
	 * @return the id tipo prato
	 */
	public Integer getIdTipoPrato() {
		return idTipoPrato;
	}

	/**
	 * Sets the id tipo prato.
	 *
	 * @param idTipoPrato the new id tipo prato
	 */
	public void setIdTipoPrato(Integer idTipoPrato) {
		this.idTipoPrato = idTipoPrato;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * Sets the quantidade.
	 *
	 * @param quantidade the new quantidade
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
