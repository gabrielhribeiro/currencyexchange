package exchange.datarib;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataBaseConnection {
    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "password";

    //coonection with database
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void DataPreparation(){
        Jsonconnection requests = new Jsonconnection("default");
        requests.ClientRequest("https://api.exchangeratesapi.io/latest");

        Set<String> ReqVariation = requests.getRates().keySet();
        System.out.println(ReqVariation);

        String e = requests.getDate();
        String s = "2018-03-09";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        System.out.println(end);
        System.out.println(start);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            System.out.println(start);
            totalDates.add(start);
            System.out.println(start);
            for (String element : ReqVariation) {
                System.out.println("element:"+element);
                System.out.println("reqva:"+ReqVariation);
                requests.ClientRequest("https://api.exchangeratesapi.io/"+ start +"?base=" + element);
                Set<String> iItembyRequest = requests.getRates().keySet();
                for (String value: iItembyRequest){
                    System.out.println("value:" + requests.getRates().get(value) + ",coin:" + value + ",base:" + requests.getBase()+ ",data:"  + requests.getDate());
                    InsertTables((Double) requests.getRates().get(value), value, requests.getBase(),requests.getDate());
                }
            }
            start = start.plusDays(1);
        }
    }

    //get value from function and prepear data to create date
    public void DatabaseValidation() {
        Jsonconnection requests = new Jsonconnection("default");
        requests.ClientRequest("https://api.exchangeratesapi.io/latest");

        Set<String> ReqVariation = requests.getRates().keySet();
        System.out.println(ReqVariation);

        for (String element : ReqVariation) {
            CreateTables(element);
            System.out.println(element);
        }
    }

    //create table if exists do not create
    public void CreateTables(String TbValue) {
        String SQL = "CREATE TABLE IF NOT EXISTS "
                + TbValue
                + "(the_id  serial PRIMARY KEY,"
                + "value NUMERIC,"
                + "coin VARCHAR(4),"
                + "base VARCHAR(4),"
                + "date DATE,"
                + "posting_date DATE NOT NULL DEFAULT CURRENT_DATE,"
                + "unique (value, coin, base, date))";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();

                //count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void InsertTables( double valuexc, String coins, String basec, String dates) {
        System.out.println( valuexc + "," + coins + "," + basec + "," +  dates );
        String SQL = ("INSERT INTO public." + basec +"(value,coin,base, date) VALUES (" +
                + valuexc + ",'" + coins + "','" + basec + "','" + dates + "')");

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();

            //count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


