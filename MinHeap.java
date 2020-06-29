import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yubotao
 * @Description:
 * @Date: Created in 17:20 2020/06/29
 * @Modified By: 要保证堆是一个完全二叉树
 *               参考链接：https://www.jianshu.com/p/895b25409f89
 */
public class MinHeap {

    public class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        public TreeNode(int data){
            this.data = data;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }

    TreeNode root;
    // 用链表存储所有节点，便于找到最后节点的父节点
    List<TreeNode> nodes = new ArrayList<>();

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(int data){
        if(!isEmpty() && root.data > data){
            int temp = root.data;
            root.data = data;
            data = temp;
        }
        TreeNode leaf = new TreeNode(data);
        nodes.add(leaf);
        if (isEmpty()){
            root = leaf;
            return;
        }
        // 得到最后节点的父节点索引，此最后节点为节点插入前的最后节点。
        int index = (nodes.size() - 1 - 1)/2; // 首先减去新增的这个节点，并且数组从0开始计数，因此size-1-1
        if(nodes.get(index).rightChild != null){
            // 如果父节点的右孩子不为空，则新增节点放到该父节点下一索引的左孩子上。
            /**     0
             *   1     2
             * 3  4  5  6
             * 如果找到父节点索引为2，则新增节点添加到3的左孩子上
             *      0
             *   1    2
             * 3  4
             * 如果找到父节点索引为1，则新增节点添加到2的左孩子上
             */
            nodes.get(index+1).leftChild = leaf;
        }else if(nodes.get(index).leftChild != null){
            // 如果右孩子为空，左孩子不为空，则直接添加到右孩子上
            nodes.get(index).rightChild = leaf;
        }else {
            // 父节点左右孩子都空，说明是根节点，直接插左孩子
            nodes.get(index).leftChild = leaf;
        }
        swap(root);
    }

    @Override
    public String toString() {
        return "MinHeap{" +
                "root=" + root +
                '}';
    }

    // 调整堆
    void swap(TreeNode root){
        if(root.leftChild != null){
            if(root.data > root.leftChild.data) {
                int temp = root.data;
                root.data = root.leftChild.data;
                root.leftChild.data = temp;
            }
            swap(root.leftChild);

        }
        if (root.rightChild != null){
            if(root.data > root.rightChild.data) {
                int temp = root.data;
                root.data = root.rightChild.data;
                root.rightChild.data = temp;
            }
            swap(root.rightChild);
        }


    }

    public static void main(String[] args) {
//        MinHeap.TreeNode root = new MinHeap().new TreeNode(4);
        MinHeap test = new MinHeap();
        test.insert(4);
        System.out.println(test);
        test.insert(9);
        System.out.println(test);
        test.insert(6);
        test.insert(2);
        test.insert(7);
        test.insert(10);
        System.out.println(test);
    }

}
