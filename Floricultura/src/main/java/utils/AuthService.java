package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;

public class AuthService {
    
    public Cliente autenticarUsuario(String email, String senha) {
        String sql = "SELECT * FROM CLIENTE WHERE Email = ? AND Senha = ?";
        
        ConnectionFactory factory = new ConnectionFactory();
        
        try (Connection conexao = factory.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setSenha(rs.getString("Senha"));
                cliente.setTipo(rs.getString("Tipo"));

                return cliente;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}