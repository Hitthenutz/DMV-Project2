import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
    private String name, address, plate, make, model;
    private int age;
    private int ssn;
    private String vin;
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
    public Customer(String plate, String make, String model, int year, String vin) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }

    // Getters and setters for customer attributes
    public int getSsn() {
        return ssn;
    }
    public void setSsn(int ssn) {
        this.ssn = ssn;
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
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public double getDebt() {
        return debt;
    }
    public void setDebt(double debt) {
        this.debt = debt;
    }
    public void addDebt(double debt){
        this.debt += debt;
    }

    public static Customer enterDataCustomer() {
        String name;
        String address;
        int age;
        int ssn;


        boolean inputIsValid = false;

        do {
            try {
                System.out.println("Enter name (First&Last no space):");
                name = input.nextLine().trim();

                if (!name.matches("[a-zA-Z]+")) {//only letters
                    throw new InputMismatchException("Name must contain only letters.");
                }

                System.out.println("Enter address:");
                address = input.nextLine().trim();

                if (!address.matches("\\d{4}[a-zA-Z]*")) {//only 4 numbers and all letters
                    throw new InputMismatchException("Address must start with four numbers followed by characters. Please Restart");
                }

                System.out.println("Enter age:");
                age = input.nextInt();

                System.out.println("Enter SSN:");
                ssn = input.nextInt();

                inputIsValid = true; // All inputs are valid, break out of the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: " + e.getMessage());
                //input.nextLine(); // Consume the invalid input
                name = null; // Reset name
                address = null; // Reset address
                age = 0; // Reset age
                ssn = 0; // Reset ssn
            }
        } while (!inputIsValid);

        return new Customer(name, address, age, ssn);
    }
    public static Customer enterDataCar(){

        //ERROR HANDLING NEED

        System.out.println("Enter plate number:");
        //7 digit number
        String plate = input.nextLine();
        //?list all makes possible!
        System.out.println("Enter make:");
        String make = input.nextLine();
        System.out.println("Enter model:");
        //list all models of each car
        String model = input.nextLine();
        System.out.println("Enter year:");
        int year = input.nextInt();
        System.out.println("Enter VIN:");
        //17Characters(letters & numbers)
        String vin = input.nextLine();
        return new Customer(plate,make,model,year,vin);
    }

    // Overriding toString() method to provide custom string representation of Customer object
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Age: " + age;
    }
}
