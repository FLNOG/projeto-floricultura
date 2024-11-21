package model;

public class Cliente {
	
	private int idCliente;
    private String nome;
    private String cpf;
    private String nascimento;
    private String email;
    private String telefone;
    private String senha;
    private String tipo;
    
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;
    private String apelido;
    private String complemento;
    
    public Cliente() {
    }
    
    public Cliente(String nome, String email, String senha, String tipo, boolean isAdmin) {
    	this.nome = nome;
    	this.email = email;
    	this.senha = senha;
    	this.tipo = tipo;
    
    }
    
    public Cliente(String nome, String email, String cpf, String telefone, String nascimento, String senha, String tipo) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.senha = senha;
		this.tipo = tipo;
	}
    
    public Cliente(String nome, String email, String telefone, String nascimento, String senha) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.senha = senha;
	}

    public Cliente(int idCliente , String nome, String email, String cpf, String telefone, String nascimento, String senha, String tipo) {
    	this.idCliente = idCliente;
    	this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.senha = senha;
		this.tipo = tipo;
	}
    
	public Cliente(int idCliente, String nome, String cpf, String nascimento, String email, String telefone, String senha) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}

	public Cliente(String nome, String email, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
    
    public Cliente(int idCliente, String nome, String email, String telefone) { //adicionar parametro de cpf
    	this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        //adicionar refencia de cpf
    }
    
    public Cliente(int idCliente, String nome, String email, String telefone, String senha) {
        this.idCliente = idCliente;
    	this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

	public Cliente(int idCliente, String logradouro, String numero, String cep, String bairro, String cidade, String uf, String complemento, String apelido) {
		this.idCliente = idCliente;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.complemento = complemento;
		this.apelido = apelido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	//ENDEREÇO DO CLIENTE
	
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}