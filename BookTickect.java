import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BookTickect {
    static ArrayList<String> availableFlight = new ArrayList<>();
    static int economyBase = 1000;
    static int businessBase = 2000;
    static int extraCharge = 100;
    static int priceIncreaseEconomy = 100;
    static int priceIncreaseBusiness = 200;
    static int meal = 200;
    static int cancellationPrice = 200;

    static HashMap<String, ArrayList<ArrayList<String>>> flightDescription = new HashMap<String, ArrayList<ArrayList<String>>>();
    static HashMap<String, ArrayList<String>> customerDetail = new HashMap<String, ArrayList<String>>();

    static boolean onlyBusiness(String name) throws Exception {
        String path = "C:\\Users\\pankaj agrawal\\Flight Ticket Booking\\Flight Ticket Booking\\Flights\\" + name;
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;
        int i = 0;
        while ((str = br.readLine()) != null) {
            i++;

        }
        br.close();
        if (i == 1) {
            return true;
        }
        return false;
    }

    boolean checkAvailability(Scanner sc) {
        System.out.println("Enter Source:");
        String source = sc.nextLine();
        System.out.println("Enter Destination");
        String destination = sc.nextLine();
        File directory = new File("C:\\Users\\pankaj agrawal\\Flight Ticket Booking\\Flight Ticket Booking\\Flights");
        String[] flightName = directory.list();

        for (String name : flightName) {
            if (name.contains(source + "-" + destination)) {
                availableFlight.add(name);
            }
        }

        if (availableFlight.size() > 0) {
            System.out.println("Available Flights:");
            int x = 1;
            for (String name : availableFlight) {
                try {
                    if (onlyBusiness(name))
                        System.out.println(x + "." + name + " -Only Business Class Avaiable");
                    else
                        System.out.println(x + "." + name);
                    x++;
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println();
                }
            }
            return true;
        }
        return false;
    }

    void availableTickect(int choice) throws Exception {
        int count = 0;
        String path = "C:\\Users\\pankaj agrawal\\Flight Ticket Booking\\Flight Ticket Booking\\Flights\\"
                + availableFlight.get(choice - 1);
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        if (onlyBusiness(availableFlight.get(choice - 1))) {
            while ((str = br.readLine()) != null) {
                String[] strArray = str.split(",");
                System.out.print("1. Available Tickect in Business Class: ");
                count += ((Integer.parseInt(String.valueOf(strArray[0].charAt(10))) + Integer.parseInt(strArray[1])
                        + Integer.parseInt(String.valueOf(strArray[2].charAt(0)))) * (Integer.parseInt(strArray[3])));
                System.out.println(count);
            }
        } else {
            int k = 0;
            while ((str = br.readLine()) != null) {
                String[] strArray = str.split(",");

                if (k == 0) {
                    count += ((Integer.parseInt(String.valueOf(strArray[0].charAt(10))) + Integer.parseInt(strArray[1])
                            + Integer.parseInt(String.valueOf(strArray[2].charAt(0))))
                            * (Integer.parseInt(strArray[3])));
                    System.out.println("1. Available Tickect in Business Class: " + count);
                    k++;
                } else {
                    count += ((Integer.parseInt(String.valueOf(strArray[0].charAt(9))) + Integer.parseInt(strArray[1])
                            + Integer.parseInt(String.valueOf(strArray[2].charAt(0))))
                            * (Integer.parseInt(strArray[3])));
                    System.out.println("2. Available Tickect in Economy Class: " + count);
                }
            }
        }
        br.close();
    }

    String seatDescription(int choice, int flightClass) {
        String flightKey = "";
        if (flightClass == 1)
            flightKey += availableFlight.get(choice - 1).substring(7, 11) + "B";
        else
            flightKey += availableFlight.get(choice - 1).substring(7, 11) + "E";
        if (!flightDescription.containsKey(flightKey)) {
            ArrayList<ArrayList<String>> tempArr = new ArrayList<ArrayList<String>>();
            try {
                String path = "C:\\Users\\pankaj agrawal\\Flight Ticket Booking\\Flight Ticket Booking\\Flights\\"
                        + availableFlight.get(choice - 1);
                String[] line = Files.readAllLines(Paths.get(path)).get(flightClass - 1).split(",");

                int a, b, c, d;
                if (flightClass == 2) {

                    a = Integer.parseInt(String.valueOf(line[0].charAt(9)));

                    b = Integer.parseInt(String.valueOf(line[1]));

                    c = Integer.parseInt(String.valueOf(line[2].charAt(0)));

                    d = Integer.parseInt(String.valueOf(line[3]));

                } else {
                    a = Integer.parseInt(String.valueOf(line[0].charAt(10)));

                    b = Integer.parseInt(String.valueOf(line[1]));

                    c = Integer.parseInt(String.valueOf(line[2].charAt(0)));

                    d = Integer.parseInt(String.valueOf(line[3]));

                }
                // System.out.println(a+" "+b+" "+c+" "+d);

                for(int k=0;k<d;k++){
                    ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    int p;
                    if (i == 0) {
                        p = a;
                    } else if (i == 1) {
                        p = b;
                    } else {
                        p = c;
                    }
                    for (int j = 0; j < p; j++) {
                        if (i == 0) {
                            if (j == 0) {
                                arr.add("W");
                            } else if (j == p - 1) {
                                arr.add("A");
                            } else {
                                arr.add("M");
                            }
                        }
                        if (i == 1) {
                            if (j == 0) {
                                arr.add("A");
                            } else if (j == p - 1) {
                                arr.add("A");
                            } else {
                                arr.add("M");
                            }
                        }
                        if (i == 2) {
                            if (j == 0) {
                                arr.add("A");
                            } else if (j == p - 1) {
                                arr.add("W");
                            } else {
                                arr.add("M");
                            }
                        }
                    }
                }
                tempArr.add(arr);
            }
                flightDescription.put(flightKey, tempArr);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        ArrayList<ArrayList<String>> desc = flightDescription.get(flightKey);
        for (int i = 0; i < desc.size(); i++) {
            for (int j = 0; j < desc.get(i).size(); j++) {
                if (desc.get(i).get(j) == "A" && desc.get(i).get(j + 1) == "A")
                    System.out.print(desc.get(i).get(j) + "     ");
                else
                    System.out.print(desc.get(i).get(j) + " ");
            }
            System.out.println();
        }
        return flightKey;
    }

    void bookSeat(int bookingCount, ArrayList<String> arr, int choice, int flightClass, int mealChoice,
            String flightKey) {
        int aisleOrWind = 0;
        for (int i = 0; i < bookingCount; i++) {
            String temp = arr.get(i);
            int row = Integer.parseInt(String.valueOf(temp.charAt(0)));
            int p = temp.charAt(2);
            int col = p - (int) ('A')+1;
            if (flightDescription.get(flightKey).get(row - 1).get(col - 1) == "A"
                    || flightDescription.get(flightKey).get(row - 1).get(col - 1) == "W")
                aisleOrWind++;
            arr.set(i, arr.get(i)+flightDescription.get(flightKey).get(row - 1).get(col - 1));
            flightDescription.get(flightKey).get(row-1).set(col-1, "B");

        }
        int totalAmount = 0;
        if (flightClass == 1)
            totalAmount = (businessBase * bookingCount) + (aisleOrWind * extraCharge);
        else
            totalAmount = (economyBase * bookingCount);
        if (mealChoice == 1)
            totalAmount += (meal * bookingCount);
        businessBase += 200;
        economyBase += 100;
        String bookingId = flightKey.substring(0, 4) + Integer.toString(customerDetail.size() + 1);
        System.out.println("Total Amount: " + totalAmount + "\nYour Booking Id: " + bookingId);
        arr.add(Integer.toString(totalAmount));
        if (mealChoice == 1)
            arr.add("Yes");
        else
            arr.add("No");
        arr.add(flightKey);
        customerDetail.put(bookingId, arr);
        System.out.println("Tickect Booked Successfully!");
    }

    void cancelBooking(String bookingId){
        ArrayList<String> tempArr=customerDetail.get(bookingId);
        int payAmount=Integer.parseInt(tempArr.get(tempArr.size()-3));
        String flightKey=tempArr.get(tempArr.size()-1);
        payAmount-=(cancellationPrice*(tempArr.size()-3));
        for (int i = 0; i < tempArr.size()-2; i++) {
            String temp = tempArr.get(i);
            int row = Integer.parseInt(String.valueOf(temp.charAt(0)));
            int p = temp.charAt(2);
            int col = p - (int) ('A')+1;
           
            flightDescription.get(flightKey).get(row-1).set(col-1, Character.toString(temp.charAt(3)));

        }
        System.out.println("Amount Refund: "+payAmount);
        customerDetail.remove(bookingId);
    }

    void mealDetail(){

        for(String string : customerDetail.keySet()) {
            ArrayList<String> arr=customerDetail.get(string);
            if(arr.get(arr.size()-2)=="Yes"){
                System.out.println("Customer with Id: "+string+" take the meal for seat number");
                for(int i=0;i<arr.size()-3;i++){
                    System.out.print(arr.get(i).substring(0, 3)+" ");
                }
                System.out.println();
            }
        }
    }

    void individualDetail(String id){
        ArrayList<String> arr=customerDetail.get(id);
           
                System.out.println("Customer with Id: "+string+" book seat numbers are");
                for(int i=0;i<arr.size()-3;i++){
                    System.out.print(arr.get(i).substring(0, 3)+" ");
                }
                System.out.println();
                if(arr.get(arr.size()-2)=="Yes"){
                    System.out.println("Customer take meal.");
                }
                else{
                    System.out.println("Customer does'nt take meal");
                }
                
    }
}
