package non.linear.trees.tests

import non.linear.trees.BinarySearchTree
import non.linear.trees.TreeNode
import non.linear.trees.traversal.Traversals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BinarySearchTreeTest {
    private val rootNode1 = createTree0()
    private val rootNode2 = createTree1()

    @Test
    fun test0() {
        val expected = "-> 1 -> 2 -> 3 "
        val actual = Traversals.getInOrderOf(rootNode1)
        assertEquals(expected, actual)
        println("Expected: $expected, Actual: $actual")
    }

    @Test
    fun test1() {
        val expected = "-> 1 -> 3 -> 2 "
        val actual = Traversals.getPostOrderOf(rootNode1)
        assertEquals(expected, actual)
        println("Expected: $expected, Actual: $actual")
    }

    @Test
    fun test2() {
        val expected = "-> 4 -> 6 -> 7 -> 8 -> 14 -> 15 -> 16 -> 18 "
        BinarySearchTree.insertInto(rootNode2, TreeNode(15))
        val actual = Traversals.getInOrderOf(rootNode2)
        assertEquals(expected, actual)
        println("Expected: $expected, Actual: $actual")
    }

    @Test
    fun test3() {
        val expected = "-> 2 -> 4 -> 6 -> 7 -> 8 -> 14 -> 16 -> 18 "
        BinarySearchTree.insertInto(rootNode2, TreeNode(2))
        val actual = Traversals.getInOrderOf(rootNode2)
        assertEquals(expected, actual)
        println("Expected: $expected, Actual: $actual")
    }

    @Test
    fun test4() {
        assertTrue(
            BinarySearchTree.lookUpFrom(rootNode1, 2)
        )
    }

    @Test
    fun test5() {
        assertFalse(
            BinarySearchTree.lookUpFrom(rootNode1, 21)
        )
    }

    private fun createTree0(): TreeNode<Int> {
        val rootNode: TreeNode<Int> = TreeNode(2)
        BinarySearchTree.insertInto(rootNode, TreeNode(1))
        BinarySearchTree.insertInto(rootNode, TreeNode(3))
        return rootNode
    }

    private fun createTree1(): TreeNode<Int> = TreeNode(8).apply {
        // left subtree
        val six = TreeNode(6); val four = TreeNode(4); val seven = TreeNode(7)
        // right subtree
        val fourteen = TreeNode(14); val sixteen = TreeNode(16); val eighteen = TreeNode(18)

        listOf(six, four, seven, fourteen, sixteen, eighteen).let { nodeList ->
            for (node in nodeList) {
                BinarySearchTree.insertInto(this, node)
            }
        }
    }
}