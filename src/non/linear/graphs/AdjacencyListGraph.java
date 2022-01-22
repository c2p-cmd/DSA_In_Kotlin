package non.linear.graphs;

import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AdjacencyListGraph implements Graph {
    private final GraphType graphType;
    private final int numVertices;

    private final List<GraphListNode> vertices = new LinkedList<>();

    boolean isInvalidVertex(final int v) {
        return (v < 0 || v >= numVertices);
    }

    public AdjacencyListGraph(final int numVertices) {
        this.numVertices = numVertices;
        this.graphType = GraphType.DIRECTED;

        for (int i = 0; i < numVertices; i++) {
            vertices.add(new GraphListNode(i));
        }
    }

    public AdjacencyListGraph(final int numVertices, final GraphType graphType) {
        this.numVertices = numVertices;
        this.graphType = graphType;

        for (int i = 0; i < numVertices; i++) {
            vertices.add(new GraphListNode(i));
        }
    }

    @Override
    public int getNumVertices() {
        return numVertices;
    }

    @Override
    public boolean isEdgePresent(final int v1, final int v2) {
        return vertices.get(v1).getGraphNodes().contains(v2);
    }

    @Override
    public void addEdge(final int v1, final int v2) {
        if (isInvalidVertex(v1) || isInvalidVertex(v2))
            throw new IllegalArgumentException("Vertex is invalid");

        if (isEdgePresent(v1, v2))
            return;

        vertices.get(v1).getGraphNodes().add(v2);
        if (graphType == GraphType.UNDIRECTED && v1 != v2)
            vertices.get(v2).getGraphNodes().add(v1);
    }

    @Override
    public boolean removeEdge(final int v1, final int v2) {
        if (isInvalidVertex(v1) || isInvalidVertex(v2))
            throw new IllegalArgumentException("Vertex is invalid");

        if (isEdgePresent(v1, v2)) {
            vertices.get(v1).getGraphNodes().remove(v2);
            return true;
        } else
            return false;
    }

    @Override
    public @NotNull Pair<Integer, List<Integer>> getAdjacentVertices(final int v) {
        if (isInvalidVertex(v))
            throw new IllegalArgumentException("Vertex is invalid");

        final List<Integer> list = vertices.get(v).getGraphNodes();

        Collections.sort(list);
        return new Pair<>(v, list);
    }

    @Override
    public @NotNull Map<Integer, List<Integer>> getAllVertices() {
        final Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            map.put(i, getAdjacentVertices(i).component2());
        }

        return map;
    }

    @Override
    public int getIndegree(final int v) {
        if (isInvalidVertex(v))
            throw new IllegalArgumentException("Invalid vertex");

        final int[] indegree = new int[1];
        getAllVertices().forEach((vertex, vertexList) ->
                indegree[0] += vertexList.contains(v) ? 1 : 0);

        return indegree[0];
    }

    @Override
    public @NotNull Map<Integer, Integer> getIndegreesOfAll() {
        final Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < numVertices; i++)
            map.put(i, getIndegree(i));

        return map;
    }

    @Override
    public String toString() {
        final StringBuilder resultString = new StringBuilder();

        vertices.forEach(pair -> resultString.append(pair).append("\n"));

        return resultString.toString();
    }

    @NotNull
    @Override
    public GraphType getGraphType() {
        return graphType;
    }
}
