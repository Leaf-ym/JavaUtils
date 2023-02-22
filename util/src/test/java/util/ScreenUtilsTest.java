package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

class ImageCanvas extends Canvas {

    private Image image;

    private Dimension prefSize;

    public ImageCanvas(Image image) {

        this.image = image;

        calculatePreferredSize();

    }

    public void setImage(Image image) {

        this.image = image;

        calculatePreferredSize();

        repaint();

    }

    private void calculatePreferredSize() {

        prefSize = new Dimension(image.getWidth(this), image.getHeight(this));

        System.out.println(image.getWidth(this));

        setSize(prefSize);

    }

    public Dimension getPreferredSize() {

        return prefSize;

    }

    public Dimension getMinimumSize() {

        return prefSize;

    }

    public void update(Graphics g) {

        paint(g);

    }

    public void paint(Graphics g) {

        g.drawImage(image, 0, 0, null);

    }

}

class ImageUtils {

    public static Image getScreenImage() {
        Robot robot;
        try {
            robot = new Robot();
        } catch (Exception e) {
            throw new RuntimeException("unable to construct Robot");
        }
        Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
        Image screen = robot.createScreenCapture(new Rectangle(0, 0, screenDims.width, screenDims.height)).getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        MediaTracker tracker = new MediaTracker(new Label());
        tracker.addImage(screen, 1);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException e) { /** ... */}
        return screen;
    }
}

class captureScreen {
    public captureScreen(String fileName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(fileName));
    }
}

class Mian_login extends JFrame {
    private Boolean isMoved = true;
    private Point pre_point;
    private Point end_point;

    public void run() {
        try {
            final Mian_login frame = this;
            Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(screenDims.width / 2, screenDims.height / 2);
            //this.add(new ImageCanvas(ImageUtils.getScreenImage()));
            // 生成无标题栏窗口，就是把窗口边框去掉，可以用setUndecorated(true)，且该方法要执行在setVisible(true)之前，
            // 但是去掉边框后就不可以移动窗口了，需要重写一些方法
            //this.setUndecorated(true);
            //this.setDraggable(frame);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    System.out.println(e);
                }
            });
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 为窗口加上监听器，使得窗口可以被拖动
    private void setDraggable(Mian_login frame) {
        frame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
                isMoved = false;// 鼠标释放了以后，是不能再拖拽的了
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                isMoved = true;
                pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。
        frame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isMoved) {// 判断是否可以拖拽
                    end_point = new Point(frame.getLocation().x + e.getX() - pre_point.x,
                            frame.getLocation().y + e.getY() - pre_point.y);
                    frame.setLocation(end_point);
                }
            }
        });
    }
}

public class ScreenUtilsTest extends Frame {
    private ImageCanvas canvas = new ImageCanvas(ImageUtils.getScreenImage());

    public ScreenUtilsTest() {
        add(canvas);
        setSize(400, 300);
        setVisible(true);
        Thread imageThread = new UpdateThread();
        imageThread.setDaemon(true);
        imageThread.start();
    }

    class UpdateThread extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (Exception ex) {
                }
                canvas.setImage(ImageUtils.getScreenImage());
                validate();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //new ScreenUtilsTest();
        //new captureScreen("abc.png");
        new Mian_login().run();
    }
}
