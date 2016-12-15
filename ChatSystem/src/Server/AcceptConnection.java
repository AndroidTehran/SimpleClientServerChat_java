/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Mohammad
 */
class AcceptConnection implements Runnable {

    ServerSocket serverSocket;
    DataInputStream dis;
    DataOutputStream dos;
    ServerFrame serverFrame;

    public AcceptConnection(ServerSocket serverSocket, ServerFrame serverFrame) {
        this.serverSocket = serverSocket;
        this.serverFrame = serverFrame;
    }

    @Override
    public void run() {
        try {
            Socket socket = this.serverSocket.accept();
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.serverFrame.connectionEstablished();

            this.receiveMessage();
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String message) {
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(AcceptConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage() {
        try {
            while (true) {
                String clientMessage = dis.readUTF();
                this.serverFrame.appendMessage(clientMessage, false);

            }
        } catch (IOException ex) {
            Logger.getLogger(AcceptConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
