package net.bigmir.venzor.simples;

public class Order {
    private int id;
    private String user;
    private String goods;

    public Order() {
    }

    public Order(String user, String goods) {
        this.user = user;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
