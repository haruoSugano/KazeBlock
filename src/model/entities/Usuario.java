package model.entities;

public class Usuario {
	//adicionar numero de identificacao interna da empresa
	private Integer id;
	private String nome;
	private String cpf;
	private String senha;
	private String tipoUsuario;
	
	public Usuario() {
	}

	public Usuario(Integer id, String nome,String cpf, String senha, String tipoUsuario) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario(Integer id, String nome, String cpf, String tipoUsuario) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", TipoUsuario=" + tipoUsuario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
