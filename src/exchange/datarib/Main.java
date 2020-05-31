package exchange.datarib;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import static com.sun.tools.javac.util.Constants.format;

public class Main {
    public static void main(String[] args) {
        Jsonconnection requests = new Jsonconnection("default");
        requests.ClientRequest("https://api.exchangeratesapi.io/latest");
        System.out.println(requests.getDate());
        System.out.println(requests.getRates());
        System.out.println(requests.getBase());
        //requests.getRates().keySet().forEach(System.out::println);
        // String[] arrData = requests.getRates().keySet();

    //    System.out.println(requests.getRates().get("HRK"));
      //  System.out.println(requests.getRates().keySet());

     //   Set<String> jsonArray = requests.getRates().keySet();
        Set<String> ReqVariation = requests.getRates().keySet();
        String e = requests.getDate();
        String s = "2018-03-01";
       // String e = "2020-05-01";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
            for (String element : ReqVariation) {
                requests.ClientRequest("https://api.exchangeratesapi.io/"+ start +"?base=" + element);
                // System.out.println(requests.getRates().get(element));
                System.out.println(element);
                System.out.println(ReqVariation);
            }
            System.out.println(start);
        }
    }
}
