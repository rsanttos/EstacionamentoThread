package br.com.ufrn.imd.concorrente.estacionamento.thread;

import br.com.ufrn.imd.concorrente.estacionamento.dominio.Estacionamento;

public class ThreadSaida extends Thread {

	private Estacionamento estacionamento;

	public ThreadSaida(Estacionamento estacionamento) {
		super();
		this.estacionamento = estacionamento;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}
	
	public synchronized void saidaDeCarro(Estacionamento e) throws InterruptedException {
		while(!(e.isDisponivel())){
			wait();
		}
		if(e.getOcupacao() <= e.getCapacidade()){
			notifyAll();			
		}
		while (e.isDisponivel()) {
			if(e.getOcupacao() == 0){
				e.setDisponivel(true);
				notifyAll();
			}
			if(e.getOcupacao() - 1 == e.getOcupacao()){
				e.setDisponivel(true);
				notifyAll();
			}
			if(e.getOcupacao() - 1 < 0){
				e.setOcupacao(0);
				e.setDisponivel(true);
				notifyAll();
			} else {
				e.retiraCarro();
				System.out.println("Saída -> Estacionamento " + e.getNome() + " Ocupação: "
						+ e.getOcupacao());
				Thread.sleep((long) (Math.random() * 1000));
				e.setDisponivel(true);
				notifyAll();	
			}
		}
		notifyAll();	
		saidaDeCarro(e);
	}
	
//	public synchronized void saidaDeCarro() throws InterruptedException {
//		while(!(estacionamento.isDisponivel())){
//			wait();
//		}
//		if(estacionamento.getOcupacao() <= estacionamento.getCapacidade()){
//			notifyAll();			
//		}
//		while (estacionamento.isDisponivel()) {
//			if(estacionamento.getOcupacao() - 1 < 0){
//				estacionamento.setOcupacao(1);
//				notifyAll();
//			} else {
//				int aux = estacionamento.getOcupacao();
//				aux--;
//				estacionamento.setOcupacao(aux);
//				notifyAll();	
//				System.out.println("Saída -> Estacionamento " + estacionamento.getNome() + " Ocupação: "
//						+ estacionamento.getOcupacao());			
//			}
//		}
//		notifyAll();	
//		saidaDeCarro();
//	}

	@Override
	public void run() {
		try {
			saidaDeCarro(estacionamento);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
