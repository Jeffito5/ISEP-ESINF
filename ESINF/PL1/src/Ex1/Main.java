package Ex1;

import java.util.Scanner;

/**
 * @author Luís Araújo
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*System.out.println("Reverse the string.");
        System.out.println("1a)" + reverseString("Luís") + "\n");
        System.out.println("Compute de product of two positive integers.");
        System.out.println("1b)" + product(2, 5));
        System.out.println("Calculate the greatest common divisor of two positive integers.");
        System.out.println("1c)" + gcd(48, 30) + "\n");
        System.out.println("Convert a string to an integer.");
        System.out.println("1d)" + conversion("13531", 0));
        System.out.println("Verify if a number is a palindrome.");
        System.out.println("1e)" + palindrome(111) + "\n");
        System.out.println("Compute the sum of all the elements in an n × n (two-dimensional) array of integers.");
        Scanner in = new Scanner(System.in);
        int row,col, i, j;
        System.out.println("Enter the number of rows:");
        row = in.nextInt();
        System.out.println("Enter the number of columns:");
        col = in.nextInt();
        int[][] matrix = new int[row][col];
        System.out.println("Enter the elements of the matrix") ;
        for(i=0;i<row;i++)
        {
            for(j=0;j<col;j++)
            {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println("1f)" + sumMatrix(matrix, row, col, 0, 0));*/
    }

    /**
     * Implement a recursive algorithm:
     * a) That receives a string and returns another string with its characters in reverse order of the original
     * string.
     */
    public String reverseString(String string) {
        if (string.length() == 0) {
            return ""; //The base case(s) should always be checked before the recursive call
        } else {
            String string2 = String.valueOf(string.charAt(string.length() - 1)); //string 2 vai ser a última letra da string original
            return string2 + reverseString(string.substring(0, string.length() - 1)); //vai retornar a palavra toda anterior sendo a letra inicial a letra final da palavra anterior
        }
    }

    /**
     * Implement a recursive algorithm:
     * b) To compute the product of two positive integers, m and n, using only the arithmetic operations:
     * addition and subtraction.
     */
    //3*2=2+2+2
    public int product(int n, int m) {
        int aux = 0;
        if (n <= 0 || m <= 0) {
            return 0;
        }
        if (n == 1) {
            return aux + m;
        } else {
            aux += m;
            return aux + product(n - 1, m);
        }
    }

    /**
     * Implement a recursive algorithm:
     * c) To calculate the greatest common divisor of two positive integers, for example m.d.c (48,30)=6, using
     * the algorithm of successive divisions.
     */
    public int gcd(int num1, int num2) {
        if (num1 > num2) {
            return gcd(num2, num1); //se o primeiro número for maior então troca-os para se poder fazer a verificação
        }

        if (num2 > num1 && num2 % num1 == 0) {
            return num1; //se o segundo número for maior e se o resto da divisão entre os 2 números for 0 então o mdc é o número mais pequeno
        } else {
            return gcd(num1, num2 % num1); //volta-se a tentar com o resto da divisão do número maior
        }
    }

    /**
     * Implement a recursive algorithm:
     * d) To convert a string of digits into the integer it represents. For example, “13531” represents the integer
     * 13531.
     */
    public int conversion(String numberStr, int index) {
        if (numberStr.length() == 0) {
            return 0;
        } else {
            String num[]=numberStr.split("");
            return (int) ((int) Double.parseDouble(num[index])*Math.pow(10,numberStr.length()-1) + conversion(numberStr.substring(index+1), index++));
        }
    }

    /**
     * Implement a recursive algorithm:
     * e) To see if a number is palindrome that is, the number is the same when written forwards or backwards
     * (examples: 99, 101, 111, 121, 1221, 21112, 10001, … ).
     */
    public boolean palindrome(int num) {
        return palindrome2(num, 0) == num; //se o número pela ordem contrária for igual ao original então retorna true
    }

    public int palindrome2(int num1, int num2) {
        if (num1 == 0) {
            return num2; //se o número original for 0 então retorna 0
        } else {
            num2 = num2 * 10; //verifica se é palíndromo
            num2 = num2 + (num1 % 10);
            num1 = num1 / 10;
            return palindrome2(num1, num2);
        }
    }

    /**Implement a recursive algorithm:
     *     f) To compute the sum of all the elements in an n × n (two-dimensional) array of integers.
     */
    public int sumMatrix(int[][] matrix, int rows, int cols, int x, int y){
        if(rows==1 && cols==1){
            return matrix[x][y];
        }
        if (rows == 1) {
            return sumMatrix(matrix, rows, (cols/2), x, y) + sumMatrix(matrix, rows, cols-(cols/2), x, y + (cols / 2));
        } else {
            return sumMatrix(matrix, (rows/2), cols, x, y) + sumMatrix(matrix, rows-(rows/2), cols, x+(rows/2), y);
        }
    }
}
