import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode(T data){
        this.data = data;
        this.children = new ArrayList<>();
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        children.add(child); // Add a child node to the current node
    }

    //returns index at which the thing called is found
    public int findChild(TreeNode node){
        return 0;
    }
    //Rename method
    //Needs to print the index at certain location
    public String print_loc(TreeNode node){
        System.out.println();
        return "stuff";
    }
}
