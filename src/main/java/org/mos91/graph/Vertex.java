package org.mos91.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"data", "edges"})
public class Vertex<E extends Edge> {

    private static final AtomicInteger idGenerator = new AtomicInteger();

    private String id;

    private Map<String, Object> data;

    private List<E> edges = new ArrayList();

    public Vertex() {
        this(Integer.valueOf(idGenerator.incrementAndGet()).toString());
    }

    public Vertex(Integer id) {
        this(id.toString());
    }

    public Vertex(String id) {
        this.id = id;
    }

    public void addEdge(E edge) {
        this.edges.add(edge);
    }

    public boolean isConnectedTo(Vertex v) {
        if (edges == null || edges.isEmpty()) {
            return false;
        }

        return edges.stream().anyMatch(e -> e.getRight().equals(v));
    }

    public Edge getConnectedEdge(Vertex v) {
        if (!isConnectedTo(v)) {
            return null;
        }

        return edges.stream().filter(e -> e.getRight().equals(v)).findFirst().get();
    }

    public Edge connectedTo(Vertex v) {
        return new BiDiEdge(this, v);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                '}';
    }
}
