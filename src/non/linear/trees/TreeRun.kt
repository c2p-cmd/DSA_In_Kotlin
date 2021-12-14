package non.linear.trees

import java.util.*


fun main() =
    createTree().let { treeRoot ->
        println("Recursive: ")
        recursivePrintTree(treeRoot)

        println("\nIterative: ")
        iterativePrintTree(treeRoot)
    }

fun createTree(): Node<String> = Node("Root").apply {
        this.leftChild = Node("One")
        this.rightChild = Node("Two")

        this.leftChild.leftChild = Node("Three")
        this.leftChild.rightChild = Node("Four")

        this.rightChild.leftChild = Node("Five")
        this.rightChild.rightChild = Node("Six")

        this.leftChild.leftChild.leftChild = Node("Ending1")
        this.leftChild.leftChild.rightChild = Node("Ending2")
    }

// recursive
fun recursivePrintTree(treeNode: Node<String>?) {
    println("Pre-Order")
    if (treeNode == null)
        return

    println("-> ${treeNode.data}")
    recursivePrintTree(treeNode.leftChild)
    recursivePrintTree(treeNode.rightChild)
}

// iterative
fun iterativePrintTree(treeRoot: Node<String>) {
    val nodeQueue: Queue<Node<String>?> = LinkedList<Node<String>?>().apply { this.add(treeRoot) }

    while (!treeRoot.isLeaf()) {
        var nodeCounter = nodeQueue.size
        if (nodeCounter == 0)
            return

        while (nodeCounter > 0) {
            nodeQueue.peek()?.let { currentNode ->
                print("-> ${currentNode.data} ")
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

fun Node<String>.isLeaf(): Boolean = (this.leftChild == null && this.rightChild == null)