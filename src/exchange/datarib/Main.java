package exchange.datarib;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Jsonconnection requests = new Jsonconnection("default");
        requests.ClientRequest("https://api.exchangeratesapi.io/latest?base=EUR");
        //System.out.println(requests.getDate());
        //System.out.println(requests.getRates());
        // System.out.println(requests.getBase());
        //requests.getRates().keySet().forEach(System.out::println);
        // String[] arrData = requests.getRates().keySet();

    //    System.out.println(requests.getRates().get("HRK"));
      //  System.out.println(requests.getRates().keySet());

     //   Set<String> jsonArray = requests.getRates().keySet();


        Set<String> ReqVariation = requests.getRates().keySet();
        for (String element : ReqVariation) {
            requests.ClientRequest("https://api.exchangeratesapi.io/latest?base=" + element);
           // System.out.println(requests.getRates().get(element));
            System.out.println(element);
        }









  //      System.out.println(jsonArray);
  //      for (String element : jsonArray) {
  //          System.out.println(requests.getRates().get(element));
  //          System.out.println(element);
  //      }
    }
}
