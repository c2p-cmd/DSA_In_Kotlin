package non.linear.trees.tests

import non.linear.trees.BinarySearchTree
import non.linear.trees.TreeNode
import non.linear.trees.traversal.Traversals

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BinarySearchTreeTest {
    private val rootNode1 = TreeNode(2).apply {
        BinarySearchTree.insertInto(this, TreeNode(1))
        BinarySearchTree.insertInto(this, TreeNode(3))
    }
    private val rootNode2 = TreeNode(8).apply {
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

    @Test
    fun test6() {
        val expected = 1
        BinarySearchTree.insertInto(rootNode2, TreeNode(1))
        val actual = BinarySearchTree.findMinimumValueIn(rootNode2)

        println("Tree In-Order: ${Traversals.getInOrderOf(rootNode2)}")
        assertEquals(expected, actual)
    }

    @Test
    fun test7() {
        val expected = 1
        val actual = BinarySearchTree.findMaxDepthOf(rootNode1)

        println("Expected depth: $expected, Actual depth: $actual")
        assertEquals(expected, actual)
    }

    @Test
    fun test8() {
        val expected = 3
        BinarySearchTree.insertInto(rootNode1, TreeNode(11))
        BinarySearchTree.insertInto(rootNode1, TreeNode(21))
        val actual = BinarySearchTree.findMaxDepthOf(rootNode1)

        println("Expected depth: $expected, Actual depth: $actual")
        assertEquals(expected, actual)
    }

    @Test
    fun test9() {
        val expectedLeftChild = TreeNode('R').data
        val tempNode = TreeNode('P').apply {
            this.leftChild = TreeNode('R')
            this.rightChild = TreeNode('O')
        }

        println("Pre-Order(before swap): ${Traversals.getPreOrderOf(tempNode)}")

        BinarySearchTree.swapChildren(tempNode)
        val actualLeftChild = tempNode.leftChild.data

        println("Pre-Order(after swap): ${Traversals.getPreOrderOf(tempNode)}")

        println("Expected: $expectedLeftChild, Actual: $actualLeftChild")
        assertNotEquals(expectedLeftChild, actualLeftChild)
    }

    @Test
    fun test10() {
        val tempRoot = TreeNode('O').apply {
            this.leftChild = TreeNode('R').apply { this.leftChild = TreeNode('P') }
            this.rightChild = TreeNode('1').apply { this.leftChild = TreeNode('G'); this.rightChild = TreeNode('3') }
        }

        val beforeMirror: String = Traversals.getInOrderOf(tempRoot)
        println("In-Order(before): $beforeMirror")

        BinarySearchTree.mirrorTree(tempRoot)

        val afterMirror: String = Traversals.getInOrderOf(tempRoot)
        println("In-Order(after): $afterMirror")

        // after mirroring the tree its in-order printing will reverse
        assertNotEquals(beforeMirror, afterMirror)
    }

    @Test
    fun test11() {
        val nodes = listOf(1, 2, 3, 4)
        val expected = listOf(1, 2, 5, 14)
        val actual = arrayListOf<Int>()
        for (numNodes in nodes) {
            actual.add(BinarySearchTree.countTreesFrom(numNodes))
        }
        println("Expected: $expected, Actual: $actual")
        assertArrayEquals(expected.toIntArray(), actual.toIntArray())
    }

    @Test
    fun test12() {
        val range: IntRange = 11..14

        val expected: String = buildString {
            for (i in range) {
                append("-> ").append(i).append(" ")
            }
        }

        val tree = TreeNode(9).apply {
            for (i in range) {
                BinarySearchTree.insertInto(this, TreeNode(i))
            }
            BinarySearchTree.insertInto(this, TreeNode(3))
        }
        val actual: String = BinarySearchTree.printRange(tree, range)

        println("Expected: $expected, Actual: $actual")
        assertEquals(expected, actual)
    }
}