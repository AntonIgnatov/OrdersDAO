package net.bigmir.venzor.DAO;

import net.bigmir.venzor.simples.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrdersDAO {
    private final Connection conn;
    private final String sql = "SELECT orders.id, users.login, goods.name FROM orders" +
            " INNER JOIN users ON orders.users_id=users.id" +
            " INNER JOIN goods ON orders.goods_id=goods.id";

    public OrderImpl(Connection conn) {
        this.conn = conn;
    }

    public void add(Order arg) {
        String sqlOne = "INSERT INTO orders (users_id, goods_id) " +
                "VALUES((SELECT id FROM users WHERE login=\"" +arg.getUser() + "\"), " +
                "(SELECT id FROM goods WHERE name=\"" +arg.getGoods() + "\"))";
        try {
            try (PreparedStatement st = conn.prepareStatement(sqlOne)) {//
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void delete(int orderId) {
        if (this.find(orderId) != null) {
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM orders WHERE id=?");
                try {
                    ps.setInt(1, orderId);
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
                try (ResultSet rs = st.executeQuery(sql)) {
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

    public Order find(int orderId) {
        Order resClient = new Order();
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    if (rs.next()) {
                        resClient.setId(rs.getInt(1));
                        resClient.setUser(rs.getString(2));
                        resClient.setGoods(rs.getString(3));
                    } else {
                        System.out.println("wrong order");
                        return null;
                    }
                }
            }

            return resClient;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
