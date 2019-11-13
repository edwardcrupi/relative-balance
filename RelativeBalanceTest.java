import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
public class RelativeBalanceTest {
  public static void main(String[] args) throws IOException {
    String result =  RelativeBalance.readTransactions(new Date("20/10/2018 12:00:00"), new Date("20/10/2018 17:00:00"), "ACC334455", "data/test.csv", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
    assert result.contains("-$25.0")
    && result.contains("1");
    System.out.println("Test passed");
    return;
  }
}