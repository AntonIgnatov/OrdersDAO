package net.bigmir.venzor;

import net.bigmir.venzor.DAO.ClientImpl;
import net.bigmir.venzor.shared.ConnectionFactory;
import net.bigmir.venzor.simples.Client;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.getConnection();
        Client client = new Client("root", "root", "root");
        Client client3 = new Client("root1", "root", "root");
        ClientImpl client1 = new ClientImpl(con);
        client1.add(client);
        client1.add(client);
        client1.delete("root");
        client1.add(client3);

    }
}
