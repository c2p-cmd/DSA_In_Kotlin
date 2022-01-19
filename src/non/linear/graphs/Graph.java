package non.linear.graphs;

import kotlin.Pair;

import java.util.List;
import java.util.Map;

public interface Graph {
    enum GraphType {
        DIRECTED,
        UNDIRECTED
    }

    int getNumVertices();

    boolean isEdgePresent(final int v1, final int v2);

    void addEdge(final int v1, final int v2);

    boolean removeEdge(final int v1, final int v2);

    Pair<Integer, List<Integer>> getAdjacentVertices(final int v);

    Map<Integer, List<Integer>> getAllVertices();
}
