package Ex3;

/**
 * @author Luís Araújo
 */
public class Main {
    /*
    determina a potência
    public double power (double b, int e){
        //O(1)
        if (e == 0)
            return 1;
        //O(1)
        if (e == 1)
            return b;
        if (e % 2 == 0)
            return power (b*b, e/2);
        else
            return b*power(b*b, e/2) ;
     }
     */
    //3a)Não determinístico porque, se os dados de entrada forem 0 ou 1 a complexidade é constante. Se os dados de entrada
    //forem diferentes de 1 já vai ter um comportamento diferente
    //3b)No pior caso a complexidade é O(log n). No melhor caso é constante
}
