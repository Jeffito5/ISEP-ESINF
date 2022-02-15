package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Luís Araújo
 */
public class Main {
    /**
     * classe genérica que extende a classe Ex1.Document e implementa a Iterable
     *
     * @param <E>
     */
    public class PriorityBufferPrinter<E extends Document> implements Iterable<E> {

        /**
         * atributos da classe
         */
        private final ArrayList<E> buffer;
        private final Integer maxSize;

        /**
         * construtor que recebe o tamanho máximo do objeto
         *
         * @param maxSize
         */
        public PriorityBufferPrinter(Integer maxSize) {
            buffer = new ArrayList<>();
            this.maxSize = maxSize;
        }

        /**
         * adiciona os documentos se houver espaço e pela prioridade
         *
         * @param doc
         * @return true se for um sucesso senão retorna false
         */
        public boolean addDocument(E doc) {
            int usedSize = 0;

            /**
             * verifica quanto espaço resta de todos os documentos guardados
             */
            for (int i = 0; i < buffer.size(); i++) {
                usedSize += buffer.get(i).getSize();
            }

            /**
             * verifica se o novo documento cabe
             */
            if (usedSize + doc.getSize() > maxSize) {
                return false;
            }

            /**
             * para cada documento vai verificar a prioridade e inserir na posição correta
             */
            for (int i = 0; i < buffer.size(); i++) {
                if (doc.getPriority() < buffer.get(i).getPriority()) {
                    buffer.add(i, doc);
                    return true;
                }
            }
            buffer.add(doc);
            return true;
        }

        /**
         * retorna e remove o próximo documento
         *
         * @return documento
         */
        public E getDocument() {
            if (buffer.isEmpty()) {
                return null;
            }
            E doc = buffer.get(0);
            buffer.remove(0);
            return doc;
        }

        /**
         * remove um documento utilizando o nome e o autor
         *
         * @return true se for removido senão dá return false
         */
        public boolean delDocument(String name, String author) {
            int i = 0;
            while (i < buffer.size()) {
                E doc = buffer.get(i);
                if (doc.getName().equals(name) && doc.getAuthor().equals(author)) {
                    buffer.remove(i);
                    return true;
                }
                i++;
            }
            return false;
        }

        /**
         * remove todos os documentos com tamanho acima de um tamanho dado
         *
         * @param size
         * @return true se o documento for removido senão retorna false
         */
        public boolean delDocumentsAbove(Integer size) {
            int i = 0;
            boolean ret = false;
            while (i < buffer.size()) {
                if (buffer.get(i).getSize() > size) {
                    buffer.remove(i);
                    //todas as posições vão recuar uma posição uma vez que um documento foi removido
                    i--;
                    ret = true;
                }
                i++;
            }
            return ret;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }
    }
}
