import java.util.ArrayList;
import java.util.List;

public class TreeNode<String> {
    private String data;
    private List<TreeNode<String>> children;

    public TreeNode(String data){
        this.data = data;
        this.children = new ArrayList<>();
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<TreeNode<String>> getChildren() {
        return children;
    }

    public void addChild(TreeNode... nodes) {
        for (TreeNode node : nodes) {
            children.add(node);
        }
    }

    //returns index at which the thing called is found
    public int findChild(TreeNode node) {
        return children.indexOf(node);
    }

    // Method to print the index of a child node
    public void print_loc(TreeNode<String> node) {
        int index = findChild(node);
        if (index != -1) {
            System.out.println("Node " + node.getData() + " found at index " + index);
        } else {
            System.out.println("Node " + node.getData() + " not found in children.");
        }
    }
}
