public class ClasseDeProblema {
    private final String nome;
    private int tempoDeEspera;
    private final int tempoMedioDeAtendimento;
    private final double fator;

    // Constantes: Nome, Fator de Peso, Tempo Médio de Atendimento
    public static final ClasseDeProblema TROCA_TRANSFORMADOR = new ClasseDeProblema("Troca de Transformador", 5.0, 60);
    public static final ClasseDeProblema TROCA_FIACAO = new ClasseDeProblema("Troca de Fiação", 4.0, 45);
    public static final ClasseDeProblema QUEDA_ENERGIA = new ClasseDeProblema("Queda de Energia", 3.5, 30);
    public static final ClasseDeProblema CURTO_CIRCUITO = new ClasseDeProblema("Curto-circuito", 3.0, 40);
    public static final ClasseDeProblema INSTALACAO_PONTO = new ClasseDeProblema("Instalação de Novo Ponto", 2.0, 50);

    public ClasseDeProblema(String nome, double fator, int tempoMedioDeAtendimento) {
        this.nome = nome;
        this.fator = fator;
        this.tempoDeEspera = 0;
        this.tempoMedioDeAtendimento = tempoMedioDeAtendimento;
    }

    public String getNome() {
        return nome;
    }

    public double getFator() {
        return fator;
    }

    @Override
    public String toString() {
        return nome + " (Fator: " + fator + ", Atendimento Médio: " + tempoMedioDeAtendimento + " min, Espera: " + tempoDeEspera + " min)";
    }
}
