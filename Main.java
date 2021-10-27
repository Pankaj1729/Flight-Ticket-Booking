import java.util.*;

public class Main{
    public static void main(String[] args) {
        
        //User Input
        Scanner sc=new Scanner(System.in);

        System.out.println("Welcome to Flight Ticket Booking!");
        int option=0;
        while(true){

            System.out.println("1.Print Flight Details.");
            System.out.println("2.Book Ticket.");
            System.out.println("3.Cancel Ticket.");
            System.out.println("4.Available Seat.");
            System.out.println("5.Seat Detail with meal ordered detail.");
            System.out.println("6.Individual Detail.");

            //Enter your choice number
            System.out.println("Enter your choice:");
            option=Integer.parseInt(sc.nextLine());
           
            switch(option){
                case 1:
                FlightDetail detail=new FlightDetail();
                detail.printDetail();
                break;
                case 2:
                BookTickect booking=new BookTickect();
                Boolean search=booking.checkAvailability(sc);
                if(!search){
                    System.out.println("Flight Not Available.");
                }
                else{
                    System.out.println("Choose Flight: ");
                    int choice=sc.nextInt();
                    try {
                        
                        booking.availableTickect(choice);
                        System.out.println("Select Class: ");
                        int flightClass=sc.nextInt();

                        
                        booking.seatDescription(choice,flightClass);
                        System.out.println("Enter Number seat booked: ");
                        int bookingCount=sc.nextInt();
                        
                        
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }

            }
        }


    }
}