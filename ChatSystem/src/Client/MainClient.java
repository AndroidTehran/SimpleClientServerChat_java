package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClient {

    public static void main(String[] args) {
        try {
            Socket sc = new Socket("localhost", 12345);
            InputStream is = sc.getInputStream();
            OutputStream os = sc.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
            
            String input = dis.readUTF();
            System.out.println(input);
            
            dos.writeUTF("Hello :D");
            dos.flush();
            
            dis.close();
            dos.close();
            is.close();
            os.close();
            sc.close();
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
