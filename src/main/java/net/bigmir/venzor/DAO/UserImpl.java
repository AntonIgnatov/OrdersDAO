package net.bigmir.venzor.DAO;

import net.bigmir.venzor.simples.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements SimpleDAO<User> {
    private final Connection conn;

    public UserImpl(Connection conn) {
        this.conn = conn;
    }

    public void add(User arg) {
        if (this.find(arg.getLogin()) == null) {
            try {
                try (PreparedStatement st = conn.prepareStatement("INSERT INTO users (login, password, adress) VALUES(?, ?, ?)")) {
                    st.setString(1, arg.getLogin());
                    st.setString(2, arg.getPassword());
                    st.setString(3, arg.getAdress());
                    st.executeUpdate();
                    System.out.println("user added");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
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

    public List<User> getAll() {
        List<User> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM users")) {
                    while (rs.next()) {
                        User user = new User();

                        user.setId(rs.getInt(1));
                        user.setLogin(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setAdress(rs.getString(4));

                        res.add(user);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User find(String name) {
        User resUser = new User();
        String sql = "SELECT * FROM users where login= \"" + name + "\"";
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    if (rs.next()) {
                        resUser.setId(rs.getInt(1));
                        resUser.setLogin(rs.getString(2));
                        resUser.setPassword(rs.getString(3));
                        resUser.setAdress(rs.getString(4));
                    } else {
                        return null;
                    }
                }
            }

            return resUser;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
