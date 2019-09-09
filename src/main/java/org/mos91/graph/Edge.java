package org.mos91.graph;

public interface Edge {

    Vertex getLeft();

    Vertex getRight();

    Edge inverse();

    void connect();

}
