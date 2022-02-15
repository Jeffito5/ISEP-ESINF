/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * @param <E> Generic list element type
 * @author DEI-ISEP
 */
public class DoublyLinkedList<E> implements Iterable<E>, Cloneable {

    // instance variables of the DoublyLinkedList
    private final Node<E> header;     // header sentinel
    private final Node<E> trailer;    // trailer sentinel
    private int size = 0;       // number of elements in the list
    private int modCount = 0;   // number of modifications to the list (adds or removes)

    /**
     * Creates both elements which act as sentinels
     */
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);      // create header
        trailer = new Node<>(null, header, null);   // trailer is preceded by header
        header.setNext(trailer);                    // header is followed by trailer
    }

    /**
     * Retorna o número de elementos da Linked List
     *
     * @return número de elementos da Linked List
     */
    public int size() {
        return size;
    }

    /**
     * Verifica se a lista está vazia
     *
     * @return true se a lista está vazia e false se não estiver
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna mas não remove o primeiro elemento da lista
     *
     * @return o primeiro elemento da lista
     */
    public E first() {
        if (size() == 0)
            return null;
        return header.next.element;
        //header.getNext().getElement()
    }

    /**
     * Retorna mas não remove o último elemento da lista
     *
     * @return o último elemento da lista
     */
    public E last() {
        if (size() == 0)
            return null;
        return trailer.prev.element;
        //trailer.getPrev().getElement()
    }

// public update methods

    /**
     * Adiciona um elemento para a frente da lista
     *
     * @param e a ser adicionado na frente da lista
     */
    public void addFirst(E e) {
        // place just after the header
        //coloca o elemento na primeira posição. Header é null e o header.getNext() é o primeiro elemento da lista antes
        //de ser alterada
        addBetween(e, header, header.getNext());
    }

    /**
     * Adiciona um elemento para o fim da lista
     *
     * @param e a ser adicionado no fim da lista
     */
    public void addLast(E e) {
        // place just before the trailer
        //coloca o elemento na última posição. Trailer é null e o trailer.getPrev() é o último elemento da lista antes
        //de ser alterada
        addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Remove e retorna o primeiro elemento da lista
     *
     * @return primeiro elemento da lista
     */
    public E removeFirst() {
        if (size > 0) {
            E element = first();
            remove(header.getNext());
            return element;
        }

        return null;
    }

    /**
     * Remove e retorna o último elemento da lista
     *
     * @return último elemento da lista
     */
    public E removeLast() {
        if (size > 0) {
            E element = last();
            remove(trailer.getPrev());
            return element;
        }

        return null;
    }

// private update methods

    /**
     * Adiciona um elemento à lista entre 2 nós
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        //cria um novo node
        Node<E> node = new Node(e, predecessor, successor);
        //coloca o nó criado à frente do precedente e atrás do sucessor
        predecessor.setNext(node);
        successor.setPrev(node);
        //incrementa o size da lista e a variável que conta as modificações na lista
        size++;
        modCount++;
    }

    /**
     * Remove um nó da lista e retora o seu conteúdo
     */
    private E remove(Node<E> node) {
        //se for diferente de null então pode ser removido
        if (node.getPrev() != null) {
            //elimina a ligação anterior
            node.getPrev().setNext(node.getNext());
        }
        if (node.getNext() != null) {
            //elimina a ligação da frente
            node.getNext().setPrev(node.getPrev());
        }

        size--;
        modCount++;

        return node.getElement();
    }

    // Overriden methods
    @Override
    public boolean equals(Object obj) {
        //se as classes forem diferentes ou nulas então dá return false
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        //se o tamanho da lista for diferente então dá return false
        DoublyLinkedList<E> dll = (DoublyLinkedList<E>) obj;
        if (dll.size() != this.size()) return false;

        boolean result = true;
        Iterator<E> it = this.iterator();
        Iterator<E> it2 = dll.iterator();

        //verifica elemento a elemento. Se o next do it for igual ao next do it2 então result=true
        while (it.hasNext()) {
            result = result & it.next().equals(it2.next());
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DoublyLinkedList<E> dll = new DoublyLinkedList<>();
        Iterator<E> it = this.iterator();
        //enquanto conseguir ler adiciona à lista o elemento
        while (it.hasNext()) {
            dll.addLast(it.next());
        }
        return dll;
    }

    //---------------- Iterable implementation ----------------
    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    public ListIterator<E> listIterator() {
        return new DoublyLinkedListIterator();
    }

    //---------------- nested Node class ----------------
    private static class Node<E> {

        private E element;      // reference to the element stored at this node
        private Node<E> prev;   // reference to the previous node in the list
        private Node<E> next;   // reference to the subsequent node in the list

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) { // Not on the original interface. Added due to list iterator implementation
            this.element = element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    } //----------- end of nested Node class ----------

    //---------------- nested DoublyLinkedListIterator class ----------------
    private class DoublyLinkedListIterator implements ListIterator<E> {

        private DoublyLinkedList.Node<E> nextNode, prevNode, lastReturnedNode; // node that will be returned using next and prev respectively
        private int nextIndex;  // Index of the next element
        private int expectedModCount;  // Expected number of modifications = modCount;

        public DoublyLinkedListIterator() {
            this.prevNode = header;
            this.nextNode = header.getNext();
            lastReturnedNode = null;
            nextIndex = 0;
            expectedModCount = modCount;
        }

        final void checkForComodification() {  // invalidate iterator on list modification outside the iterator
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

        //verifica se há um nó seguinte
        @Override
        public boolean hasNext() {
            return !(this.nextNode == trailer);
        }

        @Override
        public E next() throws NoSuchElementException {
            checkForComodification();

            if (hasNext()) {
                Node<E> node = nextNode;
                //último nó retornado irá ser o próximo nó inicial
                lastReturnedNode = node;
                //prevNode e nextNode vão avançar posições ao mesmo tempo
                this.prevNode = node;
                this.nextNode = node.next;
                //devido ao avanço, o índice tem o incremento de 1
                nextIndex++;
                return node.getElement();

            }
            //se não existir o próximo então o último retornado é null
            lastReturnedNode = null;
            throw new NoSuchElementException();
        }

        //verifica se há um nó atrás
        @Override
        public boolean hasPrevious() {
            return !(this.prevNode == header);
        }

        @Override
        public E previous() throws NoSuchElementException {
            checkForComodification();

            if (hasPrevious()) {
                Node<E> node = prevNode;
                //último nó retornado irá ser o anterior nó inicial
                lastReturnedNode = node;
                //prevNode e nextNode vão recuar posições ao mesmo tempo
                this.nextNode = node;
                this.prevNode = node.prev;
                //devido ao recuo, o índice tem o decremento de 1
                nextIndex--;
                return node.getElement();
            }

            //se não existir o próximo então o último retornado é null
            lastReturnedNode = null;
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() throws NoSuchElementException {
            checkForComodification();

            //se o último nó retornado for o nó seguinte ou o nó anterior vamos ter de avançar ou recuar
            //uma posição e remover o último nó retornado
            if (lastReturnedNode == nextNode) {
                nextNode = nextNode.next;
                DoublyLinkedList.this.remove(lastReturnedNode);
            } else if (lastReturnedNode == prevNode) {
                prevNode = prevNode.prev;
                DoublyLinkedList.this.remove(lastReturnedNode);
            } else {
                throw new NoSuchElementException();
            }

            lastReturnedNode = null;
            expectedModCount = modCount;
        }

        @Override
        public void set(E e) throws NoSuchElementException {
            if (lastReturnedNode == null) throw new NoSuchElementException();
            checkForComodification();

            lastReturnedNode.setElement(e);
        }

        @Override
        public void add(E e) {
            checkForComodification();

            DoublyLinkedList.this.addBetween(e, prevNode, nextNode);
            lastReturnedNode = this.prevNode;
            prevNode = prevNode.next;
            expectedModCount = modCount;
        }

    }    //----------- end of inner DoublyLinkedListIterator class ----------

}