/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.time.LocalDate;
import java.util.*;

/**
 * @author DEI-ISEP
 */
public class Supermarket {
    Map<Invoice, Set<Product>> sup;

    Supermarket() {
        sup = new HashMap<>();
    }
    //------------------------------------------------------ 2. / 3. ---------------------------------------------------
    //Recebe um I, a referência e depois a data
    //Recebe um P e uma série de produtos
    //Outro I recebido indica outra fatura
    //Lê invoices de uma lista
    void getInvoices(List<String> l) throws Exception {
        Invoice currentInvoice = null;
        for (String line : l) {
            String[] lineData = line.split(",");
            //se receber um "I" no início então estamos perante uma nova fatura
            if ("I".equals(lineData[0])) {
                Invoice invoice = new Invoice(lineData[1], lineData[2]);
                currentInvoice = invoice;
                //coloca a fatura no supermercado bem como um novo hashset que corresponde aos produtos
                sup.put(invoice, new TreeSet<>());
            }
            //se receber um "P" então vai adicionar os seus produtos
            if ("P".equals(lineData[0])) {
                //set criado para os produtos vai corresponder à fatura criada acima
                Set<Product> products = sup.get(currentInvoice);
                products.add(new Product(lineData[1], Integer.parseInt(lineData[2]), Long.parseLong(lineData[3])));
            }
        }

        //vai retornar a chave para sabermos o valor
        for (Invoice name : sup.keySet()) {
            String key = name.toString();
            String value = sup.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    //------------------------------------------------------ 3. / 4. ---------------------------------------------------
    //O método retorna a contagem dos produtos em cada fatura. O resultado é um map de
    //faturas e dados inteiros, em que cada elemento de dados é o número de produtos da fatura correspondente.
    Map<Invoice, Integer> numberOfProductsPerInvoice() {
        Map<Invoice, Integer> data = new HashMap<>();

        //vai retornar a chave. Com esta chave e com o size, vemos o número de produtos
        for (Invoice invoice : sup.keySet()) {
            Set<Product> products = sup.get(invoice);
            data.put(invoice, products.size());
        }
        return data;
    }

    //------------------------------------------------------ 5. --------------------------------------------------------
    //Retorna um set de faturas em que a data é superior a d1 e inferior a d2
    Set<Invoice> betweenDates(LocalDate d1, LocalDate d2) {
        Set<Invoice> dates = new TreeSet<>();

        //se a data do valor da chave obtida for maior que d1 e menor que d2 então é adicionada ao set
        for (Invoice invoice : sup.keySet()) {
            if(invoice.getDate().isAfter(d1) && invoice.getDate().isBefore(d2)){
            //if (invoice.getDate().compareTo(d1) > 0 && invoice.getDate().compareTo(d2) < 0) {
                dates.add(invoice);
            }
        }
        return dates;
    }

    //------------------------------------------------------ 6. --------------------------------------------------------
    //Retorna a soma dos preços dos produtos em todas as faturas
    long totalOfProduct(String id) {
        long sum = 0;

        //vai buscar a chave
        for (Invoice invoice : sup.keySet()) {
            //vai buscar o id do valor associado à chave e compara-o
            for (Product product : sup.get(invoice)) {
                if (product.getIdentification().equals(id)) {
                    sum += product.getPrice() * product.getQuantity();
                }
            }
        }
        return sum;
    }

    //------------------------------------------------------ 7. --------------------------------------------------------
    //O map original de faturas de produtos será transformado noutro de ids de produtos para faturas.
    //O map resultante terá um set de faturas para cada id de produto. Uma fatura é uma parte do set
    //se, no map original, o produto é mapeado para a fatura
    Map<String, Set<Invoice>> convertInvoices() {
        Map<String, Set<Invoice>> data = new HashMap<>();

        //vai buscar o id associado à chave  e faz um hashset
        for (Invoice invoice : sup.keySet()) {
            for (Product product : sup.get(invoice)) {
                data.put(product.getIdentification(), new TreeSet<>());
            }
        }

        for (Invoice invoice : sup.keySet()) {
            for (Product product : sup.get(invoice)) {
                Set<Invoice> productInvoices = data.get(product.getIdentification());
                productInvoices.add(invoice);
            }
        }

        return data;
    }

}