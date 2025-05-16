public class Demanda {
    private int id;
    private String nome;
    private ClasseDeProblema classeProblema;
    private double distanciaSede;
    private double distanciaVeiculo;
    private double custoPecas;
    private double custoMaoDeObra;
    private double custoPorHoraParada;
    private double custoEquipamentos;
    private int recorrencia;
    private String regiao;
    private double consumoMedio;
    private double score;

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

    public void calcularScore() {
        double custoTotal = custoPecas + custoMaoDeObra + custoPorHoraParada + custoEquipamentos;
        double distanciaTotal = distanciaSede + distanciaVeiculo;
        double pesoRegiao = regiao.equalsIgnoreCase("Rural") ? 1.2 : (regiao.equalsIgnoreCase("Bairro") ? 1.1 : 1.0);

        this.score = (consumoMedio * 0.4 + recorrencia * 10 + custoTotal * 0.2 - distanciaTotal * 0.1) * pesoRegiao * classeProblema.getPeso();
    }

    public void realizarNovoChamado() {
        this.recorrencia++;
        calcularScore();
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
    	return nome;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDemanda() {
        return String.format("Demanda: %s | Score: %.2f | Descrição: %s | Região: %s",
                nome, score, classeProblema.getDescricao(), regiao);
    }
}
