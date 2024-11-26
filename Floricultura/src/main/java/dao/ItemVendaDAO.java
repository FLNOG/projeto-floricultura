package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ItemVenda;
import model.Produto;

public class ItemVendaDAO {
	
	private Connection conn;
	
	public ItemVendaDAO( ) {
		
	}

    public ItemVendaDAO(Connection connection) {
        this.conn = connection;
    }
    
	public void salvarItem(ItemVenda item, Connection conn) throws SQLException {

	    if (!vendaExiste(item.getIdVenda(), conn)) {
	        throw new SQLException("Venda não encontrada!");
	    }

	    PreparedStatement ps = null;
	    String sql = "INSERT INTO ITEMVENDA (idVenda, idProduto, qtdItens, vlrProduto) VALUES (?, ?, ?, ?)";
	    try {
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, item.getIdVenda());
	        ps.setInt(2, item.getIdProduto());
	        ps.setInt(3, item.getQtdItens());
	        ps.setDouble(4, item.getVlrProduto());

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (ps != null) {
	            ps.close();
	        }
	    }
	}

	public boolean vendaExiste(int idVenda, Connection conn) throws SQLException {
		String sql = "SELECT COUNT(*) FROM VENDA WHERE idVenda = ?";
	    
	    PreparedStatement ps = null;
	    try {
	        ps = conn.prepareStatement(sql);
	        
	        ps.setInt(1, idVenda);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (ps != null) {
	            ps.close();
	        }
	    }
	    return false;
	}
	
	public List<ItemVenda> buscarPorCliente(int idCliente) {
	    List<ItemVenda> itensComprados = new ArrayList<>();

	    String sql = """
	        SELECT iv.*, p.descricao, p.preco 
	        FROM ITEMVENDA iv
	        INNER JOIN VENDA v ON iv.idVenda = v.idVenda
	        INNER JOIN PRODUTO p ON iv.idProduto = p.idProduto
	        WHERE v.idCliente = ?
	    """;

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idCliente);
	        System.out.println("Buscando itens para o cliente: " + idCliente);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Item encontrado: " + rs.getString("descricao")); // Adicionado para debug
	                ItemVenda item = new ItemVenda();
	                item.setIdItemVenda(rs.getInt("idItemVenda"));
	                item.setQtdItens(rs.getInt("qtdItens"));
	                item.setVlrProduto(rs.getDouble("preco"));

	                Produto produto = new Produto();
	                produto.setIdProduto(rs.getInt("idProduto"));
	                produto.setDescricao(rs.getString("descricao"));

	                item.setProduto(produto);
	                itensComprados.add(item);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return itensComprados;
	}
}