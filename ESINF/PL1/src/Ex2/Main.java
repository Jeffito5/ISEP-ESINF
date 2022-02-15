package Ex2;

/**
 * @author Luís Araújo
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] labyrinth = {
                {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        /*int[][] path = ex2(labyrinth, 0, 0);
        System.out.println("Ex.2");
        printMatrix(path);*/
    }

    /**
     * 2. Develop a recursive method to demonstrate the backtracking through the search of a path in a labyrinth.
     * Consider that only horizontal and vertical movements are allowed (diagonal are prohibited) and
     * movements obey the following order: north↑, east→, south↓ and west ←. Represent the labyrinth by a
     * matrix of zeros and ones, in which the walls represent zeros and ones halls.
     *
     * @param labyrinth
     * @param x
     * @param y
     * @return
     */
    public int[][] ex2(int[][] labyrinth, int x, int y) {
        //Marcar a posição do caminho com 9
        labyrinth[x][y] = 9;

        /*
            y y y y y
           x
           x
           x
           x
           x

         */

        //Retorna-se ao labirinto quando uma saída é alcançada
        if (y == labyrinth[x].length - 1 && x == labyrinth.length - 1) {
            return labyrinth;
        }

        //North
        if (x - 1 >= 0 && labyrinth[x - 1][y] == 1) {
            if (ex2(labyrinth, x - 1, y) != null) {
                return labyrinth;
            }
        }

        //East
        if (y + 1 < labyrinth[x].length && labyrinth[x][y + 1] == 1) {
            if (ex2(labyrinth, x, y + 1) != null) {
                return labyrinth;
            }
        }

        //South
        if (x + 1 < labyrinth.length && labyrinth[x + 1][y] == 1) {
            if (ex2(labyrinth, x + 1, y) != null) {
                return labyrinth;
            }
        }

        //West
        if (y - 1 >= 0 && labyrinth[x][y - 1] == 1) {
            if (ex2(labyrinth, x, y - 1) != null) {
                return labyrinth;
            }
        }

        //Usa-e o 2 para se marcar o caminho anulado
        labyrinth[x][y] = 2;
        return null;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
