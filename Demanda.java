public class Demanda {
    public String nome;
    public ClasseDeProblema classeProblema;
    public double distanciaSede;
    public double distanciaVeiculo;
    public double custoPecas;
    public double custoMaoDeObra;
    public double custoPorHoraParada;
    public double custoEquipamentos;
    public int recorrencia;
    public String regiao;
    public double consumoMedio;
    public double score;

    public Demanda(String nome, ClasseDeProblema classeProblema, double distanciaSede, double distanciaVeiculo,
                   double custoPecas, double custoMaoDeObra, double custoPorHoraParada, double custoEquipamentos, String regiao, double consumoMedio) {

        this.nome = nome;
        this.classeProblema = classeProblema;
        this.distanciaSede = distanciaSede;
        this.distanciaVeiculo = distanciaVeiculo;
        this.custoPecas = custoPecas;
        this.custoMaoDeObra = custoMaoDeObra;
        this.custoPorHoraParada = custoPorHoraParada;
        this.custoEquipamentos = custoEquipamentos;
        this.recorrencia = 1;
        this.regiao = regiao;
        this.consumoMedio = consumoMedio;

        calcularScore();
    }

    private void calcularScore() {
        double custoTotal = custoPecas + custoMaoDeObra + custoPorHoraParada + custoEquipamentos;
        double distanciaTotal = distanciaSede + distanciaVeiculo;
        double pesoRegiao = regiao.equalsIgnoreCase("Rural") ? 1.2 : (regiao.equalsIgnoreCase("Bairro") ? 1.1 : 1.0);

        this.score = (consumoMedio * 0.4 + recorrencia * 10 + custoTotal * 0.2 - distanciaTotal * 0.1) * pesoRegiao * classeProblema.getFator();
    }

    public String getDemanda() {
        return String.format("Demanda: %s | Score: %.2f | Tipo: %s | Região: %s",
                nome, score, classeProblema.getNome(), regiao);
    }
}
