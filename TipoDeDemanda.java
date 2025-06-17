public class TipoDeDemanda {
    private String descricao;
    private double peso;
    private String veiculoNecessario;

    public TipoDeDemanda(String descricao, double peso, String veiculoNecessario) {
        this.descricao = descricao;
        this.peso = peso;
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
}