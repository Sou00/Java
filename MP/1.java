// Lonak Igor - grupa 8


import java.util.Scanner;

public class Source {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int ilosc = sc.nextInt();
        while (0 < ilosc--) {
            int wiersze = sc.nextInt();
            int kolumny = sc.nextInt();
            int maxsuma = -1;
            int pierwszywiersz = 0;
            int ostatniwiersz = 0;
            int pierwszakolumna = 0;
            int ostatniakolumna = 0;
            int maxrozmiar=wiersze*kolumny;
            int pomoc = 0;
            int a[][] = new int[wiersze][kolumny];
            for (int i = 0; i < wiersze; i++) {
                for (int j = 0; j < kolumny; j++) {
                    a[i][j] = sc.nextInt();
                    if (a[i][j] >= 0) {
                        pomoc = 1;
                    }

                }
            }
            if (pomoc == 0) {
                System.out.println("empty");
            } else {
                int b[][] = new int[wiersze + 1][kolumny];

                for (int i = 0; i < wiersze; i++) {
                    for (int j = 0; j < kolumny; j++) {
                        b[i + 1][j] = b[i][j] + a[i][j];
                    }
                }
                for (int i = 0; i <wiersze+1; i++) {
                    for (int j = 0; j < kolumny; j++) {
                        System.out.print(b[i][j]+" ");
                    }

                }
                for (int pierwszywiersz1 = 0; pierwszywiersz1 < wiersze; pierwszywiersz1++)
                {
                    for (int wiersz = pierwszywiersz1; wiersz < wiersze; wiersz++)
                    {
                        int suma = 0;
                        int pierwszakolumna1 = 0;
                        for (int kolumna = 0; kolumna < kolumny; kolumna++)
                        {
                            suma += b[wiersz + 1][kolumna] - b[pierwszywiersz1][kolumna];
                            int rozmiar =((wiersz - pierwszywiersz1) + 1)*((kolumna - pierwszakolumna1) + 1);
                            if(suma==0&&maxsuma==-1)
                            {
                                maxrozmiar=rozmiar;
                                maxsuma = suma;
                                pierwszywiersz = pierwszywiersz1;
                                ostatniwiersz = wiersz;
                                pierwszakolumna = pierwszakolumna1;
                                ostatniakolumna = kolumna;
                            }
                            else  if(suma<=0)
                            {
                                suma=0;
                                pierwszakolumna1=kolumna+1;
                            }
                            else
                            {
                                if (maxsuma < suma)
                                {
                                    maxrozmiar = rozmiar;
                                    maxsuma = suma;
                                    pierwszywiersz = pierwszywiersz1;
                                    ostatniwiersz = wiersz;
                                    pierwszakolumna = pierwszakolumna1;
                                    ostatniakolumna = kolumna;
                                }
                                else if (maxsuma == suma)
                                {
                                    if (maxrozmiar > rozmiar)
                                    {
                                        maxrozmiar = rozmiar;
                                        maxsuma = suma;
                                        pierwszywiersz = pierwszywiersz1;
                                        ostatniwiersz = wiersz;
                                        pierwszakolumna = pierwszakolumna1;
                                        ostatniakolumna = kolumna;
                                    }
                                    else if (maxrozmiar == rozmiar)
                                    {
                                        if (pierwszywiersz > pierwszywiersz1)
                                        {
                                            maxrozmiar = rozmiar;
                                            maxsuma = suma;
                                            pierwszywiersz = pierwszywiersz1;
                                            ostatniwiersz = wiersz;
                                            pierwszakolumna = pierwszakolumna1;
                                            ostatniakolumna = kolumna;
                                        }
                                        else if (pierwszywiersz == pierwszywiersz1)
                                        {
                                            if (ostatniwiersz > wiersz)
                                            {
                                                maxrozmiar = rozmiar;
                                                maxsuma = suma;
                                                pierwszywiersz = pierwszywiersz1;
                                                ostatniwiersz = wiersz;
                                                pierwszakolumna = pierwszakolumna1;
                                                ostatniakolumna = kolumna;
                                            }
                                            else if (ostatniwiersz == wiersz)
                                            {
                                                if (pierwszakolumna > pierwszakolumna1)
                                                {
                                                    maxrozmiar = rozmiar;
                                                    maxsuma = suma;
                                                    pierwszywiersz = pierwszywiersz1;
                                                    ostatniwiersz = wiersz;
                                                    pierwszakolumna = pierwszakolumna1;
                                                    ostatniakolumna = kolumna;
                                                }
                                                else if (pierwszakolumna == pierwszakolumna1)
                                                {
                                                    if (ostatniakolumna > kolumna)
                                                    {
                                                        maxrozmiar = rozmiar;
                                                        maxsuma = suma;
                                                        pierwszywiersz = pierwszywiersz1;
                                                        ostatniwiersz = wiersz;
                                                        pierwszakolumna = pierwszakolumna1;
                                                        ostatniakolumna = kolumna;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                System.out.println("max_sum = " + maxsuma + ", a[" + pierwszywiersz + ".." + ostatniwiersz + "][" + pierwszakolumna + ".." + ostatniakolumna + "]");

            }
        }
    }
}

