import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
public class Assistant
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static ArrayList<Article> things = new ArrayList();
 private static ArrayList<Customer> customersList = new ArrayList();
 private static ArrayList<Seller> sellersList = new ArrayList();
 private static HashMap<String,String> customers =  new HashMap<>();
 private static HashMap<String,String> sellers =  new HashMap<>();
 private static boolean online = false;
 private static boolean admin = false;
 public  static Article currentarticle;

    private static int currentcustomer;
    private static int currentseller;


    public static int choice = 0;
 public static void createList(boolean i)
 {
     if(i == true)
     {
         Article article1 = new Article(101, "квіточка", 17, "тратата", 100);
         Article article2 = new Article(102, "комаха", 42, "тратата", 170);
         Article article3 = new Article(103, "листок", 5, "тратата", 23);
         Article article4 = new Article(104, "пташеня", 78, "тратата", 45);
         things.add(article1);
         things.add(article2);
         things.add(article3);
         things.add(article4);
     }
     else
     {
         things.add(sellersList.get(currentseller).addArticle());
     }
 }

    public static ArrayList<Article> getThings()
    {
        return things;
    }

    public static void Show(ArrayList<Article> things)
    {
        Scanner scanner = new Scanner(System.in);
        for (Article thing: things)
        {
            System.out.println(thing);
        }
        System.out.println("Введіть будь-який символ для продовження : ");
        choice = (int) Array.getChar((scanner.nextLine()).toCharArray(), 0);

    }

       public static void makeOrder()
    {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Виберіть товар використовуючи код : ");
        choice = scanner.nextInt();
        scanner.nextLine();
            for (Article thing : things)
            {
                if (choice == thing.getCode())
                {
                    thing.setCounter(thing.getCounter() - 1);
                    customersList.get(currentcustomer).orders.add(thing);
                    System.out.println(thing);
                    break;
                }
            }
        if(choice == -1) System.out.println(ANSI_RED+"Товару з таким кодом неіснує !"+ANSI_RESET);
        System.out.println("Введіть будь-який символ для продовження : ");
        choice = (int) Array.getChar((scanner.nextLine()).toCharArray(), 0);
    }

    public static void authorization()
    {
        Scanner scanner = new Scanner(System.in);
        String login = null;
        String password = null;
        while(true)
        {
            System.out.println("Зайти як покупець (1) або як продавець (2) : ");
            choice = scanner.nextInt();
            scanner.nextLine();
//            --
            if(choice == 1&&customersList.isEmpty())
            {
                System.out.println("...Створення нового покупця ");
                System.out.print("ЛОГІН : ");
                login = scanner.nextLine();
                System.out.print("ПАРОЛЬ : ");
                password =  scanner.nextLine();
                customers.put(password,login);
                Customer customer = new Customer(customers.size(), login,password);
                customersList.add(customer);
                System.out.println("Покупця створено !");
            }
//            --
            else if(choice == 2&&customersList.isEmpty())
            {
                admin = true;
                System.out.println("...Створення нового продавця ");
                System.out.print("ЛОГІН : ");
                login = scanner.nextLine();
                System.out.print("ПАРОЛЬ : ");
                password =  scanner.nextLine();
                sellers.put(password,login);
                Seller seller = new Seller(sellers.size(), login,password);
                sellersList.add(seller);
                System.out.println("Продавця створено !");
            }
            System.out.print("Створити нового користувача (3) або авторизуватися (4): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 3) continue;
            System.out.print("ЛОГІН : ");
            login = scanner.nextLine();
            System.out.print("ПАРОЛЬ : ");
            password = scanner.nextLine();
            if ((customers.containsKey(password))&&(customers.containsValue(login)))
            {
                currentcustomer = userSearch(password,login,false);
                online = true;
                System.out.println(ANSI_CYAN+"Вітаю, "+ customersList.get(currentcustomer).getLogin()+" !"+ANSI_RESET);
                break;
            }
            else if ((sellers.containsKey(password))&&(sellers.containsValue(login)))
            {
                currentseller = userSearch(password,login,true);
                online = true;
                System.out.println(ANSI_CYAN+"Вітаю, "+ sellersList.get(currentcustomer).getLogin()+" !"+ANSI_RESET);
                break;
            }

            System.out.println("+-----------+");
            System.out.println("| Їбанько ? |");
            System.out.println("+-----------+");
        }

        System.out.println("Введіть будь-який символ для продовження : ");
        choice = (int) Array.getChar((scanner.nextLine()).toCharArray(), 0);
    }


    static int userSearch(String password,String login,boolean status)
    {
        int i = 0;
        if(status == false)
        {
            for (Customer cstmr : customersList) {
                if (password == cstmr.getPassword() && login == cstmr.getLogin())
                    break;
                i++;
            }
        }
        else if(status == true)
        {
            for (Seller sllr : sellersList) {
                if (password == sllr.getPassword() && login == sllr.getLogin())
                    break;
                i++;
            }
        }

        return i-1;


    }

    static void statusOnline(int codecustomer,int codeseller)
    {
        if(admin ==true&&online==true)
        {
            System.out.println(ANSI_PURPLE+"Ви зайшли як : " + sellersList.get(codecustomer).getLogin()+ANSI_RESET);
        } else if (admin == false&&online==true) {
            System.out.println(ANSI_PURPLE+"Ви зайшли як : " + customersList.get(codeseller).getLogin()+ANSI_RESET);
        }
        else
        {
            System.out.println(ANSI_PURPLE+"Ви не авторизовані : ******"+ANSI_RESET);
        }

    }

    public static void circulation()
    {
        Scanner scanner = new Scanner(System.in);
        Assistant.createList(true);
            while (true)
            {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("CONVERSE  ★  〉》〉                                                        |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("жартую, це лохотрон)                                                      |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Каталог              |                                             |>>> 1 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Авторизація          |                                             |>>> 2 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Зробити замовлення   |                                             |>>> 3 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Відправити товар     |                                             |>>> 4 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Додати товар         |                                             |>>> 5 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Перегляд історії     |                                             |>>> 6 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Вихід з акаунту      |                                             |>>> 7 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Вихід звідси         |                                             |>>> 8 |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Створено покупців    ✪                                             |  "+ANSI_PURPLE+customersList.size()+ANSI_RESET+"   |");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Створено продавців   ✪                                             |  "+ANSI_PURPLE+sellersList.size()+ANSI_RESET+"   |");
                System.out.println("---------------------------------------------------------------------------");
                statusOnline(currentcustomer,currentseller);
                System.out.println("⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐⊐");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                    {
                        Assistant.Show(things);
                        break;
                    }
                    case 2:
                    {
                        Assistant.authorization();
                        break;
                    }
                    case 3,4:
                    {
                        if(online == true)
                        {
                            Assistant.makeOrder();
                        }
                        else
                        {
                            authorization();
                            Assistant.makeOrder();
                        }
                        break;
                    }
                    case 5:
                    {
                       if(admin == true && online == true)
                       {
                           createList(false);
                       }
                       else {System.out.println(ANSI_RED+"Ви авторизовані як покупець !"+ANSI_RESET);}
                        break;
                    }

                    case 6:
                    {
                        if(online == true&&admin==true)
                        {
                            System.out.println(sellersList.get(currentseller));
                            sellersList.get(currentseller).demonstrateHistory();
                        } else if (online == true&&admin==false)
                        {
                            System.out.println(customersList.get(currentcustomer));
                            customersList.get(currentcustomer).demonstrateHistory();
                        } else
                        {
                            authorization();
                        }
                        break;
                    }
                    case 7:
                    {
                    online = false;
                    System.out.println(ANSI_RED+"Ви вийшли як розбійник"+ANSI_RESET);
                        System.out.println("Введіть будь-який символ для продовження : ");
                        choice = (int) Array.getChar((scanner.nextLine()).toCharArray(), 0);
                        break;
                     }
                    case 8:
                    {
                    System.exit(0);
                    }
                }
                }
        }
    }





