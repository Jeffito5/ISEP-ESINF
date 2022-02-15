package PL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord1> {

    public void createTree() throws FileNotFoundException{
        Scanner readfile = new Scanner(new File("src/PL/xxx.xxx"));
        while(readfile.hasNextLine()){
            String[] pal = readfile.nextLine().split("(\\,)|(\\s)|(\\.)");
            for(String word : pal)
                if (word.length() >0 )
                    insert(new TextWord1(word, 1));
        }
        readfile.close();
    }

    /**
     * Inserts a new word in the tree, or increments the number of its occurrences.
     * @param element
     */
    @Override
    public void insert(TextWord1 element){
        root = insert(element, root);
    }

    private Node<TextWord1> insert(TextWord1 element, Node<TextWord1> node){
        if (node == null) {
            return new Node(element, null, null);
        }

        //se o elemento for maior que o nó vai ser colocado à sua direita
        if (node.getElement().compareTo(element) < 0) {
            node.setRight(insert(element, node.getRight()));
            return node;
        }
        //se o elemento for menor que o nó vai ser colocado à sua esquerda
        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
            return node;
        }

        node.getElement().incOcorrences();
        return node;
    }

    /**
     * Returns a map with a list of words for each occurrence found.
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer,List<String>> getWordsOccurrences(){
        Map<Integer,List<String>> map = new HashMap<>();
        for(TextWord1 tw : inOrder()) {
            int numberOfOcorrences = tw.getOcorrences();
            if(!map.containsKey(numberOfOcorrences)) {
                map.put(numberOfOcorrences, new ArrayList<String>());
            }
            map.get(numberOfOcorrences).add(tw.getWord());
        }
        return map;
    }

}