package algorithm.lesson6;

import java.util.NoSuchElementException;

/**
 * Класс инкапсулирует функциональность дерева.
 * */
public class MyTreeMap<T extends Comparable<T>, V> {

    /**
     * Внутренний класс инкапсулирует узел дерева
     * */
    private class Node{

        T key;              //ключ
        V value;            //данные
        Node leftNode;      //левый потомок
        Node rightNode;     //правый потомок
        int size;           //кол-во узлов в поддереве, где данный узел выступает корнем
        int height;         //высота узла

        public Node(T key, V value, int size, int height) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.height = height;
        }
    }

    private Node root = null;           //корень дерева

    /**
     * Метод проверяет сбалансированность дерева
     * */
    public boolean isBalanced(){

        return isBalanced(root);
    }

    /**
     * Служебный метод проверяет сбалансированность поддерева
     * @param node - корень поддерева
     * */
    private boolean isBalanced(Node node){

        //если потомка не существует или высота == 0
        if(node == null || node.height==0){
            return true;
        }

        int difference = height(node.leftNode) - height(node.rightNode);    //разность высоты между левым и правым потомком
        boolean result;                                                     //переменная, которая содержит результат сравнения высот поддеревьев
        if(difference == 0 || difference == -1 || difference == 1){         //если разница в высоте потомков не больше 1
            result = true;                                                  //следовательно деревья сбалансированы
        } else {                                                            //иначе
            result = false;                                                 //не сбалансированы
        }

        //рекурсивно сравниваем высоты потомков всех узлов
        return result && isBalanced(node.leftNode) && isBalanced(node.rightNode);
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node == null){
            return 0;
        } else {
            return node.size;
        }
    }

    public int height(){
        return height(root);
    }

    private int height(Node node){
        if(node == null){
            return 0;
        } else {
            return node.height;
        }
    }

    public V get(T key){
        return get(root, key);
    }

    private V get(Node node, T key){

        if(key == null) {
            throw new IllegalArgumentException("Kye cannot ");
        }

        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0){
            return node.value;
        }

        if (cmp < 0){
            return get(node.leftNode, key);
        } else {
            return get(node.rightNode, key);
        }
    }

    public boolean contrains(T key){
        return get(key) != null;
    }

    public void put(T key, V value){
        root = put(root, key, value);
    }

    private Node put(Node node, T key, V value){

        if(key == null) {
            throw new IllegalArgumentException();
        }

        if(node == null){
            return new Node(key, value, 1, 0);
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            node.value = value;
        } else if(cmp < 0){
            node.leftNode = put(node.leftNode,key,value);
        } else if(cmp > 0){
            node.rightNode = put(node.rightNode,key,value);
        }

        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        node.height = height(node.leftNode) < height(node.rightNode) ? height(node.rightNode) : height(node.leftNode);
        ++node.height;
        return node;
    }

    public void remove(T key){
        root = remove(root, key);
    }

    private Node remove(Node node, T key){
        if(node == null){
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.leftNode = remove(node.leftNode, key);
        } else if(cmp > 0){
            node.rightNode = remove(node.rightNode, key);
        } else {
            if(node.leftNode == null){
                return node.rightNode;
            }
            if (node.rightNode == null){
                return node.leftNode;
            }

            Node tmp = node;
            node = max(node.leftNode);
            node.leftNode = removeMax(tmp.leftNode);
            node.rightNode = tmp.rightNode;
            tmp = null;
        }

        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        node.height = height(node.leftNode) < height(node.rightNode) ? height(node.rightNode) : height(node.leftNode);
        ++node.height;
        return node;
    }

    private Node min (Node node){
        if(node.leftNode == null){
            return node;
        } else {
            return min(node.leftNode);
        }
    }

    private Node max (Node node){
        if(node.rightNode == null){
            return node;
        } else {
            return max(node.rightNode);
        }
    }

    public V min (){
        return min(root).value;
    }

    public V max (){
        return max(root).value;
    }

    private Node removeMin(Node node){
        if( node.leftNode == null){
            return  node.rightNode;
        } else {
            node.leftNode = removeMin(node.leftNode);
        }

        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        node.height = height(node.leftNode) < height(node.rightNode) ? height(node.rightNode) : height(node.leftNode);
        ++node.height;
        return node;
    }

    private Node removeMax(Node node){
        if( node.rightNode == null){
            return  node.leftNode;
        } else {
            node.rightNode = removeMax(node.rightNode);
        }

        node.size = size(node.leftNode) + size(node.rightNode) + 1;
        node.height = height(node.leftNode) < height(node.rightNode) ? height(node.rightNode) : height(node.leftNode);
        ++node.height;
        return node;
    }

    public void removeMin(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        root = removeMin(root);
    }

    public void removeMax(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        root = removeMax(root);
    }
}
