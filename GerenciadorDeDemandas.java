import java.util.ArrayList;
import Veiculo.Veiculo;

public class GerenciadorDeDemandas {
    // Inicia as constantes para mudar a cor do texto de acordo com a fila que se encontra a demanda
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

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

    public long simularPassagemDeTempo(long tempo, GerenciadorDeVeiculos gerenciadorDeVeiculos) {
        tempo += 30;

        // adiciona 30 minutos ao tempo de espera das demandas das listas de demandas em espera e de demandas com veículo a caminho
        for(Demanda demanda : demandasEmEspera) {
            demanda.setTempoDeEspera(demanda.getTempoDeEspera() + 30);
        }
        for(Demanda demanda : demandasACaminho) {
            demanda.setTempoDeEspera(demanda.getTempoDeEspera() + 30);
        }

        moverDeEsperaParaACaminho(tempo, gerenciadorDeVeiculos);
        moverDeACaminhoParaSendoRealizada(tempo);

        // atualiza o score de todas as demandas após a passagem do tempo
        for (Demanda d : demandasEmEspera) d.calcularScore();
        for (Demanda d : demandasACaminho) d.calcularScore();
        for (Demanda d : demandasSendoRealizadas) d.calcularScore();

        return tempo;
    }

    public void moverDeEsperaParaACaminho(long tempo, GerenciadorDeVeiculos gerenciadorDeVeiculos) {
        int i = 0;
        while (i < demandasEmEspera.size()) {
            Demanda d = demandasEmEspera.get(i);
            String tipoNecessario = d.getTipoDeDemanda().getVeiculoNecessario();

            // verifica se há veículo disponível para realizar a demanda
            Veiculo veiculo = gerenciadorDeVeiculos.buscarVeiculoDisponivel(tipoNecessario);

            if (veiculo != null) {
                // se houver veículo disponível, então altera o atributo disponivel para false e atribui o veículo encontrado à demanda atual
                veiculo.setDisponivel(false);
                d.setVeiculo(veiculo);
                d.getVeiculo().setTempoInicioDeslocamento(tempo);

                // move da lista de demandas em espera para a lista de demandas com veículo a caminho
                demandasACaminho.add(d);
                demandasEmEspera.remove(i);
            } else {
                i++; // só passa para o próximo índice se não remover uma demanda da lista de demandas em espera
            }
        }
    }

    public void moverDeACaminhoParaSendoRealizada(long tempo) {
        for (int i = 0; i < demandasACaminho.size(); i++) {
            Demanda demanda = demandasACaminho.get(i);

            double velocidade = demanda.getVeiculo().getVelocidadeMediaKmPorMinuto();
            double tempoNecessario = demanda.getDistanciaTotal() / velocidade;
            long tempoDecorrido = tempo - demanda.getVeiculo().getTempoInicioDeslocamento();

            // se o tempo decorrido for maior que o tempo necessário para o deslocamento do veículo, então move a demanda para a lista de demandas sendo realizadas
            if (tempoDecorrido >= tempoNecessario) {
                demandasSendoRealizadas.add(demanda);
                demandasACaminho.remove(i);

                i--; // corrige o índice após remoção
            }
        }
    }

    public String finalizarDemanda(String id) {
        int i = 0;
        while (i < demandasSendoRealizadas.size()) {
            Demanda demanda = demandasSendoRealizadas.get(i);

            // se encontrar uma demanda com o id informado, então disponibiliza o veículo novamente e o desvincula da demanda
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