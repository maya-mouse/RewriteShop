public class Article
{
   public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    private int code;
    private String title;
    private int price;
    private String details;
    private int counter;

    public Article(int code, String title, int price, String details, int counter)
    {
        this.code = code;
        this.title = title;
        this.price = price;
        this.details = details;
        this.counter = counter;
    }

    public int getCounter()
    {
        return counter;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

    public int getCode()
    {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return ANSI_CYAN +
                "Код товару " + ANSI_RESET+ code + ANSI_CYAN+
                ". Найменування : " + ANSI_RESET+ title +ANSI_CYAN+
                "\nЦіна : " + ANSI_RESET+ price +" грн"+ ANSI_CYAN+
                "\nДеталі : " + ANSI_RESET+ details + ANSI_CYAN+
                "\nКількість товару в наявності :"+ ANSI_RESET+ counter + ANSI_CYAN+
                "\n---\t---\t---\t---\t---\t---\t---\t---"+ ANSI_RESET;
    }
}
