package com.hananoq.utils;

import javax.servlet.http.HttpServlet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码
 */
public class CheckCode extends HttpServlet {
	public static BufferedImage createImage(String code){
		//在内存中创建一个长80，宽30的图片，默认黑色背景
		//参数一：长
		//参数二：宽
		//参数三：颜色
		int width = 80;
		int height = 30;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

		//获取画笔
		Graphics g = image.getGraphics();
		//设置画笔颜色为灰色
		g.setColor(Color.GRAY);
		//填充图片
		g.fillRect(0,0, width,height);

		//设置画笔颜色为黄色
		g.setColor(Color.YELLOW);
		//设置字体的小大
		g.setFont(new Font("黑体",Font.BOLD,24));
		//向图片上写入验证码
		g.drawString(code,15,25);

		return image;
	}

	/**
	 * 产生4位随机字符串 
	 */
	public static String getCheckCode() {
		String base = "0123456789ABCDEFGabcdefg";
		int size = base.length();
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=4;i++){
			//产生0到size-1的随机值
			int index = r.nextInt(size);
			//在base字符串中获取下标为index的字符
			char c = base.charAt(index);
			//将c放入到StringBuffer中去
			sb.append(c);
		}
		return sb.toString();
	}
}



