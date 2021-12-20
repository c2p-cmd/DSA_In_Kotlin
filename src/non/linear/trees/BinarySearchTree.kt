package non.linear.trees

import java.lang.StringBuilder

object BinarySearchTree {
    /*
     * Complexity for insertion & lookup:
     *      If the tree is skewed(either too much to the left or too much to the right)
     *       then, O(N)
     *      else O(logN)
     *      N: Number of nodes in tree
     */
    @JvmStatic
    fun insertInto(
        headNode: TreeNode<Int>?, nodeToInsert: TreeNode<Int>
    ): TreeNode<Int> {
        if (headNode == null)
            return nodeToInsert

        if (nodeToInsert.data <= headNode.data)
            headNode.leftChild = insertInto(headNode.leftChild, nodeToInsert)
        else
            headNode.rightChild = insertInto(headNode.rightChild, nodeToInsert)

        return headNode
    }

    @JvmStatic
    fun lookUpFrom(
        headNode: TreeNode<Int>?, dataToSearch: Int
    ): Boolean {
        // is current node in recursive call is null (not found)
        if (headNode == null)
            return false
        // if found
        if (headNode.data == dataToSearch)
            return true

        // recursively traversing through the subtrees
        return if (headNode.data >= dataToSearch)
            lookUpFrom(headNode.leftChild, dataToSearch)
        else
            lookUpFrom(headNode.rightChild, dataToSearch)
    }

    // method to swap children
    @JvmStatic
    fun <T> swapChildren(treeNode: TreeNode<T>) {
        val temp = treeNode.leftChild
        treeNode.leftChild = treeNode.rightChild
        treeNode.rightChild = temp
    }

    // Exercise 1:
    @JvmStatic
    fun findMinimumValueIn(node: TreeNode<Int>?): Int {
        if (node == null)
            return Int.MIN_VALUE

        var treeItr = node

        while (treeItr?.leftChild != null) {
            treeItr = treeItr.leftChild
        }
        return if (treeItr == null) Int.MIN_VALUE else treeItr.data
    }

    // Exercise 2:
    @JvmStatic
    fun findMaxDepthOf(node: TreeNode<Int>?): Int {
        if ((node == null) || (node.isLeaf)) return 0

        val leftSubTreeDepth = 1 + findMaxDepthOf(node.leftChild)
        val rightSubTreeDepth = 1 + findMaxDepthOf(node.rightChild)

        return maxOf(leftSubTreeDepth, rightSubTreeDepth)
    }

    // Exercise 3: mirror the tree (left children are right & vice-versa)
    @JvmStatic
    fun <T> mirrorTree(treeRoot: TreeNode<T>?) {
        if (treeRoot == null) return

        mirrorTree(treeRoot.leftChild)
        mirrorTree(treeRoot.rightChild)

        // swapping children at current node
        swapChildren(treeRoot)
    }

    // Exercise 4: count all structurally unique trees
    @JvmStatic
    fun countTreesFrom(numNodes: Int): Int { // numNodes number of nodes of a tree
        if (numNodes <= 1) return 1

        var sumOfTrees = 0
        for (i in 1..numNodes) {
            val leftSubTreeCount = countTreesFrom(i-1)
            val rightSubTreeCount = countTreesFrom(numNodes-i)

            sumOfTrees += (leftSubTreeCount * rightSubTreeCount)
        }

        return sumOfTrees
    }

    // Exercise 5: printing nodes from a range
    @JvmStatic
    fun printRange(
        treeRoot: TreeNode<Int>?, range: IntRange, builder: StringBuilder = StringBuilder()
    ): String {
        if (treeRoot == null) return "none"

        if (range.first <= treeRoot.data)
            printRange(treeRoot.leftChild, range, builder)

        if (treeRoot.data in range)
            builder.append("-> ").append(treeRoot.data.toString()).append(" ")

        if (range.last > treeRoot.data)
            printRange(treeRoot.rightChild, range, builder)

        return builder.toString()
    }
}