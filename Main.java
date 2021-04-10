/*
Jake Buchanan: 5450753 (bucha253)
Poojan Patel: 5453845  (patel709)
*/

import java.util.Scanner; //imported for user input

public class Main{

    public static void main(String[] args) {
      /* Prints text to the terminal. Creates new database and scanner objects.
      While loop is used to keep the function running while text is inputed. The
      input is used to create and InterpretedQuery type from the QueryEvaluator.
      The InterpretedQuery type is then used to decide which case to fulfill in the
      database class. If the user enters any invalid case, they are notified of the
      invalid input. */

        System.out.println("Database simulator");
        Database database = new Database();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while(run == true) {
            try {
               System.out.println("Please type next command");
               String line = scanner.nextLine();
               InterpretedQuery q = QueryEvaluator.evaluateQuery(line);
               run = database.useQuery(q);
            }catch(Exception exception) {
                System.out.println("command failed");
            }
        }

        scanner.close();
        System.out.println("end");

    }//main

}//class
