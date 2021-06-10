package model.entities;

import java.util.Date;

public class Pessoa implements Comparable<Pessoa> {

	private Integer id;
	private String nome;
	private Integer idade;
	private String profissao;
	private String cpf;
	private Boolean areaSaude;
	private String logradouro;
	private String numero;
	private String cidade;
	private String uf;
	private String cep;
	private Date nascimento;
	private Date dataVacinado; // adcionar ao construtor == null
	private Boolean vacinado; // adcionar no construtor   == false
	private Integer nivel; // adicionar nivel 1,2,3

	public Pessoa() {
	}

	public Pessoa(Integer id,String nome, Integer idade, String profissao, String cpf, Boolean areaSaude, String logradouro, String numero,
			String cidade, String uf, String cep, Date nascimento, Date dataVacinado, Boolean vacinado, Integer nivel) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.profissao = profissao;
		this.cpf = cpf;
		this.areaSaude = areaSaude;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.nascimento = nascimento;
		this.dataVacinado = dataVacinado;
		this.vacinado = vacinado;
		this.nivel = nivel;
		}
	
	public Pessoa(Integer id) {
		this.id = id;
	}
	
	public Pessoa(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Pessoa(Integer id, String nome, Integer idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Boolean getAreaSaude() {
		return areaSaude;
	}

	public void setAreaSaude(Boolean areaSaude) {
		this.areaSaude = areaSaude;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	public Date getDataVacinado() {
		return dataVacinado;
	}

	public void setDataVacinado(Date dataVacinado) {
		this.dataVacinado = dataVacinado;
	}

	public Boolean getVacinado() {
		return vacinado;
	}

	public void setVacinado(Boolean vacinado) {
		this.vacinado = vacinado;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", nivel=" + nivel + "]";
	}

	@Override
	public int compareTo(Pessoa o) {
		return -nivel.compareTo(o.nivel);
	}	
	
}
