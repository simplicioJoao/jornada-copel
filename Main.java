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
            System.out.println("3 - Editar score de uma demanda manualmente");
            System.out.println("4 - Fazer novo chamado para uma demanda existente");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1: {
                    inserirDemanda(scanner, demandas);
                    break;
                }
                case 2: {
                    BubbleSort.bubbleSort(demandas);
                    System.out.println("Demandas ordenadas por score:");
                    for (Demanda d : demandas) {
                        System.out.println(d.getDemanda());
                    }
                    break;
                }
                case 3: {
                    System.out.println("Informe o id da demanda: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Demanda demanda = buscarDemanda(demandas, id);
                    if(demanda != null) {
                        System.out.println("Informe o novo score da demanda de id " + id + ": ");
                        double score = Double.parseDouble(scanner.nextLine());
                        demanda.setScore(score);

                        System.out.println("Score atualizado de forma manual com sucesso!");
                        demanda.getDemanda();
                    } else {
                        System.out.println("Demanda com id " + id + " não localizada no sistema.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Informe o id da demanda: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Demanda demanda = buscarDemanda(demandas, id);
                    if(demanda != null) {
                        demanda.realizarNovoChamado();
                        demanda.getDemanda();
                    } else {
                        System.out.println("Demanda com id " + id + " não localizada no sistema.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Saindo...");
                    break;
                }
                default: {
                    System.out.println("Opção inválida.");
                    break;
                }
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void inserirDemanda(Scanner scanner, ArrayList<Demanda> demandas) {
        System.out.print("Nome da demanda: ");
        String nome = scanner.nextLine();

        System.out.println("Escolha o tipo de problema:");
        System.out.println("1 - Troca de Transformador");
        System.out.println("2 - Troca de Fiação");
        System.out.println("3 - Queda de Energia");
        System.out.println("4 - Curto-circuito");
        System.out.println("5 - Instalação de Novo Ponto");

        int tipo = Integer.parseInt(scanner.nextLine());
        ClasseDeProblema problema = switch (tipo) {
            case 1 -> ClasseDeProblema.TROCA_TRANSFORMADOR;
            case 2 -> ClasseDeProblema.TROCA_FIACAO;
            case 3 -> ClasseDeProblema.QUEDA_ENERGIA;
            case 4 -> ClasseDeProblema.CURTO_CIRCUITO;
            case 5 -> ClasseDeProblema.INSTALACAO_PONTO;
            default -> ClasseDeProblema.INSTALACAO_PONTO;
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

        System.out.print("Região (Centro, Bairro, Rural): ");
        String regiao = scanner.nextLine();

        System.out.print("Consumo médio da região (GW/h): ");
        double consumoMedio = Double.parseDouble(scanner.nextLine());

        Demanda demanda = new Demanda(nome, problema, distanciaSede, distanciaVeiculo, custoPecas, custoMaoDeObra, custoHoraParada, custoEquipamentos, regiao, consumoMedio);

        demandas.add(demanda);
        System.out.println("Demanda registrada com sucesso!");
    }

    private static Demanda buscarDemanda(ArrayList<Demanda> demandas, int id) {
        for(Demanda demanda : demandas) {
            if(demanda.getId() == id) {
                return demanda;
            }
        }
        return null;
    }
}
