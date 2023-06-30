import java.lang.reflect.Array;
import java.util.ArrayList;

public class Customer extends User
{
    public Customer(int idCode, String login, String password)
    {
        super(idCode, login, password);
    }

    @Override
    public String toString()
    {
        return "Дані про покупця " + super.getIdCode() +
                "\nІм'я :" + super.getLogin()+
                "\nКількість замовлень : " +  super.orders.size();
    }

    @Override
    public void demonstrateHistory()
    {
        super.demonstrateHistory(orders);
    }



}
