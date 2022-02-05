package algorithms.shortest.path.tests;

import algorithms.shortest.path.UnweightedGraphKt;
import non.linear.graphs.AdjacencyMatrixGraph;
import non.linear.graphs.AdjacencySetGraph;
import non.linear.graphs.Graph;

import non.linear.graphs.GraphType;
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
        final Graph graph = new AdjacencySetGraph(6);

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

        final String path = UnweightedGraphKt.shortestPath(graph, Vertex6.A.get(), Vertex6.D.get());
        System.out.println("Graph:\n" + graph + path);
        assertFalse(path.contains(notExpected));
    }

    @Test
    void test1() {
        final String expected = "There is no path from vertex";
        final Graph graph = new AdjacencyMatrixGraph(4, GraphType.UNDIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        final String path = UnweightedGraphKt.shortestPath(graph, Vertex6.A.get(), Vertex6.D.get());
        System.out.println("Graph:\n" + graph + path);
        assertTrue(path.contains(expected));
    }

    @Test
    void test2() {
        final String notExpected = "There is no path from vertex";
        final Graph graph = new AdjacencyMatrixGraph(6);

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

        final String path = UnweightedGraphKt.shortestPath(graph, Vertex6.A.get(), Vertex6.D.get());
        System.out.println("Graph:\n" + graph + path);
        assertFalse(path.contains(notExpected));
    }
}
