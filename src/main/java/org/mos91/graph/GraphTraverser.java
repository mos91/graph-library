package org.mos91.graph;

import java.util.List;

public interface GraphTraverser<E extends Edge> {

    List<Edge> getPath(Vertex<E> from, Vertex<E> to);
}
