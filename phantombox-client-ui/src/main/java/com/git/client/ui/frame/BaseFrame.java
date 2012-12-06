package com.git.client.ui.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Base frame.
 * <p/>
 * Date: 12.11.12
 * Time: 21:42
 *
 * @author rpleshkov
 */
public class BaseFrame extends JFrame {

    /**
     * Sets alignment in center.
     *
     * @param frame {@link JFrame}
     */
    protected void alignment(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int fWidth = frame.getSize().width;
        int fHeight = frame.getSize().height;
        int x = (dim.width - fWidth) / 2;
        int y = (dim.height - fHeight) / 2;
        frame.setLocation(x, y);
    }
}
