//Igor Lonak - grupa 8
import java.util.Scanner;

class Node{
    public int info;
    public Node left;
    public Node right;

    public Node(int info) {
        this.info = info;
        left = null;
        right = null;
    }

}

class Stack{
    Node[] tab;
    int Top;

    Stack(int maxSize){
        tab = new Node[maxSize];
        Top = -1;
    }

    boolean isEmpty() {
        return (Top==-1);
    }

    void Push(Node root) {
        if(Top < tab.length) {
            tab[++Top] = root;
        }
    }


    Node Pop() {
        return tab[Top--];
    }

    Node Peek() {
        return tab[Top];
    }
}

class Tree{
    Node root;
    int index;
    int amount;

    Tree(){
        root = null;
        amount = 0;
    }

    Node createPreorder(int[] tab, int min, int max, int i) {
        index=i;
        if(index == tab.length) {
            return null;
        }
        int temp = tab[index];

        if(temp < min || temp > max) {
            return null;
        }

        Node root = new Node(temp);
        index++;

        root.left = createPreorder(tab, min, temp-1, index);
        root.right = createPreorder(tab, temp+1, max, index);
        return root;
    }

    Node createPostorder(int[] tab, int min, int max, int i) {
        index=i;
        if(index < 0) {
            return null;
        }

        int temp = tab[index];

        if(temp < min || temp > max) {
            return null;
        }

        Node root = new Node(temp);
        index--;

        root.right = createPostorder(tab, temp+1, max, index);
        root.left = createPostorder(tab, min, temp-1, index);
        return root;
    }

    void Insert(int key) {
        Node temp = root;
        Node prev = null;

        while(temp != null) {
            prev = temp;
            if(temp.info == key) {
                return;
            }
            if(key < temp.info) {
                temp = temp.left;
            }
            else {
                temp = temp.right;
            }
        }

        if(key < prev.info) {
            prev.left = new Node(key);
        }
        else {
            prev.right = new Node(key);
        }
        amount++;
    }

    void Preorder() {
        if(root == null) {
            return;
        }

        Stack stack = new Stack(2* amount);
        stack.Push(root);
        Node temp = root;

        while(!stack.isEmpty()) {
            if(temp != null) {
                System.out.print(" "+temp.info);
                if(temp.right != null) {
                    stack.Push(temp.right);
                }
                temp = temp.left;
            }
            else {
                temp = stack.Pop();
            }
        }
    }

    void Inorder() {
        if(root == null) {
            return;
        }

        Node temp, prev;
        temp = root;
        while(temp != null) {
            if(temp.left == null) {
                System.out.print(" "+temp.info);
                temp = temp.right;
            }
            else {
                prev = temp.left;
                while(prev.right != null && prev.right != temp) {
                    prev = prev.right;
                }
                if(prev.right == null) {
                    prev.right = temp;
                    temp = temp.left;
                }
                else {
                    prev.right = null;
                    System.out.print(" "+temp.info);
                    temp = temp.right;
                }
            }
        }
    }

    void Postorder() {
        if(root == null) {
            return;
        }

        Stack stack = new Stack(3* amount);
        Node prev = null;
        stack.Push(root);
        while(!stack.isEmpty()) {
            Node temp = stack.Peek();
            if(prev == null || (prev.left == temp || prev.right == temp)) {
                if(temp.left != null) {
                    stack.Push(temp.left);
                }
                else if(temp.right != null) {
                    stack.Push(temp.right);
                }
            }
            else if(prev == temp.left){
                if(temp.right != null) {
                    stack.Push(temp.right);
                }
            }
            else {
                System.out.print(" "+temp.info);
                stack.Pop();
            }
            prev = temp;
        }
    }

    boolean Delete(int key){
        Node temp = root;
        Node prev = root;
        boolean isLeft = false;
        while(temp.info != key){
            prev = temp;
            if(key < temp.info){
                temp = temp.left;
                isLeft = true;
            }
            else{
                temp = temp.right;
                isLeft = false;
            }
            if(temp == null){
                return false;
            }
        }
        if(temp.left == null && temp.right == null){
            if(temp == root){
                root = null;
            }
            else if(isLeft){
                prev.left = null;
            }
            else{
                prev.right = null;
            }
        }
        else if(temp.left == null){
            if(temp == root){
                root = temp.right;
            }
            else if(isLeft){
                prev.left = temp.right;
            }
            else{
                prev.right = temp.right;
            }
        }
        else if(temp.right == null){
            if(temp == root){
                root = temp.left;
            }
            else if(isLeft){
                prev.left = temp.left;
            }
            else{
                prev.right = temp.left;
            }
        }
        else{
            Node successor = Successor(temp);
            if(temp == root){
                root = successor;
            }
            else if(isLeft){
                prev.left = successor;
            }
            else{
                prev.right = successor;
            }
            successor.left = temp.left;
        }
        return true;
    }

    Node Successor(Node node){
        Node successor = node;
        Node successorParent = node;
        Node current = node.right;
        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if(successor != node.right){
            successorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    void Parent(int key) {
        Node temp = root;
        Node parent = root;
        while(temp != null) {
            if(temp.info == key) {
                if(temp.info == root.info) {
                    System.out.print("PARENT "+key+": BRAK");
                    System.out.println();
                    return;
                }
                System.out.print("PARENT "+key+": "+parent.info);
                System.out.println();
                return;
            }
            if(temp.info > key) {
                parent = temp;
                temp = temp.left;
            }
            else {
                parent = temp;
                temp = temp.right;
            }
        }
        System.out.print("PARENT "+key+": BRAK");
        System.out.println();
    }

    Node Successor(Node root, int key){
        Node succ = null;

        while(true) {
            if(key < root.info) {
                succ = root;
                root = root.left;
            }
            else if(key > root.info) {
                root = root.right;
            }
            else {
                if(root.right != null) {
                    succ = findMinimum(root.right);
                }
                break;
            }
            if(root == null) {
                return null;
            }
        }
        return succ;
    }

    Node findMinimum(Node root){
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    Node Predecessor(Node root, int key){
        Node prec = null;

        while (true){
            if(key < root.info) {
                root = root.left;
            }
            else if(key > root.info) {
                prec = root;
                root = root.right;
            }
            else{
                if (root.left!= null) {
                    prec = findMaximum(root.left);
                }
                break;
            }
            if (root == null)
                return null;
        }
        return prec;
    }

    Node findMaximum(Node root)	{
        while (root.right!= null) {
            root = root.right;
        }

        return root;
    }

    int Height(Node root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(Height(root.left), Height(root.right));
    }

    void ScanInorder(boolean op) {
        if(root == null) {
            return;
        }
        int[] tab = new int[amount];
        int i=0;
        Node temp, prev;
        temp = root;
        while(temp != null) {
            if(temp.left == null) {
                tab[i++] = temp.info;
                temp = temp.right;
            }
            else {
                prev = temp.left;
                while(prev.right != null && prev.right != temp) {
                    prev = prev.right;
                }
                if(prev.right == null) {
                    prev.right = temp;
                    temp = temp.left;
                }
                else {
                    prev.right = null;
                    tab[i++] = temp.info;
                    temp = temp.right;
                }
            }
        }

        int result;
        if(op) {
            result = 0;
            System.out.print("SCAN + INORDER:");
        }
        else {
            result = 2*tab[0];
            System.out.print("SCAN - INORDER:");
        }

        for(int j = 0; j< amount; j++) {
            if(op) {
                result = result + tab[j];
            }
            else {
                result = result - tab[j];
            }
            System.out.print(" "+result);
        }
    }

    void ScanPostorder(boolean op) {
        if(root == null) {
            return;
        }

        Stack stack = new Stack(amount);
        Node prev = null;
        int[] tab = new int[amount];
        int i = 0;
        stack.Push(root);
        while(!stack.isEmpty()) {
            Node temp = stack.Peek();
            if(prev == null || (prev.left == temp || prev.right == temp)) {
                if(temp.left != null) {
                    stack.Push(temp.left);
                }
                else if(temp.right != null) {
                    stack.Push(temp.right);
                }
            }
            else if(prev == temp.left){
                if(temp.right != null) {
                    stack.Push(temp.right);
                }
            }
            else {
                tab[i++] = temp.info;
                stack.Pop();
            }
            prev = temp;
        }

        int result;
        if(op) {
            result = 0;
            System.out.print("SCAN + POSTORDER:");
        }
        else {
            result = 2*tab[0];
            System.out.print("SCAN - POSTORDER:");
        }

        for(int j = 0; j< amount; j++) {
            if(op) {
                result = result + tab[j];
            }
            else {
                result = result - tab[j];
            }
            System.out.print(" "+result);
        }
    }

    void ScanPreorder(boolean op) {
        if(root == null) {
            return;
        }

        Stack stack = new Stack(amount);
        stack.Push(root);
        Node temp = root;
        int[] tab = new int[amount];
        int i = 0;
        while(!stack.isEmpty()) {
            if(temp != null) {
                tab[i++] = temp.info;
                if(temp.right != null) {
                    stack.Push(temp.right);
                }
                temp = temp.left;
            }
            else {
                temp = stack.Pop();
            }
        }

        int result;
        if(op) {
            result = 0;
            System.out.print("SCAN + PREORDER:");
        }
        else {
            result = 2*tab[0];
            System.out.print("SCAN - PREORDER:");
        }

        for(int j = 0; j< amount; j++) {
            if(op) {
                result = result + tab[j];
            }
            else {
                result = result - tab[j];
            }
            System.out.print(" "+result);
        }

    }

}

public class Source {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int tests = scan.nextInt();

        for(int i=0;i<tests;i++)
        {
            int commands = scan.nextInt();
            Tree tree = new Tree();
            System.out.println("ZESTAW "+(i+1));
            for(int j=0;j<commands;j++)
            {
                String input = scan.next();
                if(input.equals("CREATE"))
                {
                    String order = scan.next();
                    int count = scan.nextInt();
                    int[] tab = new int[count];
                    tree.amount = count;
                    for(int k=0;k<count;k++)
                    {
                        tab[k] = scan.nextInt();
                    }

                    if(order.equals("PREORDER"))
                    {
                        tree.root = tree.createPreorder(tab, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
                    }
                    else if(order.equals("POSTORDER"))
                    {
                        tree.root = tree.createPostorder(tab, Integer.MIN_VALUE, Integer.MAX_VALUE, tab.length-1);
                    }
                }
                else if(input.equals("INSERT"))
                {
                    int count = scan.nextInt();
                    for(int k=0;k<count;k++)
                    {
                        tree.Insert(scan.nextInt());
                    }
                }
                else if(input.equals("PREORDER"))
                {
                    System.out.print(input+":");
                    tree.Preorder();
                    System.out.println();
                }
                else if(input.equals("INORDER"))
                {
                    System.out.print(input+":");
                    tree.Inorder();
                    System.out.println();
                }
                else if(input.equals("POSTORDER"))
                {
                    System.out.print(input+":");
                    tree.Postorder();
                    System.out.println();
                }
                else if(input.equals("PARENT"))
                {
                    tree.Parent(scan.nextInt());
                }
                else if(input.equals("DELETE"))
                {
                    int key = scan.nextInt();
                    if(!tree.Delete(key))
                    {
                        System.out.println("DELETE "+key+": BRAK");
                    }
                    else
                        {
                        tree.amount--;
                    }
                }
                else if(input.equals("SUCCESSOR"))
                {
                    int key = scan.nextInt();
                    Node result = tree.Successor(tree.root, key);
                    if(result != null)
                    {
                        System.out.print("SUCCESSOR "+key+": "+result.info);
                    }
                    else
                        {
                        System.out.print("SUCCESSOR "+key+": BRAK");
                        }
                    System.out.println();
                }
                else if(input.equals("PREDECESSOR"))
                {
                    int key = scan.nextInt();
                    Node result = tree.Predecessor(tree.root, key);
                    if(result != null)
                    {
                        System.out.print("PREDECESSOR "+key+": "+result.info);
                    }
                    else
                        {
                        System.out.print("PREDECESSOR "+key+": BRAK");
                        }
                    System.out.println();
                }
                else if(input.equals("HEIGHT")) {
                    int height = tree.Height(tree.root)-1;
                    if(height<0) {
                        System.out.println("HEIGHT: 0");
                    }
                    else {
                        System.out.println("HEIGHT: "+height);
                    }
                }
                else if(input.equals("SCAN")) {
                    String op = scan.next();
                    String order = scan.next();
                    if(op.charAt(0) == '+') {
                        if(order.equals("INORDER")) {
                            tree.ScanInorder(true);
                            if(tree.amount == 0){
                                System.out.print("SCAN + INORDER:");
                            }
                        }
                        else if(order.equals("POSTORDER")) {
                            tree.ScanPostorder(true);
                            if(tree.amount == 0){
                                System.out.print("SCAN + POSTORDER:");
                            }
                        }
                        else if(order.equals("PREORDER")) {
                            tree.ScanPreorder(true);
                            if(tree.amount == 0){
                                System.out.print("SCAN + PREORDER:");
                            }
                        }
                    }
                    else {
                        if(order.equals("INORDER")) {
                            tree.ScanInorder(false);
                            if(tree.amount == 0){
                                System.out.print("SCAN - INORDER:");
                            }
                        }
                        else if(order.equals("POSTORDER")) {
                            tree.ScanPostorder(false);
                            if(tree.amount == 0){
                                System.out.print("SCAN - POSTORDER:");
                            }
                        }
                        else if(order.equals("PREORDER")) {
                            tree.ScanPreorder(false);
                            if(tree.amount == 0){
                                System.out.print("SCAN - PREORDER:");
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}