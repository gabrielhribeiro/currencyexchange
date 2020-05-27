package exchange.datarib;

public class Main {
    public static void main(String[] args) {
        Jsonconnection requests = new Jsonconnection("default");
        requests.ClientRequest("https://api.exchangeratesapi.io/latest?base=BRL");
        System.out.println(requests.getDate());
        System.out.println(requests.getRates());
        System.out.println(requests.getBase());



    }
}
