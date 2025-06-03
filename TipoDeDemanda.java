public class TipoDeDemanda {
    private String descricao;
    private long tempoDeEspera;
    private long tempoMedioDeAtendimento;
    private double peso;
    private String veiculoNecessario;

    public TipoDeDemanda(String descricao, double peso, int tempoMedioDeAtendimento, String veiculoNecessario) {
        this.descricao = descricao;
        this.peso = peso;
        this.tempoMedioDeAtendimento = tempoMedioDeAtendimento;
        this.tempoDeEspera = 0;
        this.veiculoNecessario = veiculoNecessario;
    }

    public String getVeiculoNecessario() {
        return veiculoNecessario;
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