import java.io.File;

public class FlightDetail {
    void printDetail(){
        File directory=new File("C:\\Users\\pankaj agrawal\\Flight Ticket Booking\\Flight Ticket Booking\\Flights");
        int flightCount=directory.list().length;
        String[] flightName=directory.list();
        System.out.println("Number of Flights: "+flightCount);
        System.out.println("Flight Names:");
        for(String name:flightName){
            System.out.println(name);
        }
    }
}
