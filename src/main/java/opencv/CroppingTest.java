package opencv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;

import nu.pattern.OpenCV;

public class CroppingTest {

    public static void main(String[] args) throws IOException {
        OpenCV.loadLibrary();

        // Files.list(Paths.get("/home/artur/tmp/siggi_benchmark")).forEach(p ->
        // {

        // Mat m = new Mat();

        // Mat m =
        // Highgui.imread("/home/artur/dev/repo/jmtests/images/Types/dress.jpg",
        // Highgui.CV_LOAD_IMAGE_COLOR);

        byte[] readAllBytes = Files.readAllBytes(Paths.get("/home/artur/dev/repo/jmtests/images/Types/dress.jpg"));

        // read from byte array
        Mat m = Highgui.imdecode(new MatOfByte(readAllBytes), Highgui.CV_LOAD_IMAGE_UNCHANGED);
        LoadImage(m); // display

        // do some cropping calculations
        long start = System.currentTimeMillis();
        double x1 = 0.1;
        double y1 = 0.2;
        double cropx = 0.8;
        double cropy = 0.8;

        int scaleW = m.cols() - 1;
        int scaleH = m.rows() - 1;

        int leftEdge = (int) Math.round(scaleW * x1);
        int rightEdge = (int) Math.round(scaleW * cropx);
        int topEdge = (int) Math.round(scaleH * y1);
        int bottomEdge = (int) Math.round(scaleH * cropy);

        int width = rightEdge - leftEdge + 1;
        int height = bottomEdge - topEdge + 1;
        //crop teh image
        Mat submat = m.submat(new Rect(leftEdge, topEdge, width, height));

//        LoadImage(submat, "/home/artur/tmp/my_cropped.jpg");
        
        // read the sub-image bytes
        int bufferSize = submat.channels() * submat.cols() * submat.rows();
        byte[] b = new byte[bufferSize];
        submat.get(0, 0, b);
        
        System.out.println(b.length);
        
        MatOfByte b2  = new MatOfByte();
        Highgui.imencode(".png", submat, b2);
        
        byte[] array = b2.toArray();
        
        // THis does not work
        Mat m2 = Highgui.imdecode(new MatOfByte(array), Highgui.CV_LOAD_IMAGE_UNCHANGED);
        LoadImage(m2);

        Mat m3 = new Mat();
        m3.put(width, height, b);
        
        LoadImage(m3);
        
        // long duration = System.currentTimeMillis() - start;
        // StringBuffer b = new StringBuffer();
        //
        // b.append("Cropping from
        // ").append(m.cols()).append("x").append(m.rows()).append( " to ")
        // .append(submat.cols()).append("x").append(submat.rows()).append("
        // took ").append(duration).append(" ms");
        //
        // System.out.println(System.currentTimeMillis() - start);
        //
        // LoadImage(submat);
        // });

    }

    public static byte[] toBufferedImage(Mat m) {
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        return b;

    }
    
    public static void LoadImage(Mat m, String path2) {
        Path path = Paths.get(path2);
        Highgui.imwrite(path.toString(), m);
        JFrame frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        // Inserts the image icon
        ImageIcon image = new ImageIcon(path.toString());
        frame.setSize(image.getIconWidth() + 10, image.getIconHeight() + 35);
        // Draw the Image data into the BufferedImage
        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
        frame.getContentPane().add(label1);

        frame.validate();
        frame.setVisible(true);
    }

    public static void LoadImage(Mat m) {
        Path path = Paths.get("/tmp/", UUID.randomUUID().toString() + ".jpg");
        Highgui.imwrite(path.toString(), m);
        JFrame frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        // Inserts the image icon
        ImageIcon image = new ImageIcon(path.toString());
        frame.setSize(image.getIconWidth() + 10, image.getIconHeight() + 35);
        // Draw the Image data into the BufferedImage
        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
        frame.getContentPane().add(label1);

        frame.validate();
        frame.setVisible(true);
    }

}
