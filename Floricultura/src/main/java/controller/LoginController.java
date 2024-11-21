package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Cliente;
import dao.ClienteDAO;

import java.io.IOException;

import utils.AuthService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthService authService;

    public LoginController() {
        super();
        authService = new AuthService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
        	System.out.println("Action está nula.");
            cadastro(request, response);
            return;
        }
        try {
            switch (action) {
                case "cadastro":
                    cadastro(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                default:
                    login(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("logout")) {
            logout(request, response);
        } else {
            response.sendRedirect("views/login/login.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Cliente usuarioAutenticado = authService.autenticarUsuario(email, senha);

        if (usuarioAutenticado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuarioAutenticado);
            
            session.setAttribute("idCliente", usuarioAutenticado.getIdCliente());

            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("mensagem", "Email ou senha incorretos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAdmin = false;
        String tipo = request.getParameter("tipo");
        String nome = request.getParameter("nome");
        String mensagem;

        if (nome != null && !nome.isEmpty()) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            Cliente clienteCad = new Cliente(nome, email, senha, tipo, isAdmin);

            try {
                ClienteDAO.cadastrarDefault(clienteCad);
                mensagem = "Cadastrado com sucesso!";
            } catch (Exception e) {
                mensagem = "Erro ao cadastrar: " + e.getMessage();
            }
        } else {
            mensagem = "Não cadastrado! Preencha todos os campos obrigatórios.";
        }

        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/login/login.jsp");
        dispatcher.forward(request, response);
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        response.sendRedirect("index.jsp");
    }
}