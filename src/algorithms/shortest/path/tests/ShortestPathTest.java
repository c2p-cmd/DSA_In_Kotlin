package algorithms.shortest.path.tests;

import algorithms.shortest.path.UnweightedGraphCompanion;
import algorithms.shortest.path.WeightedGraphCompanion;
import non.linear.graphs.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathTest {
    @Test
    void test0() {
        /*
            a -> b, c
            b -> e, d
            c -> e
            e -> d
         */
        final String notExpected = "There is no path from vertex";
        Graph graph = new AdjacencyMatrixGraph(6);

        // A to B, A to C
        graph.addEdge(Vertex6.A.get(), Vertex6.B.get());
        graph.addEdge(Vertex6.A.get(), Vertex6.C.get());

        // B to E, B to D
        graph.addEdge(Vertex6.B.get(), Vertex6.E.get());
        graph.addEdge(Vertex6.B.get(), Vertex6.D.get());

        // C to E
        graph.addEdge(Vertex6.C.get(), Vertex6.E.get());

        // E to D
        graph.addEdge(Vertex6.E.get(), Vertex6.D.get());

        final String path = UnweightedGraphCompanion.shortestPath(graph, Vertex6.A.get(), Vertex6.C.get());
        System.out.println("Graph:\n" + graph + path);
        assertFalse(path.contains(notExpected));
    }

    @Test
    void test1() {
        final String expected = "There is no path from vertex";
        final Graph graph = new AdjacencyMatrixGraph(4, GraphType.UNDIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        final String path = UnweightedGraphCompanion.shortestPath(graph, Vertex6.A.get(), Vertex6.D.get());
        System.out.println("Graph:\n" + graph + path);
        assertTrue(path.contains(expected));
    }

    @Test
    void test2() {
        final String notExpected = "There is no path from vertex";
        final WeightedGraph graph = new WeightedAdjacencyMatrixGraph(6);

        graph.addEdge(0, 1, 4.2);
        graph.addEdge(0, 2, 13.0);

        graph.addEdge(1, 2, 15.9);
        graph.addEdge(1, 3, 12.1);

        graph.addEdge(2, 3, 14.5);

        graph.addEdge(3, 4, 10.0);

        final String path = WeightedGraphCompanion.shortestPath(graph, 0, 4);
        System.out.println("Graph:\n" + graph + path);
        assertFalse(path.contains(notExpected));
    }
}
