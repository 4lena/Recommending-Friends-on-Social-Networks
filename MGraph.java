package PA;

public class MGraph<K extends Comparable<K>> implements Graph<K> {
    
    public Set<K> nodes; // Do not change this
    public Map<K, Set<K>> adj; // Do not change this
    Iterator iiterator;

    public MGraph() {
        nodes = new BSTSet<K>();
        adj = new BSTMap<K, Set<K>>();
    }

    public boolean addNode(K i) {
        Set<K> adj1 = new BSTSet<>();
        
        if(nodes.exists(i))
            return false;
        
        adj.insert(i, adj1);
        nodes.insert(i);
        return true;
    }

    public boolean isNode(K i) {
        return (nodes.exists(i));
    }

    public boolean addEdge(K i, K j) {
        
        if((!nodes.exists(i)) || (!nodes.exists(j)))
            return false;
        
        Pair<Boolean, Set<K>> pair = adj.retrieve(i);
        
        if(pair.second.exists(j))
            return false;
        
        pair.second.insert(j);
        
        Pair<Boolean, Set<K>> pairII = adj.retrieve(j);
        
        if(pairII.second.exists(i))
            return false;
        
        pairII.second.insert(i);
        
        return true;
    }

    public boolean isEdge(K i, K j) {
        if((!nodes.exists(i)) || (!nodes.exists(j)))
            return false;
        
        Pair <Boolean, Set<K>> pair = adj.retrieve(i);
        
        if(!pair.second.exists(j))
            return false;
        
        Pair <Boolean, Set<K>> pairII = adj.retrieve(j);
        
        if(!pairII.second.exists(i))
            return false;
        
        return true;
    }

    public Set<K> neighb(K i) {
        Pair<Boolean,Set<K>> pair = adj.retrieve(i);
        return pair.second;
    }

    public int deg(K i) {
        Pair<Boolean,Set<K>> pair = adj.retrieve(i);
        
        if(pair.first == false)
            return -1;
        
        return pair.second.size();
    }

    public Iterator<K> nodesIt() {
        MGraphIterator<K> iterator = new MGraphIterator<K>();
        return iterator;
    }
    
    public class  MGraphIterator<K> implements Iterator<K> {
                
        public MGraphIterator() {
            iiterator = nodes.minIt();
        }
                
        public boolean isValid() {
            if (iiterator.isValid())
                return true;
            return false;
        }

        public K next() {
            return (K) iiterator.next();
        }

        public K prev() {
            return (K) iiterator.prev();
        }
    }
	
}
