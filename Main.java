import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Demanda> demandas = new ArrayList<>();

        int opcaoPrincipal;
        do {
            System.out.println("----- Menu Principal -----");
            System.out.println("1 - Listar Funcionários");
            System.out.println("2 - Nova Demanda");
            System.out.println("3 - Inserir Funcionário");  // <- NOVO
            System.out.println("4 - Sair");

            opcaoPrincipal = Integer.parseInt(scanner.nextLine());

            switch (opcaoPrincipal) {
                case 1: {
                    try {
                        ListarFuncionarios.listarFuncionarios();
                    } catch (Exception e) {
                        System.out.println("Erro ao exibir lista de funcionários: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    exibirSubMenuInserirDemanda(scanner, demandas);
                    break;
                }
                case 3: {
                    InserirFuncionario.inserirFuncionario();
                    break;
                }
                case 4: {
                    System.out.println("Encerrando o sistema...");
                    break;
                }

            }
        } while (opcaoPrincipal != 3);
    }

    // Submenu para "Inserir nova demanda"
    public static void exibirSubMenuInserirDemanda(Scanner scanner, ArrayList<Demanda> demandas) {
        int opcaoSubMenu;
        do {
            System.out.println("----- Menu de Inserção de Demanda -----");
            System.out.println("1 - Troca");
            System.out.println("2 - Manutenção");
            System.out.println("3 - Visita");
            System.out.println("4 - Incluir Demanda");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoSubMenu = Integer.parseInt(scanner.nextLine());

            switch (opcaoSubMenu) {
                case 1:
                    System.out.println("Inserindo uma demanda de tipo 'Troca'...");
                    break;
                case 2:
                    System.out.println("Inserindo uma demanda de tipo 'Manutenção'...");
                    break;
                case 3:
                    System.out.println("Inserindo uma demanda de tipo 'Visita'...");
                    break;
                case 4:
                    inserirDemanda(scanner, demandas);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcaoSubMenu != 5);
    }

    public static void inserirDemanda(Scanner scanner, ArrayList<Demanda> demandas) {
        System.out.print("Digite o nome da demanda: ");
        String nome = scanner.nextLine();

        System.out.print("Informe a distância entre a sede e o local da chamada: ");
        double distanciaSedeLocal = Double.parseDouble(scanner.nextLine());

        Demanda demanda = new Demanda(nome, distanciaSedeLocal);
        demandas.add(demanda);
        System.out.println("Demanda inserida com sucesso: " + nome + " (Distância: " + distanciaSedeLocal + " km)");
    }
}
