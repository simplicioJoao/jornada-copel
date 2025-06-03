import Veiculo.Veiculo;
import Veiculo.Carro;
import Veiculo.Caminhonete;
import Veiculo.Caminhao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeDemandas gerenciadorDeDemandas = new GerenciadorDeDemandas();

        long tempo = 0;
        int opcao;
        do {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1 - Inserir nova demanda");
            System.out.println("2 - Listar demandas ordenadas por score");
            System.out.println("3 - Editar score de uma demanda manualmente");
            System.out.println("4 - Fazer novo chamado para uma demanda existente");
            System.out.println("5 - Simular passagem de tempo (30 minutos)");
            System.out.println("6 - Finalizar uma demanda");
            System.out.println("7 - Adicionar veículo à frota");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1: {
                    if(gerenciadorDeDemandas.gerenciadorDeVeiculos.veiculos.isEmpty()) {
                        System.out.println("Não há veículos cadastrados na frota no momento.");
                    } else {
                        System.out.print("Nome do solicitante: ");
                        String nome = scanner.nextLine();

                        System.out.println("Escolha o tipo de problema:");
                        System.out.println("1 - Troca de Transformador");
                        System.out.println("2 - Troca de Fiação");
                        System.out.println("3 - Queda de Energia");
                        System.out.println("4 - Curto-circuito");
                        System.out.println("5 - Instalação de Novo Ponto");
                        int tipo = Integer.parseInt(scanner.nextLine());

                        TipoDeDemanda tipoDeDemanda = null;
                        switch (tipo) {
                            case 1:
                                tipoDeDemanda = new TipoDeDemanda("Troca de Transformador", 5.0, 60, "Caminhão");
                                break;
                            case 2:
                                tipoDeDemanda = new TipoDeDemanda("Troca de Fiação", 4.0, 45, "Caminhonete");
                                break;
                            case 3:
                                tipoDeDemanda = new TipoDeDemanda("Queda de Energia", 3.5, 30, "Carro");
                                break;
                            case 4:
                                tipoDeDemanda = new TipoDeDemanda("Curto-circuito", 3.0, 40, "Carro");
                                break;
                            case 5:
                                tipoDeDemanda = new TipoDeDemanda("Instalação de Novo Ponto", 2.0, 50, "caminhonete");
                                break;
                            default:
                                System.out.println("Tipo inválido.");
                                break;
                        }

                        System.out.print("Distância até a sede (km): ");
                        double distanciaSede = Double.parseDouble(scanner.nextLine());

                        System.out.print("Distância do veículo (km): ");
                        double distanciaVeiculo = Double.parseDouble(scanner.nextLine());

                        System.out.print("Custo de peças (R$): ");
                        double custoPecas = Double.parseDouble(scanner.nextLine());

                        System.out.print("Custo de mão de obra (R$): ");
                        double custoMaoDeObra = Double.parseDouble(scanner.nextLine());

                        System.out.print("Custo por hora de parada (R$): ");
                        double custoPorHoraParada = Double.parseDouble(scanner.nextLine());

                        System.out.print("Custo de equipamentos (R$): ");
                        double custoEquipamentos = Double.parseDouble(scanner.nextLine());

                        System.out.print("Região (1-Centro, 2-Bairro, 3-Rural): ");
                        String regiao = scanner.nextLine();

                        System.out.print("Consumo médio da região (GW/h): ");
                        double consumoMedio = Double.parseDouble(scanner.nextLine());

                        Demanda demanda = new Demanda(nome, tipoDeDemanda, distanciaSede, distanciaVeiculo, custoPecas, custoMaoDeObra, custoPorHoraParada, custoEquipamentos, regiao, consumoMedio);

                        boolean criouDemanda = gerenciadorDeDemandas.inserirDemanda(demanda);

                        if (criouDemanda)
                            System.out.println("Demanda criada com sucesso!");
                    }
                    break;
                }
                case 2: {
                    System.out.println(gerenciadorDeDemandas.exibirListas());
                    break;
                }
                case 3: {
                    System.out.println("Informe o id da demanda: ");
                    String id = scanner.nextLine();

                    Demanda demanda = gerenciadorDeDemandas.buscarDemanda(id);
                    if(demanda != null) {
                        System.out.println("Informe o novo score da demanda de id " + id + ": ");
                        double score = Double.parseDouble(scanner.nextLine());
                        demanda.setScore(score);

                        System.out.println("Score atualizado de forma manual com sucesso!");
                        demanda.getDemanda();
                    } else {
                        System.out.println("Demanda com id " + id + " não localizada na lista de demandas em espera.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Informe o id da demanda: ");
                    String id = scanner.nextLine();

                    Demanda demanda = gerenciadorDeDemandas.buscarDemanda(id);
                    if(demanda != null) {
                        gerenciadorDeDemandas.realizarNovoChamado(demanda);
                        demanda.getDemanda();
                        System.out.println("Novo chamado realizado para a demanda com id " + id + " realizado cmo sucesso.");
                    } else {
                        System.out.println("Demanda com id " + id + " não localizada na lista de demandas em espera.");
                    }
                    break;
                }
                case 5: {
                    tempo = gerenciadorDeDemandas.simularPassagemDeTempo(tempo);
                    System.out.println("Tempo atual: " + tempo + " minutos");
                    System.out.println(gerenciadorDeDemandas.exibirListas());
                    break;
                }
                case 6: {
                    System.out.print("Digite o id da demanda a ser finalizada: ");
                    String id = scanner.nextLine();
                    System.out.println(gerenciadorDeDemandas.finalizarDemanda(id));
                    break;
                }
                case 7: {
                    int opcaoVeiculo = 0;
                    do {
                        System.out.println("1 - Carro");
                        System.out.println("2 - Caminhonete");
                        System.out.println("3 - Caminhão");

                        System.out.println("Informe o tipo de veículo que será adicionado: ");
                        opcaoVeiculo = Integer.parseInt(scanner.nextLine());

                        switch (opcaoVeiculo) {
                            case 1: {
                                Veiculo veiculo = new Carro();
                                gerenciadorDeDemandas.gerenciadorDeVeiculos.adicionarVeiculo(veiculo);
                                break;
                            }
                            case 2: {
                                Veiculo veiculo = new Caminhonete();
                                gerenciadorDeDemandas.gerenciadorDeVeiculos.adicionarVeiculo(veiculo);
                                break;
                            }
                            case 3: {
                                Veiculo veiculo = new Caminhao();
                                gerenciadorDeDemandas.gerenciadorDeVeiculos.adicionarVeiculo(veiculo);
                                break;
                            }
                            default: {
                                System.out.println("Veículo inválido.");
                                break;
                            }
                        }
                    } while(opcaoVeiculo < 1 || opcaoVeiculo > 3);
                    System.out.println("Veículo adicionado à frota com sucesso.");
                    break;
                }
                case 8: {
                    System.out.println("Saindo...");
                    break;
                }
                default: {
                    System.out.println("Opção inválida.");
                    break;
                }
            }
        } while (opcao != 8);

        scanner.close();
    }
}