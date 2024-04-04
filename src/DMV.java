/**
 * Matthew Quan
 * <p>
 * <p>
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;


public class DMV {

    /**
     * Global Scanner
     **/

    public static Scanner input = new Scanner(System.in);
    public static File file = new File("customerInfo.txt");


    public static int x, j;
    public static boolean y = true;
    public static int time;
    public static Random random = new Random();
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
        TreeNode<String> b6 = new TreeNode<>("Apply for New DL");
        TreeNode<String> b7 = new TreeNode<>("Apply for Real ID");
        TreeNode<String> b8 = new TreeNode<>("Permits");


        //adds all the level 2 nodes to level 1 in registration/License branch
        a1.addChild(b1, b2, b3, b4);
        a2.addChild(b5, b6, b7, b8);

        System.out.println("1.Login\n2.Register");//(condition) ? (value if true) : (value if false).
        x = input.nextInt();

        if (x == 1){
            System.out.println("Please enter your SSN to Login");
            x = input.nextInt();
            currCustomer = Customer.login(customerList, x);
        }
        if (x==2){
            currCustomer = Customer.register();
        }
        while (y) {




            currCustomer = Customer.enterDataCustomer();
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
                TreeNode curr = root.getChild(x - 1); // Correcting for array indexing
                //stores the number the user selects. Uses number to follow Tree Structure

                /***Error handling***/
                //System.out.println(curr.getData());

                if (curr.getData().equals("Registration")) {
                    currCustomer = Customer.enterDataCustomer();

                    System.out.println("What would you like to do in " + curr.getData());
                    for (int i = 0; i < curr.getChildren().size(); i++) {
                        System.out.println((i + 1) + ". " + curr.getChild(i).getData());
                    }

                    x = input.nextInt();
                    switch (x) {
                        case 1://Renew


                            break;
                        case 2://New Registration


                            break;
                        case 3://Check Registration Status

                            break;
                        case 4://License plate/decal/placard

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


                            break;
                        case 2://Real ID


                            break;
                        case 3://New DL

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
                                input.nextLine();
                                input.nextLine();
                                currCustomer.addDebt(58.00);
                                System.out.println("You now owe, $" + currCustomer.getDebt() + " pay now or a debt will inquire on your account (Press Enter) ");
                                input.nextLine(); // clear buffer line
                                System.out.println("Delivery Time: 1:00 hr (Press Enter) "); // change into variable for time
                                input.nextLine();
                                currCustomer.setConfirmationNumber(random.nextInt(100_000, 1_000_000)); // get confirmation number
                                System.out.println("Confirmation Number: " + currCustomer.getConfirmationNumber() + " (Press Enter)"); //prints confirmation number
                                input.nextLine();
                                customerList.add(currCustomer);
                                String w = currCustomer.toString();
                                System.out.println(w);
                                y = false; // just to stop loop NOT PERM

                                try {
                                    // Create a FileWriter object

                                    FileWriter fileWriter = new FileWriter(file);

                                    // Write data to the file
                                    fileWriter.write(String.valueOf(currCustomer));

                                    // Close the FileWriter object
                                    fileWriter.close();

                                    System.out.println("Data saved to file successfully!");
                                } catch (IOException e) {
                                    System.out.println("An error occurred while saving the file.");
                                }

                                //Stores the customer into the Customer array list
                            }

                            break;
                        case 5://Record we can do a point system


                            break;
                    }
                }

                // Add other conditions for different actions as needed
            } else {
                System.out.println("Invalid input.");
            }

        }
    }

}
