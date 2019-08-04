/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author thinh
 */
public class Node<E> {

    E info;
    int height;
    public Node<E> left, right;

    Node(E x) {
        info = x;
        left = right = null;
        height = 1;
    }

    Node() {

    }

    public E getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "" + info;
    }
}
