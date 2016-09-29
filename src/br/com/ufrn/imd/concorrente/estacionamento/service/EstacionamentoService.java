package br.com.ufrn.imd.concorrente.estacionamento.service;

import br.com.ufrn.imd.concorrente.estacionamento.dominio.Estacionamento;

public class EstacionamentoService {
	
	private Estacionamento estacionamento;
	
	public EstacionamentoService(Estacionamento e){
		this.estacionamento = e;
	}
	
	public void entradaDeCarro(){
		if(this.estacionamento.isDisponivel()){
			int aux = this.estacionamento.getOcupacao();
			aux++;
			this.estacionamento.setOcupacao(aux);
			if(this.estacionamento.getOcupacao() > this.estacionamento.getCapacidade()){
				this.estacionamento.setDisponivel(false);
			}
		}
	}
	
	public void saidaDeCarro(){
		if(this.estacionamento.getOcupacao() > 0){
			int aux = this.estacionamento.getOcupacao();
			aux--;
			this.estacionamento.setOcupacao(aux);
			if(this.estacionamento.getOcupacao() >= 0){
				this.estacionamento.setDisponivel(true);
			}
		}
	}
}
