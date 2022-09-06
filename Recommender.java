package PA;

import java.io.File;
import java.util.Scanner;

public class Recommender {
    
    public static<K extends Comparable<K>> int cNums(Set<K>sI, Set<K>sII){
        int count = 0;
        
        if(sI == null || sI.size() == 0 || sII == null || sII.size() == 0)
            return 0;
        
        Iterator<K> it = sI.minIt();
        
        if(!it.isValid())
            return 0;
        
        while(it.isValid()){
            K c = it.next();
            if(sII.exists(c))
                count++;
        }
      return count;  
    }
    
    public static<K extends Comparable<K>> double wcnNums(Graph<K> g, Set<K> sI, Set<K> sII){
        double count = 0;
        
        if(sI == null || sI.size() == 0 || sII == null || sII.size() == 0)
            return 0;
        
        Iterator<K> it = sI.minIt();
        
        if(!it.isValid())
            return 0.0;
        
        while(it.isValid()){
            K c = it.next();
            if(sII.exists(c))
                count += 1.0/g.deg(c);
        }
      return count;
    }
	
    public static Graph<Integer> read(String fileName) {
	try{
            
            Graph<Integer> graph = new MGraph<Integer>();
            Scanner s = new Scanner(new File(fileName));
            
            while(s.hasNextInt()){
                
                int i = s.nextInt();
                graph.addNode(i);
                
                int j = s.nextInt();
                graph.addNode(j);
                
                graph.addEdge(i,j);  
            }
            
            s.close();
            return graph;
        }
        catch(Exception e){
            return null;
        }
    }

    public static <K extends Comparable<K>> PQK<Double, K> recommendDeg(Graph<K> g, K i, int k) {
        
	if(!g.isNode(i))
            return null;
        
        PQK<Double, K> p = new ArrayPQK<Double, K>(k);
        Iterator<K> nodes = g.nodesIt();
        
        if((!nodes.isValid()) || (nodes==null))
            return null;
        
        while(nodes.isValid()){
            K node = nodes.next();
            
            if((node.compareTo(i) != 0) && (!g.isEdge(i, node)))
                p.enqueue(g.deg(node)*1.0, node);       
        }
        return p;
    }
	
    public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
        if(!g.isNode(i))
            return null;
        
        PQK<Double, K> p = new ArrayPQK<Double, K>(k);
        Iterator<K> nodes = g.nodesIt();
        
        if((!nodes.isValid()) || (nodes==null))
            return null;
        
        Set<K> neighbI = g.neighb(i);
        
        while(nodes.isValid()){
            K node = nodes.next();
            
            if((node.compareTo(i) != 0) && (!g.isEdge(i, node))){
                Set<K> neighbII = g.neighb(node);
                int cn = cNums(neighbII, neighbI);
                p.enqueue(cn*1.0, node);
            }
        }
        return p;
    }

    public static <K extends Comparable<K>> PQK<Double, K> recommendWCN(Graph<K> g, K i, int k) {
	if(!g.isNode(i))
            return null;
        
        PQK<Double, K> p = new ArrayPQK<Double, K>(k);
        Iterator<K> nodes = g.nodesIt();
        
        if((!nodes.isValid()) || (nodes==null))
            return null;
        
        Set<K> neighbI = g.neighb(i);
        
        while(nodes.isValid()){
            K node = nodes.next();
            
            if((node.compareTo(i) != 0) && (!g.isEdge(i, node))){
                Set<K> neighbII = g.neighb(node);
                double wcn = wcnNums(g, neighbII, neighbI);
                p.enqueue(wcn*1.0, node);
            }
        }
        return p;
    }
    
}

