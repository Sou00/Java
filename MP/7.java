//Igor Lonak - grupa 8

import java.util.Scanner;

public class Source {

    static Scanner scan = new Scanner(System.in);

    public static void errory(String gen)
    {
        int koniec = -1;
        int poczatek = gen.indexOf("ATG");
        int kodstart = 0;
        int kodstop = 0;
        int kodon = 0;

        for (int i = gen.indexOf("ATG") + 3; i < gen.length(); i = i + 3)
        {
            if(i < gen.length()-2 && (gen.charAt(i) == 'T' && gen.charAt(i+1) == 'A' && gen.charAt(i+2) == 'A'))
            {
                koniec = i + 2;
            }
            else if (i < gen.length()-2 && (gen.charAt(i) == 'T' && gen.charAt(i+1) == 'G' && gen.charAt(i+2) == 'A'))
            {
                koniec = i + 2;
            }
            else if (i < gen.length()-2 && (gen.charAt(i) == 'T' && gen.charAt(i+1) == 'A' && gen.charAt(i+2) == 'G'))
            {
                koniec = i + 2;
            }
        }

        for (int i = gen.indexOf("ATG") + 3; i < gen.length(); i = i + 3)
        {
            if(i < gen.length()-2 && (gen.charAt(i) == 'A' && gen.charAt(i+1) == 'T' && gen.charAt(i+2) == 'G'))
            {
                kodstart++;
            }
        }

        for (int i = koniec - 3; i > poczatek + 5; i = i - 3)
        {
            if(i > 2 && (gen.charAt(i-2) == 'T' && gen.charAt(i-1) == 'A' && gen.charAt(i) == 'A'))
            {
                kodstop++;
            }
            else if (i > 2 && (gen.charAt(i-2) == 'T' && gen.charAt(i-1) == 'G' && gen.charAt(i) == 'A'))
            {
                kodstop++;
            }
            else if (i > 2 && (gen.charAt(i-2) == 'T' && gen.charAt(i-1) == 'A' && gen.charAt(i) == 'G'))
            {
                kodstop++;
            }
        }
        for(int i = gen.indexOf("ATG") + 3; i < koniec - 3; i++)
        {
            if(gen.charAt(i) == 'A' && gen.charAt(i) == 'C' && gen.charAt(i) == 'T' && gen.charAt(i) == 'G')
            {
                kodon++;
            }
        }

        if(koniec == -1 || koniec == poczatek + 5 || kodon%3 != 0)
        {
            System.out.println("Wrong DNA sequence.");
            return;
        }

        if(kodstart != 0 || kodstop != 0)
        {
            System.out.println("More than one START/STOP codon.");
            return;
        }

        StringBuilder gen2 = new StringBuilder(gen);
        String kodony;

        kodony = gen2.substring(poczatek+3, koniec-2);
        String []tab = new String[10000];

        int j = 0;
        int k = 3;

        for (int i = 0; i < kodony.length()/3; i++)
        {
            tab[i] =  kodony.substring(j, k);
            j = j+3;
            k = k+3;
        }
        quickSort(tab, kodony.length());

    }

    public static void quickSort(String[] tab, int rozmiar) {

        String pivot;
        int []poczatek = new int[100];
        int []koniec = new int [100];
        int i=0;
        int lewo;
        int prawo;

        poczatek[0] = 0;
        koniec[0] = rozmiar/3;

        while (i>=0)
        {
            lewo=poczatek[i];
            prawo=koniec[i]-1;

            if (lewo<prawo)
            {
                pivot = tab[lewo];
                while (lewo<prawo)
                {
                    while (tab[prawo].compareTo(pivot)>=0 && lewo<prawo)
                        prawo--;

                    if (lewo<prawo)
                        tab[lewo++] = tab[prawo];

                    while (tab[lewo].compareTo(pivot)<=0 && lewo<prawo)
                        lewo++;

                    if (lewo<prawo)
                        tab[prawo--] = tab[lewo];
                }
                tab[lewo] = pivot;
                poczatek[i+1] = lewo+1;
                koniec[i+1] = koniec[i];
                koniec[i++] = lewo;

                if (koniec[i]-poczatek[i] > koniec[i-1]-poczatek[i-1])
                {
                    int tmp = poczatek[i];
                    poczatek[i] = poczatek[i-1];
                    poczatek[i-1] = tmp;
                    tmp = koniec[i];
                    koniec[i] = koniec[i-1];
                    koniec[i-1] = tmp;
                }
            }

            else {i--;}
        }
        for(int j=0; j<rozmiar/3; j++)
        {
            System.out.print(tab[j]);
        }
        System.out.println();
    }


    public static void main(String args[]){

        int ilosc;
        String duzygen;
        String gen;

        ilosc = scan.nextInt();

        for(int i=0; i<ilosc; i++)
        {
            int blad=0;
            gen = scan.next();
            duzygen = gen.toUpperCase();

            for (int j = 0; j < duzygen.length() ; j++)
            {
                if (duzygen.charAt(j) != 'C' && duzygen.charAt(j) != 'T' && duzygen.charAt(j) != 'A' && duzygen.charAt(j) != 'G')
                    blad=1;
            }
            if(blad==1)
            {
                System.out.println("Wrong character in DNA sequence.");
            }
            else if(!duzygen.contains("ATG"))
                {
                    System.out.println("Wrong DNA sequence.");
                }
                else {
                    errory(duzygen);
                    }

        }
    }
}
