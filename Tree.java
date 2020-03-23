import java.util.Scanner;

public class Tree {

    private int size;
    Node now;
    Node head;

    Tree(){
        
        Node head = new Node();
        head.value = 100;
        this.head = head;
        this.now = head;
        
    }

    class Node{
        int value;
        Node left;
        Node right;
        Node parent;

        Node(){
            this.parent = null;
            this.left = null;
            this.right = null;
        }
        Node(int i){
            this.value = i;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }

    public boolean addNode(int value){
        Node e = new Node(value);
        if(this.now.left == null){
            this.now.left = e;
            e.parent = this.now;
            this.size++;
            return true;
        }
        if(this.now.right == null){
            this.now.right = e;
            e.parent = this.now;
            this.now = e;
            this.size++;
            return true;
        }
        return false;
        
    }

    public boolean addAVL(Node e){
        if(e.value < this.now.value){
            if(this.now.left ==null){
                this.now.left = e;
                e.parent = this.now;
                this.now = e;
                this.size++;
                return true;
            }
            addAVL(this.now.left);
        }
        if(e.value>=this.now.value)
        {
            if(this.now.right==null){
                this.now.right = e;
                e.parent = this.now;
                this.now = e;
                this.size++;
                return true;
            }
            addAVL(this.now.left);
        }
        return false;
    }

    public void printAfter(Node e){
        if(e.left!=null)
            printAfter(e.left);
        if(e.right!=null)
            printAfter(e.right);
        System.out.println(e.value);
        return;
    }

    public void printBefore(Node e){
        System.out.println(e.value);
        if(e.left!=null)
            printBefore(e.left);
        if(e.right!=null)
            printBefore(e.right);
        return;
    }

    public void printMid(Node e){       
        if(e.left!=null)
            printMid(e.left);
        System.out.println(e.value);
        if(e.right!=null)
            printMid(e.right);
        return;
    }



    public static void main(String[] args){
        Tree t = new Tree();
        for(int i = 1;i<11;i++){
            Node e = t.new Node(i);
            t.addAVL(e);
        }
            
        // Tree shape
        //   0
        //  / \
        // 1   2
        //    / \
        //   3   4
        //      / \
        //     5   6
        //        / \
        //       7   8
        //          / \
        //         9  10
        // System.out.println("后序遍历");
        // t.printAfter(t.head);
        // System.out.println("中序遍历");
        // t.printMid(t.head);
        // System.out.println("前序遍历");
        // t.printBefore(t.head);
        // System.out.println("size: "+t.size);

       

}
}

