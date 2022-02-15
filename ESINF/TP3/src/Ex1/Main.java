package Ex1;

/**
 * @author Luís Araújo
 */
public class Main {
    //A)Se uma posição do vetor somada com a posição seguinte for igual ao value então a flag fica a true
    //Imprime o índice do vetor bem como o seu valor
    //a[7]={1,13,17,18,22,33,35,38} and mystery2(a,35).
    //pos 1->13, pos 4->22
    //pos 2->17, pos 3->18

    //B)Algortimo é não determinístico pois há um processo paralelo para se verificar se a soma dos valores é igual a value
    //Complexidade: O(n^2) pois há 2 ciclos for: O(f) × O(g) = O (f × g)
    /*public boolean mystery2 (int[] A, int value)
    {
        boolean flag=false; O(1)
        for (int i = 0; i < (A.length-1); i++) O(n)
            for (int j = i+1; j < A.length; j++) O(n)
                if (A[i]+A[j] == value) { O(1)
                    flag = true; O(1)
                    System.out.println("pos "+ i +"->"+A[i]+", pos "+j+"->"+A[j]); O(1)
                }
        return flag;
    }*/

    //C) while(j>i){
    /*if(A[i] + A[j]==value){
        flag=true;
        System.out.println();
        i=i+1;
        j=j-1;
    }else{
        if(A[i] + A[j]>value
        j=j-1;
    else{
        i=i+1
        }
    }
*/
}
