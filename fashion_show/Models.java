package fashion_show;

import java.io.*;
import java.util.ArrayList;


public class Models {

    private int n, m;

    private ArrayList<String> changesList;

    private char fashion_matrix [] [],matrix_backup[][];


    public  Models(int n, int m, char[][] matrix) throws IOException {
        this.m = m;
        this.n = n;
        this.fashion_matrix = matrix.clone();
        this.matrix_backup = new char[n][n];
        this.changesList = new ArrayList<String>();
        this.fillMatrix();




    }

    public void fillMatrix(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                this.matrix_backup[i][j] = this.fashion_matrix[i][j];
            }

        }
    }

    public void printFashionMatrix() {

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(this.fashion_matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void getModelsPositions(){



        checkO(n-1,n-1);

        checkPlus(n-1,n-1);

        checkX(n-1,n-1);





    }

    public void checkO(int i, int j){

        int count = 0;

        if (validation(i,j) ){

            if (validation(i-1,j-1) && (this.fashion_matrix[i-1][j-1] == 'x' || this.fashion_matrix[i-1][j-1] == '.'  ) ){
                count++;
            }
            if (validation(i+1,j-1) && ( this.fashion_matrix[i+1][j-1] == 'x' || this.fashion_matrix[i+1][j-1] == '.' ) ){
                count++;
            }
            if (validation(i+1,j+1) && (this.fashion_matrix[i+1][j+1] == 'x' || this.fashion_matrix[i+1][j+1] == '.' )){
                count++;
            }
            if (validation(i-1,j+1) && (this.fashion_matrix[i-1][j+1] == 'x' || this.fashion_matrix[i-1][j+1] == '.')){
                count++;
            }
            if (validation(i-1,j) && (this.fashion_matrix[i-1][j] == '+' || this.fashion_matrix[i-1][j] == '.' ) ){
                count++;
            }
            if (validation(i,j-1) && (this.fashion_matrix[i][j-1] == '+' || this.fashion_matrix[i][j-1] == '.' ) ){
                count++;
            }
            if (validation(i+1,j) && (this.fashion_matrix[i+1][j] == '+' || this.fashion_matrix[i+1][j] == '.' ) ){
                count++;
            }
            if (validation(i,j+1) && (this.fashion_matrix[i][j+1] == '+' || this.fashion_matrix[i][j+1] == '.'  )){
                count++;
            }

            if(!validation(i-1,j-1)){
                count++;
            }

            if(!validation(i+1,j-1) ){
                count++;
            }
            if(!validation(i+1,j+1)  ){
                count++;
            }
            if(!validation(i-1,j+1) ){
                count++;
            }
            if(!validation(i-1,j) ){
                count++;
            }

            if(!validation(i,j-1) ){
                count++;
            }
            if(!validation(i+1,j) ){
                count++;
            }
            if(!validation(i,j+1) ){
                count++;
            }

            if(count == 8){
                this.fashion_matrix[i][j] = 'o';
            }

            if(j == 0){
                i--;
                j = n-1;
            }else{
                j--;
            }

            this.checkO(i,j);
        }

    }

    public void checkPlus(int i, int j){

        int count = 0;

        if (validation(i,j)  ){

            if (validation(i-1,j-1) && this.fashion_matrix[i][j]=='.' && (this.fashion_matrix[i-1][j-1] == 'x' || this.fashion_matrix[i-1][j-1] == '.' ) ){
                count++;

            }
            if (validation(i+1,j-1) && this.fashion_matrix[i][j]=='.' &&( this.fashion_matrix[i+1][j-1] == 'x' || this.fashion_matrix[i+1][j-1] == '.' ) ){
                count++;
            }
            if (validation(i+1,j+1) && this.fashion_matrix[i][j]=='.' &&(this.fashion_matrix[i+1][j+1] == 'x' || this.fashion_matrix[i+1][j+1] == '.' )){
                count++;
            }
            if (validation(i-1,j+1) && this.fashion_matrix[i][j]=='.' && (this.fashion_matrix[i-1][j+1] == 'x' || this.fashion_matrix[i-1][j+1] == '.')){
                count++;
            }
            if(!validation(i-1,j-1) && this.fashion_matrix[i][j]=='.'){
                count++;
            }

            if(!validation(i+1,j-1) && this.fashion_matrix[i][j]=='.'){
                count++;
            }
            if(!validation(i+1,j+1) && this.fashion_matrix[i][j]=='.'){
                count++;
            }
            if(!validation(i-1,j+1) && this.fashion_matrix[i][j]=='.'){
                count++;
            }

            if(count == 4){
                this.fashion_matrix[i][j] = '+';
            }

            if(j == 0){
                i--;
                j = n-1;


            }else{
                j--;
            }

            this.checkPlus(i,j);
        }
    }

    public void checkX(int i, int j){

        int count = 0;

        if (validation(i,j)  ){

            if (validation(i-1,j) && this.fashion_matrix[i][j]=='.' &&  (this.fashion_matrix[i-1][j] == '+' || this.fashion_matrix[i-1][j] == '.'  ) ){
                count++;
            }

            if (validation(i,j-1) && this.fashion_matrix[i][j]=='.' &&  (this.fashion_matrix[i][j-1] == '+' || this.fashion_matrix[i][j-1] == '.' ) ){
                count++;
            }

            if (validation(i+1,j) && this.fashion_matrix[i][j]=='.' &&   (this.fashion_matrix[i+1][j] == '+' || this.fashion_matrix[i+1][j] == '.' ) ){
                count++;
            }

            if (validation(i,j+1) && this.fashion_matrix[i][j]=='.' &&   (this.fashion_matrix[i][j+1] == '+' || this.fashion_matrix[i][j+1] == '.'  ) ){
                count++;
            }
            if(!validation(i-1,j) && this.fashion_matrix[i][j]=='.'){
                count++;
            }
            if(!validation(i,j-1) && this.fashion_matrix[i][j]=='.'){
                count++;
            }
            if(!validation(i+1,j) && this.fashion_matrix[i][j]=='.'){
                count++;
            }
            if(!validation(i,j+1) && this.fashion_matrix[i][j]=='.'){
                count++;
            }

            if(count == 4){
                this.fashion_matrix[i][j] = 'x';
            }
            if(j == 0){
                i--;
                j = n-1;


            }else{
                j--;
            }

            this.checkX(i,j);

        }

    }

    public boolean validation(int i, int j){
        if (i >=0 && i <n && j >=0 && j <n ){

            return true;

        }
        else{
            return false;
        }

    }

    public int countPoints(){
        int suma=0;

        for (int i=0; i<n; i++){
            for (int j=0;j<n;j++){

                if(this.fashion_matrix[i][j]=='+' || this.fashion_matrix[i][j]=='x'){

                    suma+=1;
                }
                else if (this.fashion_matrix[i][j]=='o'){

                    suma+= 2;

                }


            }
        }

        return suma;
    }

    public int matrix_Changes(){
        int count=0;

        for (int i=0; i<n; i++){
            for (int j=0;j<n;j++){

                if(this.fashion_matrix[i][j]!= this.matrix_backup[i][j]){
                        count++;

                        this.changesList.add(this.fashion_matrix[i][j]+" "+(i+1)+" "+(j+1));
                }


            }

        }
        return count;
    }

    public ArrayList<String> getChangesList() {
        return changesList;
    }
}
