package br.com.cruz.testegft;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.cruz.testegft.services.PedidoService;

/**
 * The Class PedidoServiceTest.
 */
public class PedidoServiceTest {
	
	/** The pedido service. */
	PedidoService pedidoService;
	
	/**
	 * Inicialize.
	 */
	@Before
	public void inicialize() {
		pedidoService = new PedidoService();
	}

	/**
	 * Deve retornar ovos torrada cafe passando manha 123.
	 */
	@Test
	public void deveRetornarOvosTorradaCafePassandoManha123() {
		assertEquals("ovos, torrada, café", pedidoService.realizarPedido("manhã, 1, 2, 3"));
	}
	
	/**
	 * Deve retornar ovos torrada cafe passando manha 213.
	 */
	@Test
	public void deveRetornarOvosTorradaCafePassandoManha213() {
		assertEquals("ovos, torrada, café", pedidoService.realizarPedido("manhã, 2, 1, 3"));
	}
	
	/**
	 * Deve retornar ovos torrada cafe erro passando manha 2134.
	 */
	@Test
	public void deveRetornarOvosTorradaCafeErroPassandoManha2134() {
		assertEquals("ovos, torrada, café, erro", pedidoService.realizarPedido("manhã, 2, 1, 3, 4"));
	}
	
	/**
	 * Deve retornar ovos torrada cafe X 3 passando manha 21333.
	 */
	@Test
	public void deveRetornarOvosTorradaCafeX3PassandoManha21333() {
		assertEquals("ovos, torrada, café (x3)", pedidoService.realizarPedido("manhã, 2, 1, 3, 3, 3"));
	}
	
	/**
	 * Deve retornar carne batata vinho bolo passando noite 1234.
	 */
	@Test
	public void deveRetornarCarneBatataVinhoBoloPassandoNoite1234() {
		assertEquals("carne, batata, vinho, bolo", pedidoService.realizarPedido("noite, 1, 2, 3, 4"));
	}
	
	/**
	 * Deve retornar carne batata X 2 bolo passando noite 1224.
	 */
	@Test
	public void deveRetornarCarneBatataX2BoloPassandoNoite1224() {
		assertEquals("carne, batata (x2), bolo", pedidoService.realizarPedido("noite, 1, 2, 2, 4"));
	}

}
