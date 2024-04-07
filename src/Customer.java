import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Customer {
    private static String name;
    private static String address;
    private static int ssn, confirmationNumber, age;
    private static double debt;
    private static Car car; //Aggregation Relationship "has a"
    //Customer "has a"

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

    public Customer(String name, String address, int age, int ssn) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
    }

    public Customer(String name, String address, int age, int ssn, int confirmationNumber) {
        Customer.name = name;
        Customer.address = address;
        Customer.age = age;
        Customer.ssn = ssn;
        Customer.confirmationNumber = confirmationNumber;
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
                System.out.println("Enter name (First & Last):\n"); //said no space, but that causes error changed to avoid confusion
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

        return new Customer(name, address, age, ssn);
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
            for (int i = fileTraverseSSN - 1; i < lines.length; i += 6) { //the line of every ssn = i
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

                        name = (lines[i-3].split(":")[1].trim());
                        System.out.println(name);
                        address = lines[i-2].split(":")[1].trim();
                        System.out.println(address);
                        cNum = Integer.parseInt(lines[i+1].split(":")[1].trim());
                        System.out.println(cNum);
                        age = Integer.parseInt(lines[i - 1].split(":")[1].trim());
                        System.out.println(age);

                        System.out.println("Customer Found!");
                        return new Customer(name, address, age, ssn, cNum);
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
                "\nCustomer Confirmation Number: " + confirmationNumber + "\n";
    }
}
