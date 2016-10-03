package br.com.ufrn.imd.concorrente.estacionamento.dominio;

public class Estacionamento {
	private String nome;
	private int capacidade;
	private int ocupacao;
	private boolean disponivel;
	
	public Estacionamento(String nome, int capacidade) {
		super();
		this.nome = nome;
		this.capacidade = capacidade;
		this.ocupacao = 0;
		this.disponivel = true;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public int getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(int ocupacao) {
		this.ocupacao = ocupacao;
	}
	
	public void adicionaCarro(){
		this.ocupacao++;
	}
	
	public void retiraCarro(){
		this.ocupacao--;
	}
	
}
