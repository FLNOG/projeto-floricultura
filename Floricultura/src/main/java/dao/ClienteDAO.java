package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import utils.ConnectionFactory;

public class ClienteDAO {

	public static void cadastrarDefault(Cliente cliente) {
		String sql = "INSERT INTO CLIENTE (Nome, Email, Senha, Tipo) VALUES (?,?,?,?)";
		
		PreparedStatement ps = null;
        Connection conn = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSenha());
            ps.setString(4, cliente.getTipo());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Se cadastrou com sucesso!");
            } else {
                System.out.println("Nenhuma linha foi inserida.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
	}
	
    public void cadastrarClienteAdm(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (Nome, Email, CpfCnpj, Telefone, Nascimento, Senha, Tipo) VALUES (?,?,?,?,?,?,?)";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getNascimento());
            ps.setString(6, cliente.getSenha());
            ps.setString(7, cliente.getTipo());

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
	
	public List<Cliente> listarClientes() {
		
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("Nome");
                String email = rs.getString("email");
                String contato = rs.getString("Telefone");
                

                Cliente cliente = new Cliente(idCliente, nome, email, contato);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return clientes;
    }
	
	public void removerCliente(int idCliente) {
        String sql = "DELETE FROM CLIENTE WHERE IdCliente = ?";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idCliente);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhuma linha foi removida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover cliente", e);
        }
    }
	
	public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET Nome = ?, CpfCnpj = ?, Nascimento = ?, Telefone = ?, Senha = ?, Email = ? WHERE idCliente = ?";
        
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getNascimento());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getSenha());
            ps.setString(6, cliente.getEmail());
            
            ps.setInt(7, cliente.getIdCliente());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }    
    }
	
	public Cliente buscarPorId(int id) {
		String sql = "SELECT * FROM CLIENTE WHERE IdCliente = ?";
		Cliente cliente = null;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					cliente = new Cliente(
						rs.getInt("IdCliente"),
						rs.getString("Nome"),
						rs.getString("Email"),
						rs.getString("CpfCnpj"),
						rs.getString("Telefone"),
						rs.getString("Nascimento"),
						rs.getString("Senha"),
						rs.getString("Tipo")
					);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar cliente por ID", e);
		}
		return cliente;
	}
	
	public void atualizarEndereco(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET logradouro = ?, numero = ?, cep = ?, bairro = ?, cidade = ?, uf = ?, complemento = ?, apelido = ? WHERE idCliente = ?";
        
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, cliente.getLogradouro());
            ps.setString(2, cliente.getNumero());
            ps.setString(3, cliente.getCep());
            ps.setString(4, cliente.getBairro());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getUf());
            ps.setString(7, cliente.getComplemento());
            ps.setString(8, cliente.getApelido());
            
            ps.setInt(9, cliente.getIdCliente());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }    
    }
	
	public Cliente buscarEndereco(int id) {
		String sql = "SELECT idCliente, logradouro, numero, cep, bairro, cidade, uf, apelido, complemento FROM CLIENTE WHERE IdCliente = ?";
		Cliente cliente = null;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					cliente = new Cliente(
						rs.getInt("IdCliente"),
						rs.getString("Logradouro"),
						rs.getString("Numero"),
						rs.getString("Cep"),
						rs.getString("Bairro"),
						rs.getString("Cidade"),
						rs.getString("Uf"),
						rs.getString("Apelido"),
						rs.getString("Complemento")
					);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar endereco", e);
		}
		return cliente;
	}
    
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE WHERE nome LIKE ?";
        
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCLiente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("CpfCnpj"));
                cliente.setNascimento(rs.getString("nascimento"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSenha(rs.getString("senha"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}