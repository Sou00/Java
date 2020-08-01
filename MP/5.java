//Igor Lonak - grupa 8

import java.util.LinkedList;
import java.util.Scanner;


class Node
{
    private int x, y;
    private int dir;

    public Node(int i, int j)
    {
        this.x = i;
        this.y = j;
        this.dir = 0;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }


    public int getDir()
    {
        return dir;
    }

    public void setDir(int dir)
    {
        this.dir = dir;
    }
}

class Stack {
    private Node arr[];
    private int top;
    private int capacity;


    Stack(int size) {
        arr = new Node[size];
        capacity = size;
        top = -1;
    }


    public void push(Node x) {
        if (isFull()) {

            System.exit(1);
        }


        arr[++top] = x;
    }


    public Node pop() {

        if (isEmpty()) {

            System.exit(1);
        }


        return arr[top--];
    }


    public Node peek() {
        if (!isEmpty())
            return arr[top];
        else
            return null;


    }


    public int size() {
        return top + 1;
    }


    public Boolean isEmpty() {
        return top == -1;
    }

        public Boolean isFull () {
            return top == capacity - 1;
        }
    }



public class Source
{
    public static Scanner sc =new Scanner(System.in);
    private static int N ;
    private static int M ;
    static Stack s = new Stack(200);
    static LinkedList<Character> output=new LinkedList<>();
    private static boolean[][] visited ;

    public static void main(String[] args) {
        M = sc.nextInt();
        N = sc.nextInt();
        visited = new boolean[N][M];

        int maze[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maze[i][j] = sc.nextInt();
                if(maze[i][j]==0)
                {
                    maze[i][j]=1;
                }
                else{maze[i][j]=0;}
            }
        }
        int ilosc=sc.nextInt();


        while(ilosc-->0) {
            for (int i = 0; i < visited.length; i++)
            {
                for (int j = 0; j < visited[i].length; j++)
                {
                    visited[i][j] = true;
                }
            }
            char typ = sc.next().charAt(0);
            int y1 = sc.nextInt();
            int x1 =N-sc.nextInt()-1;
            int y2 = sc.nextInt();
            int x2 =N-sc.nextInt()-1;

            if (typ == 'i') {
                if (IterativePathfinder.findPath(maze, x1, y1, x2, y2)) {

                   int i=0;
                   if(x1==x2&&y1==y2)
                   {
                       System.out.print("i");
                   }
                   else{
                    while(!s.isEmpty()){
                        Node temp1=s.peek();
                        s.pop();
                        int kierunek=temp1.getDir();
                        if(kierunek==4)
                        {
                            output.addFirst('E');
                        }
                        else if(kierunek==3)
                        {
                            output.addFirst('S');
                        }
                        else if(kierunek==2)
                        {
                            output.addFirst('W');
                        }
                        else if(kierunek==1)
                        {
                            output.addFirst('N');
                        }

                        i++;
                        }
                    System.out.print("i");
                    while(i-->1)
                    {
                        System.out.print(" "+output.pop());
                    }
                    output.pop();
                   }
                }
                else
                {
                    System.out.print("i X");}
                System.out.println();

            }
            else if (typ == 'r')
            {

                RecursivePathfinder rec=new RecursivePathfinder(x2,y2);
                System.out.print("r");
                rec.findPath(maze,x1,y1);

                while(!output.isEmpty())
                {
                    System.out.print(" "+output.pop());
                }
                System.out.println();
            }
        }
    }
public static class IterativePathfinder {
    private static boolean findPath(int maze[][], int x1, int y1, int x2, int y2) {

        int i = x1, j = y1;
        visited[i][j]=false;

        int fx, fy;
        fx = x2;
        fy = y2;
        if(x1==x2&&y1==y2)
        {
            return true;
        }

        Node temp = new Node(i, j);

        s.push(temp);

        while (!s.isEmpty()) {


            temp = s.peek();
            int d = temp.getDir();
            i = temp.getX();
            j = temp.getY();


            temp.setDir(temp.getDir() + 1);
            s.pop();
            s.push(temp);


            if (i == fx && j == fy) {
                return true;
            }

            if (d == 0) {

                if (i - 1 >= 0 && maze[i - 1][j] == 1 &&
                        visited[i - 1][j]) {
                    Node temp1 = new Node(i - 1, j);
                    visited[i - 1][j] = false;
                    s.push(temp1);
                }
            } else if (d == 1) {

                if (j - 1 >= 0 && maze[i][j - 1] == 1 &&
                        visited[i][j - 1]) {
                    Node temp1 = new Node(i, j - 1);
                    visited[i][j - 1] = false;
                    s.push(temp1);
                }
            } else if (d == 2) {

                if (i + 1 < N && maze[i + 1][j] == 1 &&
                        visited[i + 1][j]) {
                    Node temp1 = new Node(i + 1, j);
                    visited[i + 1][j] = false;
                    s.push(temp1);
                }
            } else if (d == 3) {

                if (j + 1 < M && maze[i][j + 1] == 1 &&
                        visited[i][j + 1]) {
                    Node temp1 = new Node(i, j + 1);
                    visited[i][j + 1] = false;
                    s.push(temp1);
                }
            }
            else {
                visited[temp.getX()][temp.getY()] = true;
                s.pop();
            }
        }

        return false;
    }
}


    public static class RecursivePathfinder
    {
        int x2;
        int y2;

         RecursivePathfinder(int a,int b)
        {
            x2=a;
            y2=b;
        }

        boolean findPath(int maze[][],int x1,int y1)
        {
            int sol[][] = new int[N][M];

            if (search(maze, x1, y1, sol) == false)
            {
                System.out.print(" X");
                return false;
            }


            return true;
        }

        boolean search(int maze[][], int x, int y, int sol[][])
        {
            if (x == x2 && y == y2 )
            {
                sol[x][y] = 1;
                return true;
            }

            if (x >= 0 && x < N && y >= 0 && y < M && maze[x][y] == 1 && sol[x][y]==0)
            {

                sol[x][y] = 1;

                if (search(maze, x + 1, y, sol))
                {
                    output.addFirst('S');
                    return true;
                }

                if (search(maze, x, y + 1, sol))
                {
                    output.addFirst('E');
                    return true;
                }
                if(x>0)
                if (search(maze,x - 1, y, sol))
                {
                    output.addFirst('N');
                    return true;
                }
                if(y>0)
                if (search(maze,x, y - 1, sol))
                {
                    output.addFirst('W');
                    return true;
                }

               // sol[x][y] = 0;
                //return false;
            }

            return false;
        }


    }



}