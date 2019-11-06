package com.imyeego.functional;

//import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.io.*;
import java.util.Optional;

public class FunctionalTest {

    public static void main(String[] args) {
        /*User user = null;
        user = new User("liuzhao");

        System.out.println(getName(user));*/

//        Inner inner = new Inner("Inner");
//        Inner inner = null;
//        Nested nested = new Nested(inner);
//        Nested nested = null;
//        Outer outer = new Outer(nested);
//        System.out.println(getMsg(outer));
//        System.out.println(getFeatureByDat("D:\\34262219951006295X_1.dat"));
//        System.out.println("-------------------------");
//        readTxtFile("D:\\34262219951006295X_1.dat");

    }

    private static String getName(User user){
        return Optional.ofNullable(user).map(User::getUserName).orElse("unKnown");
    }

    private static String getMsg(Outer outer){
        return Optional.ofNullable(outer)
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getMsg)
                .orElse("unKnown");
    }

    private static String getFeatureByDat(String path) {
        StringBuilder content = new StringBuilder();
        byte[] buf = new byte[1024];
        try(FileInputStream fis = new FileInputStream(path)) {
            while (fis.read(buf) != -1) {
                content.append(java.util.Base64.getEncoder().encodeToString(buf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

//    public static void readTxtFile(String filePath){
//        try {
//            String encoding="GBK";
//            File file=new File(filePath);
//            encoding= getFilecharset(file);
//
//            if(file.isFile() && file.exists()){ //判断文件是否存在
//                InputStreamReader read = new InputStreamReader(
//                        new FileInputStream(file),encoding);//考虑到编码格式
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;
//                String a ="";
//                while((lineTxt = bufferedReader.readLine()) != null){
////                        System.out.println(lineTxt);
//                    byte [] stringArr = lineTxt.getBytes();
////                    	System.out.println(Base64.getEncoder().encode(stringArr));
//
//                    a+=lineTxt;
//
//
//                }
//                System.out.println(a);
//                try{
//                    byte[] encodeBase64 = Base64.encodeBase64(a.getBytes("UTF-8"));
//                    System.out.println("RESULT: " + new String(encodeBase64));
//                } catch(UnsupportedEncodingException e){
//                    e.printStackTrace();
//                }
//                read.close();
//            }else{
//                System.out.println("找不到指定的文件");
//            }
//        } catch (Exception e) {
//            System.out.println("读取文件内容出错");
//            e.printStackTrace();
//        }
//
//    }
    private static  String getFilecharset(File sourceFile) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; //文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; //文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; //文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; //文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }
}
