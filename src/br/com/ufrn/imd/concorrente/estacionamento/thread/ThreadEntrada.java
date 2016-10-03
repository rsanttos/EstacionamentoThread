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

	public synchronized void entradaDeCarro(Estacionamento e) throws InterruptedException {
		while (!e.isDisponivel()) {
			wait();
		}
		while (e.isDisponivel()) {
			if(e.getOcupacao() == e.getCapacidade()){
				e.setDisponivel(false);
				wait();
			}
			if(e.getOcupacao() + 1 == e.getOcupacao()){
				e.setDisponivel(true);
				wait();
			}
			if(e.getOcupacao() < e.getCapacidade()){
				e.adicionaCarro();
				e.setDisponivel(true);
				notifyAll();
			} else {
				e.setDisponivel(false);
				notifyAll();
			}
			notifyAll();
			System.out.println("Entrada -> Estacionamento " + e.getNome() + " Ocupação: "
					+ e.getOcupacao());
			Thread.sleep((long) (Math.random() * 1000));
		}
		notifyAll();
		if(e.getOcupacao() <= e.getCapacidade()){
			entradaDeCarro(e);
		}
	}


	@Override
	public void run() {
		try {
			entradaDeCarro(estacionamento);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
