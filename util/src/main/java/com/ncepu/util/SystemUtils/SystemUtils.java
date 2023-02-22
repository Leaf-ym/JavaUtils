package com.ncepu.util.SystemUtils;

import com.ncepu.util.MathUtils;
import com.sun.jna.Platform;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.platform.linux.LinuxHardwareAbstractionLayer;
import oshi.hardware.platform.mac.MacHardwareAbstractionLayer;
import oshi.hardware.platform.windows.WindowsHardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wengym
 * @version 1.0
 * @desc 操作系统工具类
 * @date 2023/1/9 10:29
 */
public class SystemUtils {

    private static HardwareAbstractionLayer hardwareAbstractionLayer = null;

    /**
     * 获取系统的内存信息
     *
     * @author wengym
     *
     * @date 2023/1/13 17:46
     *
     * @return java.lang.String
     */
    public static String getMemoryInfo() {
        HardwareAbstractionLayer hardwareAbstractionLayer = getHardwareAbstractionLayer();
        GlobalMemory memory = hardwareAbstractionLayer.getMemory();
        double divNum = 1024 * 1024 * 1024;
        String total = MathUtils.toFixed((memory.getTotal() / divNum), 2);
        String used = MathUtils.toFixed(((memory.getTotal() - memory.getAvailable()) / divNum), 2);
        String available = MathUtils.toFixed((memory.getAvailable() / divNum), 2);
        return "内存信息——" + "总内存：" + total + "GB，" +
                "已用内存：" + used + "GB，" +
                "空闲内存：" + available + "GB";
    }

    /**
     * 获取Java虚拟机信息
     *
     * @author wengym
     *
     * @date 2023/1/13 17:46
     *
     * @return java.lang.String
     */
    public static String getJvmInfo() {
        Properties props = System.getProperties();
        double divNum = 1024 * 1024 * 1024;
        String totalMemory = MathUtils.toFixed((Runtime.getRuntime().totalMemory() / divNum), 2);
        String maxMemory = MathUtils.toFixed((Runtime.getRuntime().maxMemory() / divNum), 2);
        String freeMemory = MathUtils.toFixed((Runtime.getRuntime().freeMemory() / divNum), 2);
        String javaVersion = props.getProperty("java.version");
        String javaHome = props.getProperty("java.home");
        return "Java虚拟机信息——" + "总内存：" + totalMemory + "GB，" +
                "最大内存：" + maxMemory + "GB，" +
                "空闲内存：" + freeMemory + "GB，" +
                "Java版本：" + javaVersion + "，" +
                "Java Home：" + javaHome;
    }

    /**
     * 获取操作系统信息
     *
     * @author wengym
     *
     * @date 2023/1/13 18:13
     *
     * @return java.lang.String
     */
    public static String getOSInfo() {
        SystemInfo systemInfo = new SystemInfo();
        return "操作系统信息——" + "操作系统：" + systemInfo.getOperatingSystem();
    }

    /**
     * 获取磁盘信息
     *
     * @author wengym
     *
     * @date 2023/1/13 18:13
     *
     * @return java.lang.String
     */
    public static String getDiskInfo() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();
        List<String> sysFiles = new ArrayList<>();
        FileSystem fileSystem = os.getFileSystem();
        OSFileStore[] fsArray = fileSystem.getFileStores();
        /*for (OSFileStore fs : fsArray)
        {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            SysFile sysFile = new SysFile();
            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(convertFileSize(total));
            sysFile.setFree(convertFileSize(free));
            sysFile.setUsed(convertFileSize(used));
            sysFile.setUsage(Arith.mul(Arith.div(used, total, 4), 100));
            sysFiles.add(sysFile);
        }*/
        return "操作系统信息——" + "操作系统：" + systemInfo.getOperatingSystem();
    }

    /**
     * 获取硬件抽象层
     *
     * @author wengym
     *
     * @date 2023/1/13 17:51
     *
     * @return oshi.hardware.HardwareAbstractionLayer
     */
    public static HardwareAbstractionLayer getHardwareAbstractionLayer() {
        if (hardwareAbstractionLayer != null) {
            return hardwareAbstractionLayer;
        }
        if (Platform.isWindows()) {
            hardwareAbstractionLayer = new WindowsHardwareAbstractionLayer();
        } else if (Platform.isMac()) {
            hardwareAbstractionLayer =  new MacHardwareAbstractionLayer();
        } else if (Platform.isLinux()) {
            hardwareAbstractionLayer =  new LinuxHardwareAbstractionLayer();
        } else {
            hardwareAbstractionLayer =  new WindowsHardwareAbstractionLayer();
        }
        return hardwareAbstractionLayer;
    }

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
