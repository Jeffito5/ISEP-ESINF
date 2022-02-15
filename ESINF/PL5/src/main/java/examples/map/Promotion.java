package examples.map;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DEI-ESINF
 */
public class Promotion {
    private static String findPromotion(Graph<String, Integer> gp) {
        for (String v : gp.vertices()) {
            if (gp.inDegree(v) == 0) return v;
        }
        return null;
    }

    private static List<String> getPromotions(Graph<String, Integer> g, Integer n) {
        List<String> lr = new ArrayList<>();
        Graph<String, Integer> gp = g.clone();
        while ((lr.size() < n) && (gp.numVertices() > 0)) {
            String nxt = findPromotion(gp);
            if (nxt != null) {
                gp.removeVertex(nxt);
                lr.add(nxt);
            } else return null;
        }
        return lr;
    }
}
