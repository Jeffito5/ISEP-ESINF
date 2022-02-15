package Ex1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author
 */
public class MainTest {
    private Main main = new Main();

    @Test
    public void reverseString() {
        //Arrange
        String expectedString = "síuL";

        //Act
        String name = "Luís";

        //Assert
        assertEquals(expectedString, main.reverseString(name));
    }

    @Test
    public void product() {
        //Arrange
        int expectedResult = 6;

        //Act
        int num1 = 3;
        int num2 = 2;

        //Assert
        assertEquals(expectedResult, main.product(num1, num2));
    }

    @Test
    public void gcd() {
        //Arrange
        int expectedResult = 6;

        //Act
        int num1 = 48;
        int num2 = 30;

        //Assert
        assertEquals(expectedResult, main.gcd(num1, num2));
    }

    @Test
    public void conversion() {
        //Arrange
        int expectedResult = 13531;

        //Act
        String num = "13531";

        //Assert
        assertEquals(expectedResult, main.conversion(num, 0));
    }

    @Test
    public void palindrome() {
        //Arrange
        boolean expectedResult = true;

        //Act
        int num = 123321;

        //Assert
        assertEquals(expectedResult, main.palindrome(num));
    }

    @Test
    public void sumMatrix() {
        //Arrange
        int expectedResult = 9;

        //Act
        int rows = 3;
        int cols = 3;
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        //Assert
        assertEquals(expectedResult, main.sumMatrix(matrix, rows, cols, 0, 0));
    }
}