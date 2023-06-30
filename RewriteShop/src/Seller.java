import java.util.Scanner;

public class Seller extends User
{
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public Seller(int idCode, String login, String password)
    {
        super(idCode, login, password);
    }

    @Override
    public  String toString()
    {
        return "Дані про продавця " + super.getIdCode() +
                "\nІм'я :" + super.getLogin()+
                "\nКількість виконаних замовлень : " +  super.orders.size();
    }

    @Override
    public void demonstrateHistory()
    {
      super.demonstrateHistory(orders);
    }
    public static Article addArticle()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_CYAN +"Код товару : " + ANSI_RESET);
        int code = Integer.parseInt(scanner.nextLine());
        System.out.print(ANSI_CYAN +"Найменування : " + ANSI_RESET);
        String title = scanner.nextLine();
        System.out.print(ANSI_CYAN +"Ціна : " + ANSI_RESET);
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print(ANSI_CYAN +"Деталі : " + ANSI_RESET);
        String details = scanner.nextLine();
        System.out.print(ANSI_CYAN +"Кількість товару в наявності : " + ANSI_RESET);
        int counter = Integer.parseInt(scanner.nextLine());
        Article article = new Article(code,title,price,details,counter);
        return article;

    }


}
