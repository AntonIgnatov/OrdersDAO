package net.bigmir.venzor;


import net.bigmir.venzor.shared.ConnectionFactory;
import net.bigmir.venzor.shared.InitTables;
import net.bigmir.venzor.shared.WorkingLoops;


import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection conn = factory.getConnection();
        InitTables initTables = new InitTables(conn);
        try {
            initTables.init();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        WorkingLoops work = new WorkingLoops(conn);
        work.init();


    }


}
