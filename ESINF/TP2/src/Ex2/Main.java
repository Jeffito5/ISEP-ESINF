package Ex2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Luís Araújo
 */
public class Main {
    Map<Integer, LinkedList<Integer>> KsubLists(LinkedList<Integer> list, ArrayList<Integer> centers) {
        //valor de retorno
        Map<Integer, LinkedList<Integer>> mapa = new HashMap<>(centers.size());
        //sublistas dos elementos mais próximos para cada centro
        LinkedList<LinkedList<Integer>> sublists = new LinkedList<>();

        //vai criar uma lista para cada centro
        for (int i = 0; i < centers.size(); i++) {
            sublists.add(new LinkedList<>());
        }

        //5, 6 , 7 8 9
        for (Integer num : list) {
            //valor máximo de um inteiro
            Integer min = Integer.MAX_VALUE;
            int index = 0;

            for (int i = 0; i < centers.size(); i++) {
                //faz a diferença entre o número do array com um dos centros. se a diferença desse número em absoluto for menor que o valor máximo então
                //o minímo fica com o valor
                if (Math.abs(num - centers.get(i)) < min) {
                    min = Math.abs(num - centers.get(i));
                    index = i;
                }
            }
            //adiciona o número próximo no centro que é o index
            sublists.get(index).add(num);
        }
        //vai colocar
        for (int i = 0; i < centers.size(); i++) {
            mapa.put(centers.get(i), sublists.get(i));
        }
        return mapa;
    }
}
