/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad
 */
public class Connection implements Runnable {

    ClientFrame clientFrame;
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public Connection(ClientFrame clientFrame, Socket socket) {
        this.clientFrame = clientFrame;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            this.clientFrame.connectionEstablished();
            
            this.receiveMessage();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendMessage(String message) {
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage() {
        try {
            while (true) {
                String clientMessage = dis.readUTF();
                this.clientFrame.appendMessage(clientMessage, true);
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
