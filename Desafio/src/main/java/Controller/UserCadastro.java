package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/UserCadastro", "/main", "/insert", "select", "/update", "/delete"})
public class UserCadastro extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String senhaUsuario = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");
        
       
        if (!senhaUsuario.equals(confirmarSenha)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "As senhas não são iguais");
            return;
        }
       
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/prova_jr";
            String usuario = "postgres";
            String senha = "1234";
            connection = DriverManager.getConnection(url, usuario, senha);
            
            String sql = "INSERT INTO usuarios (pes_nome, pes_sexo, pes_email, pes_celular, pes_senha, pes_data_cadastro) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, sexo);
            statement.setString(3, email);
            statement.setString(4, celular);
            statement.setString(5, senhaUsuario);
            statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            
            statement.executeUpdate();
           
            response.getWriter().write("successo");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro durante o cadastro");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
