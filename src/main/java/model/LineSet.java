package model;

import java.util.HashSet;

/**
 * Set that de-dupes same {@link Line} that have different signed co-efficients.
 */
public class LineSet<L extends Line> extends HashSet<L> {
    @Override
    public boolean add(L l) {
        if (!this.contains(l.getLineSignsFlipped())) {
            return super.add(l);
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Line)) {
            return false;
        }
        final Line l = (Line) o;
        return super.contains(l) || super.contains(l.getLineSignsFlipped());
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Line)) {
            return false;
        }
        final Line l = (Line) o;
        return super.remove(l) || super.remove(l.getLineSignsFlipped());
    }

}
