package java_exam.树;

class Node {
    public String data;
    public Node left = null;
    public Node right = null;
    public boolean flag;
    Node(String data) {
        this.data = data;
    }
    Node() {
    }
    @Override
    public String toString() {

        return this.data;
    }

}