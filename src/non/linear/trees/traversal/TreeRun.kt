package non.linear.trees.traversal

import non.linear.trees.TreeNode as Node
import non.linear.trees.traversal.Traversals.postOrderPrint
import non.linear.trees.traversal.Traversals.inOrderPrint
import non.linear.trees.traversal.Traversals.preOrderPrint
import non.linear.trees.traversal.Traversals.breadthFirstPrint

fun main() {
    // first tree
    println("First")
    printAllPossible(createTree())

    // other tree
    println("\nOther")
    printAllPossible(createOtherTree())
}

fun <T> printAllPossible(treeRoot: Node<T>) {
    println("Recursive(Depth First)\nPreOrder: ")
    preOrderPrint(treeRoot)
    println()

    println("\nInOrder:")
    inOrderPrint(treeRoot)
    println()

    println("\nPostOrder:")
    postOrderPrint(treeRoot)
    println()

    println("\nIterative(Breadth First): ")
    breadthFirstPrint(treeRoot)
    println()
}

fun createOtherTree(): Node<Char> =
    Node('A').apply {
        val b = Node('B')
        val c = Node('C')
        val d = Node('D')
        val e = Node('E')
        val f = Node('F')
        val g = Node('G')
        val h = Node('H')
        val x = Node('X')

        this.leftChild = b
        this.rightChild = c

        c.leftChild = d
        c.rightChild = e
        d.leftChild = f
        d.rightChild = h
        e.rightChild = g
        b.leftChild = x
    }


fun createTree(): Node<String> =
    Node("Root").apply {
        this.leftChild = Node("One")
        this.rightChild = Node("Two")

        this.leftChild.leftChild = Node("Three")
        this.leftChild.rightChild = Node("Four")

        this.rightChild.leftChild = Node("Five")
        this.rightChild.rightChild = Node("Six")

        this.leftChild.leftChild.leftChild = Node("Ending1")
        this.leftChild.leftChild.rightChild = Node("Ending2")
    }