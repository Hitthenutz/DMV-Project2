import java.util.Scanner;

public class DMV{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        TreeNode root = new TreeNode("DMV");




        /***Level 1***/
        TreeNode a1 = new TreeNode("Registration");
        TreeNode a2 = new TreeNode("License/ID");

        root.addChild(a1, a2);

        /***Level 2***/
        TreeNode b1 = new TreeNode("Renew");
        TreeNode b2 = new TreeNode("New Registration");
        TreeNode b3 = new TreeNode("Check Registration Status");
        TreeNode b4 = new TreeNode("License Plate/Decals/Placards");

        a1.addChild(b1,b2,b3,b4);

        System.out.println("Welcome to the DMV, what can we do for you?");
        for (int i = 0; i < root.getChildren().size(); i++) {
            System.out.println((i + 1) + ". " + root.getChild(i).getData());
        }

        //Test if x is receiving user input
        //System.out.println(x);

        int x = input.nextInt();

        /******Switch Case******/
        switch(x){
            case 1:
                break;
            case 2:
                break;
        }




    }
}
