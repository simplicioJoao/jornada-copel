package Veiculo;

public abstract class Veiculo {
    protected double velocidadeMediaKmPorMinuto;
    protected boolean disponivel = true;
    protected long tempoInicioDeslocamento;

    public double getVelocidadeMediaKmPorMinuto() {
        return velocidadeMediaKmPorMinuto;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public long getTempoInicioDeslocamento() {
        return tempoInicioDeslocamento;
    }

    public void setTempoInicioDeslocamento(long tempoInicioDeslocamento) {
        this.tempoInicioDeslocamento = tempoInicioDeslocamento;
    }

    public abstract String getTipo();
}
