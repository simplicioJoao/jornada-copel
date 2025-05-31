public class TipoDeDemanda {
    private String descricao;
    private long tempoDeEspera;
    private long tempoMedioDeAtendimento;
    private double peso;

    public TipoDeDemanda(String descricao, double peso, int tempoMedioDeAtendimento) {
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

    public long getTempoDeEspera() {
        return tempoDeEspera;
    }

    public void setTempoDeEspera(long tempoDeEspera) {
        this.tempoDeEspera = tempoDeEspera;
    }

    public long getTempoMedioDeAtendimento() {
        return tempoMedioDeAtendimento;
    }

    @Override
    public String toString() {
        return "\nDescrição: " + descricao + "\nAtendimento Médio: " + tempoMedioDeAtendimento + " min\nEspera: " + tempoDeEspera + " min";
    }
}