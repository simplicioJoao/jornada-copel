import java.util.ArrayList;

public class BubbleSort {
    public static void bubbleSort(ArrayList<Demanda> demandas) {
        for (int i = 1; i < demandas.size(); i++) {
            boolean trocou = false;
            for (int j = 0; j < demandas.size() - i; j++) {
                if (demandas.get(j).score > demandas.get(j + 1).score) {
                    Demanda temp = demandas.get(j);
                    demandas.set(j, demandas.get(j + 1));
                    demandas.set(j + 1, temp);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }
}