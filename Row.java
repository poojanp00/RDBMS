/*
Jake Buchanan: 5450753 (bucha253)
Poojan Patel: 5453845  (patel709)
*/

public class Row {

    private String[] values;

    public Row(String [] vals) {
        values = vals;
    }//constructor. We can refer to these from within an instance method.

    public boolean fitsConditional(int index,String type, String operation, String comparison) {
        switch(type) {
        case "double":
            return evalDoubles(Double.parseDouble(values[index]),Double.parseDouble(comparison) , operation);
        case "int":
            return evalInts(Integer.parseInt(values[index]),Integer.parseInt(comparison) , operation);
        case "boolean":
            return evalBools(Boolean.parseBoolean(values[index]),Boolean.parseBoolean(comparison) , operation);
        case "String":
            return evalStrings(values[index],comparison , operation);
        default:
            System.out.println("invalid data type while eval conditional");
            return false;
        }
    } //Checks to see if type is allowed/valid. Then does the evaluation of the specific comparison denoted by the comparison string.
      //If type is invalid, returns false and does not continue.

    public boolean evalDoubles(double val, double comp, String op) {
        switch(op) {
        case "<=":
            return  val <= comp;
        case "<":
            return  val < comp;
        case "=":
            return  val == comp;
        case "!=":
            return  val != comp;
        case ">":
            return  val > comp;
        case ">=":
            return  val >= comp;
        default:
            return false;
        }
    }//Checks all possible cases for type double and returns the respective boolean value.

    public boolean evalInts(int val, int comp, String op) {
        switch(op) {
        case "<=":
            return  val <= comp;
        case "<":
            return  val < comp;
        case "=":
            return  val == comp;
        case "!=":
            return  val != comp;
        case ">":
            return  val > comp;
        case ">=":
            return  val >= comp;
        default:
            return false;
        }
    }//Checks all possible cases for type ints and returns the respective boolean value.

     public boolean evalBools(boolean val, boolean comp, String op) {
         switch(op) {
         case "=":
             return  val == comp;
         case "!=":
             return  val != comp;
         default:
             return false;
         }
     }//Checks all possible cases for type bool and returns the respective boolean value.


     public boolean evalStrings(String val, String comp, String op) {
         switch(op) {
         case "=":
             return  val.equals(comp);
         case "!=":
             return  !val.equals(comp);
         default:
             return false;
         }
     }//Checks all possible cases for type String and returns the respective boolean value.




    public String[] getValues() {
      //Get method returns the variable "values" value
        return values;
    }

}//Row class
