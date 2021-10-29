import java.util.*;

public class Main{
    public static void main(String[] args) {
        
        //User Input
        Scanner sc=new Scanner(System.in);

        System.out.println("Welcome to Flight Ticket Booking!");
        int option=0;
        while(true){

            BookTickect booking=new BookTickect();
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

                        
                        String flightKey=booking.seatDescription(choice,flightClass);
                        System.out.println("1. W:Window Seat\n2. M:Middle Seat\n3. A:Aisle Seat\n4. B:Already Booked");
                        System.out.println("Enter Number seat booked: ");
                        int bookingCount=sc.nextInt();
                        sc.nextLine();
                        System.out.println("Select Seat Number: ");
                        ArrayList<String> arr=new ArrayList<>();
                        for(int i=0;i<bookingCount;i++){
                            String seatNumber=sc.nextLine();
                            arr.add(seatNumber);
                        }
                        System.out.println("Do you want meal:\n1.Yes\n2.No");
                        int mealChoice=sc.nextInt();


                        booking.bookSeat(bookingCount,arr,choice,flightClass,mealChoice,flightKey);
                        
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
                break;
                case 3:
                System.out.println("Enter your booking id:");
                String bookingId=sc.nextLine();
                booking.cancelBooking(bookingId);
                break;
                case 4:
                booking.availableFlight();
                System.out.println("Enter Choice");
                int choice=sc.nextInt();
                booking.availabeTickect(choice);
                break;
                case 5:
                booking.mealDetail();
                break;
                case 6:
                System.out.println("Enter Booking ID:");
                String id=sc.nextLine();
                booking.individualDetail(id);
                break;
                default:
                System.out.println("Choose correct option!");
                break;


            }
        }


    }
}