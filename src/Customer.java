import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
    private static String name;
    private static String address;
    private static int age;
    private static int ssn, confirmationNumber;
    private static double debt;
    private static Car car; //Aggregation Relationship "has a"
    //Customer "has a"

    public static Scanner input = new Scanner(System.in);

    public Customer(String name, String address, int age, int ssn, Car car) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
        Customer.car = car;
    }
    public Customer(){

    }


    // Getters and setters for customer attributes
    public int getSsn() {
        return ssn;
    }
    public void setSsn(int ssn) {
        Customer.ssn = ssn;
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
    public static Customer login(ArrayList<Customer> c, int ssn){
        //log in with ssn
        return searchCustomer(c,ssn);
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

    public static Customer searchCustomer(ArrayList<Customer> customers, int ssn){
        for (Customer customer : customers) {
            if (customer.getSsn() == ssn) {
                return customer; // Found the customer, return it
            }
        }
        System.out.println("Customer Not Found");
        return null;
    }


    public boolean checkConfirmationNumber(int confirmationNumber) {
        // File path
        String filePath = "customerInfo.txt";

        try {
            // Create a FileReader object
            FileReader fileReader = new FileReader(filePath);

            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the file line by line until reaching the end
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split each line by whitespace to extract confirmation number
                String[] parts = line.split("\\s+");
                if (parts.length > 0) {
                    // Convert the confirmation number from String to int
                    int currConfirmationNumber = Integer.parseInt(parts[0]);

                    // Check if the confirmation number matches
                    if (currConfirmationNumber == confirmationNumber) {
                        bufferedReader.close();
                        return true; // Confirmation number found
                    }
                }
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");

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
