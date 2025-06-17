package Veiculo;

public class Caminhonete extends Veiculo {
    public Caminhonete() {
        this.velocidadeMediaKmPorMinuto = 1.0; // 60 km/h
    }

    @Override
    public String getTipo() {
        return "Caminhonete";
    }
}
