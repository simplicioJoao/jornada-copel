import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ListarDemandas {
    public static void main(String[] args) {
        listarDemandas();
    }

    public static void listarDemandas() {
        String query = "SELECT * FROM demanda";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n----- Lista de Demandas -----");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String prioridade = rs.getString("prioridade");
                double distanciaEquipeSede = rs.getDouble("distancia_equipe_sede");
                double distanciaSedeLocal = rs.getDouble("distancia_sede_local");
                double distanciaTotal = rs.getDouble("distancia_total");
                double custoReparo = rs.getDouble("custo_reparo");
                double prejuizoFiscal = rs.getDouble("prejuizo_fiscal");
                String tempoEspera = rs.getString("tempo_espera");
                double score = rs.getDouble("score");
                String status = rs.getString("status");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Prioridade: " + prioridade);
                System.out.println("Distância Equipe-Sede: " + distanciaEquipeSede + " km");
                System.out.println("Distância Sede-Local: " + distanciaSedeLocal + " km");
                System.out.println("Distância Total: " + distanciaTotal + " km");
                System.out.println("Custo do Reparo: R$ " + custoReparo);
                System.out.println("Prejuízo Fiscal: R$ " + prejuizoFiscal);
                System.out.println("Tempo de Espera: " + tempoEspera);
                System.out.println("Score: " + score);
                System.out.println("Status: " + status);
                System.out.println("-------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar demandas: " + e.getMessage());
        }
    }
}
