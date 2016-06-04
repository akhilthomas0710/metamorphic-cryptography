/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Forms;

import com.Steganography;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Receiver extends javax.swing.JFrame {

    /** Creates new form HOME */
    public Receiver() {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
        jButton1.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setimg = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        rec = new javax.swing.JTextArea();
        pb1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(setimg);
        setimg.setBounds(60, 60, 340, 200);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(360, 210, 0, 2);

        rec.setColumns(20);
        rec.setRows(5);
        jScrollPane1.setViewportView(rec);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 320, 340, 110);
        getContentPane().add(pb1);
        pb1.setBounds(60, 20, 340, 14);

        jButton1.setText("Get Message");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 280, 120, 23);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(60, 60, 340, 200);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            callMe();
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Receiver().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JProgressBar pb1;
    public static javax.swing.JTextArea rec;
    public static javax.swing.JLabel setimg;
    // End of variables declaration//GEN-END:variables

    public static int[][] colorkey() {
        int[][] colorkey = {{2, 2, -5}, {2, 1, -2}, {2, 2, -3}};
        return colorkey;
    }

    public static int[][] findpixelcord(int l) {
        System.out.println("Find cord");
        int pixelcode[][] = new int[l][2];
        int x = 0, y = 0;
        for (int i = 0; i < l; i++) {

            x = x + 2;
            y = y + 2;
//            if (x == (y * 5)) {
//                x = 2;
//                y = y + 2;
//            }
            pixelcode[i][0] = x;
            pixelcode[i][1] = y;
            System.out.println(pixelcode[i][0] + "," + pixelcode[i][1]);
        }
        return pixelcode;
    }

    public static int[] findangle(int[][] cord, int p, int y) {
        System.out.println("Find angle");
        int l = cord.length;
        int angle[] = new int[l];
        for (int i = 0; i < l; i++) {
            angle[i] = (int) (Math.atan2(cord[i][1] - y, cord[i][0] - p) * 180 / 3.14);
            System.out.println("Angle       " + angle[i]);
        }
        return angle;
    }

    public static int[][] inverseandmul(int[][] m, int[][] n) {
        int[][] rev = new int[n.length][3];
        int a = m[0][0] + m[0][1] + m[0][2];
        System.out.println(a);
        int b = m[1][0] + m[1][1] + m[1][2];
        System.out.println(b);
        int c = m[2][0] + m[2][1] + m[2][2];
        System.out.println(c);
        for (int i = 0; i < n.length; i++) {
            System.out.println("Find original Array");
            rev[i][0] = n[i][0] / a;
            rev[i][1] = n[i][1] / b;
            rev[i][2] = n[i][2] / c;
            System.out.println(rev[i][0]);
            System.out.println(rev[i][1]);
            System.out.println(rev[i][2]);
        }

        return rev;
    }

    public static int[] getRGB(int[][] cord) throws Exception {
        System.out.println("Inside get RGB");
        File fi = new File("");
        String path = fi.getCanonicalPath();
        File pa = new File(path + "\\Workplace");

        BufferedImage bi = ImageIO.read(new File(pa + "\\meta.png"));
        int len = bi.getRGB(0, 0);
        System.out.println("Length" + len);
        int[] rgb = new int[len];
        for (int i = 0; i < len; i++) {

            rgb[i] = bi.getRGB(cord[i][0], cord[i][1]);
            System.out.println(rgb[i]);
        }

        return rgb;
    }

    public static int[][] getOneMat(int[] pix) {
        String px = "";
        int onemat[][] = new int[pix.length][3];
        for (int i = 0; i < pix.length; i++) {
            px = pix[i] + "";

            if (px.length() == 7) {


                onemat[i][0] = Integer.parseInt(px.substring(0, 3));
                onemat[i][1] = Integer.parseInt(px.substring(3, 4));
                onemat[i][2] = Integer.parseInt(px.substring(4, 7));

            }
            if (px.length() == 8) {
                char a = px.charAt(0);
                char b = px.charAt(7);
                System.out.println("..........................." + b);
                if (a == '-' && b != '0') {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 4));
                    onemat[i][1] = Integer.parseInt(px.substring(4, 5));
                    onemat[i][2] = Integer.parseInt(px.substring(5, 8));
                    System.out.println("Get back one matrix......................daSFDDS");
                } else if (a == '-' && b == '0') {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 4));
                    onemat[i][1] = Integer.parseInt(px.substring(4, 7));
                    onemat[i][2] = Integer.parseInt(px.substring(7, 8));
                    System.out.println("Get back one matrix......................");
                    System.out.println(onemat[i][0]);
                    System.out.println(onemat[i][1]);
                    System.out.println(onemat[i][2]);
                } else {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 3));
                    onemat[i][1] = Integer.parseInt(px.substring(3, 5));
                    onemat[i][2] = Integer.parseInt(px.substring(5, 8));
                    System.out.println("Get back one matrix");
                    System.out.println(onemat[i][0]);
                    System.out.println(onemat[i][1]);
                    System.out.println(onemat[i][2]);
                }
            }
            if (px.length() == 9) {
                char a = px.charAt(0);
                if (a == '-') {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 4));
                    onemat[i][1] = Integer.parseInt(px.substring(4, 6));
                    onemat[i][2] = Integer.parseInt(px.substring(6, 9));
                } else {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 3));
                    onemat[i][1] = Integer.parseInt(px.substring(3, 6));
                    onemat[i][2] = Integer.parseInt(px.substring(6, 9));
                    System.out.println("Get back one matrix");
                    System.out.println(onemat[i][0]);
                    System.out.println(onemat[i][1]);
                    System.out.println(onemat[i][2]);
                }
            }

            if (px.length() == 10) {
                char a = px.charAt(0);
                if (a == '-') {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 4));
                    onemat[i][1] = Integer.parseInt(px.substring(4, 7));
                    onemat[i][2] = Integer.parseInt(px.substring(7, 10));
                    System.out.println("Get back one matrix");
                    System.out.println(onemat[i][0]);
                    System.out.println(onemat[i][1]);
                    System.out.println(onemat[i][2]);
                } else {
                    onemat[i][0] = Integer.parseInt(px.substring(0, 3));
                    onemat[i][1] = Integer.parseInt(px.substring(3, 7));
                    onemat[i][2] = Integer.parseInt(px.substring(7, 10));
                }
            }

        }
        return onemat;
    }

    public static String[] onemattoval(int[][] bak) {
        String bit[] = new String[bak.length];
        for (int i = 0; i < bak.length; i++) {
            String first = bak[i][0] + "";
            String mid = bak[i][1] + "";
            String last = bak[i][2] + "";

            if (mid.length() == 3) {
                bit[i] = first + mid + last;
            }
            if (mid.length() == 2) {
                bit[i] = first + "0" + mid + last;
            }
            if (mid.length() == 1) {
                bit[i] = first + "00" + mid + last;
            }
            first = "";
            mid = "";
            last = "";
            System.out.println(bit[i]);
        }
        return bit;
    }
    
    
    
    

    public static int[] shiftRight(int ang[], String bit[]) {
        System.out.println("Right Shift");
//        int[] lshift = new int[ang.length];
        int[] rshift = new int[ang.length];
        for (int i = 0; i < bit.length; i++) {
            int b = Integer.parseInt(bit[i]);
            // System.out.println("Bit" + bit[i]);
            //  System.out.println("Angle" + ang[i]);
            String ls = bit[i] + "";

//            lshift[i] = Integer.rotateLeft(b, ang[i]);
//            System.out.println("Left" + lshift[i]);
            rshift[i] = Integer.rotateRight(b, ang[i]);
            System.out.println("Rshift" + rshift[i]);


        }
        return rshift;
    }

    public static int[] getAscii(int np, int bits[]) {
        System.out.println("Get Ascii");

        int or[] = new int[np];
        int as[] = new int[np];
        for (int i = 0; i < np; i++) {
            String b = bits[i] + "";
            System.out.println(b);
            int val = Integer.parseInt(b, 2);
            // or[i] = ascii[i] ^ np;

            as[i] = val ^ np;
            System.out.println(" ascii back      " + as[i]);
        }
        return as;
    }

    public static String getStrBack(int[] asc) {
        String msg = "";
        char c;
        for (int i = 0; i < asc.length; i++) {
            System.out.println(asc[i]);
            c = (char) asc[i];
            msg = msg + c;
        }
        System.out.println(msg);
        return msg;
    }

    public static void callMe() throws Exception {
        try {
            File fi = new File("");
            String path = fi.getCanonicalPath();
            File pa = new File(path + "\\Workplace");

            Steganography.deembed(pa + "\\final.png");
            int p = 0;
            int y = 0;
            BufferedImage bi = ImageIO.read(new File(pa + "\\meta.png"));
            int len = bi.getRGB(0, 0);
            System.out.println("length" + len);
            int cord[][] = findpixelcord(len);
            int ang[]=findangle(cord, p, y);
            int[] pix = getRGB(cord);
            int threemat[][] = colorkey();
            int[][] backone = getOneMat(pix);
            int[][] rev = inverseandmul(threemat, backone);

            String tors[] = onemattoval(rev);
            int[] rsh = shiftRight(ang, tors);
            System.out.println(rsh.length);
            int[] asc = getAscii(rsh.length, rsh);
            String message = getStrBack(asc);
            message = message.replaceAll("zxa", "\n");
            System.out.println("Got message Back" + message);
            rec.setText(message);
        } catch (Exception e) {
            System.out.println(e);
        }



    }

    public static void setImage(String ic) throws Exception {
        try {
            System.out.println("Image path" + ic);
            File f = new File(ic);
            FileOutputStream fout = new FileOutputStream("temp.jpg");
            FileInputStream fin = new FileInputStream(f);
            byte b[] = new byte[fin.available()];
            fin.read(b);
            fin.close();

            fout.write(b);
            fout.close();
            File ff = new File("temp.jpg");
            System.out.println(ff.getAbsolutePath());


            ImageIcon icon = new ImageIcon(ff.getAbsolutePath());

            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(340, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            setimg.setIcon(icon);
            jButton1.setEnabled(true);

        } catch (Exception e) {
        }
    }
}
