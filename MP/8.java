//Igor Lonak - grupa 8

import java.util.Scanner;

class Node{

    int data;
    Node left, right;

    Node(int x)
    {
        data = x;
        left = right = null;
    }
}

class Source {

    public static Scanner scan = new Scanner(System.in);
    static int count = 0;

    public static Node insert(Node root, int x)
    {
        if (root == null)
            return new Node(x);

        if (x <= root.data)
            root.left = insert(root.left, x);

        else if (x >= root.data)
            root.right = insert(root.right, x);

        return root;
    }

    public static Node SearchInorder(Node root, int k)
    {

        if (root == null)
            return null;

        Node left = SearchInorder(root.left, k);

        if (left != null)
            return left;

        count++;
        if (count == k)
            return root;

        return SearchInorder(root.right, k);
    }

    public static int print(Node root, int k)
    {
        count = 0;
        Node output = SearchInorder(root, k);
        return output.data;
    }

    public static void main (String[] args) {

        int ilosc = scan.nextInt();

        for(int i=0; i<ilosc; i++)
        {
            Node root = null;
            int n = scan.nextInt();
            int tab[] = new int[n];

            for(int j=0; j<n; j++)
                tab[j] = scan.nextInt();

            for(int k=0; k<tab.length; k++)
            {
                root = insert(root,tab[k]);
            }

            int m = scan.nextInt();

            for(int l=0; l<m; l++)
            {
                int k = scan.nextInt();

                if (k >= 1 && k <= tab.length)
                {
                    System.out.println(k+" "+print(root, k));
                }

                else System.out.println(k + " brak");
            }
        }
    }
} 
