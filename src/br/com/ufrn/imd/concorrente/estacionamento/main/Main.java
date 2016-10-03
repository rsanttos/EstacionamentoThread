package br.com.ufrn.imd.concorrente.estacionamento.main;

import br.com.ufrn.imd.concorrente.estacionamento.dominio.Estacionamento;
import br.com.ufrn.imd.concorrente.estacionamento.thread.ThreadEntrada;
import br.com.ufrn.imd.concorrente.estacionamento.thread.ThreadSaida;

public class Main {

	public static void main(String[] args) {
		Estacionamento e = new Estacionamento("E1", 100);
		ThreadEntrada entrada = new ThreadEntrada(e);
		ThreadSaida saida = new ThreadSaida(e);
		
		entrada.start();
		saida.start();
		
		if(!(entrada.isAlive()) || !(saida.isAlive())){
			System.exit(0);
		}
	}

}
