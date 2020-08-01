//Lonak Igor - grupa 8
import java.util.Scanner;

class Source {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int ilosczestawow = sc.nextInt();
        while (ilosczestawow-- != 0) {
            int dlugosc = sc.nextInt();
            int a[]= new int [dlugosc];
            for (int i = 0; i < dlugosc; i++) {
                a[i] = sc.nextInt();
            }
            int iloscpytan = sc.nextInt();
            for (int i = 0; i < iloscpytan; i++) {
                int liczba1 = sc.nextInt();
                int liczba2 = sc.nextInt();
                int lewo = 0;
                int prawo = dlugosc - 1;

                // szukamy pierwszego wystapienia pierwszej liczby
                int pierwszy = -1;
                while (lewo <= prawo)
                {
                    int srodek = (prawo - lewo) / 2 + lewo;
                    if (a[srodek] > liczba1) {
                        prawo = srodek - 1;
                    } else if (a[srodek] == liczba1)
                    {
                        pierwszy = srodek;
                        prawo = srodek - 1;
                    } else
                        lewo = srodek + 1;
                }
                if(pierwszy==-1) //jesli nie znalezlismy to szukamy najmniejszej liczby ktora jest wieksza od szukanej
                {
                    lewo = 0;
                    prawo = dlugosc - 1;
                    while(lewo<=prawo)
                    {
                        int srodek = (lewo+prawo)/2;
                        if(a[srodek]<=liczba1)
                        {
                            lewo=srodek+1;
                        }
                        else
                        {
                            pierwszy=srodek;
                            prawo=srodek-1;
                        }
                    }
                }

                //szukamy ostatniego wystapienia drugiej liczby
                int ostatni = -1;
                lewo = 0;
                prawo = dlugosc - 1;
                while (lewo <= prawo) {
                    int srodek = (prawo - lewo) / 2 + lewo;
                    if (a[srodek] > liczba2) {
                        prawo = srodek - 1;
                    } else if (a[srodek] <= liczba2) {
                        ostatni = srodek;
                        lewo = srodek + 1;
                    } else
                        lewo = srodek + 1;
                }

                if(ostatni==-1)//jesli nie znaleziono to szukamy najwiekszej liczby mniejszej od szukanej
                {
                    lewo = 0;
                    prawo = dlugosc - 1;
                    while(lewo<=prawo)
                    {
                        int srodek = (lewo+prawo)/2;
                        if(a[srodek]>=liczba2)
                        {
                            prawo=srodek-1;
                        }
                        else
                        {
                            ostatni=srodek;
                            lewo=srodek+1;
                        }
                    }
                }

                int rozmiar=ostatni-pierwszy;
                if (liczba2-liczba1>=0&&rozmiar>=0&&ostatni!=-1&&pierwszy!=-1) {
                    rozmiar++;
                    System.out.println(rozmiar);
                } else System.out.println("0");
            }
            Source.roznezeznania(a,dlugosc);
        }
    }
    public static void roznezeznania(int a[],int n)
    {
        int zeznanie=a[0];
        int liczbazeznan=1;
        for (int i = 1; i < n; i++)
        {
            if(a[i]!=zeznanie)
            {
                zeznanie=a[i];
                liczbazeznan++;
            }
        }
        System.out.println(liczbazeznan);
    }
}