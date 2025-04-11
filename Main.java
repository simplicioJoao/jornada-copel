import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Demanda> demandas = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1 - Inserir nova demanda");
            System.out.println("2 - Listar demandas ordenadas por score");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    inserirDemanda(scanner, demandas);
                    break;
                case 2:
                    BubbleSort.bubbleSort(demandas);
                    System.out.println("Demandas ordenadas por score:");
                    for (Demanda d : demandas) {
                        System.out.println(d);
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void inserirDemanda(Scanner scanner, ArrayList<Demanda> demandas) {
        System.out.print("Nome da demanda: ");
        String nome = scanner.nextLine();

        System.out.println("Escolha o cenário do problema:");
        System.out.println("1 - Emergencial");
        System.out.println("2 - Crítico");
        System.out.println("3 - Urgente");
        System.out.println("4 - Moderado");
        System.out.println("5 - Normal");
        int tipo = Integer.parseInt(scanner.nextLine());
        ClasseDeProblema problema = switch (tipo) {
            case 1 -> ClasseDeProblema.EMERGENCIAL;
            case 2 -> ClasseDeProblema.CRITICO;
            case 3 -> ClasseDeProblema.URGENTE;
            case 4 -> ClasseDeProblema.MODERADO;
            case 5 -> ClasseDeProblema.NORMAL;
            default -> ClasseDeProblema.NORMAL;
        };

        System.out.print("Distância da sede: ");
        double distanciaSede = Double.parseDouble(scanner.nextLine());

        System.out.print("Distância do veículo: ");
        double distanciaVeiculo = Double.parseDouble(scanner.nextLine());

        System.out.print("Custo de peças (R$): ");
        double custoPecas = Double.parseDouble(scanner.nextLine());

        System.out.print("Custo de mão de obra (R$): ");
        double custoMaoDeObra = Double.parseDouble(scanner.nextLine());

        System.out.print("Custo por hora parada (R$/h): ");
        double custoHoraParada = Double.parseDouble(scanner.nextLine());

        System.out.print("Custo de equipamentos (R$): ");
        double custoEquipamentos = Double.parseDouble(scanner.nextLine());

        System.out.print("Recorrência de chamadas: ");
        int recorrencia = Integer.parseInt(scanner.nextLine());

        System.out.print("Região (Centro, Bairro, Rural): ");
        String regiao = scanner.nextLine();

        System.out.print("Consumo médio da região (GW/h): ");
        double consumoMedio = Double.parseDouble(scanner.nextLine());

        System.out.print("Score manual adicional: ");
        double scoreManual = Double.parseDouble(scanner.nextLine());

        Demanda demanda = new Demanda(nome, problema, distanciaSede, distanciaVeiculo, custoPecas, custoMaoDeObra, custoHoraParada, custoEquipamentos, recorrencia, regiao, consumoMedio, scoreManual);

        demandas.add(demanda);
        System.out.println("Demanda registrada com sucesso!");
    }
}
