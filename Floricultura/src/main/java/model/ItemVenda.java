package model;

public class ItemVenda {
    private int idItemVenda;
    private String nome;
    private int idVenda;
    private int idProduto;
    private int qtdItens;
    private double vlrProduto;
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVenda(int idItemVenda, String nome, int idVenda, int idProduto, int qtdItens, double vlrProduto) {
        this.idItemVenda = idItemVenda;
        this.nome = nome;
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.qtdItens = qtdItens;
        this.vlrProduto = vlrProduto;
    }

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }

    public double getVlrProduto() {
        return vlrProduto;
    }

    public void setVlrProduto(double vlrProduto) {
        this.vlrProduto = vlrProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double calcularTotalItem() {
        return this.qtdItens * this.vlrProduto;
    }
}