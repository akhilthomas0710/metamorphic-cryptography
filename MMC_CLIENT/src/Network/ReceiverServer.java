package Network;



/**
 *
 * @author Administrator
 */
import Forms.Receiver;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverServer extends Thread {

    static String dir = "";
    public static boolean serverstatus;
    ServerSocket serversocket;
    int port;
  public static ServerSocket socket;

    public ReceiverServer() throws IOException {
        System.out.println("Server Started");
        port = 2000;
        socket = new ServerSocket(port);
    }

    @Override
    public void run() {




        try {

            while (true) {
                System.out.println("Waiting");
                Socket clientSocket = socket.accept();
                // JOptionPane.showMessageDialog(null, "You have a message");
                new Receiver().setVisible(true);
                System.out.println("Connection request");

                File fi = new File("");
                String path = fi.getCanonicalPath();
                File pa = new File(path + "\\Workplace");

                pa.mkdir();

                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                FileOutputStream fout = new FileOutputStream(pa + "\\final.png", false);
                int i;
                Receiver.pb1.setMinimum(0);

                InputStream in = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String size = br.readLine().trim();
                System.out.println("size" + size);
                Receiver.pb1.setMaximum(Integer.parseInt(size));
                int k = 0;
                //Thread.sleep(500);
                while ((i = dis.read()) > -1) {
                    fout.write(i);
                    Receiver.pb1.setValue(k);
                    Receiver.pb1.setString("Sending..");
                    k++;
                    System.out.println(k);
                }

                fout.flush();
                fout.close();
                dis.close();
                clientSocket.close();



                System.out.println("Image received completely");


                Receiver.setImage(pa + "\\final.png");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) throws IOException {
    }
}
