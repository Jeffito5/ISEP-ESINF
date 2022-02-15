package Ex2;

/**
 * @author Luís Araújo
 */
public class Main {
    /**
     * 2a) 1 + f(m, n-1)
     */
    /**
     * 2b) String dec2bin(Integer m){
     *     if(m<2) return m.toString();
     *     Integer q=m/2;
     *     Integer r=m%2;
     *     return dec2bin(q)+r.toString();
     * }
     */
    /**
     * 2c) boolean isPrime(int m){
     *     return isPrime(m,2);
     *     private boolean isPrime(int m, int d){
     *         if(d*d>n) return true;
     *         if(n%d==0) return false;
     *         return isPrime(n, d+1);
     *     }
     * }
     */
    /**
     * 2d) boolean palindrome(String s){
     *     if(s.length()<2) return true;
     *     if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
     *     return palindrome(s.substring(1, s.length()-1));
     * }
     */
}
