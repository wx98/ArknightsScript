package com.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenSave {
    public static void Screen(String path) {
        try {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            BufferedImage bim = new Robot().createScreenCapture(new Rectangle(0, 0, dim.width, dim.height));
            ImageIO.write(bim, "png", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}