public class ClasseDeProblema {
    private String descricao;
    private int tempoDeEspera;
    private int tempoMedioDeAtendimento;
    private double peso;

    public static final ClasseDeProblema TROCA_TRANSFORMADOR = new ClasseDeProblema("Troca de Transformador", 5.0, 60);
    public static final ClasseDeProblema TROCA_FIACAO = new ClasseDeProblema("Troca de Fiação", 4.0, 45);
    public static final ClasseDeProblema QUEDA_ENERGIA = new ClasseDeProblema("Queda de Energia", 3.5, 30);
    public static final ClasseDeProblema CURTO_CIRCUITO = new ClasseDeProblema("Curto-circuito", 3.0, 40);
    public static final ClasseDeProblema INSTALACAO_PONTO = new ClasseDeProblema("Instalação de Novo Ponto", 2.0, 50);

    public ClasseDeProblema(String descricao, double peso, int tempoMedioDeAtendimento) {
        this.descricao = descricao;
        this.peso = peso;
        this.tempoDeEspera = 0;
        this.tempoMedioDeAtendimento = tempoMedioDeAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "\nDescrição: " + descricao + "\nAtendimento Médio: " + tempoMedioDeAtendimento + " min\nEspera: " + tempoDeEspera + " min";
    }
}
