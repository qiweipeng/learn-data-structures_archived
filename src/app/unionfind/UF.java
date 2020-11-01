package app.unionfind;

/**
 * UF
 * 并查集是孩子指向父亲，可以高效处理连接问题 Connectivity Problem
 * 并查集可以非常快判断网络中节点间的连接状态
 * 并查集也是数学中集合类的很好实现，高效查两个集合的并
 * 
 * 并查集也不考虑添加和删除元素，只考虑当下的元素进行并或者查的操作
 * 
 *     0 1 2 3 4 5 6 7 8 9
 * id  0 1 0 1 0 1 0 1 0 1
 * 
 * 表示 0 2 4 6 8 在一个集合中，1 3 5 7 9 在另一个集合中
 */
public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}