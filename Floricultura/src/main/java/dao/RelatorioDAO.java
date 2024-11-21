package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Produto;
import model.Venda;
import utils.ConnectionFactory;
import model.ItemVenda;

public class RelatorioDAO {
	public RelatorioDAO(Connection connection) {
    }

    public List<Venda> obterRelatorioSintetico(String dataInicio, String dataFim) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sqlVendas = """
            SELECT V.idVenda, V.vlrTotal, V.dataVenda, V.idCliente, C.nome AS nomeCliente
            FROM VENDA V
            INNER JOIN CLIENTE C ON V.idCliente = C.idCliente
            WHERE V.dataVenda BETWEEN ? AND ?
        """;

        String sqlItens = """
            SELECT IV.idItemVenda, IV.idVenda, IV.idProduto, P.descricao AS dscProduto, IV.qtdItens, IV.vlrProduto
            FROM ITEMVENDA IV
            INNER JOIN PRODUTO P ON IV.idProduto = P.idProduto
            WHERE IV.idVenda = ?
        """;

        Connection conn = null;
        PreparedStatement psVendas = null;
        PreparedStatement psItens = null;
        ResultSet rsVendas = null;
        ResultSet rsItens = null;

        try {
            conn = new ConnectionFactory().getConnection();
            psVendas = conn.prepareStatement(sqlVendas);
            psVendas.setString(1, dataInicio);
            psVendas.setString(2, dataFim);

            rsVendas = psVendas.executeQuery();
            
            while (rsVendas.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rsVendas.getInt("idVenda"));
                venda.setVlrTotal(rsVendas.getDouble("vlrTotal"));
                venda.setDataVenda(rsVendas.getDate("dataVenda"));

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rsVendas.getInt("idCliente"));
                cliente.setNome(rsVendas.getString("nomeCliente"));
                venda.setCliente(cliente);

                List<ItemVenda> itens = new ArrayList<>();
                psItens = conn.prepareStatement(sqlItens);
                psItens.setInt(1, venda.getIdVenda());
                rsItens = psItens.executeQuery();

                while (rsItens.next()) {
                    ItemVenda item = new ItemVenda();
                    item.setIdItemVenda(rsItens.getInt("idItemVenda"));
                    item.setQtdItens(rsItens.getInt("qtdItens"));
                    item.setVlrProduto(rsItens.getDouble("vlrProduto"));

                    Produto produto = new Produto();
                    produto.setIdProduto(rsItens.getInt("idProduto"));
                    produto.setDescricao(rsItens.getString("dscProduto"));
                    item.setProduto(produto);

                    itens.add(item);
                }

                venda.setItens(itens);
                vendas.add(venda);
            }
        } finally {
            if (rsItens != null) try { rsItens.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (rsVendas != null) try { rsVendas.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (psItens != null) try { psItens.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (psVendas != null) try { psVendas.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return vendas;
    }
    
    public List<ItemVenda> relatorioAnalitico(int idVenda) throws SQLException {
        List<ItemVenda> itensVenda = new ArrayList<>();
        String sql = """
            SELECT IV.idProduto, P.descricao AS dscProduto,
                   SUM(IV.qtdItens) AS totalQtd, 
                   AVG(IV.vlrProduto) AS vlrProduto
            FROM ItemVenda IV
            INNER JOIN Produto P ON IV.idProduto = P.idProduto
            WHERE IV.idVenda = ?
            GROUP BY IV.idProduto, P.descricao
        """;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idVenda);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemVenda itemVenda = new ItemVenda();
                    itemVenda.setIdItemVenda(rs.getInt("idProduto"));
                    
                    Produto produto = new Produto();
                    produto.setDescricao(rs.getString("dscProduto"));
                    itemVenda.setProduto(produto);

                    itemVenda.setQtdItens(rs.getInt("totalQtd"));
                    itemVenda.setVlrProduto(rs.getDouble("vlrProduto"));

                    itensVenda.add(itemVenda);
                }
            }
        }

        return itensVenda;
    }
}