package net.bigmir.venzor.shared;

import net.bigmir.venzor.DAO.GoodImpl;
import net.bigmir.venzor.DAO.OrderImpl;
import net.bigmir.venzor.DAO.UserImpl;
import net.bigmir.venzor.simples.Goods;
import net.bigmir.venzor.simples.Order;
import net.bigmir.venzor.simples.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkingLoops {
    private final Scanner sc = new Scanner(System.in);
    private final Connection conn;
    public WorkingLoops(Connection conn) {
        this.conn = conn;
    }

    public void init(){
        this.mainLoop();
    }

    private void mainLoop() {
        while (true) {
            System.out.println("1: users");
            System.out.println("2: goods");
            System.out.println("3: orders");
            System.out.print("->");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    this.usersLoop();
                    break;
                case "2":
                    this.goodsLoop();
                    break;
                case "3":
                    this.ordersLoop();
                    break;
                default:
                    return;
            }
        }
    }
    private void usersLoop() {
        while (true) {
            System.out.println("1: add user");
            System.out.println("2: remove user");
            System.out.println("3: find user");
            System.out.println("4: get all users");
            System.out.println("->");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    this.addUser();
                    break;
                case "2":
                    this.removeUser();
                    break;
                case "3":
                    this.findUser();
                    break;
                case "4":
                    this.getAllUsers();
                    break;
                default:
                    return;
            }
        }
    }
    private void addUser(){
        System.out.println("enter login:");
        String login = sc.nextLine();
        System.out.println("enter password:");
        String password = sc.nextLine();
        System.out.println("enter adress:");
        String adress = sc.nextLine();
        UserImpl user = new UserImpl(conn);
        user.add(new User(login, password, adress));

    }
    private void removeUser(){
        System.out.println("enter login:");
        UserImpl user = new UserImpl(conn);
        user.delete(sc.nextLine());
        System.out.println("user deleted");
    }

    private void findUser(){
        System.out.println("enter login:");
        UserImpl userImpl = new UserImpl(conn);
        User user = userImpl.find(sc.nextLine());
        System.out.println(user.toString());
    }
    private void getAllUsers(){
        UserImpl userImpl = new UserImpl(conn);
        List<User> users = userImpl.getAll();
        for(User u : users) {
            System.out.println(u.toString());
        }
    }
    private void goodsLoop() {
        while (true) {
            System.out.println("1: add product");
            System.out.println("2: remove product");
            System.out.println("3: find product");
            System.out.println("4: get all product");
            System.out.println("->");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    this.addProduct();
                    break;
                case "2":
                    this.removeProduct();
                    break;
                case "3":
                    this.findProduct();
                    break;
                case "4":
                    this.getAllProducts();
                    break;
                default:
                    return;
            }
        }
    }
    private void addProduct(){
        System.out.println("enter name:");
        String name = sc.nextLine();
        System.out.println("enter description:");
        String description = sc.nextLine();
        System.out.println("enter price:");
        int price = sc.nextInt();
        GoodImpl product = new GoodImpl(conn);
        product.add(new Goods(name, description, price));

    }
    private void removeProduct(){
        System.out.println("enter name:");
        GoodImpl product = new GoodImpl(conn);
        product.delete(sc.nextLine());
        System.out.println("product deleted");
    }

    private void findProduct(){
        System.out.println("enter name:");
        GoodImpl product = new GoodImpl(conn);
        Goods prod = product.find(sc.nextLine());
        System.out.println(prod.toString());
    }
    private void getAllProducts(){
        GoodImpl goodsImpl = new GoodImpl(conn);
        List<Goods> goods = goodsImpl.getAll();
        for(Goods u : goods) {
            System.out.println(u.toString());
        }
    }

    private void ordersLoop() {
        while (true) {
            System.out.println("1: add order");
            System.out.println("2: remove order");
            System.out.println("3: find order");
            System.out.println("4: get all orders");
            System.out.println("->");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    this.addOrder();
                    break;
                case "2":
                    this.removeOrder();
                    break;
                case "3":
                    this.findOrder();
                    break;
                case "4":
                    this.getAllOrders();
                    break;
                default:
                    return;
            }
        }
    }
    private void addOrder(){
        List<String> allUsers = new ArrayList<>();
        UserImpl user = new UserImpl(conn);
        for(User u : user.getAll()){
            allUsers.add(u.getLogin());
        }
        List<String> allGoods = new ArrayList<>();
        GoodImpl product = new GoodImpl(conn);
        for(Goods g : product.getAll()){
            allGoods.add(g.getName());
        }
        boolean f = true;
        String login="";
        while (f) {
            System.out.println("enter users login:");
            login = sc.nextLine();
            if (allUsers.contains(login)){
                f=false;
            } else {
                System.out.println("wrong user");
            }
        }
        f=true;
        String name="";
        while (f) {
            System.out.println("enter product name:");
            name = sc.nextLine();
            if (allGoods.contains(name)){
                f=false;
            }else {
                System.out.println("wrong product");
            }
        }

        OrderImpl order = new OrderImpl(conn);
        order.add(new Order(login, name));
        System.out.println("order added");
    }
    private void removeOrder(){
        System.out.println("enter id:");
        OrderImpl order = new OrderImpl(conn);
        order.delete(sc.nextInt());
        System.out.println("order deleted");
    }

    private void findOrder(){
        System.out.println("enter id:");
        OrderImpl orderImpl = new OrderImpl(conn);
        Order order = orderImpl.find(sc.nextInt());
        System.out.println(order.toString());
    }
    private void getAllOrders(){
        OrderImpl orderImpl = new OrderImpl(conn);
        List<Order> orders = orderImpl.getAll();
        for(Order o : orders) {
            System.out.println(o.toString());
        }
    }


}
