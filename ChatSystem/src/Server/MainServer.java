package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(12345);
            Socket sc = ss.accept();
            InputStream is = sc.getInputStream();
            OutputStream os = sc.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            
            String s = dis.readUTF();
            System.out.println(s);
            
            dos.writeUTF("Hi");
            dos.flush();
            
            dis.close();
            dos.close();
            is.close();
            os.close();
            sc.close();
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
