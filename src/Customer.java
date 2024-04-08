import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;

public class Customer {
    private static String name;
    private static String address;
    private static int ssn, confirmationNumber, age;
    private static double debt;
    private static Car car; //Aggregation Relationship "has a"
    //Customer "has a"
    public static LocalDate licenseExpirationDate, registrationExpirationDate; // Static field for license expiration
    private final Random random = new Random(System.currentTimeMillis());
    //Because it generates the random # very fast, it could use the same seed, meaning it will generate the same random int
    //you ensure that it uses a different seed each time the method is called, reducing the likelihood of generating
    //the same confirmation number repeatedly.

    public static Scanner input = new Scanner(System.in);

    public Customer(String name, String address, int age, int ssn, Car car) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
        Customer.car = car;
    }

    public Customer(String name, String address, int age, int ssn, double debt) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
        Customer.debt = debt;
    }

    public Customer(String name, String address, int age, int ssn, double debt, int confirmationNumber) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
        Customer.debt = debt;
        Customer.confirmationNumber = confirmationNumber;
    }

    public Customer() {

    }

    // Static methods related to license management
    public static void setLicenseExpirationDate(LocalDate date) {
        licenseExpirationDate = date;
    }

    // Static methods related to license management
    public static void renewLicense() {
        licenseExpirationDate = LocalDate.now().plusYears(5);
        System.out.println("License/ID renewed. New expiration date: " + licenseExpirationDate);
    }
    // Static method related to car management
    public static void renewRegistration(){
        registrationExpirationDate = LocalDate.now().plusYears(1);
       System.out.println("Registration Renewed till: "+registrationExpirationDate);
    }

    // Getters and setters for customer attributes
    public int getSsn() {
        return ssn;
    }
    public void setSsn(int ssn) {
        Customer.ssn = ssn;
    }
    public static LocalDate getLicenseExpirationDate() {
        return licenseExpirationDate;
    }
    public static LocalDate getRegistrationExpirationDate() {
        return registrationExpirationDate;
    }
    public static void setRegistrationExpirationDate(LocalDate registrationExpirationDate) {
        Customer.registrationExpirationDate = registrationExpirationDate;
    }
    public static Car getCar() {
        return car;
    }
    //searches the Car File for the Customer's Car USING THEIR SSN
    public Car getSearchedCustomerCar() {
        //Use this method to search for ssn
        if (this.getSsn() == ssn) {
            Files fileHandler = new Files(new File("CarInfo.txt"));
            String plate, make, model, vin;
            int year;
            try {
                String content = fileHandler.read();

                String[] lines = content.split("\\n");

                // Assuming confirmation number is on line 5 (index 4), you can split it using ":"
                //multiplier of every info = 6
                int fileTraverseSSN = 6;
                //the first ssn


                //lines.length for however many customers there are
                //i+=7 for every 7 lines is the ssn number
                for (int i = fileTraverseSSN - 1; i < lines.length; i += 7) { //the line of every ssn = i
                    String[] parts = lines[i].split(":");

                    if (parts.length == 2) {
                        String ssn1 = parts[1].trim();

                        if (ssn1.equals(String.valueOf(ssn))) { // ssn found at line 6
                            //In here we found ssn, now need to find other Customer data using ssn
                            //Return customer with verified ssn

                            //License Plate:wa
                            //Vin:wadawd
                            //Make:wad
                            //Model:wada
                            //Year:5
                            //SSN:1233

                            plate = (lines[i - 5].split(":")[1].trim());

                            vin = lines[i - 4].split(":")[1].trim();

                            make = (lines[i - 3].split(":")[1].trim());

                            model = lines[i - 2].split(":")[1].trim();

                            year = Integer.parseInt(lines[i - 1].split(":")[1].trim());

                            ssn1 = lines[i].split(":")[1].trim();

                            System.out.println("Customer Car Found!");
                            System.out.println(new Car(plate,make,model,vin,year, new Customer().setSsn(ssn1)));
                            return new Car(plate, make, model, vin, year);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid integer format: " + e.getMessage());
            }

            System.out.println("Customer Not Found");
            return null;
            //Asserts are because of this
        }
        return null;
    }

public static void setCar(Car car) {
    Customer.car = car;
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

public void addDebt(double debt) {
    Customer.debt += debt;
} // setter but adding

public static Customer enterDataCustomer() {

    boolean inputIsValid = false;

    do {
        try { // Try Catch for error handling

            /**NAME**/
            System.out.println("Enter name (First & Last):"); //said no space, but that causes error changed to avoid confusion
            name = input.nextLine().trim();
            //word|space|word
            if (!name.matches("[a-zA-Z]+\\s[a-zA-Z]+")) {//matches one or more letters (first name & last name) Middle is a space.
                throw new InputMismatchException("Name must contain only letters.\n");
            }
            System.out.println(name); //Error Handle

            /**ADDRESS**/
            System.out.println("Enter address:");
            address = input.nextLine().trim();

            if (!address.matches("\\d{4,5}\\s[a-zA-Z]*\\s[a-zA-Z]+")) {//only 4 numbers and all letters then str/rd/cir
                throw new InputMismatchException("Example: 1111 YourMom Str\n");
            }
            System.out.println(address); //Error Handle

            /**AGE**/
            System.out.println("Enter age:");
            age = input.nextInt();
            if (!(age > 1)) {
                throw new InputMismatchException("Address must start with 4-5 digits followed by characters.\n");
            }
            System.out.println(age); //Error Handle

            /**SSN**/
            System.out.println("Enter SSN:");
            ssn = input.nextInt();
            System.out.println(ssn); //Error Handle

            inputIsValid = true; // All inputs are valid, break out of the loop
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage() + "\n");
            name = null; // Reset name
            address = null; // Reset address
            age = 0; // Reset age
            ssn = 0; // Reset ssn
        }
    } while (!inputIsValid);

    for (int i = 0; i < DMV.customerList.size(); i++) {
        if (new Customer(name, address, age, ssn, debt).equals(DMV.customerList.get(i))) {
            System.out.println("Customer already in System!");
            return DMV.customerList.get(i);
        }
    }


    return new Customer(name, address, age, ssn, 0.0);
}

public static Customer searchCustomer(int ssn) {//Searches for the customer in the txt doc using SSN
    Files fileHandler = new Files(new File("customerInfo.txt"));
    String name;
    String address;
    int age;
    int cNum;
//1        Customer Name: 1 1
//2        Customer Address: 1234 wadaw waw
//3        Customer Age: 12
//4        Customer SSN: 12
//5        Customer Confirmation Number: 123312
//6
//7        Customer Name: 2 2
    try {
        String content = fileHandler.read();

        String[] lines = content.split("\\n");

        // Assuming confirmation number is on line 5 (index 4), you can split it using ":"
        //multiplier of every info = 6
        int fileTraverseSSN = 4;


        //lines.length for however many customers there are
        for (int i = fileTraverseSSN - 1; i < lines.length; i += 7) { //the line of every ssn = i
            String[] parts = lines[i].split(":");

            if (parts.length == 2) {
                String ssn1 = parts[1].trim();


                if (ssn1.equals(String.valueOf(ssn))) {
                    //In here we found ssn, now need to find other Customer data using ssn
                    //Return customer with verified ssn

                    //1 above = age
                    //2 above = address
                    //3 above = name
                    //1 below = confirmation number
                    //2 below = debt

                    name = (lines[i - 3].split(":")[1].trim());
                    System.out.println(name);
                    address = lines[i - 2].split(":")[1].trim();
                    System.out.println(address);
                    cNum = Integer.parseInt(lines[i + 1].split(":")[1].trim());
                    System.out.println(cNum);
                    age = Integer.parseInt(lines[i - 1].split(":")[1].trim());
                    System.out.println(age);
                    debt = Integer.parseInt(lines[i + 2].split(":")[1].trim());
                    System.out.println(debt);

                    System.out.println("Customer Found!");
                    return new Customer(name, address, age, ssn, debt, cNum);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the file.");

    } catch (NumberFormatException e) {
        System.out.println("Invalid integer format: " + e.getMessage());
    }

    System.out.println("Customer Not Found");
    return null;
    //Asserts are because of this
}

public int generateConfirmationNumber() { //generates random cNum, if already taken, will do loop until a number is found that is not taken
    int r;
    do {
        r = random.nextInt(100_000, 1_000_000);
    }
    while (checkConfirmationNumber(r)); // Loop until a unique confirmation number is generated
    return r;

}

public int getConfirmationNumber() {
    return confirmationNumber;
}

public void setConfirmationNumber(int confirmationNumber) {
    Customer.confirmationNumber = confirmationNumber;
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
        // Log the error for debugging purposes
        System.out.println("An error occurred while reading the file: " + e.getMessage());
    }
    return false; // Confirmation number not found or error occurred
}

// Overriding toString() method to provide custom string representation of Customer object
@Override
public String toString() {
    return "Customer Name: " + name +
            "\nCustomer Address: " + address +
            "\nCustomer Age: " + age +
            "\nCustomer SSN: " + ssn +
            "\nCustomer Confirmation Number: " + confirmationNumber +
            "\nCustomer Debt: " + debt;
}
}
