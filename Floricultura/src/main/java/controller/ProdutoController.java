package controller;

import dao.ProdutoDAO;
import model.Produto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/produtos")
public class ProdutoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        super();
        produtoDAO = new ProdutoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
        	System.out.println("Action está nula.");
        	cadastrarProduto(request, response);
            return;
        }
        
        try {
            switch (action) {
                case "cadastrar":
                    cadastrarProduto(request, response);
                    break;
                case "listar":
                	RequestDispatcher dispatcher = request.getRequestDispatcher("views/produtos/cadastro-produto.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "buscar":
                    buscarProduto(request, response);
                    break;
                case "editar":
                    editarProduto(request, response);
                    break;
                case "remover":
                    removerProduto(request, response);
                    break;
                default:
                	cadastrarProduto(request, response);
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
                cadastrarProduto(request, response);
            } else if ("editar".equals(action)) {
                atualizarProduto(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void buscarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descricaoBusca = request.getParameter("descricaoBusca");
        List<Produto> listaProdutos = produtoDAO.buscarPorDescricao(descricaoBusca);
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/produtos/buscar-produto.jsp");
        dispatcher.forward(request, response);
    }

    private void cadastrarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descricao = request.getParameter("descricao");
        String detalhes = request.getParameter("detalhes");
        String imagem = request.getParameter("imagem");

        String quantidadeStr = request.getParameter("quantidade");
        String precoStr = request.getParameter("preco");

        int quantidade = 0;
        if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
            try {
                quantidade = Integer.parseInt(quantidadeStr);
            } catch (NumberFormatException e) {
                quantidade = 0;
            }
        }

        double preco = 0.0;
        if (precoStr != null && !precoStr.isEmpty()) {
            try {
                preco = Double.parseDouble(precoStr);
            } catch (NumberFormatException e) {
                preco = 0.0;
            }
        }

        if (descricao == null || descricao.isEmpty() || quantidade <= 0 || preco <= 0) {
            request.setAttribute("erro", "Preencha todos os campos corretamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/produtos/cadastro-produto.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Produto produto = new Produto(descricao, quantidade, preco, detalhes, imagem);
        produtoDAO.cadastrarProduto(produto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/produtos/cadastro-produto.jsp");
        dispatcher.forward(request, response);
    }

    private void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto produto = produtoDAO.buscarPorId(id);
        request.setAttribute("produto", produto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/produtos/editar-produto.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descricao = request.getParameter("descricao");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        String detalhes = request.getParameter("detalhes");
        String imagem = request.getParameter("imagem");

        Produto produto = new Produto(id, descricao, quantidade, preco, detalhes, imagem);
        produtoDAO.atualizarProduto(produto);
        response.sendRedirect("produtos?action=listar");
    }

    private void removerProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDAO.removerProduto(id);
        response.sendRedirect("views/produtos/cadastro-produto.jsp");
    }
}