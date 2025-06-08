package TADgraph.graphListEdge;


public class EdgeObj<V, E> {
    protected E info;
    protected VertexObj<V, E> endVertex1;
    protected VertexObj<V, E> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    public VertexObj<V, E> getEndVertex1() {
        return endVertex1;
    }

    public VertexObj<V, E> getEndVertex2() {
        return endVertex2;
    }

    public E getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    public boolean connects(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        return (endVertex1.equals(v1) && endVertex2.equals(v2)) || (endVertex1.equals(v2) && endVertex2.equals(v1));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EdgeObj)) return false;
        EdgeObj<V, E> other = (EdgeObj<V, E>) obj;
        return connects(other.endVertex1, other.endVertex2);
    }

    @Override
    public String toString() {
        return "(" + endVertex1.getInfo() + " - " + endVertex2.getInfo() + " : " + info + ")";
    }
}
