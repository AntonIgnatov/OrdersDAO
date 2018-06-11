package net.bigmir.venzor.DAO;

import net.bigmir.venzor.simples.Order;

import java.util.List;

public interface OrdersDAO {
    void add(Order arg);
    void delete(int orderId);
    List<Order> getAll();
    Order find(int orderId);
}
