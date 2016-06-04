/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Forms.SendMessage;
import Forms.Userhome;
import Network.ReceiverServer;
import com.Steganography;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author VAISAKH N
 */
public class Process extends Thread {

    String enmessage;
    String ip;
    String path;
SendMessage sm;
    public Process(String en, String pa, String i,SendMessage sm) {
        enmessage = en;
        path = pa;
        ip = i;
        this.sm=sm;
    }

    @Override
    public void run() {
        try {
            int l = enmessage.length();
            int p = 0;
            int y = 0;
            int ascii[] = findascii(enmessage);
            int cord[][] = findpixelcord(enmessage.length());
            int ang[] = findangle(cord, p, y);
            int[] or = exclusiveOr(l, ascii);
            String[] bit = findbinary(or);
            int shift[] = shiftleft(ang, bit);
            int onemat[][] = matrix1(shift);
            int threemat[][] = colorkey();
            int pro[][] = matrixmul(threemat, onemat);
            int rgb[] = makergb(pro);
            makeimage(cord, rgb, p, y);
            File f = new File("");
            String pa = f.getCanonicalPath() + "\\Workplace";
            File source = new File(pa);
            source.mkdir();
            Steganography.embed(path, pa + "\\meta.png");
            int serverPort = 2000;
            int i;

            FileInputStream fis = new FileInputStream(pa + "\\final.png");

            Socket sock = new Socket(ip, serverPort);
            DataOutputStream os = new DataOutputStream(sock.getOutputStream());
            OutputStream ou = sock.getOutputStream();
            PrintStream ps = new PrintStream(ou);

            //..............................................................

          

            // creates progress bar

            SendMessage.sjp.setMinimum(0);
            SendMessage.sjp.setMaximum(fis.available());
            ps.println(fis.available());
            Thread.sleep(1000);
            int k = 0;
//........................................................................
            while ((i = fis.read()) > -1) {
                os.write(i);

                SendMessage.sjp.setValue(k);
                //SendMessage.sjp.setString("Sending..");
                k++;
            }

            
            fis.close();
            os.close();
            sock.close();
            JOptionPane.showMessageDialog(null, "Message Sent successfully");
            
            sm.setVisible(false);
            
         ReceiverServer.socket.close();
         new Userhome().setVisible(true);
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Unknown Destinaton");
        }

    }

    public static int[] findascii(String enmessage) {
        System.out.println("Find asciii");


        int ascii[] = new int[2000];


        for (int i = 0; i
                < enmessage.length(); i++) {
            ascii[i] = enmessage.charAt(i);
            System.out.println("Ascii        " + ascii[i]);


        }
        return ascii;


    }

    public static int[][] colorkey() {
        int[][] colorkey = {{2, 2, -5}, {2, 1, -2}, {2, 2, -3}};


        return colorkey;


    }

    public static int[][] findpixelcord(int l) {
        System.out.println("Find cord");


        int pixelcode[][] = new int[l][2];


        int x = 0, y = 0;


        for (int i = 0; i
                < l; i++) {

            x = x + 2;
            y = y + 2;

            pixelcode[i][0] = x;
            pixelcode[i][1] = y;
            System.out.println(pixelcode[i][0] + "," + pixelcode[i][1]);


        }
        return pixelcode;


    }

    public static int[] exclusiveOr(int np, int ascii[]) {
        System.out.println("XOR");


        int or[] = new int[np];


        int as[] = new int[np];


        for (int i = 0; i
                < np; i++) {
            or[i] = ascii[i] ^ np;
            as[i] = or[i] ^ np;
            System.out.println("OR        " + or[i] + "    and ascii back      " + as[i]);


        }
        return or;


    }

    public static int[] findangle(int[][] cord, int p, int y) {
        System.out.println("Find angle");


        int l = cord.length;


        int angle[] = new int[l];


        for (int i = 0; i< l; i++) {
            angle[i] = (int) (Math.atan2(cord[i][1] - y, cord[i][0] -p) * 180 / 3.14);
            System.out.println("Angle       " + angle[i]);


        }
        return angle;


    }

    public static String[] findbinary(int[] value) {
        System.out.println("Find binary");
        String bin[] = new String[value.length];


        for (int i = 0; i
                < value.length; i++) {
            bin[i] = Integer.toString(value[i], 2);
            System.out.println("Binary     " + bin[i] + "  and integer back  " + Integer.parseInt(bin[i], 2));



        }
        return bin;


    }

    public static int[] shiftleft(int ang[], String bit[]) {
        System.out.println("Left Shift");


        int[] lshift = new int[ang.length];


        int[] rshift = new int[ang.length];


        for (int i = 0; i
                < bit.length; i++) {
            int b = Integer.parseInt(bit[i]);
            System.out.println("Bit        " + bit[i]);
            System.out.println("Angle      " + ang[i]);
            String ls = bit[i] + "";

            lshift[i] = Integer.rotateLeft(b, ang[i]);
            System.out.println("Left       " + lshift[i]);



        }
        return lshift;


    }

    public static int[][] matrix1(int[] bit) {

        int mat[][] = new int[bit.length][5];
        String tmp;




        for (int i = 0; i
                < bit.length; i++) {
            System.out.println("One dimentional matrix");
            tmp = "" + bit[i];


            int x = 0, y = 3;


            if (tmp.contains("-")) {
                y = 4;


            }

            for (int j = 0; j
                    < 3; j++) {

                mat[i][j] = Integer.parseInt(tmp.substring(x, y));
                x = y;
                y = y + 3;

                System.out.println(mat[i][j]);




            }

        }



        return mat;


    }

    public static int[][] matrixmul(int three[][], int one[][]) {

        int sum[][] = new int[one.length][3];


        for (int i = 0; i
                < one.length; i++) {
            System.out.println("One dimentional matrix after multiplication with color key");


            int t = 0;


            for (int j = 0; j
                    < 3; j++) {
                sum[i][j] = one[i][j] * three[t][0] + one[i][j] * three[t][1] + one[i][j] * three[t][2];
                t++;

                System.out.println(sum[i][j]);




            }


        }
        return sum;


    }

    public static int[] makergb(int pro[][]) {
        int rgb[] = new int[pro.length];
        String pix = "";


        for (int i = 0; i
                < pro.length; i++) {
            for (int j = 0; j
                    < 3; j++) {
                pix = pix + pro[i][j];


            }


            rgb[i] = Integer.parseInt(pix);
            System.out.println("Rgb pixel" + rgb[i]);
            pix = "";


        }
        return rgb;


    }

    public static void makeimage(int[][] cord, int[] rgb, int p, int y) throws Exception {
        System.out.println("Inside make image");


        int len = cord.length;
        System.out.println(len);


        int imgw = cord[len - 1][0] + 10;


        int imgh = cord[len - 1][1] + 10;
        System.out.println(imgh);
        System.out.println(imgw);
        System.out.println("Buffered image");
        BufferedImage image = new BufferedImage(imgw, imgh, BufferedImage.TYPE_INT_ARGB);


        int l[] = new int[1];
        l[0] = len;
        image.setRGB(0, 0, 1, 1, l, 0, 2);


        for (int i = 0; i
                < len; i++) {
            int pix = rgb[i];
            System.out.println(pix);


            int datargb[] = new int[1];
            datargb[0] = pix;
            image.setRGB(cord[i][0], cord[i][1], 1, 1, datargb, 0, 2);


        }
        File f = new File("");
        String pa = f.getCanonicalPath() + "\\Workplace";
        File source = new File(pa);
        source.mkdir();
        ImageIO.write(image, "png", new File(pa + "\\meta.png"));

    }
}
