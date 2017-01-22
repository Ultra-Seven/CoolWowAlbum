package com.fudan2015.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.fudan2015.dao.impl.UserDaoImpl;
import com.opensymphony.xwork2.ActionContext;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class VerificationAction {
	private InputStream inputStream;
	
	public String execute(){
		BufferedImage image
		 =new BufferedImage(120, 40,
				 BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
		int number;
		char code;
        String checkCode = "";
        Font[] fonts = {
                new Font("Times New Roman", Font.BOLD,27),
                new Font("Georgia",Font.BOLD, 27),
                new Font("Arial",Font.BOLD, 27),
                new Font("Comic Sans MS",Font.BOLD, 27)
             };
		Random r=new Random();
		//设置背景的颜色
		g.setColor(Color.white);
		g.fillRect(0, 0, 120, 40);
		//验证码数字颜色随机
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//产生验证码的数字
		
		for (int i = 0; i < 4; i++)//可以任意设定生成验证码的位数
        {
            number = r.nextInt(255);

            if (number % 2 == 0)
                code = (char)('0' + (char)(number % 10));
            else
                code = (char)('A' + (char)(number % 26));
            checkCode=checkCode+code;
            g2d.setFont(fonts[r.nextInt(4)]);
			g2d.drawString(code+"", 10+i*25, 30);
			g2d.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        }
		
		
		
		
		 Random random = new Random();       //生成随机生成器
         for (int i = 0; i < 300; i++)
         {
             int x = random.nextInt(120);
             int y = random.nextInt(40);

             g2d.drawLine(x, y, x, y);
             g2d.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
         }
         //画图片的边框线
         g2d.drawRect(0, 0, 120 - 1, 40 - 1);
         g2d.setColor(Color.black);
         
		
		ActionContext.getContext().getSession()
		         .put("checkCode", checkCode+"");
		ByteArrayOutputStream bos
		   =new ByteArrayOutputStream();
		JPEGImageEncoder encoder
		   =JPEGCodec.createJPEGEncoder(bos);
		try {
			encoder.encode(image);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}

	public String userGetCheck(){
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/plain;charset=UTF-8"); 
		HttpServletRequest request = ServletActionContext.getRequest();
		String check= request.getParameter("check");
		String checkUpper = check.toUpperCase();
		String checkCode=(String) ActionContext.getContext().getSession().get("checkCode");
		if(checkUpper.equals(checkCode)){
			try { 
			     response.getWriter().write("true"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
		}
		else{
			try { 
			     response.getWriter().write("false"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
		}
		return null;
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
