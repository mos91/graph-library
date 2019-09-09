package org.mos91.graph;

import lombok.Getter;

@Getter
public class BiDiEdge implements Edge {

    private Vertex left;

    private Vertex right;

    public BiDiEdge(Vertex left, Vertex right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Edge inverse() {
        return new BiDiEdge(right, left);
    }

    @Override
    public void connect() {
        left.addEdge(this);
        right.addEdge(inverse());
    }

}
