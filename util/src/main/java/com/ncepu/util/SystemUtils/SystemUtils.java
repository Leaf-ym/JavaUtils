package com.ncepu.util.SystemUtils;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author wengym
 * @version 1.0
 * @desc 操作系统工具类
 * @date 2023/1/9 10:29
 */
public class SystemUtils {
    /**
     * 把文本设置到剪贴板中（复制）
     *
     * @param text
     * @return void
     * @author wengym
     * @date 2023/1/9 10:31
     */
    public static void setClipboardStr(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板中
        clipboard.setContents(trans, null);
    }

    /**
     * 从剪贴板中获取文本（粘贴）
     */
    public static String getClipboardStr() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);
        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 把文本设置到剪贴板中（复制）
     *
     * @param image
     * @return void
     * @author wengym
     * @date 2023/1/9 10:31
     */
    public static void setClipboardImage(Image image) {
        Transferable trans = new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            @Override
            public Object getTransferData(DataFlavor flavor)
                    throws UnsupportedFlavorException {
                if (isDataFlavorSupported(flavor)) {
                    return image;
                }
                throw new UnsupportedFlavorException(flavor);
            }
        };
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
    }

    /**
     * 从剪贴板中获取图像（粘贴）
     * 如果在剪贴板中有图像，则返回图像，否则返回null
     *
     * @return java.awt.Image
     * @author wengym
     * @date 2023/1/9 10:43
     */
    public static Image getClipboardImage() {
        Clipboard systemClipboard = (Clipboard) AccessController.doPrivileged(new PrivilegedAction() {
            @Override
            public Object run() {
                Clipboard tempClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                return tempClipboard;
            }
        });
        // get the contents on the clipboard in a
        // Transferable object
        Transferable clipboardContents = systemClipboard.getContents(null);
        // check if contents are empty, if so, return null
        if (clipboardContents == null) {
            return null;
        } else {
            try {
                // make sure content on clipboard is
                // falls under a format supported by the
                // imageFlavor Flavor
                if (clipboardContents.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    // convert the Transferable object
                    // to an Image object
                    Image image = (Image) clipboardContents.getTransferData(DataFlavor.imageFlavor);
                    return image;
                }
            } catch (UnsupportedFlavorException ufe) {
                ufe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return null;
    }
}
