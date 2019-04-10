package com.arcsoft.facego.util;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.arcsoft.facego.facetech.model.ImageInfo;

import sun.misc.BASE64Decoder;


public class ImageFileUtil {
    private static Logger log = Logger.getLogger(ImageFileUtil.class);

    /**
     * 从图片文件中得到RGB数据
     * @param file	- 图片文件
     * @return		- RGB数据
     */
    public static ImageInfo getRGBData(File file) {
        ImageInfo imageInfo = new ImageInfo();
        if (file == null) {
            return null;
        }
        try {
            //将图片文件加载到内存缓冲区
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            // 使图片居中
            width = width & (~3);
            height = height & (~3);
            imageInfo.width = width;
            imageInfo.height = height;
            //根据原图片信息新建一个图片缓冲区
            BufferedImage resultImage = new BufferedImage(width, height, image.getType());

            //得到原图的rgb像素矩阵
            int[] rgb = image.getRGB(0, 0, width, height, null, 0, width);
            //将像素矩阵 绘制到新的图片缓冲区中
            resultImage.setRGB(0, 0, width, height, rgb, 0, width);
            //进行数据格式化为可用数据
            BufferedImage dstImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            if(resultImage.getType() != BufferedImage.TYPE_3BYTE_BGR) {
                ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
                ColorConvertOp colorConvertOp = new ColorConvertOp(cs, dstImage.createGraphics().getRenderingHints());
                colorConvertOp.filter(resultImage, dstImage);
            } else {
                dstImage = resultImage;
            }

            //获取rgb数据
            imageInfo.rgbData = ((DataBufferByte) (dstImage.getRaster().getDataBuffer())).getData();

        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
        return imageInfo;
    }

    /**
     * 通过图片文件流获取RGB数据
     * @param file	- 图片文件流
     * @return		- RGB数据
     */
    public static ImageInfo getRGBData(InputStream file) {
        ImageInfo imginfo = new ImageInfo();
        if (file == null) {
            return null;
        }
        try {
            //将文件流转换为内存缓冲区图片
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            // 将图片居中，align 4 bytes
            width = width & (~3);
            height = height & (~3);
            imginfo.width = width;
            imginfo.height = height;
            BufferedImage resultimg = new BufferedImage(width, height, image.getType());

            int[] rgb = image.getRGB(0, 0, width, height, null, 0, width);

            resultimg.setRGB(0, 0, width, height, rgb, 0, width);
            BufferedImage dstimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

            if(resultimg.getType() != BufferedImage.TYPE_3BYTE_BGR) {
                ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
                ColorConvertOp colorConvertOp = new ColorConvertOp(cs, dstimage.createGraphics().getRenderingHints());
                colorConvertOp.filter(resultimg, dstimage);
            } else {
                dstimage = resultimg;
            }
            imginfo.rgbData = ((DataBufferByte) (dstimage.getRaster().getDataBuffer())).getData();

        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
        return imginfo;
    }

    /**
     * 根据内存缓存区图片得到RGB数据
     * @param image	- 内存缓存区图片
     * @return		- RGB数据
     */
    public static ImageInfo getRGBData(BufferedImage image) {
        ImageInfo imginfo = new ImageInfo();

        int width = image.getWidth();
        int height = image.getHeight();
        //将图片居中 align 4 bytes
        width = width & (~3);
        height = height & (~3);
        imginfo.width = width;
        imginfo.height = height;
        BufferedImage resultimg = new BufferedImage(width, height, image.getType());
        int[] rgb = image.getRGB(0, 0, width, height, null, 0, width);
        resultimg.setRGB(0, 0, width, height, rgb, 0, width);
        imginfo.rgbData = ((DataBufferByte) (resultimg.getRaster().getDataBuffer())).getData();
        return imginfo;
    }

    /**
     * 得到原缓冲区图片的左右反向缓冲区图片
     * @param bufferedImage	- 原缓冲区图片
     * @return				- BufferedImage  新的缓冲区图片
     */
    public static BufferedImage flipImage(final BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();

        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, width, height, width, 0, 0, height, null);

        try {
            return image;
        } finally {
            if (graphics2D != null) {
                graphics2D.dispose();
            }
        }
    }


    /**
     * 根据Base64图片数据和人员编号保存图片
     * @param image64        - Base64图片数据
     * @param personSerial   - 人员编号
     * @return               - 返回方向图片
     */
    public static File saveImageFileBase64(String image64, String personSerial) {
        try {
            //得到图片保存名称与路径
            Random random = new Random();
            int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;

//            if(ConfigUtil.imagedir == null || ConfigUtil.imagedir.equals("")){
//                ConfigUtil tempConfig = new ConfigUtil();
//            }


            File savedFile = new File("D:/" + File.separator + personSerial + "_"
                    + System.currentTimeMillis() + rannum + ".jpg");
            BASE64Decoder decoder = new BASE64Decoder();

            byte[] buffer = decoder.decodeBuffer(image64);
            File tmp = savedFile.getParentFile();
            if (!tmp.exists()) {
                tmp.mkdirs();
            }

            //创建图片文件输出流，保存图片
            FileOutputStream fileOut = new FileOutputStream(savedFile);

            fileOut.write(buffer);
            fileOut.flush();
            fileOut.close();

            BufferedImage image = ImageIO.read(savedFile);
            BufferedImage dstImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
            ColorConvertOp colorConvertOp = new ColorConvertOp(cs, dstImage.createGraphics().getRenderingHints());
            colorConvertOp.filter(image, dstImage);
            ImageIO.write(dstImage, "jpg", savedFile);
            return savedFile;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 将原图保存flip图片，
     * @param orignalFile
     * @param filpFileName
     * @return
     *  flip图片名称是对应的注册照的名字  _flip.jpg，方便之后和主照片一起删除
     */
    public static File saveFlipImageFile(File orignalFile, String filpFileName) {
        try {
            File savedfile = new File("D:/" + File.separator + filpFileName);
            BufferedImage img = flipImage(ImageIO.read(orignalFile));
            ImageIO.write(img, "jpg", savedfile);
            return savedfile;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 根据Base64图片数据为图片，保存Logo图片，保存打卡图片
     * @param image64
     * @param iamgeDirName
     */
    public static void saveFileBase64(String image64,String iamgeDirName) {
        try {
            //得到图片保存名称与路径
            File savedFile = new File(iamgeDirName);

            BASE64Decoder decoder = new BASE64Decoder();

            byte[] buffer = decoder.decodeBuffer(image64);
            File tmp = savedFile.getParentFile();
            if (!tmp.exists()) {
                tmp.mkdirs();
            }

            //创建图片文件输出流，保存图片
            FileOutputStream fileOut = new FileOutputStream(savedFile);
            fileOut.write(buffer);
            fileOut.flush();
            fileOut.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 将图片保存到指定路径
     * @param inFile			图片
     * @param imageDirImage		指定路径
     * @return					true：成功  fase:失败
     */
    public static boolean saveFile(MultipartFile inFile, String imageDirImage) {
        try {
            //生成图片名称
            File savedfile = new File(imageDirImage);
            File tmp = savedfile.getParentFile();
            if (!tmp.exists()) {
                tmp.mkdirs();
            }
            inFile.transferTo(savedfile);
            //获取图片流
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }
}
