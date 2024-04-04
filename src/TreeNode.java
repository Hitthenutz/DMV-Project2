import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode<String> {
    private String data;
    private final List<TreeNode<String>> children;

    public TreeNode(String data) {
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

    public TreeNode<String> getChild(int index) {
        if (index >= 0 && index < children.size()) {
            return children.get(index);
        }
        return null; // Return null if index is out of bounds
    }

    public void addChild(TreeNode... nodes) {
        for (TreeNode<String> node : nodes) {
            children.add(node);
        }    }

    //returns index at which the thing called is found
    public int findChild(TreeNode node) {
        return children.indexOf(node);
    }

    // Method to print the index of a child node
    public int getLoc(TreeNode<String> node) {
        return findChild(node);
    }
}
