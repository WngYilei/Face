package com.example.facedemo.faceservice;

import java.io.*;

/**
 * 閺傚洣娆㈢拠璇插絿�?�搞儱鍙跨猾锟�
 */
public class FileUtil {

    /**
     * 鐠囪褰囬弬鍥︽閸愬懎顔愰敍灞肩稊娑撳搫鐡х粭锔胯鏉╂柨娲�?
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } 

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        } 

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 閸掓稑缂撶�涙濡潏鎾冲弳濞达�?  
        FileInputStream fis = new FileInputStream(filePath);  
        // 閸掓稑缂撴稉锟芥稉顏堟毐鎼达缚璐�?10240閻ㄥ嚉uffer
        byte[] bbuf = new byte[10240];  
        // 閻€劋绨穱婵嗙摠鐎圭偤妾拠璇插絿閻ㄥ嫬鐡ч懞鍌涙殶  
        int hasRead = 0;  
        while ( (hasRead = fis.read(bbuf)) > 0 ) {  
            sb.append(new String(bbuf, 0, hasRead));  
        }  
        fis.close();  
        return sb.toString();
    }

    /**
     * 閺嶈宓�?弬鍥︽鐠侯垰绶炵拠璇插絿byte[] 閺佹壆绮�?
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
        	FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
            	
                byte[] buffer = new byte[1024];
                int len;
                while((len = in.read(buffer)) != -1){
                	bos.write(buffer, 0, len);
                }
                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }
}
