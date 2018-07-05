/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Oscar Luis
 */
public class Sending {
    private String id;
    private String body;
    private String nameClient;
    public Sending(String id, String body) {
        this.id = id;
        this.body = body;
        
    }

    public Sending() {
    }
    
    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return  id + "," + body+ "," +nameClient;
    }
    
    
}
