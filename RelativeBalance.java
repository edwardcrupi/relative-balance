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
public class RelativeBalance {
    /**
    ** Reads a set of transactions from a file
    */
    public static String readTransactions(Date from, Date to, String accountId, String path, SimpleDateFormat formatter) throws IOException {
      try {
        // Read file as string and find reversed transactions
        String content = Files.readString(Paths.get(path));
        System.out.println(content);
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("TX[0-9]+\n")
                    .matcher(content);
        while (m.find()) {
          System.out.println(m.group()+ " was reversed");
          allMatches.add(m.group());
        }
        int i = 0;
        
        // Ignore all reversed transactions
        for(String reversal : allMatches){
          content = content.replaceAll("(?m)^"+reversal+".*", "");
          i++;
        }
        System.out.println("Removed " + allMatches.size() + " transactions");
        
        // Calculate relative balance for date range over remaining transactions
        double runningTotal = 0.0;
        int includedTransactions = 0;
        Scanner scanner = new Scanner(content);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
          String[] lines = scanner.nextLine().split(",");
          String dateString = lines[3].trim();
          Date date=formatter.parse(dateString);
          String debitAccount = lines[1].trim();
          String creditAccount = lines[2].trim();
          double amount = new Double(lines[4].trim());

          // Check if date is in range and account matches the one given as a debit
          if(from.compareTo(date) * date.compareTo(to) > 0 && accountId.equalsIgnoreCase(debitAccount)){
            runningTotal -= amount;
            includedTransactions++;
          }
          // Check if date is in range and account matches the one given as a credit
          if(from.compareTo(date) * date.compareTo(to) > 0 && accountId.equalsIgnoreCase(creditAccount)){
            runningTotal += amount;
            includedTransactions++;
          }
        }
        scanner.close();
        String result = "Relative balance for the period is: " + (runningTotal > 0 ? "$" : "-$") + Math.abs(runningTotal) + "\nNumber of transactions included is: " + includedTransactions;
        System.out.println(result);
        return result;
      } catch (IOException e) {
          System.out.println("Error reading file in readTransactions:");
          e.printStackTrace();
          throw(e);
      } catch (ParseException e){
          System.out.println("Error parsing string in readTransactions:");
          e.printStackTrace();
          return "failed";
      }
    }
  }
