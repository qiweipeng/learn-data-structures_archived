package app.segmenttree;

/**
 * Merger
 */
public interface Merger<E> {

    E merge(E a, E b);
}