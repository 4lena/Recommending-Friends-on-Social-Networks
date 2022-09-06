package PA;

public class BSTSetNode<K extends Comparable<K>> {
	public K key;
	public BSTSetNode<K> left, right, next, prev;

	public BSTSetNode(K key) {
		this.key = key;
		left = right = next = prev = null;
	}
}
