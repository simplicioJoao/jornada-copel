import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Demanda> demandas = new ArrayList<>();

        int opcao;
        do {
            System.out.print("1 - Inserir nova demanda");
            System.out.print("2 - Sair");

            System.out.print("Escolha uma opcao: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch(opcao) {
                case 1: {
                    inserirDemanda();
                    break;
                }
                case 2: {
                    break;
                }
                default: {
                    System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 2);
    }

    public static void inserirDemanda() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da demanda: ");
        String nome = scanner.nextLine();

        System.out.print("Informe a distância entre a sede e o local da chamada: ");
        double distanciaSedeLocal = Double.parseDouble(scanner.nextLine());

        Demanda demanda = new Demanda(nome, distanciaSedeLocal);
    }
}
