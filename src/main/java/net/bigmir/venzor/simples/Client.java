package net.bigmir.venzor.simples;

public class Client {
    private int id;
    private String login;
    private String password;
    private String adress;

    public Client() {
    }

    public Client(String login, String password, String adress) {
        this.login = login;
        this.password = password;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Users: "+this.getLogin()+"; ");
        sb.append("id - "+this.getId()+"; ");
        if (this.getAdress()!=null){
            sb.append("adress - "+this.getAdress()+"; ");
        }
        return sb.toString();
    }
}
