package br.com.ufrn.imd.concorrente.estacionamento.thread;

import br.com.ufrn.imd.concorrente.estacionamento.dominio.Estacionamento;

public class ThreadEntrada extends Thread {

	private Estacionamento estacionamento;

	public ThreadEntrada(Estacionamento estacionamento) {
		super();
		this.estacionamento = estacionamento;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public synchronized void entradaDeCarro() throws InterruptedException {
		while (!estacionamento.isDisponivel()) {
			wait();
		}
		while (estacionamento.isDisponivel()) {
			if(estacionamento.getOcupacao() < estacionamento.getCapacidade()){
				int aux = estacionamento.getOcupacao();
				aux++;
				estacionamento.setOcupacao(aux);	
				notifyAll();
			} else {
				estacionamento.setDisponivel(false);
				notifyAll();
			}
			notifyAll();
			System.out.println("Entrada -> Estacionamento " + estacionamento.getNome() + " Ocupação: "
					+ estacionamento.getOcupacao());
			Thread.sleep((long) (Math.random() * 1000));
		}
		notifyAll();
		if(estacionamento.getOcupacao() <= estacionamento.getCapacidade()){
			entradaDeCarro();
		}
	}


	@Override
	public void run() {
		try {
			entradaDeCarro();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
