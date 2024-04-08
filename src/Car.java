import java.util.Scanner;

public class Car {
    private String plate,make, model, vin;
    private int year;

    private static final Scanner input = new Scanner(System.in);

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
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public Car(String plate, String make, String model, String vin, int year) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.year = year;
    }
    public Car(){

    }
    public static Car enterDataCar(){

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
        input.nextLine();
        System.out.println("Enter VIN:");
        //17Characters(letters & numbers)
        String vin = input.nextLine();
        return new Car(plate,make,model,vin,year);
    }
    @Override
    public String toString() {
        //License Plate:
        //Vin:
        //Make:
        //Model:
        //Year:
        //SSN:
        return "License Plate: " + plate +
                "\nVIN: "+ vin +
                "\nMake: "+make +
                "\nModel: " + model +
                "\nYear: " + year +
                "\nSSN: "; //can be removed
    }
}
