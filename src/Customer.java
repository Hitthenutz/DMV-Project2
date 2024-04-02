import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends Car {
    private String name, address;
    private int age;
    private int ssn, confirmationNumber;
    private double debt;

    public static Scanner input = new Scanner(System.in);

    public Customer(String name, String address, int age, int ssn) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.ssn = ssn;
    }
    public Customer(){

    }

    // Getters and setters for customer attributes
    public int getSsn() {
        return ssn;
    }
    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }
    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getDebt() {
        return debt;
    }
    public void addDebt(double debt){
        this.debt += debt;
    } // setter but adding

    public static Customer enterDataCustomer() {
        String name;
        String address;
        int age;
        int ssn;

        boolean inputIsValid = false;

        do {
            try { // Try Catch for error handling
                System.out.println("Please complete a small questionnaire before proceeding ");
                System.out.println("Enter name (First&Last no space):");
                name = input.nextLine().trim();
                                        //word|space|word
                if (!name.matches("[a-zA-Z]+\\s[a-zA-Z]+")) {//matches one or more letters (first name & last name) Middle is a space.
                    throw new InputMismatchException("Name must contain only letters.\n");
                }

                System.out.println("Enter address:");
                address = input.nextLine().trim();

                if (!address.matches("\\d{4}\\s[a-zA-Z]*")) {//only 4 numbers and all letters
                    throw new InputMismatchException("Address must start with four numbers followed by characters.\n");
                }

                System.out.println("Enter age:");
                age = input.nextInt();
                if (!(age > 1)) {
                    throw new InputMismatchException("Address must start with four numbers followed by characters.\n");
                }

                System.out.println("Enter SSN:");
                ssn = input.nextInt();

                inputIsValid = true; // All inputs are valid, break out of the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: " + e.getMessage() + "\n");
                name = null; // Reset name
                address = null; // Reset address
                age = 0; // Reset age
                ssn = 0; // Reset ssn
            }
        } while (!inputIsValid);

        return new Customer(name, address, age, ssn);
    }

    public Customer searchCustomer(){
        boolean found;




        return null;
    }


    public boolean checkConfirmationNumber(int confirmationNumber){ // checks if customers have same confirmation number
        return this.confirmationNumber == confirmationNumber;
    }

    // Overriding toString() method to provide custom string representation of Customer object
    @Override
    public String toString() {

        return  "Customer Name: " + name +
                "\nCustomer Address: "+address +
                "\nCustomer Age: " + age +
                "\nCustomer SSN: " + ssn +
                "\nCustomer Confirmation Number: " + confirmationNumber;
    }
}
