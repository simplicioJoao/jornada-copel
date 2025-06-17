package Veiculo;

public class Carro extends Veiculo {
    public Carro() {
        this.velocidadeMediaKmPorMinuto = 1.5; // 90 km/h
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}
