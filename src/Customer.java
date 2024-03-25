import java.util.Scanner;

public class Customer {
    private String name, address, plate, make, model;
    private int age, ssn, vin, year;

    public static Scanner input = new Scanner(System.in);

    public Customer(String name, String address, int age, int ssn) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.ssn = ssn;
    }
    public Customer(){

    }

    public Customer(String plate, String make, String model, int year, int vin) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
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

    public static Customer enterDataCustomer(){

        System.out.println("Enter name:");
        String name = input.nextLine();
        System.out.println("Enter address:");
        String address = input.nextLine();
        System.out.println("Enter age:");
        int age = input.nextInt();
        System.out.println("Enter SSN");
        int ssn = input.nextInt();

        return new Customer(name, address, age, ssn);
    }
    public static Customer enterDataCar(){
        System.out.println("Enter plate number:");
        String plate = input.nextLine();
        System.out.println("Enter make:");
        String make = input.nextLine();
        System.out.println("Enter model:");
        String model = input.nextLine();
        System.out.println("Enter year:");
        String year = input.nextLine();
        System.out.println("Enter VIN:");
        int vin = input.nextInt();
        return new Customer(plate,make,model,year,vin);
    }

    // Overriding toString() method to provide custom string representation of Customer object
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Age: " + age;
    }
}