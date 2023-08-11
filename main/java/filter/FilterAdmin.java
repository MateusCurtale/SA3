package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import conexao.ConexaoBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/painel/*"})
public class FilterAdmin extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	private static Connection conn;
	
	public FilterAdmin() {
    }

	public void destroy() {

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession sessao = req.getSession();
			
			String usuarioLogado = (String) sessao.getAttribute("usuario");
			String urlAutenticar = req.getServletPath();
			
			if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/painel/ServletOi")) {

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlAutenticar);
				request.setAttribute("mensagem", "Por favor efetue o Login!");
				redireciona.forward(request, response);
				return;
			}else {
				chain.doFilter(request, response);
			}
			conn.commit();
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("error.jsp");
			request.setAttribute("mensagem", e.getMessage());
			redireciona.forward(request, response);
			try {
				conn.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// Iniciar a conexão com o Banco
		conn = ConexaoBanco.getConnection();
	}

}
