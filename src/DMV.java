/**
 * Matthew Quan
 * Lizette Hernandez
 * Simardeep Kaur 
 * Angelo Angelidis
 * <p>
 * Project 2 - DMV Customer Service (Making DMV employee's job easier)
 * <p>
 * Unauthorized reproduction, duplication, or distribution of any content,
 * including but not limited to text, images, audio, or code, is strictly
 * prohibited and may result in legal action. All rights to copy, modify,
 * or distribute any materials are reserved and require explicit authorization
 * from the rightful copyright owner.
 **/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




public class DMV {

    public static Scanner input = new Scanner(System.in);
    public static String filePath = "customerInfo.txt";
    public static File f = new File(filePath);
    public static Files file = new Files(f);
    public static int x, j;
    public static boolean y = true;
    public static ArrayList<Customer> customerList = new ArrayList<>();




    public static void main(String[] args) {
        Customer currCustomer = new Customer();

        TreeNode<String> root = new TreeNode<>("DMV");

        /*
         * Level 1 of Tree Node
         * Registration
         * License/ID
         */

        TreeNode<String> a1 = new TreeNode<>("Registration");
        TreeNode<String> a2 = new TreeNode<>("License/ID");

        root.addChild(a1, a2);

        /*
         * Level 2 of Tree Node
         * Renew
         * New Registration Branch
         * Check Status
         * License Plate/Decal/Placard
         */
        TreeNode<String> b1 = new TreeNode<>("Renew");
        TreeNode<String> b2 = new TreeNode<>("New Registration");
        TreeNode<String> b3 = new TreeNode<>("Check Registration Status");
        TreeNode<String> b4 = new TreeNode<>("License Plate/Decals/Placards");

        TreeNode<String> b5 = new TreeNode<>("Renew");
        TreeNode<String> b6 = new TreeNode<>("Apply for Real ID");
        TreeNode<String> b7 = new TreeNode<>("Apply for New DL");
        TreeNode<String> b8 = new TreeNode<>("Permits");


        //adds all the level 2 nodes to level 1 in registration/License branch
        a1.addChild(b1, b2, b3, b4);
        a2.addChild(b5, b6, b7, b8);



            System.out.println("1.Login\n2.Register");//(condition) ? (value if true) : (value if false).
            x = input.nextInt();
            if (x == 1) {
                System.out.println("Please enter your SSN to Login");
                x = input.nextInt();
                currCustomer = Customer.searchCustomer(x);
            } else if (x == 2) {
                currCustomer = Customer.enterDataCustomer();
            } else {
                System.out.println("Invalid choice. Exiting");
                return;
            }


        while (y) {

            System.out.println("Welcome to the DMV, what can we do for you?");
            for (int i = 0; i < root.getChildren().size(); i++) {
                System.out.println((i + 1) + ". " + root.getChild(i).getData());
            }

            System.out.println("3. Exit");

            x = input.nextInt();
            /***Error handling***/
            //Test if x is receiving user input
            //System.out.println(""+ x);

            //curr holds current node
            if (x >= 1 && x <= root.getChildren().size()) {
                TreeNode<String> curr = root.getChild(x - 1); // Correcting for array indexing
                //stores the number the user selects. Uses number to follow Tree Structure


                if (curr.getData().equals("Registration")) {
                    System.out.println("What would you like to do in " + curr.getData());
                    for (int i = 0; i < curr.getChildren().size(); i++) {
                        System.out.println((i + 1) + ". " + curr.getChild(i).getData());
                    }

                    x = input.nextInt();
                    switch (x) {
                        case 1://Renew



                            break;
                            case 2://New Registration
                            Car newCar = Car.enterDataCar(); // Collect car details
                            Customer.registerNewCar(newCar); // Register the car
                            System.out.println("Vehicle registered successfully.");


                            break;
                        case 3://Check Registration Status
                            System.out.println("Have you checked the Registration Status page on our DMV website?\n1.Yes\n2.No");
                            input.nextLine();
                            if (j == 2) {
                                System.out.println("""
                                        Please visit & follow: https://www.dmv.ca.gov/portal/vehicle-registration/vehicle-registration-status/
                                        You can now check your registration entirely online.
                                        """);

                                break;
                            }
                            else {
                                System.out.println("You have successfully checked your registration status.");
                            }
                            break;
                            
                        case 4://License plate/decal/placard
                            System.out.println("");

                            break;
                    }


                    /****Enter Client Data stores into Customer Object***/
                } else if (curr.getData().equals("License/ID")) {
                    System.out.println("What would you like to do in " + curr.getData());
                    for (int i = 0; i < curr.getChildren().size(); i++) {
                        System.out.println((i + 1) + ". " + curr.getChild(i).getData());
                    }
                    x = input.nextInt();
                    switch (x) {
                        case 1://Renew
                            Customer.renewLicense(); // Renew the license
                            System.out.println("License/ID renewed successfully.");


                            break;
                        case 2://Real ID
                            System.out.println("Have you checked the Real ID page on our DMV website?\n1.Yes\n2.No");
                            j = input.nextInt();
                            if (j == 2) {
                                System.out.println(" Please visit & follow: https://www.dmv.ca.gov/portal/driver-licenses-identification-cards/real-id" +
                                        " Once all steps have been completed, continue here.");
                                break;
                            }

                            else {
                                System.out.println("The following is only for those who are a lawfully present US citizen and non-citizen:\n What type of identity document do you?");
                                input.nextLine();
                                input.nextLine();
                                System.out.println("Please in put the required identity proof documents which must include your full name:\nPress Enter");
                                input.nextLine();
                                
                                //Go to the website and fill out the application and schedule an appointment
                                System.out.println("Please Visit & follow: https://www.dmv.ca.gov/portal/driver-licenses-identification-cards/dl-id-online-app-edl-44/");
                                input.nextLine();
                                System.out.println("Your Real Id appointment is scheduled for Friday 26 at 11am.");
                                
                                // Once the appointment is scheduled, the user will get a confirmation number
                                System.out.println("Your confirmation number is: " + currCustomer.getConfirmationNumber());
                                input.nextLine();
                                int s = currCustomer.generateConfirmationNumber();
                                customerList.add(currCustomer);
                                System.out.println("\nYou have successfully scheduled your appointment for Real Id, please remember to bring your documentations!\n");
                                System.out.println("Thanks for using our online service.");

                                //Contiue or Sign out of the DMV account
                                System.out.println(" To continue to our table of contents, Press enter OR Other wise you may sign out. Have great day!");
                                input.nextLine();
                
                            }
                            break;
                        case 3://New DL
                            System.out.println("Have you checked the Drivers License page on our DMV website?\n1.Yes\n2.No");
                            j = input.nextInt();
                            if (j == 2) {
                                System.out.println("""
                                        Please visit & follow: https://www.dmv.ca.gov/portal/driver-licenses-identification-cards/dl-id-online-app-edl-44/
                                        Once all steps have been completed, continue here.
                                        """);

                               break;
                            }
                            else {
                                System.out.println("The following is only for those over 18 years of age:\nWhat class drivers license are you applying for?");
                                input.nextLine();
                                input.nextLine();
                                System.out.println("Please in put all of your required documents: \nPress Enter");
                                input.nextLine();
                                assert currCustomer != null;
                                //assert bc of searchCustomer null
                                currCustomer.addDebt(39.00);
                                System.out.println("Cost: $" + currCustomer.getDebt());
                                System.out.println("Your written test is scheduled next week on tuesday at 1pm.");
                                System.out.println("Your confirmation number is: " + currCustomer.getConfirmationNumber());
                                input.nextLine();
                                int s = currCustomer.generateConfirmationNumber();
                                customerList.add(currCustomer);
                                //to enclose
                            }
                                break;
                        case 4://Permits

                            System.out.println("Have you checked the Permit page on the DMV website?\n1.Yes\n2.No");
                            j = input.nextInt();
                            if (j == 2) {
                                System.out.println();
                                System.out.println("""
                                        Please visit & follow: https://www.dmv.ca.gov/portal/driver-licenses-identification-cards/instruction-permits/
                                        Once all steps have been completed, continue here.
                                        """);
                                break;
                            } else {
                                System.out.println("What type of permit are you applying for");



                                System.out.println("Please submit all required documents (Press Enter)");
                                input.nextLine(); // Clear the newline character left in the buffer
                                assert currCustomer != null;
                                //assert bc of searchCustomer null
                                currCustomer.addDebt(58.00);
                                System.out.println("Debt: $" + currCustomer.getDebt());
                                System.out.println("Delivery Time: 1:00 hr"); // Change into variable for time if needed

                                int a = currCustomer.generateConfirmationNumber();
                                currCustomer.setConfirmationNumber(a); // Get confirmation number and check if other people have the same cNum
                                System.out.println("Confirmation Number: " + currCustomer.getConfirmationNumber()+"\n");
                                customerList.add(currCustomer);
                                try {
                                    file.write(currCustomer +"\n");
                                    file.write("\n");
                                    System.out.println("Data saved to file successfully!\n");
                                } catch (IOException e) {
                                    System.out.println("An error occurred while saving the file.");
                                }

                            }

                            break;
                        case 5://Record we can do a point system


                            break;
                    }
                }

                // Add other conditions for different actions as needed
            } else if (x==3){
                System.out.println("Exiting Now...");
               System.exit(0);
            }

        }
    }

}
