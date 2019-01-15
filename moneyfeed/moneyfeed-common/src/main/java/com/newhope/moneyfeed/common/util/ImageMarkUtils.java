package com.newhope.moneyfeed.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文字水印
 */
public final class ImageMarkUtils {
	private static Logger logger = LoggerFactory.getLogger(ImageMarkUtils.class);

	/**
	 * @param srcImgPath 源图片路径
	 * @param tarImgPath 保存的图片路径
	 * @param markContentColor 水印颜色
	 * @param waterMarkContent 水印内容
	 * @param font 水印字体
	 * @throws Exception 
	 */
	private static void addWaterMarkTextForUrl(String srcImgPath, String tarImgPath, Color markContentColor, String waterMarkContent, Font font) throws Exception {
		FileOutputStream outImgStream = null;
//		logger.info(">>>>>>>>>>:"+srcImgPath);
		URL url = new URL(srcImgPath);
		URLConnection urlConn = url.openConnection();
		InputStream input = urlConn.getInputStream();
		try {
			// 读取原图片信息
			//			File srcImgFile = new File(new URI(srcImgPath));//得到文件
			Image srcImg = ImageIO.read(input);//文件转化为图片
			int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
			int srcImgHeight = srcImg.getHeight(null);//获取图片的高
			// 加水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			g.setColor(markContentColor); //根据图片的背景设置水印颜色
			g.setFont(font); //设置字体

			//设置水印的坐标
			int x = srcImgWidth - 20 - ImageMarkUtils.getWatermarkWidth(waterMarkContent, g);
			int y = srcImgHeight - 1 - ImageMarkUtils.getWatermarkHeight(waterMarkContent, g);

			g.drawString(waterMarkContent, x, y); //画出水印
			g.dispose();
			// 输出图片  
			outImgStream = new FileOutputStream(tarImgPath);
			ImageIO.write(bufImg, "jpg", outImgStream);
			logger.debug("[ImageMarkUtils.addWaterMarkText]添加文字水印完成");
			outImgStream.flush();
			outImgStream.close();
			input.close();

		} catch (Exception e) {
			logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
			//			throw e;
		} finally {
			try {
				if (null != outImgStream) {
					outImgStream.close();
				}
				if (null != input) {
					input.close();
				}
			} catch (Exception e) {
				logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
				//				throw e;
			}
		}
	}

	/**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public static void addWaterMarkText (String srcImgPath, String tarImgPath, Color markContentColor, String waterMarkContent, Font font) {
    	FileOutputStream outImgStream = null;
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
			int x = srcImgWidth - 20 - ImageMarkUtils.getWatermarkWidth(waterMarkContent, g);
			int y = srcImgHeight - 1 - ImageMarkUtils.getWatermarkHeight(waterMarkContent, g);
			
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();  
            // 输出图片  
            outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            logger.debug("[ImageMarkUtils.addWaterMarkText]添加文字水印完成");
            outImgStream.flush();  
            outImgStream.close();  
        } catch (Exception e) {
			logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
		} finally {
			try {
				if (null != outImgStream) {
					outImgStream.close();
				}
			} catch (Exception e) {
				logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
			}
		}
    }
    
	/**  
	* @Title: getWatermarkLength  
	* @Description: 得到内容的长度
	* @param @param waterMarkContent
	* @param @param g
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws  
	*/
	private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
		return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
	}

	/**  
	* @Title: getWatermarkWidth  
	* @Description: 得到内容宽度
	* @param @param waterMarkContent
	* @param @param g
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws  
	*/
	public static int getWatermarkWidth(String waterMarkContent, Graphics2D g) {
		Font f = g.getFont();
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		return fm.stringWidth(waterMarkContent);
	}

	/**  
	* @Title: getWatermarkHeight  
	* @Description: 得到内容高度
	* @param @param waterMarkContent
	* @param @param g
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws  
	*/
	private static int getWatermarkHeight(String waterMarkContent, Graphics2D g) {
		Font f = g.getFont();
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		return fm.getHeight();
	}

	/**  
	* @Title: markText  
	* @Description: 加文字水印
	* @param @param url
	* @param @param text
	* @param @param currentDir
	* @param @throws Exception    设定文件  
	* @return void    返回类型  
	* @throws  
	*/
	public static void markText(String url, String text, String currentDir) throws Exception {
		String srcImgPath = url; //源图片地址
		String tarImgPath = url;
//		String srcImgPath = url.replaceFirst("http://zhuxiaoeimg.breeds.cc", "http://10.60.50.2:5000");
//		String tarImgPath = WechatConstant.UPLOAD_FILE + currentDir + "/" + url.substring(url.lastIndexOf("/")); //待存储的地址
		String waterMarkContent = text;
		if(text.length() > 10){
			waterMarkContent = text.substring(0, 10);
		}
		
		//水印内容
		Font font = new Font("隶书", Font.PLAIN, 35); //水印字体
		Color color = new Color(255, 255, 255, 200); //水印图片色彩以及透明度
		ImageMarkUtils.addWaterMarkText(srcImgPath, tarImgPath, color, waterMarkContent, font);
	}

	
	/**  
	* @Title: markText  
	* @Description: 加文字水印
	* @param url 需要添加水印的图片路径
	* @param text 水印文字
	* @param color 水印文字色彩以及透明度
	* @param font 水印字体
	* @param degree 水印文字旋转角度，为null表示不旋转
	* @param @throws Exception    设定文件  
	* @return void    返回类型  
	* @throws  
	*/
	public static void markText(String url, String text, Color color, Font font, Integer degree) throws Exception {
		logger.info(">>>>>" + url);
		String srcImgPath = url; //源图片地址
		String tarImgPath = url;
		String waterMarkContent = text;
		if(text.length() > 10){
			waterMarkContent = text.substring(0, 10);
		}
		ImageMarkUtils.addWaterMarkText(srcImgPath, tarImgPath, color, waterMarkContent, font, degree);
}
	
	/**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public static void addWaterMarkText (String srcImgPath, String tarImgPath, Color markContentColor, String waterMarkContent, Font font, Integer degree) {
    	logger.info(">>>>>" + srcImgPath);
    	logger.info(">>>>>" + tarImgPath);
    	FileOutputStream outImgStream = null;
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体
            
            int x = srcImgWidth - 20 - ImageMarkUtils.getWatermarkWidth(waterMarkContent, g);
			int y = srcImgHeight - 1 - ImageMarkUtils.getWatermarkHeight(waterMarkContent, g);
			
			//呈现一个图像，在绘制前进行从图像空间到用户空间的转换
			g.drawImage(srcImg.getScaledInstance(srcImgWidth,srcImgHeight,Image.SCALE_SMOOTH),0,0,null);
			if (null != degree) {
				//设置水印旋转
				g.rotate(Math.toRadians(degree),(double) bufImg.getWidth() / 2, (double) bufImg.getHeight() / 2);
			}
			
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();  
            // 输出图片  
            outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            logger.info("[ImageMarkUtils.addWaterMarkText]添加文字水印完成");
            outImgStream.flush();  
            outImgStream.close();  
        } catch (Exception e) {
			logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
		} finally {
			try {
				if (null != outImgStream) {
					outImgStream.close();
				}
			} catch (Exception e) {
				logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
			}
		}
    }

	public static ByteArrayInputStream markText(InputStream inputStream, String text) throws Exception {
		String waterMarkContent = text;
		if(text.length() > 10){
			waterMarkContent = text.substring(0, 10);
		}
		//水印内容
		Font font = new Font("隶书", Font.PLAIN, 35); //水印字体
		Color color = new Color(255, 255, 255, 200); //水印图片色彩以及透明度
		return ImageMarkUtils.addWaterMarkText(inputStream, color, waterMarkContent, font,null);
	}

	/**
	 * 直接对流进行加水印，省去磁盘io操作提升效率
	 * @param markContentColor 水印颜色
	 * @param waterMarkContent 水印内容
	 * @param font 水印字体
	 */
	public static ByteArrayInputStream addWaterMarkText (InputStream inputStream, Color markContentColor, String waterMarkContent, Font font, Integer degree)  {
		ByteArrayOutputStream outImgStream = outImgStream = new ByteArrayOutputStream();
		try {
			// 读取原图片信息
			Image srcImg = ImageIO.read(inputStream);//文件转化为图片
			int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
			int srcImgHeight = srcImg.getHeight(null);//获取图片的高
			// 加水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();
			g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
			g.setColor(markContentColor); //根据图片的背景设置水印颜色
			g.setFont(font);              //设置字体
			int x = srcImgWidth - 20 - ImageMarkUtils.getWatermarkWidth(waterMarkContent, g);
			int y = srcImgHeight - 1 - ImageMarkUtils.getWatermarkHeight(waterMarkContent, g);
			//呈现一个图像，在绘制前进行从图像空间到用户空间的转换
			g.drawImage(srcImg.getScaledInstance(srcImgWidth,srcImgHeight,Image.SCALE_SMOOTH),0,0,null);
			if (null != degree) {
				//设置水印旋转
				g.rotate(Math.toRadians(degree),(double) bufImg.getWidth() / 2, (double) bufImg.getHeight() / 2);
			}
			g.drawString(waterMarkContent, x, y);  //画出水印
			g.dispose();
			// 输出图片
			ImageIO.write(bufImg, "jpg", outImgStream);

		} catch (Exception e) {
			logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
		}
		return parse(outImgStream);

	}

	/**
	 * 将输入流转为输出流
	 * @param out
	 * @return
	 */
	public static ByteArrayInputStream parse(OutputStream out)  {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}

//	public static void main(String[] args) throws Exception {
////		String srcImgPath = "http://zhuxiaoeimg.breeds.cc/wechat/image/20171010/1507619871883.jpg"; //源图片地址
////		String tarImgPath = "D:/temp/xx/trade/wechat/image/20170930/1.jpg"; //待存储的地址
////		String waterMarkContent = "金黔在线 www.gog.com.cn"; //水印内容
////		Font font = new Font("隶书", Font.PLAIN, 35); //水印字体
////		Color color = new Color(255, 255, 255, 200); //水印图片色彩以及透明度
////		ImageMarkUtils.addWaterMarkText(srcImgPath, tarImgPath, color, waterMarkContent, font);
////
////		String url = "http://zhuxiaoeimg.breeds.cc//wechat/image/20171010/1507619871883.jpg";
////		String srcImgPath = url; //源图片地址
////		srcImgPath = url.replaceFirst("http://zhuxiaoeimg.breeds.cc", "http://10.60.50.2:5000");
////		System.out.println(srcImgPath);
//		String url = "http://zhuxiaoeimg.breeds.cc/wechat/image/20171010/1507619871883.jpg";
//		System.out.println(url.substring(url.indexOf("/"), url.length()));
//		System.out.println(url.replaceAll("http://*.\\.*(com|cn|net|org|biz|info|cc|tv)/", ""));
//	}

}
