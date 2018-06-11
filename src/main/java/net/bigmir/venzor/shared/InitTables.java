package net.bigmir.venzor.shared;

import net.bigmir.venzor.DAO.GoodImpl;
import net.bigmir.venzor.DAO.OrderImpl;
import net.bigmir.venzor.DAO.UserImpl;
import net.bigmir.venzor.simples.Goods;
import net.bigmir.venzor.simples.Order;
import net.bigmir.venzor.simples.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitTables {
    private final Connection conn;
    public InitTables(Connection conn) {
        this.conn = conn;
    }

    private void fillUsers(){
        UserImpl userImpl = new UserImpl(conn);
        for (int i = 0; i < 11; i++){
            userImpl.add(new User("login"+i, "password"+i,"adress"+i));
        }
        System.out.println("fill Users done");
    }

    private void fillGoods(){
        GoodImpl goodImpl = new GoodImpl(conn);
        for (int i = 0; i < 11; i++){
            goodImpl.add(new Goods("name"+i, "description", i+100));
        }
        System.out.println("fill Goods done");
    }

    private void fillOrders(){
        OrderImpl orderImpl = new OrderImpl(conn);
        for (int i = 0; i < 5; i++){
            orderImpl.add(new Order("login"+(int) (Math.random()*10)/1, "name"+(int) (Math.random()*10)/1));
        }
        System.out.println("fill Orders done");
    }

    private void prepareTables() throws SQLException {
        Statement st = conn.createStatement();
        try{
            st.execute("DROP TABLE IF EXISTS users");
            st.execute("CREATE TABLE users(id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "login VARCHAR(20) NOT NULL,\n" +
                    "password VARCHAR(20) NOT NULL,\n" +
                    "adress VARCHAR(128) DEFAULT NULL);\n");
            st.execute("DROP TABLE IF EXISTS goods");
            st.execute("CREATE TABLE goods(id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "name VARCHAR(20) NOT NULL,\n" +
                    "description VARCHAR(128) DEFAULT NULL,\n" +
                    "price INT NOT NULL);\n");
            st.execute("DROP TABLE IF EXISTS orders");
            st.execute("CREATE TABLE orders(id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "users_id INT NOT NULL,\n" +
                    "goods_id INT NOT NULL);\n");
        } finally {
            st.close();
        }
    }
    public void init() throws SQLException {
        this.prepareTables();
        this.fillUsers();
        this.fillGoods();
        this.fillOrders();
    }


}
