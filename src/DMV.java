/**
 * Matthew Quan
 *
 *
 *
 * Project 2 - DMV Customer Service (Making DMV employee's job easier)
 *
 * Unauthorized reproduction, duplication, or distribution of any content,
 * including but not limited to text, images, audio, or code, is strictly
 * prohibited and may result in legal action. All rights to copy, modify,
 * or distribute any materials are reserved and require explicit authorization
 * from the rightful copyright owner.
 *
 * **/
import java.util.Scanner;

public class DMV{

    /**Global Scanner**/

    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int x, age, ssn;
        String name, address;
        Customer currCustomer = new Customer();



        TreeNode root = new TreeNode("DMV");

        /*
         * Level 1 of Tree Node
         * Registration
         * License/ID
         */

        TreeNode a1 = new TreeNode("Registration");
        TreeNode a2 = new TreeNode("License/ID");

        root.addChild(a1, a2);

        /*
         * Level 2 of Tree Node
         * Renew
         * New Registration Branch
         * Check Status
         * License Plate/Decal/Placard
         */
        TreeNode b1 = new TreeNode("Renew");
        TreeNode b2 = new TreeNode("New Registration");
        TreeNode b3 = new TreeNode("Check Registration Status");
        TreeNode b4 = new TreeNode("License Plate/Decals/Placards");


        TreeNode b5 = new TreeNode("Renew");
        TreeNode b6 = new TreeNode("Apply for New DL");
        TreeNode b7 = new TreeNode("Apply for Real ID");
        TreeNode b8 = new TreeNode("Permits");


        //adds all the level 2 nodes to level 1 in registration branch
        a1.addChild(b1,b2,b3,b4);

        System.out.println("Welcome to the DMV, what can we do for you?");
        for (int i = 0; i < root.getChildren().size(); i++) {
            System.out.println((i+1) + ". " + root.getChild(i).getData());
        }

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
                System.out.println("What would you like to do in " + curr.getData());
                for (int i = 0; i < curr.getChildren().size(); i++) {
                    System.out.println((i + 1) + ". " + curr.getChild(i).getData());
                }

               /****Enter Client Data stores into Customer Object***/
               currCustomer = DMV.enterData();
            } else if (curr.getData().equals("License/ID")) {
                System.out.println("What would you like to do in "+ curr.getData());
                for (int i = 0; i < curr.getChildren().size(); i++) {
                    System.out.println((i + 1) + ". " + curr.getChild(i).getData());
                }
            }

            // Add other conditions for different actions as needed
        } else {
            System.out.println("Invalid input.");
        }
    }
    public static Customer enterData(){

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
}
