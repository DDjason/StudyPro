package com.example.jason.studypro;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/29$ 15:37$
 * <p/>
 */
public class Test {
    public static void main(String[] args) {
        File s = new File("D:\\test.rar");
        File t = new File("D:\\to.rar");
        File sss = new File("D:\\to3.rar");

        long startt,endt;
        startt = System.currentTimeMillis();
        fileChannelCopy(s, new File("D:\\to1.rar"));
        endt = System.currentTimeMillis();
        System.out.println(startt + "  "+ endt +  " "+(endt-startt)+"\n");
        startt = System.currentTimeMillis();
        copy(s, new File("D:\\to2.rar"));
        endt = System.currentTimeMillis();
        System.out.println(startt + "  "+ endt +  " "+(endt-startt)+"\n");
        startt = System.currentTimeMillis();
        copy2(s, sss.toString());
        endt = System.currentTimeMillis();
        System.out.println(startt + "  "+ endt +  " "+(endt-startt)+"\n");

    }

    public static void fileChannelCopy(File s, File t) {
        FileInputStream  fi  = null;
        FileOutputStream fo  = null;
        FileChannel      in  = null;
        FileChannel      out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void copy(File s,File t){
        InputStream  in  = null;
        OutputStream out = null;
        try{
            in = new BufferedInputStream(new FileInputStream(s));
            out = new BufferedOutputStream(new FileOutputStream(t));
            byte[] bytes = new byte[2048];
            while (in.read(bytes) != -1){
                out.write(bytes,0,1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (null != in){
                    in.close();
                }
                if (null != out){
                    out.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;
    public static boolean copy2(File sourceFile, String targetFile) {
        FileInputStream  inputStream  = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(new File(targetFile));
            FileChannel in  = inputStream.getChannel();
            FileChannel out = outputStream.getChannel();
            //设定缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                buffer.flip();//准备写入，防止其他读取，锁住文件
                out.write(buffer);
                buffer.clear();//准备读取。将缓冲区清理完毕，移动文件内部指针
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }

                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
