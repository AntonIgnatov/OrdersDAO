package net.bigmir.venzor.DAO;

import net.bigmir.venzor.simples.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientImpl implements SimpleDAO<Client> {
    private final Connection conn;

    public ClientImpl(Connection conn) {
        this.conn = conn;
    }

    public void add(Client arg) {
        if (this.find(arg.getLogin())==null) {
            try {
                try (PreparedStatement st = conn.prepareStatement("INSERT INTO users (login, password, adress) VALUES(?, ?, ?)")) {
                    st.setString(1, arg.getLogin());
                    st.setString(2, arg.getPassword());
                    st.setString(3, arg.getAdress());
                    st.executeUpdate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else {
            System.out.println("user allready exist");
        }
    }

    public void delete(String name) {
        if (this.find(name) != null) {
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE login=?");
                try {
                    ps.setString(1, name);
                    ps.execute();
                } finally {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<Client> getAll() {
        List<Client> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM users")) {
                    while (rs.next()) {
                        Client client = new Client();

                        client.setId(rs.getInt(1));
                        client.setLogin(rs.getString(2));
                        client.setPassword(rs.getString(3));
                        client.setAdress(rs.getString(4));

                        res.add(client);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Client find(String name) {
        Client resClient = new Client();
        String sql = "SELECT * FROM users where login= \"" + name + "\"";
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    if (rs.next()) {
                        resClient.setId(rs.getInt(1));
                        resClient.setLogin(rs.getString(2));
                        resClient.setPassword(rs.getString(3));
                        resClient.setAdress(rs.getString(4));
                    } else {
                        System.out.println("wrong user");
                        return null;
                    }
                }
            }

            return resClient;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean existing(String name) {
        return false;
    }
}
