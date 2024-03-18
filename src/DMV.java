public class DMV{
    public static void main(String[] args) {

        TreeNode root = new TreeNode("DMV");




        /***Level 1***/
        TreeNode a1 = new TreeNode("Registration");
        root.addChild(a1);
        TreeNode a2 = new TreeNode("License/ID");
        root.addChild(a2);

        /***Level 2***/
        TreeNode b1 = new TreeNode("Renew");
        TreeNode b2 = new TreeNode("New Registration");
        TreeNode b3 = new TreeNode("Check Registration Status");
        TreeNode b4 = new TreeNode("License Plate/Decals/Placards");

        a1.addChild(b1,b2,b3,b4);


        switch(x){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
        }




    }
}
