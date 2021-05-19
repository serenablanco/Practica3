/*Copyright [2021] [Serena Blanco García]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package pr2.org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Graph<V> {


    //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /******************************************************************
    * Añade el vértice ‘v‘ al grafo.
    * Complejidad temporal: O(1) constante - Solo se ejecuta una vez si se cumple la condición if
    * Complejidad espacial: O(n) lineal - Al añadir un vértice primero tengo que ver si ya existe
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
    * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. 
    * Se trata de un grafo bidireccional
    * Complejidad temporal: O(1) constante - Solo se ejecuta una vez si se cumple la condición if
    * Complejidad espacial: O(n) lineal - Hay que comprobar si ya están unidos los vértices
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
    * Complejidad temporal: O(1) constante 
    * Complejidad espacial: O(n) lineal - Se necesita espacio para guardar n datos en la lista de adyacencia
    *
    * @param v vértice del que se obtienen los adyacentes.
    * @return conjunto de vértices adyacentes.
    ******************************************************************/
        
    public Set<V> obtainAdjacents(V v) throws Exception{
        return this.adjacencyList.get(v); 
    }
    
    /******************************************************************
    * Comprueba si el grafo contiene el vértice dado.
    * Complejidad temporal: O(1) constante 
    * Complejidad espacial: O(n) lineal - Se recorre la lista de adyacencia para comparar los vértices
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
    * Complejidad temporal: O(n)*O(n) = O(n^2) cuadrática - Se ejecuta n veces el primer bucle for, y n veces el segundo bucle for
    * Complejidad espacial: O(1) constante - Solo necesito guardar el resultado
    *
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
    * Complejidad temporal: O(n) lineal
    * Complejidad espacial: O(n) lineal 
    *
    * @param v1 el vértice origen.
    * @param v2 el vértice destino.
    * @return lista con la secuencia de vértices desde ‘v1‘ hasta ‘v2‘ pasando por arcos del grafo.
    ******************************************************************/
   
    public List<V> onePath(V v1, V v2){
        Stack<V> abierta = new Stack<>();
        Map<V, V> traza = new HashMap<>(); 
        List<V> caminoGrafo = new ArrayList<>();

        abierta.push(v1);
        boolean encontrado = false;
        V verticeActual = null;
        V verticeAnterior = null; 
        traza.put(verticeActual, null);
        while(!abierta.isEmpty() && !encontrado){
            verticeActual = abierta.pop();
            if(verticeAnterior != null && adjacencyList.get(verticeAnterior).contains(verticeActual)){
                traza.put(verticeActual, verticeAnterior);
            } else{
                for(V predecesor : traza.keySet()){
                    if(predecesor != null && adjacencyList.get(predecesor).contains(verticeActual)) {
                        verticeAnterior = predecesor;
                        traza.put(verticeActual, verticeAnterior);
                    }
                }
            }
            if(verticeActual.equals(v2)){ encontrado = true;}
            if(!encontrado){
                for (V adjacentes : adjacencyList.get(verticeActual)) {
                    if(!traza.containsKey(adjacentes)) {   
                        abierta.push(adjacentes);
                    }
                }  
            }
            verticeAnterior = verticeActual;
        }
        if(encontrado){ 
            while (verticeActual != null){
                caminoGrafo.add(verticeActual);
                verticeActual = traza.get(verticeActual);
            }
            Collections.reverse(caminoGrafo);
            return caminoGrafo; // lista donde mi primer elemento es v1 y el último es v2
        }else {
            return null; 
        } 
    }
}
