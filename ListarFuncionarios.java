import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ListarFuncionarios {
    public static void main(String[] args) {
        listarFuncionarios();
    }

    public static void listarFuncionarios() {
        String query = "SELECT * FROM funcionarios";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n----- Lista de Funcionários -----");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                String departamento = rs.getString("departamento");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Cargo: " + cargo);
                System.out.println("Departamento: " + departamento);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);
                System.out.println("-------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }
}
