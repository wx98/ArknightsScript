package com.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 比较两张图片的相似度
 */
public class SimilarityComparer {
    // 改变成二进制码
    private static String[][] getPX(BufferedImage image) {
        int[] rgb = new int[3];
        int width = image.getWidth();
        int height = image.getHeight();
        int minx = image.getMinX();
        int miny = image.getMinY();
        String[][] list = new String[width][height];
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = image.getRGB(i, j);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                list[i][j] = rgb[0] + "," + rgb[1] + "," + rgb[2];
            }
        }
        return list;
    }


    public static boolean compareImage(BufferedImage image1, BufferedImage image2) {
        boolean result = false;
        // 分析图片相似度 begin
        String[][] list1 = getPX(image1);
        String[][] list2 = getPX(image2);
        int xiangsi = 0;
        int busi = 0;
        int i = 0, j = 0;
        for (String[] strings : list1) {
            if ((i + 1) == list1.length) {
                continue;
            }
            for (int m = 0; m < strings.length; m++) {
                try {
                    String[] value1 = list1[i][j].toString().split(",");
                    String[] value2 = list2[i][j].toString().split(",");
                    int k = 0;
                    for (int n = 0; n < value2.length; n++) {
                        if (Math.abs(Integer.parseInt(value1[k]) - Integer.parseInt(value2[k])) < 5) {
                            xiangsi++;
                        } else {
                            busi++;
                        }
                    }
                } catch (RuntimeException e) {
                    continue;
                }
                j++;
            }
            i++;
        }
        list1 = getPX(image2);
        list2 = getPX(image1);
        i = 0;
        j = 0;
        for (String[] strings : list1) {
            if ((i + 1) == list1.length) {
                continue;
            }
            for (int m = 0; m < strings.length; m++) {
                try {
                    String[] value1 = list1[i][j].toString().split(",");
                    String[] value2 = list2[i][j].toString().split(",");
                    int k = 0;
                    for (int n = 0; n < value2.length; n++) {
                        if (Math.abs(Integer.parseInt(value1[k]) - Integer.parseInt(value2[k])) < 5) {
                            xiangsi++;
                        } else {
                            busi++;
                        }
                    }
                } catch (RuntimeException e) {
                    continue;
                }
                j++;
            }
            i++;
        }
        if (busi == 0) {
            result = true;
        }
        String baifen = "";
        try {
            baifen = ((Double.parseDouble(xiangsi + "") / Double.parseDouble((busi + xiangsi) + "")) + "");
            baifen = baifen.substring(baifen.indexOf(".") + 1, baifen.indexOf(".") + 3);
        } catch (Exception e) {
            baifen = "0";
        }
        System.out.println(baifen);
        return result;
    }


    public static void main(String[] args) throws IOException {
//        InputStream stream = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(ImageManager.numeric.get("6")));
//        BufferedImage n6 = ImageIO.read(stream);
//        stream = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(ImageManager.numeric.get("9")));
//        BufferedImage n9 = ImageIO.read(stream);

        String imgPath1 = "D:\\workspace\\IDEAProject\\AdbScript\\src\\main\\resources\\1.png";
        String imgPath2 = "D:\\workspace\\IDEAProject\\AdbScript\\src\\main\\resources\\2.png";
//        String imgPath3 = "D:\\workspace\\IDEAProject\\AdbScript\\src\\main\\resources\\Snipaste_2020-07-25_06-02-30.png";


        BufferedImage image1 = ImageIO.read(new FileInputStream(imgPath1));
        BufferedImage image2 = ImageIO.read(new FileInputStream(imgPath2));
//        BufferedImage image3 = ImageIO.read(new FileInputStream(imgPath3));


        System.out.println(SimilarityComparer.compareImage(image1, image2));
    }
}