package PA;

public class BSTMapNode<K extends Comparable<K>, T> {
	public K key;
	public T data;
	public BSTMapNode<K, T> left, right, next, prev;

	public BSTMapNode(K key, T data) {
		this.key = key;
		this.data = data;
		left = right = next = prev = null;
	}
}