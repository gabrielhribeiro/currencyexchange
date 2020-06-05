package exchange.datarib;

public class Main {
    public static void main(String[] args) {
        DataBaseConnection connect = new DataBaseConnection();
        //connection with database
        connect.connect();
        //Verify if all tables exist
        connect.DatabaseValidation();
        DataBaseConnection DataPreparation = new DataBaseConnection();
        DataPreparation.DataPreparation();






    }
}