package br.com.cruz.testegft.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cruz.testegft.constants.StringConstants;
import br.com.cruz.testegft.entities.Prato;
import br.com.cruz.testegft.enums.TipoPratoEnum;
import br.com.cruz.testegft.exceptions.DadosInvalidosException;
import br.com.cruz.testegft.utils.StringUtil;

/**
 * The Class PedidoService.
 */
public class PedidoService {
	
	/**
	 * Realizar pedido.
	 *
	 * @param pedido the pedido
	 * @return the string
	 */
	public String realizarPedido(String pedido) {
		String periodo = null;
		try {
			validarPedido(pedido);			
			periodo = extrairPeriodo(pedido);
			validarPeriodo(periodo);
			List<Prato> pratosPedido = new ArrayList<>();
			
			for (Integer i : extrairTiposPrato(pedido)) {
				adicionarPratoAoPedido(i, periodo, pratosPedido);
			}
				
				
			return montarRetornoComSucesso(pratosPedido);
		} catch (DadosInvalidosException e) {
			return montarRetornoSemSucesso(periodo, e.getMessage());
		}
		
	}
	

	/**
	 * Validar periodo.
	 *
	 * @param periodo the periodo
	 * @throws DadosInvalidosException the dados invalidos exception
	 */
	private void validarPeriodo(String periodo) throws DadosInvalidosException {
		if(!isValidoPeriodo(periodo)) {
			throw new DadosInvalidosException(StringConstants.ERRO);
		}
	}


	/**
	 * Montar retorno sem sucesso.
	 *
	 * @param periodo the periodo
	 * @param message the message
	 * @return the string
	 */
	private String montarRetornoSemSucesso(String periodo, String message) {
		String retorno = "";
		if(isValidoPeriodo(periodo)) {
			List<TipoPratoEnum> listaTipoPratoEnum = ordenarListaTipoPratoEnum(TipoPratoEnum.listarPorPeriodo(periodo));
			
			for (TipoPratoEnum tipoPratoEnum : listaTipoPratoEnum) {
				retorno += carregarDescricaoPratoRetornoSemSucesso(tipoPratoEnum);
			}			
			
			retorno = retorno + message ;
		} else {
			retorno = message;
		}
		
		return retorno;
	}


	/**
	 * Ordenar lista tipo prato enum.
	 *
	 * @param listaTipoPratoEnum the lista tipo prato enum
	 * @return the list
	 */
	private List<TipoPratoEnum> ordenarListaTipoPratoEnum(List<TipoPratoEnum> listaTipoPratoEnum) {
		return listaTipoPratoEnum.stream().sorted((p1, p2) -> p1.getIdTipoPrato().compareTo(p2.getIdTipoPrato())).collect(Collectors.toList());
	}


	/**
	 * Montar retorno com sucesso.
	 *
	 * @param pratosPedido the pratos pedido
	 * @return the string
	 */
	private String montarRetornoComSucesso(List<Prato> pratosPedido) {
		String retorno = "";
		
		for (Prato prato : ordenarListaPrato(pratosPedido)) {
			retorno += carregarDescricaoPratoRetornoComSucesso(prato);
		}
		
		return retorno.substring(0, retorno.length() - 2);
	}


	/**
	 * Ordenar lista prato.
	 *
	 * @param pratosPedido the pratos pedido
	 * @return the list
	 */
	private List<Prato> ordenarListaPrato(List<Prato> pratosPedido) {
		return pratosPedido.stream().sorted((p1, p2) -> p1.getIdTipoPrato().compareTo(p2.getIdTipoPrato())).collect(Collectors.toList());
	}


	/**
	 * Carregar descricao prato retorno com sucesso.
	 *
	 * @param prato the prato
	 * @return the string
	 */
	private String carregarDescricaoPratoRetornoComSucesso(Prato prato) {
		
		if(prato.getQuantidade() > 1) {
			return prato.getDescricaoPrato() + " (x" + prato.getQuantidade() + "), ";
		} else {
			return prato.getDescricaoPrato() + StringConstants.VIRGULA + " ";
		}
	}
	
	/**
	 * Carregar descricao prato retorno sem sucesso.
	 *
	 * @param tipoPratoEnum the tipo prato enum
	 * @return the string
	 */
	private String carregarDescricaoPratoRetornoSemSucesso(TipoPratoEnum tipoPratoEnum) {
		return tipoPratoEnum.getDescricaoPrato() + StringConstants.VIRGULA + " ";
	}


	/**
	 * Adicionar prato ao pedido.
	 *
	 * @param i the i
	 * @param periodo the periodo
	 * @param pratosPedido the pratos pedido
	 * @throws DadosInvalidosException the dados invalidos exception
	 */
	private void adicionarPratoAoPedido(Integer i, String periodo, List<Prato> pratosPedido) throws DadosInvalidosException {
		TipoPratoEnum tipoPratoEnum = TipoPratoEnum.buscarPorIdTipoPratoAndPeriodo(i, periodo);
		
		if(tipoPratoEnum == null) {
			throw new DadosInvalidosException(StringConstants.ERRO);
		}
		
		Prato pratoExistente = pratosPedido.stream()
				.filter(p -> p.getIdTipoPrato().equals(i) 
						&& StringUtil.removerAcentos(p.getPeriodo()).equals(StringUtil.removerAcentos(periodo)))
				.findFirst().orElse(null);
		
		if(pratoExistente == null) {
			pratosPedido.add(montarPrato(tipoPratoEnum));
		} else if(tipoPratoEnum.isMultiploPedido()) {
			pratoExistente.setQuantidade(pratoExistente.getQuantidade() + 1);
		} else {
			throw new DadosInvalidosException(StringConstants.ERRO);
		}
	}


	/**
	 * Montar prato.
	 *
	 * @param tipoPratoEnum the tipo prato enum
	 * @return the prato
	 */
	private Prato montarPrato(TipoPratoEnum tipoPratoEnum) {		
		return new Prato(tipoPratoEnum.getDescricaoPrato(), tipoPratoEnum.getIdTipoPrato(), tipoPratoEnum.getPeriodo());
	}


	/**
	 * Checks if is valido periodo.
	 *
	 * @param periodo the periodo
	 * @return the boolean
	 */
	private Boolean isValidoPeriodo(String periodo) {
		if(periodo == null || (StringUtil.removerAcentos(periodo).equals(StringUtil.removerAcentos(StringConstants.PERIODO_MANHA)) 
				&& StringUtil.removerAcentos(periodo).equals(StringUtil.removerAcentos(StringConstants.PERIODO_NOITE)))) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validar pedido.
	 *
	 * @param pedido the pedido
	 * @throws DadosInvalidosException the dados invalidos exception
	 */
	private void validarPedido(String pedido) throws DadosInvalidosException {
		if(pedido == null || pedido.isEmpty()
				|| pedido.indexOf(StringConstants.VIRGULA) == -1 
				|| pedido.split(StringConstants.VIRGULA).length < 2) {
			throw new DadosInvalidosException(StringConstants.ERRO);
		}
	}
	
	/**
	 * Extrair tipos prato.
	 *
	 * @param pedidoString the pedido string
	 * @return the list
	 * @throws DadosInvalidosException the dados invalidos exception
	 */
	private static List<Integer> extrairTiposPrato(String pedidoString) throws DadosInvalidosException {
        try {
            String opcoes = pedidoString.substring(pedidoString.indexOf(StringConstants.VIRGULA) + 1);
            
            String[] tiposPratosString = opcoes.split(StringConstants.VIRGULA);
            List<Integer> tiposPratos = new ArrayList<>();
            for (String string : tiposPratosString) {
				tiposPratos.add(Integer.parseInt(string.trim()));
			}

            return tiposPratos;
        } catch (Exception e) {
        	throw new DadosInvalidosException(StringConstants.ERRO);
        }
    }
	
	/**
	 * Extrair periodo.
	 *
	 * @param pedido the pedido
	 * @return the string
	 */
	private static String extrairPeriodo(String pedido) {
        return pedido.substring(0, pedido.indexOf(StringConstants.VIRGULA));
    }
	
}
