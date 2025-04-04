public class Demanda {
    private int id;
    private String nome;
    private ClasseDeProblema classeDeProblema;
    private double distanciaSede;
    private double distanciaVeiculo;
    private double custoPecas;
    private double custoMaoDeObra;
    private double custoPorHoraParada;
    private double custoEquipamentos;
    private int recorrenciaChamadas;
    private String regiao;
    private double consumoMedio;
    public double score;

    public Demanda(String nome) {
        this.nome = nome;
    }
}