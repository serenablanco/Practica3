# RECORRIDO DE UN GRAFO #

El propósito de este proyecto es obtener un camino en un grafo de un vértice v1 o otro v2.


### Pre-requisitos ###

* Version 11 DE Open JDK
* Saber utilizar Maven

### Comandos maven ###

* mvn validate - validar que el proyecto es correcto
* mvn compile - compilar el código del proyecto
* mvn test - testear el código compilado
* mvn verify - comprobar que los tests están integrados correctamente

### Complejidad de los métodos ###

* método addVertex: Complejidad temporal - O(1) constante - Solo se ejecuta una vez si se cumple la condición if
                    Complejidad espacial - O(n) lineal - Al añadir un vértice primero tengo que ver si ya existe
                    
* método addEdge: Complejidad temporal - O(1) constante - Solo se ejecuta una vez si se cumple la condición if
                  Complejidad espacial - O(n) lineal - Hay que comprobar si ya están unidos los vértices
                  
* método obtainAdjacents: Complejidad temporal - O(1) constante 
                          Complejidad espacial - O(n) lineal - Se necesita espacio para guardar n datos en la lista de adyacencia
                          
* método containsVertex: Complejidad temporal - O(1) constante 
                         Complejidad espacial - O(n) lineal - Se recorre la lista de adyacencia para comparar los vértices
                         
* método toString: Complejidad temporal - O(n)×O(n) = O(n^2) cuadrática - Se ejecuta n veces el primer bucle for, y n veces el segundo bucle for
                   Complejidad espacial - O(1) constante - Solo necesito guardar el resultado
                   
* método onePath: Complejidad temporal - O(n) lineal
                  Complejidad espacial - O(n) lineal

### Autores ###

Serena Blanco García
