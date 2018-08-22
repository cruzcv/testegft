package br.com.cruz.testegft;

import java.util.Scanner;

import br.com.cruz.testegft.services.PedidoService;

public class Main {

	public static void main(String[] args) {
		PedidoService pedidoService = new PedidoService();
    	Scanner scan = new Scanner(System.in);
    	System.out.print("Informe o pedido: ");
    	String pedido = scan.nextLine();
    	System.out.println(pedidoService.realizarPedido(pedido));
    	scan.close();
	}

}
