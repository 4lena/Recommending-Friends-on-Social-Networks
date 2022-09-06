package PA;

public class ArrayPQK<P extends Comparable<P>, T> implements PQK<P, T> {
    
    private int maxSize;
    private int size;
    private int head;
    private int tail;
    private Pair<P,T>[] nodes;
        	
    public ArrayPQK(int k) {
	maxSize = k;
        size = 0;
        head = tail = 0;
        nodes = new Pair[k];
    }

    public int length() {
        return size;
    }

    public void enqueue(P pr, T e) {
        Pair<P, T> obj = new Pair<P, T>(pr, e); 
             
        if(size==0) {
            nodes[tail] = obj;
            tail = (tail+1)%maxSize;
            size++;
        }

        else if(size<=maxSize){
            int bHead = (head-1+maxSize)%maxSize;
            int last = (tail-1+maxSize)%maxSize;

            if(pr.compareTo(nodes[head].first)>0){ 
                nodes[bHead] = obj;
                head = bHead; 
                if(size==maxSize)
                    tail = last;
                else
                    size++;
            }
            

            else if(pr.compareTo(nodes[last].first)<=0){
                if(size==maxSize)
                    return;
                else{
                nodes[tail] = obj;
                tail = (tail+1)%maxSize;
                size++;
                }    
            }
            
            else{
                
                int i = head;
              
                for(int j=1; nodes[i].first.compareTo(pr)>=0; j++)
                    i = (i+1)%maxSize;
                
                int shift;
                
                if(size==maxSize)
                    shift = last;
                else
                    shift = tail;
                
                int ne;
                
                while(shift != i){
                    ne = (shift-1+maxSize)%maxSize;
                    nodes[shift] = nodes[ne];
                    shift = ne;
                }
                
                nodes[i] = obj;
                
                if(size<maxSize){
                    tail = (tail+1)%maxSize;
                    size++;
                }   
            }
        }
    }
        
    public Pair<P, T> serve() {
        if(size==0)
            return null;

        Pair<P, T> node = nodes[head];
        head = (head+1)%maxSize;
        size--;
        return node;
    }
}

