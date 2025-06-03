package Veiculo;

public class Veiculo {
    protected String tipo;
    protected double velocidadeMediaKmPorMinuto;
    protected boolean disponivel = true;
    protected long tempoInicioDeslocamento;

    public String getTipo() {
        return tipo;
    }

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

    @Override
    public String toString() {
        return tipo + " - " + (disponivel ? "Disponível" : "Em uso");
    }
}
