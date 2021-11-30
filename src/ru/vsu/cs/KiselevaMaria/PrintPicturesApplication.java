package ru.vsu.cs.KiselevaMaria;

import java.io.*;

public class PrintPicturesApplication {

    public void printPict(){
        String file = PrintPicturesApplication.class.getResource("image/test.txt").getFile();
        File f = new File(file);
        if(f.exists()){
            try{
                BufferedReader fr = new BufferedReader(new FileReader(f));
                String s;
                while ((s= fr.readLine()) != null){
                    System.out.println(s);
                }
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PrintPicturesApplication pP = new PrintPicturesApplication();
        pP.printPict();
    }
}
