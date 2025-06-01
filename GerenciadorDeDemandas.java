import java.util.ArrayList;
import java.util.Iterator;

public class GerenciadorDeDemandas {
    // Inicia as constantes para mudar a cor do texto
    // de acordo com a fila que se encontra a demanda
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    final double VELOCIDADE_KM_POR_HORA = 60.0;
    final double VELOCIDADE_KM_POR_MINUTO = VELOCIDADE_KM_POR_HORA / 60;

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

        moverDeACaminhoParaSendoRealizada(tempo);

        for (Demanda d : demandasEmEspera) d.calcularScore();
        for (Demanda d : demandasACaminho) d.calcularScore();
        for (Demanda d : demandasSendoRealizadas) d.calcularScore();

        return tempo;
    }

    public void moverDeACaminhoParaSendoRealizada(long tempo) {
        int i = 0;
        while (i < demandasACaminho.size()) {
            Demanda demanda = demandasACaminho.get(i);
            double tempoNecessario = demanda.getDistanciaTotal() / VELOCIDADE_KM_POR_MINUTO;
            long tempoDecorrido = tempo - demanda.getTempoInicioDeslocamento();

            if (tempoDecorrido >= tempoNecessario) {
                demandasSendoRealizadas.add(demanda);
                demandasACaminho.remove(i); // remove e NÃO avança o índice
                System.out.println("[CHEGOU AO LOCAL] " + demanda.getNome() + " após " + tempoDecorrido + " minutos.");
            } else {
                i++; // só avança se não remover
            }
        }
    }

    public void finalizarDemanda(String id) {
        int i = 0;
        while (i < demandasSendoRealizadas.size()) {
            Demanda demanda = demandasSendoRealizadas.get(i);

            if (demanda.getId().equals(id)) {
                demandasFinalizadas.add(demanda);
                demandasSendoRealizadas.remove(i); // remove e não avança o índice
                System.out.println("[FINALIZADA] Demanda '" + demanda.getNome() + "' foi finalizada com sucesso.");
                return;
            }

            i++; // só avança se não remover
        }

        System.out.println("Demanda com id " + id + " não encontrada na lista de demandas em andamento.");
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

    public void iniciarDeslocamento(long tempoAtual) {
        if (demandasEmEspera.isEmpty()) {
            System.out.println("Nenhuma demanda na lista de espera para iniciar deslocamento.");
            return;
        }

        Demanda demandaComMaiorScore = null;
        double maiorScore = Double.NEGATIVE_INFINITY;

        for (Demanda d : demandasEmEspera) {
            if (d.getScore() > maiorScore) {
                maiorScore = d.getScore();
                demandaComMaiorScore = d;
            }
        }

        if (demandaComMaiorScore != null) {
            demandaComMaiorScore.setTempoInicioDeslocamento(tempoAtual);

            demandasACaminho.add(demandaComMaiorScore);
            demandasEmEspera.remove(demandaComMaiorScore);

            System.out.println("[DESLOCAMENTO INICIADO] " + demandaComMaiorScore.getNome() +
                    " | Score: " + String.format("%.2f", demandaComMaiorScore.getScore()));
        }
    }
}