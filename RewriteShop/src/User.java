import java.util.ArrayList;

public abstract class User
{
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    private int idCode;
    private String login;
    private String password;
    public ArrayList <Article> orders = new ArrayList<>();
//    private int countOrder;
    public boolean online = false;

    public User (int idCode, String login, String password)
    {
        this.idCode = idCode;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Перевизначу потім";
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
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

    public ArrayList<Article> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Article> orders) {
        this.orders = orders;
    }

//    public int getCountOrder() {
//        return countOrder;
//    }

//    public void setCountOrder(int countOrder) {
//        this.countOrder = countOrder;
//    }

    public void addToHistory(int code)
    {
//        setCountOrder(getCountOrder()+1);
        orders.add(Assistant.getThings().get(code));
    }
    public void demonstrateHistory(ArrayList <Article>orders)
    {
        for (Article article: orders) {
            System.out.println(ANSI_CYAN +
                    "Код товару " + ANSI_RESET+ article.getCode() + ANSI_CYAN+
                    ". Найменування : " + ANSI_RESET+ article.getTitle() +ANSI_CYAN+
                    "\nЦіна : " + ANSI_RESET+ article.getPrice() +" грн"+ ANSI_CYAN+
                    "\nДеталі : " + ANSI_RESET+ article.getDetails() + ANSI_CYAN);

        }

    }

    public abstract void demonstrateHistory();
}

