package examples.matrix;

import graph.Algorithms;
import graph.Graph;
import graph.matrix.MatrixGraph;

import java.util.*;

public class LabyrinthCheater {
    private final Graph<Room, Door> labirinty;

    public LabyrinthCheater() {
        labirinty = new MatrixGraph<Room, Door>(false);
    }

    /**
     * Inserts a new room in the map
     *
     * @param name    the room name
     * @param hasExit wheather the room has an exit
     * @return false if city exists in the map
     */

    public boolean insertRoom(String name, boolean hasExit) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Inserts a new door in the map
     * fails if room does not exist or door already exists
     *
     * @param from room
     * @param to   room
     * @return false if a room does not exist or door already exists between rooms
     */

    public boolean insertDoor(String from, String to) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns a list of rooms which are reachable from one room
     *
     * @param room room
     * @return list of room names or null if room does not exist
     */

    public Collection<String> roomsInReach(String room) {
        if (!labirinty.validVertex(new Room(room, false)))
            return null;

        LinkedList<Room> lst = Algorithms.DepthFirstSearch(labirinty, new Room(room, false));
        ArrayList<String> lstrooms = new ArrayList<>();
        for (Room r : lst)
            lstrooms.add(r.name);
        return lstrooms;
    }

    /**
     * Returns the nearest room with an exit
     *
     * @param room from room
     * @return room name or null if from room does not exist or there is no reachable exit
     */

    public String nearestExit(String room) {
        if (!labirinty.validVertex(new Room(room, false)))
            return null;

        LinkedList<Room> lst = Algorithms.BreadthFirstSearch(labirinty, new Room(room, false));

        for (Room r : lst)
            if (r.hasExit)
                return r.name;

        return null;
    }

    /**
     * Returns the shortest path to the nearest room with an exit
     *
     * @param from from room
     * @return list of room names or null if from room does not exist or there is no reachable exit
     */
    public LinkedList<String> pathToExit(String from) {
        String nearExit = nearestExit(from);
        if (nearExit == null)
            return null;

        LinkedList<Room> shortPath = new LinkedList<>();
        shortPath = BFSshortestpath(new Room(from, false), new Room(nearExit, true));

        LinkedList<String> roomstoExit = new LinkedList<>();
        for (Room r : shortPath)
            roomstoExit.add(r.name);

        return roomstoExit;
    }

    private LinkedList<Room> BFSshortestpath(Room rOrig, Room rDest) {
        int numVertices = labirinty.numVertices();
        boolean ends = false;

        LinkedList<Room> qaux = new LinkedList<>();
        boolean[] visited = new boolean[numVertices]; //default:false
        Integer[] pred = new Integer[numVertices];
        for (int i = 0; i < labirinty.numVertices(); i++) {
            pred[i] = -1;
        }
        qaux.add(rOrig);
        while (!qaux.isEmpty() && !ends) {
            rOrig = qaux.remove();
            int vKey = labirinty.key(rOrig);
            visited[vKey] = true;
            for (Room rAdj : labirinty.adjVertices(rOrig)) {
                int adjKey = labirinty.key(rAdj);
                if (!visited[adjKey]) {
                    qaux.add(rAdj);
                    pred[adjKey] = vKey;

                    if (rAdj.equals(rDest))
                        ends = true;
                }
            }
        }

        LinkedList<Room> path = new LinkedList<>();
        path.addFirst(rDest);
        int i = labirinty.key(rDest);
        while (pred[i] != -1) {
            Room room = labirinty.vertex(pred[i]);
            path.addFirst(room);
            i = pred[i];
        }
        return path;
    }

    private static class Door {
    }

    private static class Room {
        public final String name;
        public final boolean hasExit;

        public Room(String n, boolean exit) {
            name = n;
            hasExit = exit;
        }

        /*
         * Implementation of equals
         * Comparison of rooms is by name only
         */
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Room)) return false;
            return name.equals(((Room) other).name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, hasExit);
        }
    }

//    private static class Door {
//    }

}
