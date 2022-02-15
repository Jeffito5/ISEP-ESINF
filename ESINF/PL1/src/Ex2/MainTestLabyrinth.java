package Ex2;

import static org.junit.Assert.assertEquals;

/**
 * @author
 */
public class MainTestLabyrinth {
    private Main main=new Main();

    @org.junit.Test
    public void ex2() {
        //Arrange
        int[][] expectedResult = {
                {9, 9, 9, 0, 2, 2, 0, 0, 0, 2, 2, 2, 2},
                {1, 0, 9, 9, 9, 0, 2, 2, 2, 2, 2, 0, 2},
                {1, 0, 0, 0, 9, 0, 2, 0, 2, 0, 2, 0, 2},
                {1, 0, 0, 0, 9, 2, 2, 0, 2, 0, 2, 0, 2},
                {1, 1, 1, 1, 9, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9}
        };

        //Act
        int[][] labyrinth = {
                {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        //Assert
        assertEquals(expectedResult, main.ex2(labyrinth, 0, 0));
    }
}
