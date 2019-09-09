package org.mos91;

import org.junit.Test;
import org.mos91.graph.BiDiEdge;
import org.mos91.graph.Edge;
import org.mos91.graph.SimpleGraph;
import org.mos91.graph.Vertex;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PathTests {

    @Test
    public void shouldBuildSimplePath() {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();

        SimpleGraph simpleGraph = new SimpleGraph();
        simpleGraph.addVertex(v1);
        simpleGraph.addVertex(v2);
        simpleGraph.addEdge(new BiDiEdge(v1, v2));

        List<Edge> path = simpleGraph.getPath(v1, v2);
        assertEquals(1, path.size());

        Edge edge = path.iterator().next();
        assertEquals(v1, edge.getLeft());
        assertEquals(v2, edge.getRight());
    }

    @Test
    public void shouldBuildPathInTree() {
        Vertex root = new Vertex("root");
        Vertex left1 = new Vertex("left1");
        Vertex right1 = new Vertex("right1");
        Vertex left11 = new Vertex("left11");
        Vertex left12 = new Vertex("left12");
        Vertex right11 = new Vertex("right11");
        Vertex right12 = new Vertex("right12");

        SimpleGraph treeGraph = new SimpleGraph();
        treeGraph.addVertex(root);
        treeGraph.addVertex(left1);
        treeGraph.addVertex(right1);
        treeGraph.addVertex(left11);
        treeGraph.addVertex(left12);
        treeGraph.addVertex(right11);
        treeGraph.addVertex(right12);

        treeGraph.addEdge(new BiDiEdge(root, left1));
        treeGraph.addEdge(new BiDiEdge(root, right1));
        treeGraph.addEdge(new BiDiEdge(left1, left11));
        treeGraph.addEdge(new BiDiEdge(left1, left12));
        treeGraph.addEdge(new BiDiEdge(right1, right11));
        treeGraph.addEdge(new BiDiEdge(right1, right12));

        List<Edge> path = treeGraph.getPath(root, left1);
        assertEquals(1, path.size());
        checkEdge(path.get(0), root, left1);

        path = treeGraph.getPath(root, right1);
        assertEquals(1, path.size());
        checkEdge(path.get(0), root, right1);

        path = treeGraph.getPath(root, left11);
        assertEquals(2, path.size());
        checkEdge(path.get(0), left1, left11);
        checkEdge(path.get(1), root, left1);

        path = treeGraph.getPath(root, left12);
        assertEquals(2, path.size());
        checkEdge(path.get(0), left1, left12);
        checkEdge(path.get(1), root, left1);

        path = treeGraph.getPath(root, right11);
        assertEquals(2, path.size());
        checkEdge(path.get(0), right1, right11);
        checkEdge(path.get(1), root, right1);

        path = treeGraph.getPath(root, right12);
        assertEquals(2, path.size());
        checkEdge(path.get(0), right1, right12);
        checkEdge(path.get(1), root, right1);
    }

    private void checkEdge(Edge edge, Vertex expectedLeft, Vertex expectedRight) {
        assertEquals(expectedLeft, edge.getLeft());
        assertEquals(expectedRight, edge.getRight());
    }
}
