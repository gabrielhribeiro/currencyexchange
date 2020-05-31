package exchange.datarib;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Jsonconnection requests = new Jsonconnection("default");
        requests.ClientRequest("https://api.exchangeratesapi.io/latest");

        Set<String> ReqVariation = requests.getRates().keySet();
        System.out.println(ReqVariation);
        String e = requests.getDate();
        String s = "2018-03-01";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
            for (String element : ReqVariation) {
                requests.ClientRequest("https://api.exchangeratesapi.io/"+ start +"?base=" + element);
                Set<String> iItembyRequest = requests.getRates().keySet();
                System.out.println(requests.getDate());
                System.out.println(requests.getBase());
                for (String value: iItembyRequest){
                    System.out.println(
                              "valor:" + requests.getRates().get(value)
                            + ",coin:" + value
                            + ",base:" + requests.getBase()
                            + ",data"  + requests.getDate());
                }
            }
        }
    }
}