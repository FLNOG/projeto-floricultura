package model;

import dao.ProdutoDAO;

public class Produto {

    private int idProduto;
    private String descricao;
    private int quantidade;
    private double preco;
    private String detalhe;
    private String url_imagem;
    
    public Produto() {
    }

    public Produto(String descricao, int quantidade, double preco, String detalhe, String url_imagem) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.detalhe = detalhe;
        this.url_imagem = url_imagem;
    }

    public Produto(int idProduto, String descricao, int quantidade, double preco, String detalhe, String url_imagem) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.detalhe = detalhe;
        this.url_imagem = url_imagem;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getUrl_imagem() {
		return url_imagem;
	}
	
	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}
    
    public void salvar() {
        new ProdutoDAO().cadastrarProduto(this);
    }

    public void remover() {
        new ProdutoDAO().removerProduto(this.idProduto);
    }
}