package com.example.facedemo.faceservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.baidu.aip.util.Base64Util;

public class FaceService {

	private static String matchUrl = "https://aip.baidubce.com/rest/2.0/face/v2/match";
	public static String match(String filepath01, String filepath02){
		try {
            byte[] imgData = FileUtil.readFileByBytes(filepath01);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");


            byte[] imgData2 = FileUtil.readFileByBytes(filepath02);
            String imgStr2 = Base64Util.encode(imgData2);
            String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");

            String param = "images=" + imgParam + "," + imgParam2;
			String accessToken = AuthService.getAuth();
			String result = HttpUtil.post(matchUrl, accessToken, param);
			System.out.println(result);
            return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "失败";
	}
	
}
