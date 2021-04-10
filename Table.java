/*
Jake Buchanan: 5450753 (bucha253)
Poojan Patel: 5453845  (patel709)
*/

import java.io.File; //imported for file handling
import java.io.FileWriter; //imported to write to files

public class Table {
    //initializes private variables: name, colTypes, colNames. Creates new row object that has a length of 0.
    private String name;
    private String[] colTypes;
    private String[] colNames;

    Row [] rows = new Row[0];

    public Table(String tablename, String[] types,String[] columns) {
      //constructor. We can refer to any of these from within an instance method.
        name = tablename;
        colTypes = types;
        colNames = columns;
    }

    public void addRow(String[] values) {
      /* addRow simply adds another row to the table array and expands the length
      of the table array by one. Does this by creating a temporary table that is
      one length larger than the previous one. For loop to copy contents of initial
      table into temp, leaving the last index empty. Final lines add values to last index.
      */
        Row[] temp = new Row[rows.length+1];
        for(int x = 0; x < rows.length; x ++ ) {
            temp[x] = rows[x];
        }
        temp[rows.length] = new Row(values);
        rows = temp;
    }

    public void select(String [] columns, String conditional) {
      // Prints requested information from all columns in a given table.

        for(int x = 0; x < columns.length; x ++) {
            System.out.print(columns[x]);
            if(x != columns.length -1)
                System.out.print(",");
        } // Prints out columns and adds comma to every value besides the last

        System.out.println();
        int typeIndex = 0;
        for(int x = 0; x < colNames.length && typeIndex < columns.length; x ++) {
            if(colNames[x].trim().equals(columns[typeIndex].trim())) {
                System.out.print(colTypes[x]);
                typeIndex ++;
                x = 0;
                if(typeIndex != columns.length) {
                    System.out.print(",");
                }
            }
        }// Prints out column types and adds comma to all values besides the last. If
        //statement checks if specific column input is in wanted colNames

        System.out.println();


        String selectedType = "";
        String[] conditionalParts = conditional.split(" ");
        String whereCol = conditionalParts[0];
        String operation = conditionalParts[1];
        String comparison = conditionalParts[2];
        //String array conditionalParts created and indexes are where the column is, operand,
        //and thing being compared, respectively.

        int index = 0;
        for(int x = 0; x < colNames.length; x ++) {
            if(colNames[x].equals(whereCol)) {
                selectedType = colTypes[x];
                index = x;
                break;
            }
        }//If the column name agrees where the column is, the selectedType is assigned for use later.

        int indexInCols = 0;
        for(int r = 0; r < rows.length; r ++) {
            if(rows[r].fitsConditional(index, selectedType, operation, comparison)) {
                for(int x = 0; x < colNames.length && indexInCols < columns.length; x ++) {
                    if(colNames[x].trim().equals(columns[indexInCols].trim())) {
                        System.out.print(rows[r].getValues()[x]);
                        indexInCols ++;
                        x = 0;
                        if(indexInCols != columns.length) {
                            System.out.print(",");
                        }
                    }
                }
                System.out.println();
                indexInCols = 0;
            }
        }//First, checks if selectedType is valid. If fitsConditional returns true, prints each of the selected.
    }

    public void print() {
        for(int x = 0; x < colNames.length; x ++) {
            System.out.print(colNames[x]);
            if(x != colNames.length -1)
                System.out.print(",");
        }

        System.out.println();
        for(int x = 0; x < colTypes.length; x ++) {
            System.out.print(colTypes[x]);
            if(x != colTypes.length -1)
                System.out.print(",");
        }

        System.out.println();
        for(int x = 0; x < rows.length; x ++) {
            String [] values = rows[x].getValues();
            for(int v = 0; v < values.length; v ++) {
                System.out.print(values[v]);
                if(v != values.length -1)
                    System.out.print(",");
            }
            System.out.println();
        }
    }//displays the contents of the inputed table to the terminal. It prints it in the same
    //format as the .db file by adding commas and printing on new lines.

    public void printToFile() {
        try {
            FileWriter writer = new FileWriter(new File(name+".db"));

            for(int x = 0; x < colNames.length; x ++) {
                writer.write(colNames[x]);
                if(x != colNames.length -1)
                    writer.write(",");
            }

            writer.write("\n");
            for(int x = 0; x < colTypes.length; x ++) {
                writer.write(colTypes[x]);
                if(x != colTypes.length -1)
                    writer.write(",");
            }

            writer.write("\n");
            for(int x = 0; x < rows.length; x ++) {
                String [] values = rows[x].getValues();
                for(int v = 0; v < values.length; v ++) {
                    writer.write(values[v]);
                    if(v != values.length -1)
                        writer.write(",");
                }
                writer.write("\n");
            }

            writer.close();
        }catch(Exception exception) {
            System.out.println("File not found");
        }
    }//Uses FileWriter to write to a new .db file. Uses for loops to add new lines and commas.

    public int getNumberOfColumns() {
      //Get method returns the length of colNames
        return colNames.length;
    }

    public String getName() {
      //Get method returns the variable name value
        return name;
    }

    public void setName(String name) {
      //Set method sets the variable name value to given object's name
        this.name = name;
    }

    public String[] getColTypes() {
      //Get method returns the variable colTypes value
        return colTypes;
    }

    public void setColTypes(String[] colTypes) {
      //Set method sets the variable colTypes value to given object's type
        this.colTypes = colTypes;
    }

    public String[] getColNames() {
      //Get method returns the variable colNames value
        return colNames;
    }

    public void setColNames(String[] colNames) {
      //Set method sets the variable colNames value to given object's name
        this.colNames = colNames;
    }

} //Table class
