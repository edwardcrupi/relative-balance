import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
public class RelativeBalanceTest {
  public static void main(String[] args) throws IOException, ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String result =  RelativeBalance.readTransactions(formatter.parse("20/10/2018 12:00:00"), formatter.parse("20/10/2018 17:00:00"), "ACC334455", "./data/test.csv", formatter);
    assert(result.contains("-$25.0")
    && result.contains("1"));
    System.out.println("Test passed");
    return;
  }
}