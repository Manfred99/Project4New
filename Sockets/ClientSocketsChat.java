package Sockets;

import Domain.BufferMemoryGame;
import Domain.Sending;
import Interface.Window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ClientSocketsChat {

    String nameCliet;
    private BufferMemoryGame sharedMemory;
    public ClientSocketsChat() {
        this.nameCliet = sharedMemory.clientName;
    }

    public void functionalityClientServer(String message, String id) {
        InetAddress address;
        Socket mySocket;
        try {

            //address = InetAddress.getLocalHost();//direccion local
            address = InetAddress.getByName("172.20.10.6");//sustituyo los números por la IP real del Server

            mySocket = new Socket(address, 5000);
            PrintStream send = new PrintStream(mySocket.getOutputStream());

            //Preparo el mensaje que deseo enviar
            String info = message;

            Sending sending = new Sending(id, info);
            sending.setNameClient(nameCliet);

            Element eSending = new Element("sending");
            eSending.setAttribute("id", sending.getId());

            Element eBody = new Element("body");
            eBody.addContent(sending.getBody());
            Element eName = new Element("name");
            eName.addContent(sending.getNameClient());

            
            eSending.addContent(eBody);
            eSending.addContent(eName);

            //convertimos a string este xml
            XMLOutputter output = new XMLOutputter(Format.getCompactFormat());
            String xmlStringStudentElement = output.outputString(eSending);

            //quitar cambios de linea
            xmlStringStudentElement = xmlStringStudentElement.replace("\n", "");
            //Debe enviarme el XML
            send.println(xmlStringStudentElement);
            //Cerrar la conexion con el Server
            mySocket.close();

        } catch (Exception ex) {
            Logger.getLogger(ClientSocketsChat.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("1000 - an exception has occurred, review socket connection");
        }

    }

}
