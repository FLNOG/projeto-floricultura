package controller;

import dao.RelatorioDAO;
import model.ItemVenda;
import model.Venda;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/relatorio")
public class RelatorioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private RelatorioDAO relatorioDAO;

    @Override
    public void init() throws ServletException {
        relatorioDAO = new RelatorioDAO(null);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
        	System.out.println("Action está nula.");
            action = "listar";
        }

        try {
            switch (action) {
                case "listar":
                    listarRelatorio(request, response);
                    break;
                case "analitico":
                	exibirRelatorioAnalitico(request, response);
                	break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarRelatorio(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");

        if (dataInicio == null || dataFim == null) {
            dataInicio = "2000-01-01";
            dataFim = "2100-01-01";
        }

        List<Venda> vendas = relatorioDAO.obterRelatorioSintetico(dataInicio, dataFim);

        request.setAttribute("relatorio", vendas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/relatorios/relatorio-sintetico.jsp");
        dispatcher.forward(request, response);
    }
    
    public void exibirRelatorioAnalitico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idVenda = Integer.parseInt(request.getParameter("id"));
        
        try {
            List<ItemVenda> itensVenda = relatorioDAO.relatorioAnalitico(idVenda);
            request.setAttribute("itensVenda", itensVenda);
            request.getRequestDispatcher("/views/relatorios/relatorio-analitico.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao carregar o relatório analítico.");
            request.getRequestDispatcher("/views/relatorios/relatorio-analitico.jsp").forward(request, response);
        }
    }
}