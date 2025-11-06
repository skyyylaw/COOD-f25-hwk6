package flutweets.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
  public static ArrayList<String> getInput(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter file name for tweets data: ");
    String dataFileName = scanner.nextLine();
    System.out.println("Enter file name for states data: ");
    String stateFileName = scanner.nextLine();
    System.out.println("Enter file name for logger: ");
    String loggerFileName = scanner.nextLine();
    scanner.close();
    return new ArrayList<>(List.of(dataFileName, stateFileName, loggerFileName));
  }

  public static void display(String msg) {
    System.out.println(msg);
  }
}
