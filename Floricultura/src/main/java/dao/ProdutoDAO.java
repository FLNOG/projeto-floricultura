package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import utils.ConnectionFactory;

public class ProdutoDAO {

    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO PRODUTO (descricao, quantidade, preco, detalhes, imagem) VALUES (?,?,?,?,?)";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQuantidade());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getDetalhe());
            ps.setString(5, produto.getUrl_imagem());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto inserido com sucesso!");
            } else {
                System.out.println("Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto", e);
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("idProduto"),
                    rs.getString("descricao"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco"),
                    rs.getString("detalhes"),
                    rs.getString("imagem")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
        return produtos;
    }

    public void removerProduto(int idProduto) {
        String sql = "DELETE FROM PRODUTO WHERE idProduto = ?";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idProduto);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhuma linha foi removida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover produto", e);
        }
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE PRODUTO SET descricao = ?, quantidade = ?, preco = ?, detalhes = ?, imagem = ? WHERE idProduto = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQuantidade());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getDetalhe());
            ps.setString(5, produto.getUrl_imagem());
            ps.setInt(6, produto.getIdProduto());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhuma linha foi atualizada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM PRODUTO WHERE idProduto = ?";
        Produto produto = null;

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(
                        rs.getInt("idProduto"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"),
                        rs.getString("detalhes"),
                        rs.getString("imagem")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID", e);
        }
        return produto;
    }

    public List<Produto> buscarPorDescricao(String descricao) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO WHERE descricao LIKE ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + descricao + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("idProduto"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"),
                        rs.getString("detalhes"),
                        rs.getString("imagem")
                    );
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos por descrição", e);
        }
        return produtos;
    }
    
    public List<Produto> listarProdutosPorCategoria(String categoria) {
        List<Produto> listaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO WHERE DETALHES LIKE ?";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + categoria + "%"); 
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setUrl_imagem(rs.getString("imagem"));
                
                listaProdutos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaProdutos;
    }
}