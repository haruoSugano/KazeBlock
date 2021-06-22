package model.entities;

import java.text.SimpleDateFormat;

public class DadosPaciente extends Pessoa {

	private String dados;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public DadosPaciente() {
	}

	public DadosPaciente(String dados) {
		this.dados = dados;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}
	
	@Override
	public String toString() {
		return "Dados deste paciente: "
				+ "\nNome: " + super.getNome()
				+ "\nIdade: " + super.getIdade() 
				+ "\nProfissão: " + super.getProfissao() 
				+ "\nA profissão pertence à área da saúde?: " + super.getAreaSaude()
				+ "\nCPF: " + super.getCpf()
				+ "\nTelefone: " + super.getTelefone()
				+ "\nEmail: " + super.getEmail()
				+ "\nNascimento: " + sdf.format(super.getNascimento()) 
				+ "\nLogradouro: " + super.getLogradouro()
				+ "\nNúmero: " + super.getNumero()
				+ "\nCidade: " + super.getCidade() 
				+ "\nUF: " + super.getUf()
				+ "\nCEP: " + super.getCep()
				+ "\nJá foi vacinada?: " + super.getVacinado()
				+ "\nData em que foi vacinada: " + super.getDataVacinado()
				+ "\nO seu nível de prioridade: " + super.getNivel();
	}
}
