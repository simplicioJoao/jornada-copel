import java.time.LocalDate;

public class Demanda {
    public String nome;
    public int prioridade;
    public double distanciaEquipeSede;
    public double distanciaSedeLocal;
    public double distanciaTotal;
    public double custoReparo;
    public double prejuizoFiscal;
    public String tempoDeEspera; // agora é String no formato "DD/MM/AAAA"
    public double score;

    public Demanda(String nome, double distanciaSedeLocal) {
        this.nome = nome;
        this.distanciaSedeLocal = distanciaSedeLocal;
    }
}