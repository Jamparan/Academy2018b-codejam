import fashion_show.Models;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;

public class Fashion_Show {



    public  static  void main (String args[])throws FileNotFoundException, IOException{
        FileReader f = new FileReader("test.in");
        BufferedReader b = new BufferedReader(f);
        File fileOutput = new File("test.out");

        FileWriter w = new FileWriter(fileOutput);

        BufferedWriter bw = new BufferedWriter(w);

        PrintWriter wr = new PrintWriter(bw);

        int t;
        int n, m;
        String data[],temp_line;
        t = Integer.parseInt(b.readLine());



        for (int i=1; i <= t; i++){

            wr.print("Case #"+i+": ");

            temp_line = b.readLine();

            data = temp_line.split(" ");

            n = Integer.parseInt(data[0]) ;

            m = Integer.parseInt(data[1]);

            char matrix [][] = new char[n][n];

            matrix = fillMatrix(matrix,n);

            int pos_i, pos_j;
            char type_model;

            for (int j = 0; j <m; j++){

                temp_line = b.readLine();
                data = temp_line.split(" ");
                type_model = data[0].charAt(0);
                pos_i = Integer.parseInt(data[1]);
                pos_j = Integer.parseInt(data[2]);

                matrix[pos_i - 1][pos_j -1] = type_model;


            }

            Models mo = new Models(n,m,matrix.clone());

            mo.getModelsPositions();
            wr.print(mo.countPoints()+" ");
            wr.print(mo.matrix_Changes()+"\n");
            for (String e: mo.getChangesList()) {

                wr.println(e);

            }
            mo.printFashionMatrix();


        }
        wr.close();
        bw.close();
        b.close();



    }

    public static  char[][] fillMatrix(char [][]matrix, int n){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix [i][j] = '.';
            }
        }

        return matrix.clone();
    }

    public static  char[][] print(char [][]matrix, int n){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        return matrix;
    }





}
