package net.bigmir.venzor.simples;

public class Goods {
    private int id;
    private String name;
    private String description;
    private int price;

    public Goods() {
    }

    public Goods(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product: "+this.getName()+"; ");
        sb.append("description: "+this.getDescription()+"; ");
        sb.append("price: "+this.getPrice()+"; ");
        return sb.toString();
    }
}
