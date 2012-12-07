package com.git.client.ui.panel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
