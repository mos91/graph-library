package org.mos91.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class SubOptimalGraphTraverser<E extends Edge> implements GraphTraverser<E> {

    private Set<Vertex> visited = new HashSet<>();

    @Override
    public List<Edge> getPath(Vertex<E> from, Vertex<E> to) {
        List<Edge> path = new ArrayList();
        traverse(from, to, path);
        return path;
    }

    private void traverse(Vertex<E> from, Vertex<E> to, List<Edge> path) {
        log.info("check connectivity : {} <-> {}", from, to);
        if (visited.contains(from)) {
            log.debug("{} already visited. Exit", from);
            return;
        }

        if (from.isConnectedTo(to)) {
            log.debug("connected : {} <-> {}", from, to);
            log.debug("add new segment : {} <-> {}", from, to);
            path.add(from.getConnectedEdge(to));
            return;
        }

        visited.add(from);

        List<E> edges = from.getEdges();
        if (edges != null && !edges.isEmpty()) {
            int oldPathSize = path.size();

            for (E edge : edges) {
                Vertex<E> right = edge.getRight();

                if (!visited.contains(right)) {
                    log.debug("Walk to {}", right);
                    traverse(right, to, path);
                    if (path.size() > oldPathSize) {
                        Edge conn = from.getConnectedEdge(right);
                        path.add(conn);
                        log.debug("add new segment : {} <-> {}", conn.getLeft(), conn.getRight());
                        break;
                    }
                }
            }
        }
    }
}
