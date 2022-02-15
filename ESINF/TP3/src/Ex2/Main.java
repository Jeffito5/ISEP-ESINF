package Ex2;

/**
 * @author Luís Araújo
 */
public class Main {
    //2a)
    //retorno é um Map com os parâmetros tipo: Pessoa e um Set do Cartão de Crédito
    /*public Map<Pessoa, Set<CartaoCredito>> anComplex (HashMap<CartaoCredito, Pessoa> mc){
        //cria um map com a pessoa e o cartão de crédito
        Map<Pessoa, Set<CartaoCredito>> mp = new TreeMap<>();

        //faz o Map.Entry e assim cada objeto irá ter uma chave e o seu valor. O entrySet é para ter ua view do set
        for (Map.Entry<CartaoCredito, Pessoa> mccp : mc.entrySet()) { O(n)
            //vai buscar a chave que é o cc
            CartaoCredito cc = mccp.getKey(); O(1)
            //vai buscar o valor que é a pessoa
            Pessoa p = mccp.getValue(); O(1)
            //no set do cartão de crédito vai buscar a pessoa associada àquele cartão
            Set<CartaoCredito> scc = mp.get(p); O(log n)
            //se esse set for nulo/estiver vazio então cria um novo HashSet e associa a pessoa a esse cartão
            if (scc == null) {
                scc = new HashSet<>();
                mp.put(p,scc); O(log n)
            }
            //adiciona ao set o cartão
            scc.add(cc); O(1)
        }
        return mp;
    }*/

    //Este método é responsável por criar um set com as pessoas associadas ao seu cartão de crédito e isto é feito através
    //da entrada do Map. A partir daqui, e com a retirada da chave e do valor, é criado um set final. Ao invés de receber os cartões
    //vai receber as pessoas com os seus cartões de crédito

    //2b)
    //O(n 2log n) -> O(nlog n)
    //Determinístico
}
