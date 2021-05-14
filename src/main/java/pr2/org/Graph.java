package pr2.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Graph<V> {


    //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /******************************************************************
    * Añade el vértice ‘v‘ al grafo.
    *
    * @param v vértice a añadir.
    * @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso
    * contrario.
    ******************************************************************/
    
    public boolean addVertex(V vertice){
        if(!this.adjacencyList.containsKey(vertice)){ 
            this.adjacencyList.put(vertice, new TreeSet<V>());
            return true;
        }
        return false;
    }

    /******************************************************************
    * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. En
    * caso de que no exista alguno de los vértices, lo añade
    * también.
    *
    * @param v1 el origen del arco.
    * @param v2 el destino del arco.
    * @return ‘true‘ si no existía el arco y ‘false‘ en caso contrario.
    ******************************************************************/
        
    public boolean addEdge(V v1, V v2){
        if(!this.adjacencyList.containsKey(v2) || !this.adjacencyList.containsKey(v1)){ return false;}
        Set<V> misAdyacentesAlVertice1 = this.adjacencyList.get(v1);
        Set<V> misAdyacentesAlVertice2 = this.adjacencyList.get(v2);
        if(!misAdyacentesAlVertice1.contains(v2) && !misAdyacentesAlVertice2.contains(v1)){ 
            misAdyacentesAlVertice1.add(v2);
            misAdyacentesAlVertice2.add(v1); //Se trata de un grafo dirigido (bidireccional)
            return true;
        } 
        return false;
    }
    
    /******************************************************************
    * Obtiene el conjunto de vértices adyacentes a ‘v‘.
    *
    * @param v vértice del que se obtienen los adyacentes.
    * @return conjunto de vértices adyacentes.
    ******************************************************************/
        
    public Set<V> obtainAdjacents(V v) throws Exception{
        return this.adjacencyList.get(v); 
    }
    
    /******************************************************************
    * Comprueba si el grafo contiene el vértice dado.
    *
    * @param v vértice para el que se realiza la comprobación.
    * @return ‘true‘ si ‘v‘ es un vértice del grafo.
    ******************************************************************/
    
    public boolean containsVertex(V v){
        if(this.adjacencyList.containsKey(v)){ return true;}
        return false;
    }
    
    /******************************************************************
    * Método ‘toString()‘ reescrito para la clase ‘Grafo.java‘.
    * @return una cadena de caracteres con la lista de adyacencia
     .
    ******************************************************************/
    @Override
    public String toString(){
        String resultado = "\n------GRAFO-------\nClave\tValor\n";
        for(V clave : adjacencyList.keySet()){
            resultado += clave + "\t";
            for(V adyacente : adjacencyList.get(clave)){
                resultado += adyacente + " ";
            }
            resultado += "\n";
        }
        return resultado; 
    }

    /******************************************************************
    * Obtiene, en caso de que exista, un camino entre ‘v1‘ y ‘v2‘. En caso contrario, devuelve ‘null‘.
    *
    * @param v1 el vértice origen.
    * @param v2 el vértice destino.
    * @return lista con la secuencia de vértices desde ‘v1‘ hasta ‘v2‘ pasando por arcos del grafo.
    ******************************************************************/
   
    public List<V> onePath(V v1, V v2){
        Stack<V> abierta = new Stack<>();
        List<V> traza = new ArrayList<>(); //Lista que devuelve el camino de mi grafo

        abierta.push(v1);
        traza.add(v1);
        boolean encontrado = false;
        V v; // v es un vértice auxiliar

        //Crear variable "visitado" para saber qué vértices he visitado o no

        while(!abierta.isEmpty() && !encontrado){
            v = abierta.pop();
            if (!traza.contains(v)) traza.add(v);
            else { traza.remove(traza.size() - 1);}
            if(v == v2){ encontrado = true;}
            if(!encontrado){
                abierta.addAll(adjacencyList.get(v));
            }
        }
        if(encontrado){ 
            return traza; // lista donde mi primer elemento es v1 y el último es v2
        }else {
            return null;
        }
        
    }
}
