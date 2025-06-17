package Veiculo;

public class Caminhao extends Veiculo {
    public Caminhao() {
        this.velocidadeMediaKmPorMinuto = 0.8; // 48 km/h
    }

    @Override
    public String getTipo() {
        return "Caminhão";
    }
}
