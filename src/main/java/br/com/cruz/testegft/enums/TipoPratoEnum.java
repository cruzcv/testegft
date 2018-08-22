package br.com.cruz.testegft.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.cruz.testegft.constants.StringConstants;
import br.com.cruz.testegft.utils.StringUtil;

/**
 * The Enum TipoPratoEnum.
 */
public enum TipoPratoEnum {
	
	/** The entrada manha. */
	ENTRADA_MANHA(1, StringConstants.PERIODO_MANHA, "ovos", false),
	
	/** The acompanhamento manha. */
	ACOMPANHAMENTO_MANHA(2, StringConstants.PERIODO_MANHA, "torrada", false),
	
	/** The bebida manha. */
	BEBIDA_MANHA(3, StringConstants.PERIODO_MANHA, "café", true),
	
	/** The entrada noite. */
	ENTRADA_NOITE(1, StringConstants.PERIODO_NOITE, "carne", false),
	
	/** The acompanhamento noite. */
	ACOMPANHAMENTO_NOITE(2, StringConstants.PERIODO_NOITE, "batata", true),
	
	/** The bebida noite. */
	BEBIDA_NOITE(3, StringConstants.PERIODO_NOITE, "vinho", false),
	
	/** The sobremesa noite. */
	SOBREMESA_NOITE(4, StringConstants.PERIODO_NOITE, "bolo", false);
	
	/** The id tipo prato. */
	private Integer idTipoPrato;
	
	/** The periodo. */
	private String periodo;
	
	/** The descricao prato. */
	private String descricaoPrato;
	
	/** The is multiplo pedido. */
	private boolean isMultiploPedido;
	
	/**
	 * Instantiates a new tipo prato enum.
	 *
	 * @param idTipoPrato the id tipo prato
	 * @param periodo the periodo
	 * @param descricaoPrato the descricao prato
	 * @param isMultiploPedido the is multiplo pedido
	 */
	private TipoPratoEnum(Integer idTipoPrato, String periodo, String descricaoPrato, boolean isMultiploPedido) {
		this.idTipoPrato = idTipoPrato;
		this.periodo = periodo;
		this.descricaoPrato = descricaoPrato;
		this.isMultiploPedido = isMultiploPedido;
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
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
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
	 * Checks if is multiplo pedido.
	 *
	 * @return true, if is multiplo pedido
	 */
	public boolean isMultiploPedido() {
		return isMultiploPedido;
	}
	
	/**
	 * Buscar por id tipo prato and periodo.
	 *
	 * @param idTipoPrato the id tipo prato
	 * @param periodo the periodo
	 * @return the tipo prato enum
	 */
	public static TipoPratoEnum buscarPorIdTipoPratoAndPeriodo(Integer idTipoPrato, String periodo) {
		for (TipoPratoEnum tipoPratoEnum : TipoPratoEnum.values()) {
			if(tipoPratoEnum.getIdTipoPrato() == idTipoPrato
					&& StringUtil.removerAcentos(tipoPratoEnum.getPeriodo()).equals(StringUtil.removerAcentos(periodo))) {
				return tipoPratoEnum;
			}
		}
		return null;
	}
	
	/**
	 * Listar por periodo.
	 *
	 * @param periodo the periodo
	 * @return the list
	 */
	public static List<TipoPratoEnum> listarPorPeriodo(String periodo) {
		List<TipoPratoEnum> listaTipoPratoEnum = new ArrayList<>();
		for (TipoPratoEnum tipoPratoEnum : TipoPratoEnum.values()) {
			if(StringUtil.removerAcentos(tipoPratoEnum.getPeriodo()).equals(StringUtil.removerAcentos(periodo))) {
				listaTipoPratoEnum.add(tipoPratoEnum);
			}
		}
		return listaTipoPratoEnum;
	}
}
