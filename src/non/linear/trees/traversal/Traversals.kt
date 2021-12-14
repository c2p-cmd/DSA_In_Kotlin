package non.linear.trees.traversal

import non.linear.trees.TreeNode
import java.util.*


object Traversals {
    // recursive printing
    // left, right, root
    fun <T> postOrderPrint(treeRoot: TreeNode<T>?) {
        if (treeRoot == null)
            return

        postOrderPrint(treeRoot.leftChild)
        postOrderPrint(treeRoot.rightChild)
        print("-> ${treeRoot.data} ")
    }

    // left, root, right
    fun <T> inOrderPrint(treeRoot: TreeNode<T>?) {
        if (treeRoot == null)
            return

        inOrderPrint(treeRoot.leftChild)
        print("-> ${treeRoot.data} ")
        inOrderPrint(treeRoot.rightChild)
    }

    // root, left, right
    fun <T> preOrderPrint(treeNode: TreeNode<T>?) {
        if (treeNode == null)
            return

        print("-> ${treeNode.data} ")
        preOrderPrint(treeNode.leftChild)
        preOrderPrint(treeNode.rightChild)
    }

    /*
     * Iterative:
     *      root
     *  left    right
     */
    fun <T> breadthFirstPrint(treeRoot: TreeNode<T>) =
        LinkedList<TreeNode<T>?>().apply { add(treeRoot) }.let { nodeQueue ->
            while (!nodeQueue.isEmpty()) {
                var nodeCounter = nodeQueue.size

                while (nodeCounter > 0) {
                    nodeQueue.peek()?.let { currentNode ->
                        print("${currentNode.data} ")
                        nodeQueue.remove()

                        if (currentNode.leftChild != null)
                            nodeQueue.add(currentNode.leftChild)
                        if (currentNode.rightChild != null)
                            nodeQueue.add(currentNode.rightChild)
                    }
                    nodeCounter--
                }
                println()
            }
        }
}