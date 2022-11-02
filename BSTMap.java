package PA;

public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
    
    public BSTMapNode<K, T> root; // Do not change this
    int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean full() {
        return false;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean update(K k, T e) {
        if(root==null) 
            return false;
        
        BSTMapNode<K, T> p = root;
       
        while(p != null){
            if(k.compareTo(p.key)==0){
                p.data = e;
                return true;
            }
            
            else if(k.compareTo(p.key)<0)
                p = p.left;
            
            else
                p = p.right;
        }
        return false; 
    }

    public Pair<Boolean, T> retrieve(K k) {
        Pair<Boolean, T> pair = new Pair<Boolean, T>(false, null);
        
        if(root==null) 
            return pair;
        
        BSTMapNode<K, T> p = root;
        
        while(p != null){
            
            if(k.compareTo(p.key)==0){
                pair.first = true;
                pair.second = p.data;
                return pair;
            }
            
            else if(k.compareTo(p.key)<0)
                p = p.left;
            
            else
                p = p.right;
        }
        
        return pair;
    }

    public boolean insert(K k, T e) { 
        BSTMapNode<K, T> node = new BSTMapNode<K, T>(k,e);
        
        if(root == null){
            root = node;
            size++;
            return true;
        }
        
        BSTMapNode<K, T> p = root;
        BSTMapNode<K, T> q = null;
        
        while(p != null){
            q = p;
    
            if(k.compareTo(p.key)==0)
                return false;
            
            else if(k.compareTo(p.key)<0)
                p = p.left;
            
            else
                p = p.right;       
        }
        
        if(k.compareTo(q.key)<0){
            q.left = node;
            node.next = q;
            node.prev = q.prev;
            
            if(q.prev != null)
                q.prev.next = node;
            
            q.prev = node;  
        }
            
        else{
            q.right = node;
            node.prev = q;
            node.next = q.next;
            
            if(q.next != null)
                q.next.prev = node;
            
            q.next = node;
        }
            
            size++;
            return true;
    }

    public boolean remove(K k) {
        
        K kI = k;
        BSTMapNode<K, T> p = root;
        BSTMapNode<K, T> rnode = p; 
        BSTMapNode<K, T> q = null;
        
        while(p != null){
            
            int kII = kI.compareTo(p.key);
            
            if(kII<0){
                q = p;
                p = p.left;
            }
           
            else if(kII>0){
                q = p;
                p = p.right;
            }
            
            else{
                
                if((p.left != null)&&(p.right != null)){
                    
                    BSTMapNode<K, T> min = p.right;
                    q = p;
                    
                    while(min.left!= null){
                        q = min;
                        min = min.left;
                    }
                    
                    p.key = min.key;
                    p.data = min.data;
                    kI = min.key;
                    p = min;
                }
                
                rnode = p;
                
                if(p.left != null)
                    p = p.left;
                else
                    p = p.right;
                
                if(q == null)
                    root = p;
                else if(kI.compareTo(q.key)<0)
                        q.left = p;
                    else
                        q.right = p;
                
                if(rnode.next!=null)
                    rnode.next.prev = rnode.prev;
                if(rnode.prev!=null)
                    rnode.prev.next = rnode.next;
                
                size--;
                return true; 
            }
        }
        return false;
    }

    public Iterator<Pair<K, T>> minIt() {
        BSTMapNode<K, T> p = root;
        
        if(p == null)
            return null;
        
        while(p.left != null)
            p = p.left;
        
        return (Iterator<Pair<K, T>>) new mapIterator<K, T>(p);
    }

    public Iterator<Pair<K, T>> maxIt() {
        BSTMapNode<K, T> p = root;
        
        if(p == null)
            return null;
        
        while(p.right != null)
            p = p.right;
        
        return (Iterator<Pair<K, T>>) new mapIterator<K, T>(p);
    }
    
    public class mapIterator<K extends Comparable<K>,T> implements Iterator<T>{
    
        BSTMapNode<K, T> p;
        Pair<K, T> pair = new Pair<K, T>(null, null);

        public mapIterator(BSTMapNode<K, T> p) {
            this.p = p;
        }
 
        public boolean isValid() {
            if(p != null)
                return true;
            return false;
        }

        public T next() {
            pair.first = p.key;
            pair.second = p.data;
            p = p.next;
            return (T)pair;
        }

        public T prev() {
            pair.first = p.key;
            pair.second = p.data;
            p = p.prev;
            return (T)pair;
        }
    }
}