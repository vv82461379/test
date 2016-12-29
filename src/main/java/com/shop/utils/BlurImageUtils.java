package com.shop.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * 只需要更改图片地址即可，实现图片模糊
 * @author garen_li
 *
 */
public class BlurImageUtils {
	
	
	
	public static void main(String[] args) throws IOException {
	    BufferedImage img = ImageIO.read(new File("d:/c.jpg"));
	    long start = System.currentTimeMillis();
	    img = blur(img, 15);
	    long end = System.currentTimeMillis();
	    ImageIO.write(img, "jpeg", new File("e:/test.jpeg"));//保存为test.jpeg文件
	    System.out.println("花费时间："+(end-start));
	    System.out.println("success");
	}
	
	/**
	 *
	 * <p class="detail">
	 * 功能：模糊执行方法
	 * </p>
	 * @date 2016年4月20日
	 * @author <a href="mailto:1435290472@qq.com">zq</a>
	 * @param img        原图片
	 * @param radius     模糊权重
	 * @return     模糊后图片
	 * @throws IOException
	 */
	public static BufferedImage blur(BufferedImage img,int radius) throws IOException{
	    int height = img.getHeight();
	    int width = img.getWidth();
	    int[] values = getPixArray(img, width, height);
	    values = doBlur(values, width, height, radius);
	    img.setRGB(0, 0, width, height, values, 0, width);
	    return img;
	}
	
	/**
	 *
	 * <p class="detail">
	 * 功能：获取图像像素矩阵
	 * </p>
	 * @date 2016年4月20日
	 * @author <a href="mailto:1435290472@qq.com">zq</a>
	 * @param im
	 * @param w
	 * @param h
	 * @return
	 */
	private static int[] getPixArray(Image im, int w, int h) {
	    int[] pix = new int[w * h];
	    PixelGrabber pg = null;
	    try {
	        pg = new PixelGrabber(im, 0, 0, w, h, pix, 0, w);
	        if (pg.grabPixels() != true)
	            try {
	                throw new java.awt.AWTException("pg error" + pg.status());
	            } catch (Exception eq) {
	                eq.printStackTrace();
	            }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return pix;
	}
	
	/**
	 *
	 * <p class="detail">
	 * 功能：高斯模糊算法。
	 * </p>
	 * @date 2016年4月20日
	 * @author <a href="mailto:1435290472@qq.com">zq</a>
	 * @param pix
	 * @param w
	 * @param h
	 * @param radius
	 * @return
	 * @throws IOException
	 */
	public static int[] doBlur(int[] pix,int w,int h,int radius) throws IOException {
	    int wm = w - 1;
	    int hm = h - 1;
	    int wh = w * h;
	    int div = radius + radius + 1;
	    int r[] = new int[wh];
	    int g[] = new int[wh];
	    int b[] = new int[wh];
	    int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
	    int vmin[] = new int[Math.max(w, h)];
	    int divsum = (div + 1) >> 1;
	    divsum *= divsum;
	    int dv[] = new int[256 * divsum];
	    for (i = 0; i < 256 * divsum; i++) {
	        dv[i] = (i / divsum);
	    }
	    yw = yi = 0;
	    int[][] stack = new int[div][3];
	    int stackpointer;
	    int stackstart;
	    int[] sir;
	    int rbs;
	    int r1 = radius + 1;
	    int routsum, goutsum, boutsum;
	    int rinsum, ginsum, binsum;
	    for (y = 0; y < h; y++) {
	        rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
	        for (i = -radius; i <= radius; i++) {
	            p = pix[yi + Math.min(wm, Math.max(i, 0))];
	            sir = stack[i + radius];
	            sir[0] = (p & 0xff0000) >> 16;
	            sir[1] = (p & 0x00ff00) >> 8;
	            sir[2] = (p & 0x0000ff);
	            rbs = r1 - Math.abs(i);
	            rsum += sir[0] * rbs;
	            gsum += sir[1] * rbs;
	            bsum += sir[2] * rbs;
	            if (i > 0) {
	                rinsum += sir[0];
	                ginsum += sir[1];
	                binsum += sir[2];
	            } else {
	                routsum += sir[0];
	                goutsum += sir[1];
	                boutsum += sir[2];
	            }
	        }
	        stackpointer = radius;
	        for (x = 0; x < w; x++) {
	            r[yi] = dv[rsum];
	            g[yi] = dv[gsum];
	            b[yi] = dv[bsum];
	            rsum -= routsum;
	            gsum -= goutsum;
	            bsum -= boutsum;
	            stackstart = stackpointer - radius + div;
	            sir = stack[stackstart % div];
	            routsum -= sir[0];
	            goutsum -= sir[1];
	            boutsum -= sir[2];
	            if (y == 0) {
	                vmin[x] = Math.min(x + radius + 1, wm);
	            }
	            p = pix[yw + vmin[x]];
	            sir[0] = (p & 0xff0000) >> 16;
	            sir[1] = (p & 0x00ff00) >> 8;
	            sir[2] = (p & 0x0000ff);
	            rinsum += sir[0];
	            ginsum += sir[1];
	            binsum += sir[2];
	            rsum += rinsum;
	            gsum += ginsum;
	            bsum += binsum;
	            stackpointer = (stackpointer + 1) % div;
	            sir = stack[(stackpointer) % div];
	            routsum += sir[0];
	            goutsum += sir[1];
	            boutsum += sir[2];
	            rinsum -= sir[0];
	            ginsum -= sir[1];
	            binsum -= sir[2];
	            yi++;
	        }
	        yw += w;
	    }
	    for (x = 0; x < w; x++) {
	        rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
	        yp = -radius * w;
	        for (i = -radius; i <= radius; i++) {
	            yi = Math.max(0, yp) + x;
	            sir = stack[i + radius];
	            sir[0] = r[yi];
	            sir[1] = g[yi];
	            sir[2] = b[yi];
	            rbs = r1 - Math.abs(i);
	            rsum += r[yi] * rbs;
	            gsum += g[yi] * rbs;
	            bsum += b[yi] * rbs;
	            if (i > 0) {
	                rinsum += sir[0];
	                ginsum += sir[1];
	                binsum += sir[2];
	            } else {
	                routsum += sir[0];
	                goutsum += sir[1];
	                boutsum += sir[2];
	            }
	            if (i < hm) {
	                yp += w;
	            }
	        }
	        yi = x;
	        stackpointer = radius;
	        for (y = 0; y < h; y++) {
	            // Preserve alpha channel: ( 0xff000000 & pix[yi] )
	            pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
	                    | (dv[gsum] << 8) | dv[bsum];
	            rsum -= routsum;
	            gsum -= goutsum;
	            bsum -= boutsum;
	            stackstart = stackpointer - radius + div;
	            sir = stack[stackstart % div];
	            routsum -= sir[0];
	            goutsum -= sir[1];
	            boutsum -= sir[2];
	            if (x == 0) {
	                vmin[y] = Math.min(y + r1, hm) * w;
	            }
	            p = x + vmin[y];
	            sir[0] = r[p];
	            sir[1] = g[p];
	            sir[2] = b[p];
	            rinsum += sir[0];
	            ginsum += sir[1];
	            binsum += sir[2];
	            rsum += rinsum;
	            gsum += ginsum;
	            bsum += binsum;
	            stackpointer = (stackpointer + 1) % div;
	            sir = stack[stackpointer];
	            routsum += sir[0];
	            goutsum += sir[1];
	            boutsum += sir[2];
	            rinsum -= sir[0];
	            ginsum -= sir[1];
	            binsum -= sir[2];
	            yi += w;
	        }
	    }
	    return pix;
	}
}
