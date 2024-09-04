

public class SimpleDatabase {
    public static void main(String[] args) {
        try{
            System.err.println("Driver loaded successfully");
            
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}