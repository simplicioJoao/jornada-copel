package simulacao;

import java.util.Scanner;
import java.util.ArrayList;

public class TempoExecucao {
	public static void main(String[] args) {
		// Inicia uma variável para controlar o loop (infinito)
		boolean x = false;
		
		// Inicia uma variável para salvar o nome do usuário
		String nome;
		
		// Scanner...
		Scanner teclado = new Scanner(System.in);
		
		// Lista para guardar as demandas
		ArrayList<DemandaNovo> demandas = new ArrayList<DemandaNovo>();
		
		// Laço for para adicionar as demandas a lista (com nome e score inicial = 0)
		for(int i = 0; i < 2; i++) {
			System.out.println("Informe seu nome: ");
			nome = teclado.next(); 
			DemandaNovo demanda = new DemandaNovo(nome, 1);
			demandas.add(demanda);
		}
		
		teclado.close();
		
		// Marca o início do tempo após a inserção em ms
		long inicio = System.currentTimeMillis();

		// Loop infinito
		while(!x) {	
			for(DemandaNovo demanda : demandas) {
				 // Exibe o score antes do incremento de score
				 System.out.println("\nAntes da alteração: " + demanda.getNome() + " | Score: " + demanda.getScore());
				 
				 // Altera o score utilizando o método alterarScore da classe "Demanda"
		         demanda.alterarScore();
		         
		         // Exibe o score depois do incremento de score
		         System.out.println("\nDepois da alteração: " + demanda.getNome() + " | Score: " + demanda.getScore());
			}
			
			// Marca o fim (ms)
			long fim = System.currentTimeMillis();
			// Calcula a diferença entre o tempo de início e fim
	        long tempoExecucaoMs = fim - inicio;

	        // Converte o tempo em ms para s
	        double tempoExecucaoSegundos = tempoExecucaoMs / 1000.0;
	        // Converte o tempo de double para int
	        int tempo = (int) tempoExecucaoSegundos;

	        //double tempoExecucaoMinutos = tempoExecucaoMs / 60000.0;

	        // Exibe o tempo de execução em segundos
	        System.out.println("\nTempo de execução: " + tempo + " segundos");
	        //System.out.println("Tempo de execução: " + tempoExecucaoMinutos + " minutos");
			
			try {
				// Pausa a execução por 5000ms (5s)
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }
}