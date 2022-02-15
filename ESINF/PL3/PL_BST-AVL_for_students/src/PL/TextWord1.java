
package PL;

/**
 *
 * @author DEI-ESINF
 */
public class TextWord1 implements Comparable<TextWord1>{

    private String word;
    private int ocorrences;

    public TextWord1(String word, int ocorrences){
        setWord(word,ocorrences);
    }

    public void setWord(String word, int ocorrences){
        this.word=word;
        this.ocorrences=ocorrences;
    }
    public void incOcorrences(){
        this.ocorrences++;
    }
    public String getWord(){
        return word;
    }
    public int getOcorrences(){
        return ocorrences;
    }

    @Override
    public int compareTo(TextWord1 o) {
        return word.compareTo(o.getWord());
    }

    public String toString(){
        return "<"+word+">:"+ocorrences;
    }
}