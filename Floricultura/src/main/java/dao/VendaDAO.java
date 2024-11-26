package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Venda;

public class VendaDAO {
	public int salvarVenda(Venda venda, Connection conn) throws SQLException {
	    int idVenda = -1;

	    if (!clienteExiste(venda.getIdCliente(), conn)) {
	        throw new SQLException("Cliente não encontrado!");
	    }

	    PreparedStatement ps = null;
	    String sql = "INSERT INTO VENDA (vlrTotal, dataVenda, idCliente) VALUES (?, ?, ?)";
	    try {
	        ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setDouble(1, venda.getVlrTotal());
	        ps.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
	        ps.setInt(3, venda.getIdCliente());

	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                idVenda = rs.getInt(1);
	            }
	        }

	        if (idVenda == -1) {
	            throw new SQLException("Erro ao gerar o idVenda.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (ps != null) {
	            ps.close();
	        }
	    }

	    return idVenda;
	}
	
	public boolean clienteExiste(int idCliente, Connection conn) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM CLIENTE WHERE idCliente = ?";
	    PreparedStatement ps = null;

	    try {
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, idCliente);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            } else {
	                return false;
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
	}
}