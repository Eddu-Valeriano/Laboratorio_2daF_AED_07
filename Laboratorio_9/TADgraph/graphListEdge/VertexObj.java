package TADgraph.graphListEdge;


public class VertexObj<V, E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    public void setInfo(V info) {
        this.info = info;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VertexObj)) return false;
        VertexObj<?, ?> other = (VertexObj<?, ?>) obj;
        return this.info.equals(other.info);
    }

    @Override
    public String toString() {
        return info.toString();
    }
}
