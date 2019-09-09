package org.mos91.graph;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
public class SimpleGraph implements Graph {

    private Set<Vertex> vertices = new HashSet();

    @Override
    public void forEachVertex(Consumer<Vertex> consumer) {
        if (vertices != null && !vertices.isEmpty()) {
            vertices.stream().forEach(consumer);
        }
    }

    @Override
    public <E extends Edge> void addVertex(Vertex<E> vertex) {
        vertices.add(vertex);
    }

    @Override
    public void addVertices(Vertex... vertices) {
        for (Vertex v: vertices) {
            this.vertices.add(v);
        }
    }

    @Override
    public void addEdge(Edge edge) {
        Optional<Vertex> leftOptional = vertices.stream().filter(v -> v.equals(edge.getLeft())).findFirst();
        Optional<Vertex> rightOptional = vertices.stream().filter(v -> v.equals(edge.getRight())).findFirst();

        if (!leftOptional.isPresent()) {
            throw new IllegalArgumentException("Left end of the edge does not belong to graph!");
        }

        if (!rightOptional.isPresent()) {
            throw new IllegalArgumentException("Right end of the edge does not belong to graph!");
        }

        edge.connect();
    }

    @Override
    public void addEdges(Edge... edges) {
        for (Edge e : edges) {
            addEdge(e);
        }
    }

    @Override
    public List<Edge> getPath(Vertex from, Vertex to) {
        boolean fromNotFound = vertices.stream().noneMatch(v -> v.equals(from));
        if (fromNotFound) {
            throw new IllegalArgumentException("From vertex does not belong to a graph");
        }

        boolean toNotFound = vertices.stream().noneMatch(v -> v.equals(to));
        if (toNotFound) {
            throw new IllegalArgumentException("To vertex does not belogn to a graph");
        }

        GraphTraverser graphTraverser = new SubOptimalGraphTraverser();
        return graphTraverser.getPath(from, to);
    }
}
