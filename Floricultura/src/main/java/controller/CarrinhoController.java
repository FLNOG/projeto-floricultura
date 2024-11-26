package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Date;

import model.Carrinho;
import model.Cliente;
import model.Produto;
import model.Venda;
import utils.ConnectionFactory;
import dao.ItemVendaDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import model.ItemVenda;

@WebServlet("/vendas")
public class CarrinhoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CarrinhoController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        

        try {
            if ("adicionar".equals(action)) {
                adicionarCarrinho(request, response);
            } else if ("remover".equals(action)) {
                removerCarrinho(request, response);
            } else if ("comprar-agora".equals(action)){
            	comprarAgora(request, response);
            } else if ("finalizar".equals(action)) {
                finalizarCompra(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void adicionarCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscarPorId(idProduto);

        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", carrinho);
        }

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setIdProduto(produto.getIdProduto());
        itemVenda.setNome(produto.getDescricao());
        itemVenda.setQtdItens(1);
        itemVenda.setVlrProduto(produto.getPreco());

        carrinho.adicionarItem(itemVenda);

        response.sendRedirect("index.jsp");
    }

    public void removerCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho != null) {
            carrinho.removerItem(idProduto);
        }

        response.sendRedirect("views/vendas/carrinho.jsp");
    }

    public void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho != null && !carrinho.getItens().isEmpty()) {
            Cliente usuarioLogado = (Cliente) session.getAttribute("usuarioLogado");

            if (usuarioLogado == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int idCliente = usuarioLogado.getIdCliente();

            Venda venda = new Venda();
            venda.setIdCliente(idCliente);
            venda.setDataVenda(new Date());
            venda.setItens(carrinho.getItens());
            venda.setVlrTotal(carrinho.calcularTotal());

            VendaDAO vendaDAO = new VendaDAO();
            ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

            Connection conn = null;
            try {
                conn = new ConnectionFactory().getConnection();
                conn.setAutoCommit(false);

                int vendaId = vendaDAO.salvarVenda(venda, conn);

                if (vendaId == -1) {
                    request.setAttribute("mensagem", "Erro ao salvar a venda.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("views/carrinho.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

                for (ItemVenda item : carrinho.getItens()) {
                    item.setIdVenda(vendaId);
                    itemVendaDAO.salvarItem(item, conn);
                }

                conn.commit();

                session.removeAttribute("carrinho");

                response.sendRedirect("views/vendas/compraSucesso.jsp");
            } catch (SQLException e) {
                if (conn != null) {
                    conn.rollback();
                }
                e.printStackTrace();
                request.setAttribute("mensagem", "Erro ao processar a compra.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/carrinho.jsp");
                dispatcher.forward(request, response);
            } finally {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            }
        } else {
            request.setAttribute("mensagem", "Carrinho vazio.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/carrinho.jsp");
            dispatcher.forward(request, response);
        }    
    }
    
    public void comprarAgora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Cliente usuarioLogado = (Cliente) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect("views/login/login.jsp");
            return;
        }
    
    	int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscarPorId(idProduto);

        if (produto == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Venda venda = new Venda();
        venda.setIdCliente(usuarioLogado.getIdCliente());
        venda.setDataVenda(new Date());
        venda.setVlrTotal(produto.getPreco());

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setIdProduto(produto.getIdProduto());
        itemVenda.setNome(produto.getDescricao());
        itemVenda.setQtdItens(1);
        itemVenda.setVlrProduto(produto.getPreco());
        venda.adicionarItemVenda(itemVenda);

        VendaDAO vendaDAO = new VendaDAO();
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

        try (Connection conn = new ConnectionFactory().getConnection()) {
            conn.setAutoCommit(false);

            int vendaId = vendaDAO.salvarVenda(venda, conn);
            if (vendaId == -1) {
                conn.rollback();
                response.sendRedirect("index.jsp?mensagem=erro");
                return;
            }

            itemVenda.setIdVenda(vendaId);
            itemVendaDAO.salvarItem(itemVenda, conn);

            conn.commit();
            response.sendRedirect("views/vendas/compraSucesso.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?mensagem=erro");
        }
    }    
}