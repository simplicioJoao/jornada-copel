import java.util.ArrayList;
import Veiculo.Veiculo;

public class GerenciadorDeDemandas {
    // Inicia as constantes para mudar a cor do texto de acordo com a fila que se encontra a demanda
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    GerenciadorDeVeiculos gerenciadorDeVeiculos = new GerenciadorDeVeiculos();

    ArrayList<Demanda> demandasEmEspera = new ArrayList<>();
    ArrayList<Demanda> demandasACaminho = new ArrayList<>();
    ArrayList<Demanda> demandasSendoRealizadas = new ArrayList<>();
    ArrayList<Demanda> demandasFinalizadas = new ArrayList<>();

    public boolean inserirDemanda(Demanda demanda) {
        demandasEmEspera.add(demanda);
        return true;
    }

    public Demanda buscarDemanda(String id) {
        for(Demanda demanda : demandasEmEspera) {
            if(demanda.getId().equals(id)) {
                return demanda;
            }
        }
        return null;
    }

    public void realizarNovoChamado(Demanda demanda) {
        demanda.setRecorrencia(demanda.getRecorrencia() + 1);
        demanda.calcularScore();
    }

    public long simularPassagemDeTempo(long tempo) {
        tempo += 30;

        for(Demanda demanda : demandasEmEspera) {
            demanda.getTipoDeDemanda().setTempoDeEspera(demanda.getTipoDeDemanda().getTempoDeEspera() + 30);
        }
        for(Demanda demanda : demandasACaminho) {
            demanda.getTipoDeDemanda().setTempoDeEspera(demanda.getTipoDeDemanda().getTempoDeEspera() + 30);
        }

        moverDeEsperaParaACaminho(tempo);
        moverDeACaminhoParaSendoRealizada(tempo);

        for (Demanda d : demandasEmEspera) d.calcularScore();
        for (Demanda d : demandasACaminho) d.calcularScore();
        for (Demanda d : demandasSendoRealizadas) d.calcularScore();

        return tempo;
    }

    public void moverDeEsperaParaACaminho(long tempo) {
        int i = 0;
        while (i < demandasEmEspera.size()) {
            Demanda d = demandasEmEspera.get(i);
            String tipoNecessario = d.getTipoDeDemanda().getVeiculoNecessario();

            Veiculo veiculo = gerenciadorDeVeiculos.buscarVeiculoDisponivel(tipoNecessario);

            if (veiculo != null) {
                veiculo.setDisponivel(false);
                d.setVeiculo(veiculo);
                d.getVeiculo().setTempoInicioDeslocamento(tempo);

                demandasACaminho.add(d);
                demandasEmEspera.remove(i);
            } else {
                i++; // tenta próxima demanda
            }
        }
    }

    public void moverDeACaminhoParaSendoRealizada(long tempo) {
        for (int i = 0; i < demandasACaminho.size(); i++) {
            Demanda demanda = demandasACaminho.get(i);

            double velocidade = demanda.getVeiculo().getVelocidadeMediaKmPorMinuto();
            double tempoNecessario = demanda.getDistanciaTotal() / velocidade;
            long tempoDecorrido = tempo - demanda.getVeiculo().getTempoInicioDeslocamento();

            if (tempoDecorrido >= tempoNecessario) {
                for (int j = i; j < demandasACaminho.size() - 1; j++) {
                    demandasACaminho.set(j, demandasACaminho.get(j + 1));
                }
                demandasACaminho.remove(demandasACaminho.size() - 1);
                demandasSendoRealizadas.add(demanda);

                i--; // corrigir índice após remoção
            }
        }
    }

    public String finalizarDemanda(String id) {
        int i = 0;
        while (i < demandasSendoRealizadas.size()) {
            Demanda demanda = demandasSendoRealizadas.get(i);

            if (demanda.getId().equals(id)) {
                demanda.getVeiculo().setDisponivel(true);
                demanda.setVeiculo(null);
                demandasFinalizadas.add(demanda);
                demandasSendoRealizadas.remove(i); // remove e não avança o índice
                return "Demanda " + demanda.getId() + " [FINALIZADA]";
            }

            i++; // só avança se não remover
        }

        return "Demanda com id " + id + " não localizada na lista de demandas em andamento.";
    }

    // Retorna as filas e suas demandas com as respectivas cores:
    // Vermelho - em espera
    // Amarelo - a caminho
    // Azul - sendo realizadas
    // Verde - concluídas
    // Reset - volta a cor normal
    public String exibirListas() {
        String saida = "";

        saida += ANSI_RED + exibirLista("Demandas em espera", demandasEmEspera) + ANSI_RESET;
        saida += ANSI_YELLOW + exibirLista("Demandas com veículo a caminho", demandasACaminho) + ANSI_RESET;
        saida += ANSI_BLUE + exibirLista("Demandas sendo realizadas", demandasSendoRealizadas) + ANSI_RESET;
        saida += ANSI_GREEN + exibirLista("Demandas finalizadas", demandasFinalizadas) + ANSI_RESET;

        return saida;
    }

    private String exibirLista(String nome, ArrayList<Demanda> lista) {
        BubbleSort.bubbleSort(lista);

        String demandas = nome + ":\n";
        for (Demanda demanda : lista) {
            demandas += demanda.getDemanda() + "\n";
        }
        return demandas + "\n";
    }
}