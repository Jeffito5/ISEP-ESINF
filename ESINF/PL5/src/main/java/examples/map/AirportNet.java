package examples.map;

import graph.Algorithms;
import graph.Edge;
import graph.Graph;
import graph.map.MapGraph;

import java.util.*;

/**
 * @author DEI-ESINF
 */
public class AirportNet {

    final private Graph<String, Route> airports;

    public AirportNet() {
        airports = new MapGraph<>(true);
    }

    public void addAirport(String a) {
        airports.addVertex(a);
    }

    public void addRoute(String a1, String a2, double miles, Integer passengers) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int keyAirport(String airport) {
        return airports.key(airport);
    }

    public String airportbyKey(int key) {
        ArrayList<String> vertices = airports.vertices();
        if (key < 0 || key > vertices.size())
            return null;

        return vertices.get(key);
    }

    public Integer trafficAirports(String airp1, String airp2) {
        Integer numPassagers = 0;
        Edge<String, Route> edge1 = airports.edge(airp1, airp2);
        Edge<String, Route> edge2 = airports.edge(airp2, airp1);
        if (edge1 != null)
            numPassagers = edge1.getWeight().passengers;
        if (edge2 != null)
            numPassagers += edge2.getWeight().passengers;

        return numPassagers;
    }

    public Double milesAirports(String airp1, String airp2) {
        Edge<String, Route> edge = airports.edge(airp1, airp2);
        if (edge != null)
            return null;
        return edge.getWeight().miles;
    }

    public Map<String, Integer> nRoutesAirport() {
        Map<String, Integer> m = new HashMap<>();

        for (String airp : airports.vertices()) {
            int grau = airports.outDegree(airp) + airports.inDegree(airp);
            m.put(airp, grau);
        }

        return m;
    }

    public List<ArrayList<String>> airpMaxMiles() {
        List<ArrayList<String>> airpMaxMiles = new LinkedList<>();
        double maxMiles = Double.MIN_VALUE;
        for (Edge<String, Route> edge : airports.edges()) {
            if (maxMiles <= edge.getWeight().miles) {
                if (maxMiles < edge.getWeight().miles) {
                    maxMiles = edge.getWeight().miles;
                    airpMaxMiles.clear();
                }
                airpMaxMiles.add(new ArrayList<>(Arrays.asList(edge.getVOrig(), edge.getVDest())));
            }
        }
        return airpMaxMiles;
    }

    public Boolean connectAirports(String airport1, String airport2) {
        LinkedList<String> qairports = Algorithms.DepthFirstSearch(airports, airport1);
        return qairports.contains(airport2);
    }

    @Override
    public String toString() {
        return airports.toString();
    }

    private static class Route {
        public final int passengers;
        public final double miles;

        public Route(int passengers, double miles) {
            this.passengers = passengers;
            this.miles = miles;
        }
    }
}