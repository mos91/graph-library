package org.mos91.graph;

import java.util.List;
import java.util.function.Consumer;

public interface Graph {

    void forEachVertex(Consumer<Vertex> consumer);

    <E extends Edge> void addVertex(Vertex<E> vertex);

    <E extends Edge> void addVertices(Vertex<E>... vertices);

    void addEdge(Edge edge);

    void addEdges(Edge... edges);

    List<Edge> getPath(Vertex from, Vertex to);
}
