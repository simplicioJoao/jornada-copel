public class ClasseDeProblema {
    private final String nome;
    private final double fator;

    public static final ClasseDeProblema EMERGENCIAL = new ClasseDeProblema("Emergencial", 5.0);
    public static final ClasseDeProblema CRITICO = new ClasseDeProblema("Crítico", 4.0);
    public static final ClasseDeProblema URGENTE = new ClasseDeProblema("Urgente", 3.0);
    public static final ClasseDeProblema MODERADO = new ClasseDeProblema("Moderado", 2.0);
    public static final ClasseDeProblema NORMAL = new ClasseDeProblema("Normal", 1.0);

    public ClasseDeProblema(String nome, double fator) {
        this.nome = nome;
        this.fator = fator;
    }

    public String getNome() {
        return nome;
    }

    public double getFator() {
        return fator;
    }

    @Override
    public String toString() {
        return nome + " (Fator: " + fator + ")";
    }
}
