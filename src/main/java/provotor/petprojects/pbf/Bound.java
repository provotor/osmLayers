package provotor.petprojects.pbf;

import java.util.Objects;

public class Bound {
    private Double left;
    private Double right;
    private Double bottom;
    private Double top;

    public Bound() {
    }

    public Bound(Double left, Double right, Double bottom, Double top) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getBottom() {
        return bottom;
    }

    public void setBottom(Double bottom) {
        this.bottom = bottom;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bound bound = (Bound) o;
        return Objects.equals(left, bound.left) &&
                Objects.equals(right, bound.right) &&
                Objects.equals(bottom, bound.bottom) &&
                Objects.equals(top, bound.top);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, bottom, top);
    }

    @Override
    public String toString() {
        return "Bound{" +
                "left=" + left +
                ", right=" + right +
                ", bottom=" + bottom +
                ", top=" + top +
                '}';
    }
}
