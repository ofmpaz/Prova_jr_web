package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/UserLogin", "/main", "/insert", "/select", "/update", "/delete"})
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String URL = "jdbc:postgresql://localhost:5432/prova_jr";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public UserLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (authenticateUser(email, senha)) {
            response.sendRedirect("sucesso.jsp");
        } else {
            response.sendRedirect("falha.jsp");
        }
    }

    private boolean authenticateUser(String email, String senha) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);

            String sql = "SELECT * FROM users WHERE email = ? AND senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);

            ResultSet resultado = statement.executeQuery();

            boolean authenticated = resultado.next();

            resultado.close();
            statement.close();
            connection.close();

            return authenticated;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
