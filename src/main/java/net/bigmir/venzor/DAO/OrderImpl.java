package net.bigmir.venzor.DAO;

import net.bigmir.venzor.simples.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements SimpleDAO<Order> {
    private final Connection conn;

    public OrderImpl(Connection conn) {
        this.conn = conn;
    }

    public void add(Order arg) {
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO orders (user_id, goods_id) VALUES((SELECT id FROM users WHERE login=?), (SELECT id FROM goods WHERE name=?))")) {
                st.setString(1, arg.getUser());
                st.setString(2, arg.getGoods());
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
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

    public List<Order> getAll() {
        List<Order> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT orders.id, users.login, goods.name FROM orders" +
                        "INNER JOIN users ON orders.users_id=users.id" +
                        "INNER JOIN goods ON orders.goods_id=goods.id")) {
                    while (rs.next()) {
                        Order order = new Order();

                        order.setId(rs.getInt(1));
                        order.setUser(rs.getString(2));
                        order.setGoods(rs.getString(3));

                        res.add(order);
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
