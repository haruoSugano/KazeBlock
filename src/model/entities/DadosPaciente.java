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
				+ "\nProfiss�o: " + super.getProfissao() 
				+ "\nA profiss�o pertence � �rea da sa�de?: " + super.getAreaSaude()
				+ "\nCPF: " + super.getCpf()
				+ "\nTelefone: " + super.getTelefone()
				+ "\nEmail: " + super.getEmail()
				+ "\nNascimento: " + sdf.format(super.getNascimento()) 
				+ "\nLogradouro: " + super.getLogradouro()
				+ "\nN�mero: " + super.getNumero()
				+ "\nCidade: " + super.getCidade() 
				+ "\nUF: " + super.getUf()
				+ "\nCEP: " + super.getCep()
				+ "\nJ� foi vacinada?: " + super.getVacinado()
				+ "\nData em que foi vacinada: " + super.getDataVacinado()
				+ "\nO seu n�vel de prioridade: " + super.getNivel();
	}
}
