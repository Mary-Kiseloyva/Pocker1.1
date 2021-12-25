package KiselevaMaria;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;

public class PrintPicturesApplication {

    public void printPict(){
        URL resource = PrintPicturesApplication.class.getResource("image");
        String file = null;
        try {
            file = URLDecoder.decode(resource.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
