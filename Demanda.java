import Veiculo.Veiculo;

public class Demanda {
    private static int contador = 0;

    private String id;
    private String nome;
    private TipoDeDemanda tipoDeDemanda;
    private double distanciaEquipeAteSede;
    private double distanciaSedeAteLocal;
    private double distanciaTotal;
    private double custoPecas;
    private double custoMaoDeObra;
    private double custoPorHoraParada;
    private double custoEquipamentos;
    private int recorrencia;
    private String regiao;
    private double consumoMedio;
    private double score;
    private Veiculo veiculo;

    public Demanda(String nome, TipoDeDemanda tipoDeDemanda, double distanciaEquipeAteSede, double distanciaSedeAteLocal,
                   double custoPecas, double custoMaoDeObra, double custoPorHoraParada, double custoEquipamentos, String regiao, double consumoMedio) {

        this.id = gerarId();
        this.nome = nome;
        this.tipoDeDemanda = tipoDeDemanda;
        this.distanciaEquipeAteSede = distanciaEquipeAteSede;
        this.distanciaSedeAteLocal = distanciaSedeAteLocal;
        this.distanciaTotal = this.distanciaEquipeAteSede + this.distanciaSedeAteLocal;
        this.custoPecas = custoPecas;
        this.custoMaoDeObra = custoMaoDeObra;
        this.custoPorHoraParada = custoPorHoraParada;
        this.custoEquipamentos = custoEquipamentos;
        this.recorrencia = 1;
        this.regiao = regiao;
        this.consumoMedio = consumoMedio;

        calcularScore();
    }

    public void calcularScore() {
        double custoTotal = custoPecas + custoMaoDeObra + custoPorHoraParada + custoEquipamentos;
        double pesoRegiao = regiao.equalsIgnoreCase("Rural") ? 1.2 : (regiao.equalsIgnoreCase("Bairro") ? 1.1 : 1.0);

        this.score = consumoMedio * 0.4
                        + recorrencia * 10
                        + custoTotal * 0.2
                        + distanciaTotal * 0.1
                        + tipoDeDemanda.getTempoDeEspera() * 0.3
                        + tipoDeDemanda.getTempoMedioDeAtendimento() * 0.1
                        + pesoRegiao + tipoDeDemanda.getPeso();
    }

    private String gerarId() {
        contador++;
        int valor = contador;

        char[] digitos = new char[5];
        int i = 4;

        while (valor > 0 && i >= 0) {
            int digito = valor % 10;
            digitos[i] = (char) ('0' + digito);
            valor /= 10;
            i--;
        }

        while (i >= 0) {
            digitos[i] = '0';
            i--;
        }

        return new String(digitos);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoDeDemanda getTipoDeDemanda() {
        return tipoDeDemanda;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public int getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(int recorrencia) {
        this.recorrencia = recorrencia;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public String getDemanda() {
        if(veiculo != null) {
            return String.format("ID da demanda: %s | Solicitante: %s | Score: %.2f | Tipo: %s | Veículo utilizado: %s | Região: %s | Tempo de espera: %d min",
                    id, nome, score, tipoDeDemanda.getDescricao(), veiculo.getTipo(), regiao, tipoDeDemanda.getTempoDeEspera());
        } else {
            return String.format("ID da demanda: %s | Solicitante: %s | Score: %.2f | Tipo: %s | Região: %s | Tempo de espera: %d min",
                    id, nome, score, tipoDeDemanda.getDescricao(), regiao, tipoDeDemanda.getTempoDeEspera());
        }
    }
}