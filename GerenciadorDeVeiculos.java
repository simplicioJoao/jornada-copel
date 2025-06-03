import Veiculo.Veiculo;
import Veiculo.Carro;
import Veiculo.Caminhonete;
import Veiculo.Caminhao;

import java.util.ArrayList;

public class GerenciadorDeVeiculos {
    ArrayList<Veiculo> veiculos = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo buscarVeiculoDisponivel(String tipoNecessario) {
        for (Veiculo v : veiculos) {
            if (v.getTipo().equalsIgnoreCase(tipoNecessario) && v.isDisponivel()) {
                return v;
            }
        }
        return null;
    }
}
