package graph;

import graph.matrix.MatrixGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.BinaryOperator;

/**
 *
 * @author DEI-ISEP
 *
 */
public class Algorithms {
    /** Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
//    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
//        LinkedList<V> result = null;
//
//        //check if the vertex exists
//        if (g.validVertex(vert)) {
//            result = new LinkedList<>();
//
//            //boolean array with the known vertices
//            boolean[] knownVertices = new boolean[g.numVertices()];
//
//            //auxiliary linked linked
//            LinkedList<V> auxStack = new LinkedList<>();
//
//            //adds the first vertex to the aux list
//            auxStack.addFirst(vert);
//
//            //if the vertex of the aux list exists and has a key then puts a true in the known vertices array
//            knownVertices[g.key(auxStack.getFirst())] = true;
//
//            //adds the vertex to the final list
//            result.add(vert);
//
//            //while there are vertexes to see
//            while (!auxStack.isEmpty()) {
//                //for a temporary edge that represents all the edges that depart from the vertex in question
//                for (Edge<V, E> tmpEdge : g.outgoingEdges(auxStack.getFirst())) {
//                    //by departing from the initial vertex and using an edge to reach the destiny vertex, if this last vertex
//                    //is not known then the known vertices array is updated as well as the final list
//                    if (!knownVertices[g.key(tmpEdge.getVDest())]) {
//                        knownVertices[g.key(tmpEdge.getVDest())] = true;
//                        auxStack.addLast(tmpEdge.getVDest());
//                        result.add(tmpEdge.getVDest());
//                    }
//                }
//                auxStack.removeFirst();
//            }
//        }
//
//        return result;
//    }
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qbfs = new LinkedList<>();
        ArrayList<V> qaux = new ArrayList<>();
        boolean[] visited = new boolean[g.numVertices()];
        qbfs.add(vert);
        qaux.add(vert);
        visited[g.key(vert)] = true;
        while (!qaux.isEmpty()) {
            V vInf = qaux.remove(0);
            for (V vAdj : g.adjVertices(vInf)) {
                if (!visited[g.key(vAdj)]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[g.key(vAdj)] = true;
                }
            }

        }
        return qbfs;
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vOrig vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs return LinkedList with vertices of depth-first search
     */
//    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
//        //index of the current vertex
//        int currIdx = g.key(vOrig);
//        //updates the array of the visited vertexes with the initial vertex
//        visited[currIdx] = true;
//        //adds to the queue
//        qdfs.addLast(vOrig);
//        //by departing from the initial vertex and using an edge to reach the destiny vertex, if this last vertex
//        //has not being visited yet then the DepthFirstSearch is called again
//        for (Edge<V, E> tmpEdge : g.outgoingEdges(vOrig)) {
//            V vDest = tmpEdge.getVDest();
//            int destIdx = g.key(vDest);
//            if (!visited[destIdx]) {
//                Algorithms.DepthFirstSearch(g, vDest, visited, qdfs);
//            }
//        }
//    }
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.key(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (!visited[g.key(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
    }

    /** Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vert vertex of graph g that will be the source of the search

     * @return a LinkedList with the vertices of depth-first search
     */
//    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
//        LinkedList<V> result = null;
//
//        if (g.validVertex(vert)) {
//            boolean[] visited = new boolean[g.numVertices()];
//            result = new LinkedList<>();
//
//            Algorithms.DepthFirstSearch(g, vert, visited, result);
//        }
//
//        return result;
//    }
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qdfs = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];
        qdfs.add(vert);
        visited[g.key(vert)] = true;
        for (V vAdj : g.adjVertices(vert)) {
            if (!g.validVertex(vAdj)) {
                return null;
            }

            if (!visited[g.key(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
        return qdfs;
    }

    /** Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
//    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
//                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
//        //gets the key associated to the origin vertex
//        int origIdx = g.key(vOrig);
//        //if the vertex has not been visited yet, then it is used the equals method to verify if they are the same
//        if (!visited[origIdx]) {
//            if (vOrig.equals(vDest)) {
//                //current path list updated
//                path.addLast(vOrig);
//                LinkedList<V> clone = new LinkedList<>(path);
//                //all paths list updated
//                paths.add(clone);
//                path.removeLast();
//            } else {
//                //vertex visited
//                visited[origIdx] = true;
//                //current path list updated
//                path.addLast(vOrig);
//                //by departing from the initial vertex and using an edge to reach the destiny vertex, if both are different
//                //the function is called again to continue the path
//                for (Edge<V, E> tmpEdge : g.outgoingEdges(vOrig)) {
//                    allPaths(g, tmpEdge.getVDest(), vDest, visited, path, paths);
//                }
//                path.removeLast();
//                visited[origIdx] = false;
//            }
//        }
//    }
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        visited[g.key(vOrig)] = true;
        path.push(vOrig);
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.push(vDest);
                paths.add(revPath(path));
                path.pop();
            } else {
                if (!visited[g.key(vAdj)]) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }
            }
        }
        V vertex = path.pop();
        visited[g.key(vertex)] = false;
    }

    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty())
            pathrev.push(pathcopy.pop());

        return pathrev;
    }

    /** Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
//    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
//        ArrayList<LinkedList<V>> result = new ArrayList<>();
//
//        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
//            boolean[] visited = new boolean[g.numVertices()];
//            LinkedList<V> path = new LinkedList<>();
//
//            allPaths(g, vOrig, vDest, visited, path, result);
//        }
//
//        return result;
//    }

    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        LinkedList<V> path = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];
        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            allPaths(g, vOrig, vDest, visited, path, paths);
        }

        return paths;

    }


    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V [] pathKeys, E [] dist) {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    /** Shortest-path between two vertices
     *
     * @param g graph
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** Shortest-path between a vertex and all other vertices
     *
     * @param g graph
     * @param vOrig start vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
//    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts,
//                                       int [] pathKeys, LinkedList<V> path) {
//        //gets the key to the last vertex
//        int destIdx = g.key(vDest);
//        //if the key is valid
//        if (pathKeys[destIdx] > -1) {
//            int pathIdx = destIdx;
//            //while the key is valid
//            while (pathKeys[pathIdx] > -1) {
//                //adds the vertex to the path
//                path.addFirst(verts[pathIdx]);
//                pathIdx = pathKeys[pathIdx];
//            }
//            path.addFirst(vOrig);
//        } else if (vOrig.equals(vDest)) {
//            path.addFirst(vOrig);
//        }
//    }

    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {
        path.push(vDest);
        int vKey = pathKeys[g.key(vDest)];
        if (vKey != -1) {
            vDest = verts[vKey];
            getPath(g, vOrig, vDest, verts, pathKeys, path);
        }

    }

    /** Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g initial graph
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    public static <V,E> MatrixGraph <V,E> minDistGraph(Graph <V,E> g, Comparator<E> ce, BinaryOperator<E> sum) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

}