import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Customer {
    private static String name;
    private static String address;
    private static int age;
    private static int ssn, confirmationNumber;
    private static double debt;
    private static Car car; //Aggregation Relationship "has a"
    //Customer "has a"

    private final Random random = new Random();

    public static Scanner input = new Scanner(System.in);

    public Customer(String name, String address, int age, int ssn, Car car) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
        Customer.car = car;
    }

    public Customer() {

    }


    // Getters and setters for customer attributes
    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        Customer.ssn = ssn;
    }

    public int generateConfirmationNumber() { //generates random cNum, if already taken, will do loop until a number is found that is not taken
        int r = random.nextInt(100_000, 1_000_000);
        while (!checkConfirmationNumber(r)) {
            r = random.nextInt(100_000, 1_000_000);
        }
        return r;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }
    public void setConfirmationNumber(int confirmationNumber) {
        Customer.confirmationNumber = confirmationNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        Customer.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        Customer.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        Customer.age = age;
    }
    public double getDebt() {
        return debt;
    }
    public void addDebt(double debt){
        Customer.debt += debt;
    } // setter but adding
    public static Customer register(){
        Customer customer;
        customer = enterDataCustomer();
        return customer;
    }
    public static Customer login(int ssn){
        //log in with ssn
        return searchCustomer(ssn);
    }

    public static Customer enterDataCustomer() {

        boolean inputIsValid = false;

        do {
            try { // Try Catch for error handling

                /**NAME**/
                System.out.println("Enter name (First&Last no space):\n");
                name = input.nextLine().trim();
                                        //word|space|word
                if (!name.matches("[a-zA-Z]+\\s[a-zA-Z]+")) {//matches one or more letters (first name & last name) Middle is a space.
                    throw new InputMismatchException("Name must contain only letters.\n");
                }
                System.out.println(name);

                /**ADDRESS**/
                System.out.println("Enter address:");
                address = input.nextLine().trim();

                if (!address.matches("\\d{4,5}\\s[a-zA-Z]*")) {//only 4 numbers and all letters
                    throw new InputMismatchException("Address must start with four numbers followed by characters.\n");
                }
                System.out.println(address);

                /**AGE**/
                System.out.println("Enter age:");
                age = input.nextInt();
                if (!(age > 1)) {
                    throw new InputMismatchException("Address must start with 4-5 digits followed by characters.\n");
                }
                System.out.println(age);

                /**SSN**/
                System.out.println("Enter SSN:");
                ssn = input.nextInt();
                System.out.println(ssn);

                inputIsValid = true; // All inputs are valid, break out of the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: " + e.getMessage() + "\n");
                name = null; // Reset name
                address = null; // Reset address
                age = 0; // Reset age
                ssn = 0; // Reset ssn
            }
        } while (!inputIsValid);

        return new Customer(name, address, age, ssn, car);
    }

    public static Customer searchCustomer(int ssn){//Searches for the customer in the txt doc
        Files fileHandler = new Files(new File("customerInfo.txt"));

        try {
            String content = fileHandler.read();
            String[] lines = content.split("\\n");
            for (String line : lines) {
                String[] parts = line.split(": ");
                if (parts.length == 6) { // Assuming each line has exactly 6 parts
                    String name = parts[1].trim();
                    String address = parts[3].trim();
                    int age = Integer.parseInt(parts[5].trim());
                    System.out.println(parts[0].trim());
                    System.out.println(parts[1].trim());
                    System.out.println(parts[2].trim());
                    System.out.println(parts[3].trim());
                    System.out.println(parts[4].trim());
                    System.out.println(parts[5].trim());

                    int customerSSN = Integer.parseInt(parts[2].trim());
                    int confirmationNumber = Integer.parseInt(parts[5].trim());
                    if (customerSSN == ssn) {
                        return new Customer(name, address, age, customerSSN, null); // Creating a Customer object and returning it
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer format: " + e.getMessage());
        }

        System.out.println("Customer Not Found");
        return null; // Customer not found or error occurred
    }


    /****ERROR******/
    public boolean checkConfirmationNumber(int confirmationNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader("customerInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String fieldName = parts[0].trim();
                    if (fieldName.equals("Customer Confirmation Number")) {
                        int currConfirmationNumber = Integer.parseInt(parts[1].trim());
                        if (currConfirmationNumber == confirmationNumber) {
                            return true; // Confirmation number found
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return false; // Confirmation number not found
    }

    // Overriding toString() method to provide custom string representation of Customer object
    @Override
    public String toString() {

        return  "Customer Name: " + name +
                "\nCustomer Address: "+address +
                "\nCustomer Age: " + age +
                "\nCustomer SSN: " + ssn +
                "\nCustomer Confirmation Number: " + confirmationNumber + "\n";
    }
}
