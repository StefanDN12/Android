package BBDD.Clases;

public class UserLogin {

    private static int ID;
    private String cuenta;
    private String password;


    public UserLogin() {
    }

    public static int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
