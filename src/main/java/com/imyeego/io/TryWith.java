package com.imyeego.io;

import java.io.*;
import java.util.Arrays;

public class TryWith {

    public static void main(String[] args) {
        /*byte b = -113;
        byte c = 7;
        System.out.println(b & 0xff);
        System.out.println((c & 0xff) << 8);
        System.out.println(b & 0xff + ((c & 0xff) << 8));
        double s = 12;
        System.out.println(s);*/
//        System.out.println(System.getProperty("user.dir"));
        String path = "/Users/mac/Documents/Desktop/PicFingerData";
        File file = new File(path);
        if (file.exists() && file.list() != null) {
            String name = file.list()[0];
            System.out.println(name);
            try (FileReader fr = new FileReader(path + "/" + name);
                 BufferedReader br = new BufferedReader(fr)){
                 String line = br.readLine();
                System.out.println(line);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
//        String[] classpaths = System.getProperty("java.class.path").split(";");

//        Arrays.stream(classpaths).forEach(System.out::println);

//        System.out.println(readTxt("C:\\Users\\zhongyu\\IdeaProjects\\quickjava\\src\\main\\java\\com\\imyeego\\io\\hello.txt"));

    }

    private static String readTxt(final String path){
        StringBuilder content = new StringBuilder();

        try (FileReader fr = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fr)){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return content.toString();

    }

}
