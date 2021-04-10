/*
Jake Buchanan: 5450753 (bucha253)
Poojan Patel: 5453845  (patel709)
*/

import java.util.Scanner; //imported to read files
import java.io.File; //imported to read files

public class Database {

    private Table[] tables = new Table[0]; //creates new Database which is a table array


    public void addTable(Table t) {
        Table[] temp = new Table[tables.length+1];
        for(int x = 0; x < tables.length; x ++ ) {
            temp[x] = tables[x];
        }
        temp[tables.length] = t;
        tables = temp;
    } /*makes temp table array that is one length larger than current table array.
        copies all elements of current table array into the temp array, which leaves
        one open spot at the end. Puts Table t parameter as the last table in temp.
        Current table array then assigned the temp.*/

    public Table getTable(String name) {
        for(int x = 0; x < tables.length; x ++) {
            if(tables[x].getName().equals(name))
                return tables[x];
        }
        System.out.println("table not found");
        return null;
    } //Checks if table with name parameter exists and returns it


    public boolean useQuery(InterpretedQuery query) {
        QueryType type = query.getQueryType();
        switch(type) {
            case EXIT_STATEMENT:
                return false;
            case CREATE_STATEMENT:
                createTable(query.getTableName(),query.getColumnTypes(),query.getColumnNames());
                return true;
            case INSERT_STATEMENT:
                insert(query.getTableName(),query.getInsertValues());
                return true;
            case PRINT_STATEMENT:
                printTable(query.getTableName());
                return true;
            case STORE_STATEMENT:
                storeTable(query.getTableName());
                return true;
            case LOAD_STATEMENT:
                loadTable(query.getFileName());
                return true;
            case SELECT_STATEMENT:
                select(query.getTableName(),query.getColumnNames(),query.getConditional());
                return true;
             default:
                 System.out.println("invalid query type");
                 return true;
          }
    } /*Splits up parsed query for the given type of statement. Returns boolean so the exit statement
        is able to return false and terminate program, true otherwise. For each case, does the specified
        method associated with statement.*/

    public void insert(String name, String[] insertValues) {
        Table table = getTable(name);
        if(table != null && table.getNumberOfColumns() == insertValues.length) {
            table.addRow(insertValues);
        }else {
            System.out.println("table doesnt exist in database or incorrect values");
        }
    } //Inserts row into named table. If no table named exists in database prints error

    public void select(String name, String[] columns, String conditional) {
        Table table = getTable(name);
        if(table != null) {
            table.select(columns, conditional);
        }else
            System.out.println("table not found");

    } /*Selects columns in the string array for a named table and checks each row value
        in the columns with the conditional. If no table named exists in database prints error*/


    public void printTable(String name) {
        Table table = getTable(name);
        if(table != null) {
            table.print();
        }else {
            System.out.println("table not found");
        }
    } //Prints out table if named table exists, otherwise prints error.

    public void storeTable(String name) {
        Table table = getTable(name);
        if(table != null) {
            table.printToFile();
        }else {
            System.out.println("table not found");
        }
    } //Stores named table by printing to file if table exists, otherwise prints error.

    public void loadTable(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            String cols = scanner.nextLine();
            String types = scanner.nextLine();
            String newName = filename.substring(0, filename.length()-3);


            Table table = new Table(newName, types.split(","),cols.split(","));
            addTable(table);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] insertVals = line.split(",");
                table.addRow(insertVals);

            }
            scanner.close();
        }catch(Exception exception) {
            System.out.println("file not found");
        }
    } /*Loads in .db file and creates a new table with the data in the file.
        If file doesn't exist prints error.*/


    public void createTable(String name, String[] types, String[] columns) {

        boolean canCreate = true;

        for(int x = 0; x < types.length; x ++) {
            String s = types[x];
            switch(s) {
            case "double":
                break;
            case "int":
                break;
            case "boolean":
                break;
            case "String":
                break;
            default:
                System.out.println(s+ " is an invalid data type");
                canCreate = false;
                break;
            }
        } //Checks all cases to see if there is an invalid data type. If the type is valid then it breaks and boolean value remains true.

        for(int x= 0; x < columns.length; x ++) {
            for(int y = 0; y < columns.length; y ++) {
                if(y != x && columns[x].equals(columns[y])) {
                    System.out.println(columns[x] + " is not a unique column name ");
                    canCreate = false;
                }
            }
        }//Checks to see if column name is unique

        for(int x = 0; x < tables.length; x ++) {
            if(tables[x].getName().equals(name)) {
                System.out.println(tables[x].getName()+" already exists in database");
                canCreate = false;
            }
        }//checks to see if table name is unique

        if(canCreate == true) {
            Table t = new Table(name, types, columns);
            addTable(t);
        }//Creates new table object and adds it to the database

    }

    public void checkTables() {
        for(int x = 0; x < tables.length; x ++) {
            System.out.println(tables[x].getName());
        }
    }//Checks database and print table names

} //Database class
