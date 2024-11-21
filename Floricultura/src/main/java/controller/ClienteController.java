package controller;

import dao.ClienteDAO;
import dao.ItemVendaDAO;
import model.Cliente;
import model.ItemVenda;
import utils.ConnectionFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteDAO clienteDAO;

    public ClienteController() {
        super();
        clienteDAO = new ClienteDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
        	cadastrarClienteAdm(request, response);
            return;
        }
        
        try {
            switch (action) {
                case "cadastrar":
                    cadastrarClienteAdm(request, response);
                    break;
                case "listar":
                	RequestDispatcher dispatcher = request.getRequestDispatcher("views/clientes/cadastro-cliente.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "buscar":
                    buscarCliente(request, response);
                    break;
                case "editar":
                    editarCliente(request, response);
                    break;  
                case "remover":
                    removerCliente(request, response);
                    break;
                case "endereco":
                    enderecoCliente(request, response);
                    break;
                case "meu-perfil":
                    meuPerfil(request, response);
                    break;
                case "listarPerfil":
                	RequestDispatcher dispatcher2 = request.getRequestDispatcher("views/perfil/meu-perfil.jsp");
                    dispatcher2.forward(request, response);
                    break;
                case "listarEndereco":
                	RequestDispatcher dispatcher3 = request.getRequestDispatcher("views/perfil/meu-endereco.jsp");
                	dispatcher3.forward(request, response);
                	break;
                case "enderecoPerfil":
                	enderecoPerfil(request, response);
                	break;	
                case "pedidos":
                	meusPedidos(request, response);
                	break;
                default:
                	cadastrarClienteAdm(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("cadastrar".equals(action)) {
                cadastrarClienteAdm(request, response);
            } else if ("editar".equals(action)) {
                atualizarCliente(request, response);
            } else if ("editar-endereco".equals(action)) {
            	atualizarEndereco(request, response);
            } else if ("meu-perfil".equals(action)) {
            	atualizarPerfil(request, response);
            } else if ("meu-endereco".equals(action)) {
            	atualizarEnderecoPerfil(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String NomeBusca = request.getParameter("NomeBusca");
        List<Cliente> listaClientes = clienteDAO.buscarPorNome(NomeBusca);
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/clientes/buscar-cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void cadastrarClienteAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpfcnpj");
        String telefone = request.getParameter("telefone");
        String nascimento = request.getParameter("nascimento");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("escolha");
        
        Cliente cliente = new Cliente(nome, email, cpf, telefone, nascimento, senha, tipo);
        clienteDAO.cadastrarClienteAdm(cliente);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/clientes/cadastro-cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.buscarPorId(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/clientes/editar-cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String nome = request.getParameter("nome");
	    String email = request.getParameter("email");
	    String cpf = request.getParameter("cpf");
	    String telefone = request.getParameter("telefone");
	    String nascimento = request.getParameter("nascimento");
	    String senha = request.getParameter("senha");

	    Cliente cliente = new Cliente(id, nome, cpf, nascimento, email, telefone, senha);

	    clienteDAO.atualizarCliente(cliente);
	    response.sendRedirect("clientes?action=listar");
	}

    private void removerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.removerCliente(id);
        response.sendRedirect("views/clientes/cadastro-cliente.jsp");
    }
    
    private void atualizarEndereco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String logradouro = request.getParameter("logradouro");
	    String numero = request.getParameter("numero");
	    String cep = request.getParameter("cep");
	    String bairro = request.getParameter("bairro");
	    String cidade = request.getParameter("cidade");
	    String uf = request.getParameter("uf");
	    String complemento = request.getParameter("complemento");
	    String apelido = request.getParameter("apelido");

	    Cliente cliente = new Cliente(id, logradouro, numero, cep, bairro, cidade, uf, complemento, apelido);

	    clienteDAO.atualizarEndereco(cliente);
	    response.sendRedirect("clientes?action=listar");
	}
    
    private void enderecoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.buscarEndereco(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/clientes/endereco-cliente.jsp");
        dispatcher.forward(request, response);
    } 
    
    public void meuPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().println("ID do cliente não fornecido ou inválido.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.getWriter().println("ID do cliente inválido.");
            return;
        }

        Cliente cliente = clienteDAO.buscarPorId(id);

        if (cliente == null) {
            response.getWriter().println("Cliente não encontrado.");
            return;
        }

        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/perfil/meu-perfil.jsp");
        dispatcher.forward(request, response);
    }
    
    private void atualizarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String nascimento = request.getParameter("nascimento");
        String senha = request.getParameter("senha");

        Cliente cliente = new Cliente(id, nome, cpf, nascimento, email, telefone, senha);

        clienteDAO.atualizarCliente(cliente);
        response.sendRedirect("clientes?action=meu-perfil&id=" + id);
	}
    
    public void atualizarEnderecoPerfil (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String complemento= request.getParameter("complemento");
        String apelido = request.getParameter("apelido");

        Cliente cliente = new Cliente(id, logradouro, numero, cep, bairro, cidade, uf, complemento, apelido);

        clienteDAO.atualizarEndereco(cliente);
        response.sendRedirect("clientes?action=enderecoPerfil&id=" + id);
    	
    }
    
    private void enderecoPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.buscarEndereco(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/perfil/meu-endereco.jsp");
        dispatcher.forward(request, response);
    } 
    
    private void meusPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	HttpSession session = request.getSession();
    	Integer idCliente = (Integer) session.getAttribute("idCliente");
    	Connection conn = null;

    	if (idCliente == null) {
    		request.setAttribute("mensagem", "Você precisa estar logado para acessar esta página.");
    		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    		return;
    	}

    	try {

    		conn = new ConnectionFactory().getConnection();
    		ItemVendaDAO itemVendaDAO = new ItemVendaDAO(conn);
    		List<ItemVenda> itensComprados = itemVendaDAO.buscarPorCliente(idCliente);

    		request.setAttribute("itensComprados", itensComprados);
    		request.getRequestDispatcher("/views/perfil/meus-pedidos.jsp").forward(request, response);
    	} catch (Exception e) {
    		e.printStackTrace();
    		request.setAttribute("mensagem", "Erro ao carregar produtos.");
    		request.getRequestDispatcher("/views/perfil/meus-pedidos.jsp").forward(request, response);
    	}
    }
}