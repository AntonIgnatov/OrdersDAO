package net.bigmir.venzor.DAO;

import net.bigmir.venzor.simples.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodImpl implements SimpleDAO<Goods> {
    private final Connection conn;

    public GoodImpl(Connection conn) {
        this.conn = conn;
    }

    public void add(Goods arg) {
        if (this.find(arg.getName())==null) {
            try {
                try (PreparedStatement st = conn.prepareStatement("INSERT INTO goors (name, description, price) VALUES(?, ?, ?)")) {
                    st.setString(1, arg.getName());
                    st.setString(2, arg.getDescription());
                    st.setInt(3, arg.getPrice());
                    st.executeUpdate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else {
            System.out.println("product allready exist");
        }
    }

    public void delete(String name) {
        if (this.find(name) != null) {
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM goods WHERE name=?");
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

    public List<Goods> getAll() {
        List<Goods> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM goods")) {
                    while (rs.next()) {
                        Goods goods = new Goods();

                        goods.setId(rs.getInt(1));
                        goods.setName(rs.getString(2));
                        goods.setDescription(rs.getString(3));
                        goods.setPrice(rs.getInt(4));

                        res.add(goods);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Goods find(String name) {
        Goods resGoods = new Goods();
        String sql = "SELECT * FROM goods where name= \"" + name + "\"";
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    if (rs.next()) {
                        resGoods.setId(rs.getInt(1));
                        resGoods.setName(rs.getString(2));
                        resGoods.setDescription(rs.getString(3));
                        resGoods.setPrice(rs.getInt(4));
                    } else {
                        System.out.println("wrong user");
                        return null;
                    }
                }
            }

            return resGoods;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean existing(String name) {
        return false;
    }
}
