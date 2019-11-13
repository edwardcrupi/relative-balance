import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class RelativeBalanceRelease {

    public static void main(String[] args) {
      // Creates a reader instance which takes
      // input from standard input - keyboard
      if(args.length == 0) {
        System.out.println("Please add a csv file as an argument");
        return;
      }
      // Get account info
      Scanner reader = new Scanner(System.in);
      System.out.print("accountId: ");
      // nextInt() reads the next line from the keyboard
      String accountId = reader.nextLine();

      try
      {
          // Get date range
          System.out.print("from: ");
          String fromString = reader.nextLine();
          SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date from = formatter.parse(fromString);  
          System.out.print("to: ");
          String toString = reader.nextLine();
          Date to=formatter.parse(toString);

          String result = RelativeBalance.readTransactions(from, to, accountId, args[0], formatter);
          return;
      } 
      catch (IOException e) 
      { 
          System.out.println("Error reading file in main:");
          e.printStackTrace();
          return;
      } catch (ParseException e){
          System.out.println("Error parsing string in main:");
          e.printStackTrace();
          return;
      }
    }
}