package app.map;

/**
 * Map
 * 
 * 映射的应用
 * 
 *          key----->value
 * 字典     单词------>释意
 * 名册     身份证好--->人
 * 车辆管理  车牌号---->车
 * 数据库    id------>信息
 * 单词统计  单词----->频率
 * 
 * 映射也分有序映射和无序映射
 * 有序映射基于搜索树实现
 * 无序映射通常会使用哈希表实现
 * 
 * 集合和映射是很相似的，使用映射完全可以包装出一个集合的结构，只需不管 value 即可
 */
public interface Map<K, V> { 
    
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}