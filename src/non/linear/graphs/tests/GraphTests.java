package non.linear.graphs.tests;

import kotlin.Pair;
import non.linear.graphs.AdjacencyListGraph;
import non.linear.graphs.AdjacencyMatrixGraph;
import non.linear.graphs.AdjacencySetGraph;
import non.linear.graphs.Graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        final Graph g = new AdjacencyMatrixGraph(4, Graph.GraphType.UNDIRECTED);

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
        List<Pair<Integer, List<Integer>>> verticesOfG1 = buildGraph(g1);

        final Graph g2 = new AdjacencySetGraph(6);
        List<Pair<Integer, List<Integer>>> verticesOfG2 = buildGraph(g2);

        final Graph g3 = new AdjacencyListGraph(6);
        List<Pair<Integer, List<Integer>>> verticesOfG3 = buildGraph(g2);

        System.out.println("Vertices Of G1:\n" + verticesOfG1);
        System.out.println("\nVertices Of G2:\n" + verticesOfG2);
        System.out.println("\nVertices Of G3:\n" + verticesOfG3);
        assertEquals(verticesOfG1, verticesOfG2);
        assertEquals(verticesOfG2, verticesOfG3);
    }

    @Test
    void test6() {
        final Graph graph = new AdjacencyListGraph(3, Graph.GraphType.UNDIRECTED);
        final Graph graph1 = new AdjacencySetGraph(3, Graph.GraphType.UNDIRECTED);

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

    private List<Pair<Integer, List<Integer>>> buildGraph(Graph g) {
        g.addEdge(0, 0);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        final List<Pair<Integer, List<Integer>>> verticesOfGraph = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            verticesOfGraph.add(g.getAdjacentVertices(i));
        }
        return verticesOfGraph;
    }
}
