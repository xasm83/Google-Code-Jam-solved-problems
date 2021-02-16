import java.util.Objects;

class Line {
    Double k;
    Double b;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return k.equals(line.k) &&
                Objects.equals(b, line.b);
    }

    @Override
    public String toString() {
        return "Line{" +
                "k=" + k +
                ", b=" + b +
                '}';
    }

    public Line(Double k, Double b) {
        this.k = k;
        this.b = b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, b);
    }
}