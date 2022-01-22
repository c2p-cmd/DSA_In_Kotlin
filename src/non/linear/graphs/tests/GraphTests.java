package non.linear.graphs.tests;

import non.linear.graphs.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {
    @Test
    void test0() {
        final Graph g1 = new AdjacencyMatrixGraph(3);

        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 2);

        System.out.println("List: " + g1.getAdjacentVertices(2));

        final String graph = g1.toString();
        System.out.println(graph);

        assertNotNull(graph);
    }

    @Test
    void test1() {
        final Graph g = new AdjacencyMatrixGraph(4, GraphType.UNDIRECTED);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);

        g.addEdge(1, 2);
        g.addEdge(2, 2);
        g.addEdge(2, 3);

        System.out.println("List: " + g.getAdjacentVertices(0));
        System.out.println("Matrix:\n" + g);

        assertNotNull(g.toString());
    }

    @Test
    void test2() {
        final Graph g1 = new AdjacencyMatrixGraph(3);

        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 2);

        System.out.println(g1);
        assertTrue(g1.removeEdge(2,2));
    }

    @Test
    void test3() {
        final Graph g1 = new AdjacencySetGraph(3);

        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 2);

        System.out.println(g1);
        assertFalse(g1.removeEdge(2,1));
    }

    @Test
    void test4() {
        final Graph g1 = new AdjacencyListGraph(3);

        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 2);

        System.out.println("List: " + g1.getAdjacentVertices(2));

        final String graph = g1.toString();
        System.out.println(graph);

        assertNotNull(graph);
    }

    @Test
    void test5() {
        final Graph g1 = new AdjacencyMatrixGraph(6);
        final Map<Integer, List<Integer>> verticesOfG1 = buildGraph(g1);

        final Graph g2 = new AdjacencySetGraph(6);
        final Map<Integer, List<Integer>> verticesOfG2 = buildGraph(g2);

        final Graph g3 = new AdjacencyListGraph(6);
        final Map<Integer, List<Integer>> verticesOfG3 = buildGraph(g3);

        System.out.println("Vertices Of G1:\n" + verticesOfG1);
        System.out.println("\nVertices Of G2:\n" + verticesOfG2);
        System.out.println("\nVertices Of G3:\n" + verticesOfG3);

        assertEquals(verticesOfG1, verticesOfG2);
        assertEquals(verticesOfG2, verticesOfG3);
    }

    @Test
    void test6() {
        final Graph graph = new AdjacencyListGraph(3, GraphType.UNDIRECTED);
        final Graph graph1 = new AdjacencySetGraph(3, GraphType.UNDIRECTED);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(2,2);

        graph1.addEdge(0,1);
        graph1.addEdge(0,2);
        graph1.addEdge(2,2);

        System.out.println(graph);
        System.out.println(graph1);

        assertEquals(graph.getAllVertices(), graph1.getAllVertices());
    }

    @Test
    void test7() {
        // DFS traversal test
        final Graph graph0 = new AdjacencyMatrixGraph(5, GraphType.UNDIRECTED);
        final Graph graph1 = new AdjacencyMatrixGraph(5, GraphType.DIRECTED);

        graph0.addEdge(0, 0);
        graph0.addEdge(0, 1);
        graph0.addEdge(1, 2);
        graph0.addEdge(2, 3);
        graph0.addEdge(3, 3);
        graph0.addEdge(3, 4);

        graph1.addEdge(0, 0);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 3);
        graph1.addEdge(3, 4);

        System.out.println("Vertices of Graph0: " + graph0.getAllVertices());

        final String res0 = GraphTraversals.depthFirstTraversal(graph0);
        System.out.println("\nGraph0:\n" + res0);

        System.out.println("\nVertices of Graph1: " + graph1.getAllVertices());

        final String res1 = GraphTraversals.depthFirstTraversal(graph1);
        System.out.println("\nGraph1:\n" + res1);

        assertEquals(res0, res1);
    }

    @Test
    void test8() {
        // traversal test
        final Graph graph0 = new AdjacencySetGraph(5, GraphType.UNDIRECTED);

        graph0.addEdge(0, 0);
        graph0.addEdge(0, 1);
        graph0.addEdge(1, 2);
        graph0.addEdge(2, 3);
        graph0.addEdge(3, 3);
        graph0.addEdge(3, 4);

        System.out.println("Vertices: " + graph0.getAllVertices());

        final String graphString = GraphTraversals.breadthFirstTraversal(graph0);

        assertNotNull(graphString);
    }

    @Test
    void test9() {
        final Graph[] graphs = {
                new AdjacencySetGraph(3, GraphType.UNDIRECTED),
                new AdjacencyListGraph(3, GraphType.UNDIRECTED),
                new AdjacencyMatrixGraph(3, GraphType.UNDIRECTED)
        };
        final Map<Integer, Integer[]> graphDegrees = new TreeMap<>();

        int ctr = 0;
        for (Graph graph : graphs) {
            System.out.println("Graph-" + (ctr+1));
            final Integer[] counts = new Integer[graph.getNumVertices()];
            graph.addEdge(0, 1);
            graph.addEdge(1, 2);

            for (int i = 0; i < graph.getNumVertices(); i++) {
                counts[i] = graph.getIndegree(i);
                System.out.println("degree of vertex " + i + " is " + counts[i]);
            }

            graphDegrees.put(ctr, counts);
            ctr++;
            System.out.println();
        }

        assertArrayEquals(graphDegrees.get(0), graphDegrees.get(1));
        assertArrayEquals(graphDegrees.get(1), graphDegrees.get(2));
    }

    @Test
    void test10() {
        final Graph graph0 = new AdjacencyMatrixGraph(5, GraphType.DIRECTED);

        graph0.addEdge(0, 0);
        graph0.addEdge(0, 1);
        graph0.addEdge(1, 2);
        graph0.addEdge(2, 3);
        graph0.addEdge(3, 3);
        graph0.addEdge(3, 4);

        System.out.println("List: " + graph0.getAllVertices());
    }

    private Map<Integer, List<Integer>> buildGraph(Graph g) {
        g.addEdge(0, 0);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        final Map<Integer, List<Integer>> verticesOfGraph = new HashMap<>();
        for (int i = 0; i <= 5; i++) {
            verticesOfGraph.put(g.getAdjacentVertices(i).getFirst(), g.getAdjacentVertices(i).getSecond());
        }
        return verticesOfGraph;
    }
}
