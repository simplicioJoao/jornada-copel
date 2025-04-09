import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InserirDemanda {
    public static void main(String[] args) {
        inserirDemanda();
    }

    public static void inserirDemanda() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Prioridade (Baixa, Média, Alta): ");
        String prioridade = scanner.nextLine();

        System.out.print("Distância equipe até a sede (km): ");
        double distanciaEquipeSede = Double.parseDouble(scanner.nextLine());

        System.out.print("Distância sede até o local (km): ");
        double distanciaSedeLocal = Double.parseDouble(scanner.nextLine());

        double distanciaTotal = distanciaEquipeSede + distanciaSedeLocal;

        System.out.print("Custo do reparo: ");
        double custoReparo = Double.parseDouble(scanner.nextLine());

        System.out.print("Prejuízo fiscal: ");
        double prejuizoFiscal = Double.parseDouble(scanner.nextLine());

        System.out.print("Tempo de espera (DD/MM/AAAA): ");
        String tempoEspera = scanner.nextLine();

        System.out.print("Score: ");
        double score = Double.parseDouble(scanner.nextLine());

        System.out.print("Status (Espera, Andamento, Finalizado): ");
        String status = scanner.nextLine();

        String sql = "INSERT INTO demanda (nome, prioridade, distancia_equipe_sede, distancia_sede_local, distancia_total, custo_reparo, prejuizo_fiscal, tempo_espera, score, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, prioridade);
            stmt.setDouble(3, distanciaEquipeSede);
            stmt.setDouble(4, distanciaSedeLocal);
            stmt.setDouble(5, distanciaTotal);
            stmt.setDouble(6, custoReparo);
            stmt.setDouble(7, prejuizoFiscal);
            stmt.setString(8, tempoEspera); // formato DD/MM/AAAA como String
            stmt.setDouble(9, score);
            stmt.setString(10, status);

            stmt.executeUpdate();
            System.out.println("Demanda inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir demanda: " + e.getMessage());
        }
    }
}
