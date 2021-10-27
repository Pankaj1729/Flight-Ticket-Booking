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

    static HashMap<String,ArrayList<ArrayList<String>>> flightDescription=new HashMap<String,ArrayList<ArrayList<String>>>();

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
                System.out.print("Available Tickect in Business Class: ");
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
                    System.out.println("Available Tickect in Business Class: " + count);
                    k++;
                } else {
                    count += ((Integer.parseInt(String.valueOf(strArray[0].charAt(9))) + Integer.parseInt(strArray[1])
                            + Integer.parseInt(String.valueOf(strArray[2].charAt(0))))
                            * (Integer.parseInt(strArray[3])));
                    System.out.println("Available Tickect in Economy Class: " + count);
                }
            }
        }
        br.close();
    }

    void  seatDescription(int choice,int flightClass){
        String flightKey="";
        if(flightClass==1)
        flightKey+=availableFlight.get(choice-1).substring(7, 11)+"B";
        else
        flightKey+=availableFlight.get(choice-1).substring(7, 11)+"E";
        if(!flightDescription.containsKey(flightKey)){
            ArrayList<ArrayList<String>> tempArr=new ArrayList<ArrayList<String>>();
            try{
                String path = "C:\\Users\\pankaj agrawal\\Flight Ticket Booking\\Flight Ticket Booking\\Flights\\"
                + availableFlight.get(choice - 1);
                String[] line=Files.readAllLines(Paths.get(path)).get(flightClass).split(",");
                int a,b,c,d;
                if(flightClass==2){

                    a=Integer.parseInt(String.valueOf(line[0].charAt(9)));
                    b=Integer.parseInt(String.valueOf(line[1]));
                    c=Integer.parseInt(String.valueOf(line[2]));
                    d=Integer.parseInt(String.valueOf(line[3]));

                }
                else{
                    a=Integer.parseInt(String.valueOf(line[0].charAt(10)));
                    b=Integer.parseInt(String.valueOf(line[1]));
                    c=Integer.parseInt(String.valueOf(line[2]));
                    d=Integer.parseInt(String.valueOf(line[3]));
                }
                for(int i=0;i<3;i++){
                    int p;
                    if(i==0){
                        p=a;
                    }
                    else if(i==1){
                        p=b;
                    }
                    else{
                        p=c;
                    }
                    ArrayList<String> arr=new ArrayList<String>();
                    for(int j=0;j<p;j++){
                        if(i==0){
                            if(j==0){
                                arr.add("W");
                            }
                            else if(j==p-1){
                                arr.add("A");
                            }
                            else{
                                arr.add("M");
                            }
                        }
                        if(i==1){
                            if(j==0){
                                arr.add("A");
                            }
                            else if(j==p-1){
                                arr.add("A");
                            }
                            else{
                                arr.add("M");
                            }
                        }
                        if(i==2){
                            if(j==0){
                                arr.add("A");
                            }
                            else if(j==p-1){
                                arr.add("W");
                            }
                            else{
                                arr.add("M");
                            }
                        }
                    }
                    tempArr.add(arr);
                }

                for(int i=0;i<d-1;i++){
                    tempArr.add(tempArr.get(0));
                }
                flightDescription.put(flightKey,tempArr);
            }
            catch (Exception e) {
                //TODO: handle exception
            }
        }
        ArrayList<ArrayList<String>> desc=flightDescription.get(flightKey);
        for(int i=0;i<desc.size();i++){
            for(int j=0;j<desc.get(i).size();j++){
                System.out.print(desc.get(i).get(j)+" ");
            }
            System.out.println();
        }   
    }
}
