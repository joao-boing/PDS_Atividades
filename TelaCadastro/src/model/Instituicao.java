package model;

public class Instituicao {
	private String nome;
	private String cnpj;
	private String endereco;
	private String telefone;
	private String tipo;

	public Instituicao(String nome, String cnpj, String endereco, String telefone, String tipo) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.tipo = tipo;
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }

	public String getEndereco() { return endereco; }
	public void setEndereco(String endereco) { this.endereco = endereco; }

	public String getTelefone() { return telefone; }
	public void setTelefone(String telefone) { this.telefone = telefone; }

	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }
}