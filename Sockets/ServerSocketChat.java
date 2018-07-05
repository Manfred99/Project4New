package Sockets;

import Domain.Sending;
import com.vdurmont.emoji.EmojiParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ServerSocketChat extends Thread {

    private static String serverName;
    private TextArea textContainer;

    public ServerSocketChat(TextArea textContainer) {
        serverName = "El nombre del jugador";
        this.textContainer = textContainer;
    }

    @Override
    public void run() {
        try {
            //while(true){
                functionalityServerSocket();
            //}
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSocketChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void functionalityServerSocket() throws IOException {

        ServerSocket serverSocket;
        Socket client;
        int clientNumber = 0;
        serverSocket = new ServerSocket(10000);

        do {
            
                //Instancia de espera
                client = serverSocket.accept();
                System.out.println("se ha conectado alguien " + ++clientNumber);
                //++clientNumber;

                //Para poder acceder a los mensajes que me envía el ServerCliente hago la instancia siguiente
                BufferedReader recieve = new BufferedReader(
                        new InputStreamReader(
                                client.getInputStream()
                        )
                );

                //En este punto, según el código del Server, este ya me envió el mensaje,
                //procedo a leer el mensaje que me envió
                Sending sending = new Sending();
                String studentString = recieve.readLine();
                SAXBuilder saxBuilder = new SAXBuilder();
                StringReader stringReader = new StringReader(studentString);
                try {
                    //obtenemos un elemento xml
                    org.jdom.Document doc = saxBuilder.build(stringReader);
                    Element rootStudent = doc.getRootElement();

                    //LLAMAR A LA BIBLIOTECA QUE SE ENCARGA DE INSETAR XML
                    sending=new Sending(
                            rootStudent.getAttributeValue("id"),
                            rootStudent.getChildText("body"));
                    sending.setNameClient(rootStudent.getChildText("name"));
                    
                } catch (JDOMException ex) {
                   // Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            if (sending.getId().equals("Message")) {
                String infoToSend = textContainer.getText() +sending.getNameClient()+": "+ " "+sending.getBody()+" ";
                String messageEmoticon = EmojiParser.parseToUnicode(infoToSend.replace(" ", ":"))+"\n";
               
                this.textContainer.setText(messageEmoticon.replace(":", " "));
            }else if (sending.getId().equals("Shoot")){
                System.out.println(sending.getBody());
            }

//Impresion de control para ver el mensaje que me envió el Server
//            ta_TextContainer.setText(infoResponse);
//            System.out.println("THE SERVER SENT ME: " + infoResponse);
//finalizamos la comunicacion con ese cliente
                client.close();
            

        } while (true);
    }

}
