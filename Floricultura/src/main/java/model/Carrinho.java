package model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemVenda> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemVenda itemVenda) {
        this.itens.add(itemVenda);
    }

    public void removerItem(int idProduto) {
        if (itens != null) {
            itens.removeIf(item -> item.getIdProduto() == idProduto);
        }
    }
    
    public List<ItemVenda> getItens() {
        return itens;
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(item -> item.getVlrProduto() * item.getQtdItens()).sum();
    }
}