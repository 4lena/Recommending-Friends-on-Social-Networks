package PA;

public class BSTSet<K extends Comparable<K>> implements Set<K> {
    
    public BSTSetNode<K> root; // Do not change this
    int size;
  
    public BSTSet() {
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

    public boolean exists(K k) {
        BSTSetNode<K> p = root;
                        
        while(p != null){
            
            if(k.compareTo(p.key)==0)
                return true;
            
            
            else if(k.compareTo(p.key)<0)
                p = p.left;
            
            else
                p = p.right;
        }
        
        return false;
    }

    public boolean insert(K k) {
        BSTSetNode<K> node = new BSTSetNode(k);
        
        if(root == null){
            root = node;
            size++;
            return true;
        }
        
        BSTSetNode<K> p = root;
        BSTSetNode<K> q = null;
        
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
        BSTSetNode<K> p = root;
        BSTSetNode<K> rnode = p; 
        BSTSetNode<K> q = null;
        
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
                    
                    BSTSetNode<K> min = p.right;
                    q = p;
                    
                    while(min.left!= null){
                        q = min;
                        min = min.left;
                    }
                    
                    p.key = min.key;
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

    public Iterator<K> minIt() {
        BSTSetNode<K> p = root;
        
        if(p == null)
            return null;
        
        while(p.left != null)
            p = p.left;
        
        return (Iterator<K>) new setIterator<K>(p);
    }

    public Iterator<K> maxIt() {
        BSTSetNode<K> p = root;
        
        if(p == null)
            return null;
        
        while(p.right != null)
            p = p.right;
        
        return (Iterator<K>) new setIterator<K>(p);
    }
    
    public class setIterator<K extends Comparable<K>> implements Iterator<K>{
    
        BSTSetNode<K>p;

        public setIterator(BSTSetNode<K> p) {
            this.p = p;
        }

        public boolean isValid() {
            if(p != null)
                return true;
            return false;
        } 

        public K next() {
            K n = p.key;
            p = p.next;
            return n;
        }

        public K prev() {
            K pr = p.key;
            p = p.prev;
            return pr;
        }
    }
}
