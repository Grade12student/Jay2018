package 数据结构.Tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 由两种遍历结果确定二叉树（其中一个结果必须是中序遍历的结果）
 *
 * 前序: 1 2 4 7 3 5 6 8
 * 中序: 4 7 2 1 5 3 6 8
 *
 */
public class ReConstructBST{
    /**
     * 前序遍历与中序遍历序列重建二叉树
     * @param preOrder 前序结果
     * @param inOrder  中序结果
     * @return root根元素
     */
    public static Node construct(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length<=0||inOrder.length<=0) return null;

        return reConstruct(preOrder, 0 ,preOrder.length-1, inOrder,0, inOrder.length-1);
    }

    /**
     * @param preOrder 前序序列
     * @param ps 前序序列开始索引
     * @param pe 前序序列结束索引
     * @param inOrder 中序序列
     * @param is 中序序列开始索引
     * @param ie 中序序列结束索引
     * @return 本次重建的根节点
     */
    private static Node reConstruct(int[] preOrder, int ps, int pe, int[] inOrder, int is, int ie){
        int rootValue = preOrder[ps];
        Node root = new Node(rootValue);
        //只有一个元素，递归返回
        if(ps == pe){
            if(is == ie && preOrder[ps] == inOrder[is]){
                return root;
            }
            throw new RuntimeException("输入错误!");
        }

        //不止有一个元素,在中序遍历中找到根节点的位置
        int rootIndexInOrder = is;
        while(rootIndexInOrder <= ie && inOrder[rootIndexInOrder]!=rootValue) rootIndexInOrder++;

        int lCTLengthInOrder = rootIndexInOrder - is;  //左子树长度
        int lCTEndIndexPreOrder = ps + lCTLengthInOrder;  //左子树末尾节点在前序遍历序列中的位置
        if(lCTLengthInOrder > 0){
            //左子树有元素,构建左子树
            root.left = reConstruct(preOrder, ps+1, lCTEndIndexPreOrder, inOrder, is, rootIndexInOrder-1);
        }
        if(lCTLengthInOrder < (pe-ps)){
            //右子树有元素，构建右子树
            root.right = reConstruct(preOrder, lCTEndIndexPreOrder+1, pe, inOrder, rootIndexInOrder+1, ie);
        }

        return root; //递归返回
    }

    //层次遍历
    public static void traveralByLevel(Node n){
        if(n == null) return;

        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(n);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.data + " ");
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }

    //Node节点
    public static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}


