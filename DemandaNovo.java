package simulacao;

public class DemandaNovo {

	private String nome;
	private int score;

	// CONSTRUTORES - GETTERS / SETTERS
	
	public DemandaNovo() {}

	public DemandaNovo(String nome, int score) {
		this.nome = nome;
		this.score = score;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	// Método para alterar o score
	public void alterarScore() {
		// Gera um número aleatório de 1 a 5
		int scoreAdicionar = (int)(Math.random() * 5) + 1;
		// Adiciona o peso aleatório gerado ao score da demanda
		setScore(score += scoreAdicionar);
	}
	
}

