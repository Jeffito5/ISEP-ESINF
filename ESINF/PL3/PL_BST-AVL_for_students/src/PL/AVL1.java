/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

/**
 * @param <E>
 * @author DEI-ESINF
 */
public class AVL1<E extends Comparable<E>> extends BST<E> {

    private int balanceFactor(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<E> rightRotation(Node<E> node) {
        //apanho o primeiro nó da esquerda
        Node<E> leftSon = node.getLeft();

        //filho direito do nó da esquerda irá ser o filho da esquerda da raiz inicial
        node.setLeft(leftSon.getRight());
        //raiz vai ser o filho direito do antigo filho da esquerda da raiz
        leftSon.setRight(node);

        node = leftSon;

        return node;
    }

    private Node<E> leftRotation(Node<E> node) {
        //apanho o primeiro nó da direita
        Node<E> rightSon = node.getRight();

        //filho esquerdo do nó da direita irá ser o filho da direita da raiz inicial
        node.setRight(rightSon.getLeft());
        //raiz vai ser o filho esquerdo do antigo filho da direita da raiz
        rightSon.setLeft(node);

        node = rightSon;

        return node;
    }

    private Node<E> twoRotations(Node<E> node) {
        //se for menor que 0 vamos redefinir o nó da esquerda
        if (balanceFactor(node) < 0) {
            //rotação do nó da esquerda para a esquerda
            node.setLeft(leftRotation(node.getLeft()));
            //rotação para a direita do node
            node = rightRotation(node);
        } else {
            //rotação do nó da direita para a direita
            node.setRight(rightRotation(node.getRight()));
            //rotação para a esquerda do node
            node = leftRotation(node);
        }

        return node;
    }

    /**
     10              10              8
     7        D  ->    8    D  ->    7       10
     A   8            7    C         A   B    C   D
     B   C        A  B
     **/

    /**
     * if(balanceFactor(node)<-1)
     * if(balanceFactor(node.getLeft()<=0)
     * ...
     * else
     * ...
     * <p>
     * if(balanceFactor(node)>1)
     * if(balanceFactor(node.getRight()>=0)
     * ...
     * else
     * ...
     *
     * @param node
     * @return
     */

    private Node<E> balanceNode(Node<E> node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) >= 0)
                node = leftRotation(node);
            else
                node = twoRotations(node);
        }
        if (Math.abs(balanceFactor(node)) > 1)
            balanceNode(node);
        return node;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement() == element) {
            node.setElement(element);
        } else {
            if (node.getElement().compareTo(element) > 0) {
                node.setLeft(insert(element, node.getLeft()));
                node = balanceNode(node);
            } else {
                node.setRight(insert(element, node.getRight()));
                node = balanceNode(node);
            }
        }

        return node;
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getElement() == element) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(remove(element, node.getLeft()));
        } else {
            node.setRight(remove(element, node.getRight()));
        }

        return node;
    }

    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        AVL1<E> second = (AVL1<E>) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}