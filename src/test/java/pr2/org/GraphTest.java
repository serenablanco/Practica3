/*Copyright [2021] [Serena Blanco García]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions
and limitations under the License.*/

package pr2.org;

import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.*;

import org.junit.Test;

public class GraphTest {
    
    private Graph<String> miGrafoDeStrings;
    private Graph<Integer> g;

    @Before
    public void setup() {
        this.miGrafoDeStrings = new Graph<String>();
        this.g = new Graph<Integer>();
    }

    @Test
    public void graphExists() {
        assertNotNull(this.miGrafoDeStrings);
    }

    @Test
    public void addVertexOk() {
        boolean result = this.miGrafoDeStrings.addVertex("v1");
        assertTrue(result);
    }

    @Test
    public void addVertexFail() {
        boolean result1 = this.miGrafoDeStrings.addVertex("v1");
        boolean result2 = this.miGrafoDeStrings.addVertex("v1");
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void addEdgeOk(){
        this.miGrafoDeStrings.addVertex("v1");
        this.miGrafoDeStrings.addVertex("v2");
        boolean result = this.miGrafoDeStrings.addEdge("v1", "v2");
        assertTrue(result);
    }

    @Test
    public void addEdgeFail1(){
        this.miGrafoDeStrings.addVertex("v1");
        boolean result = this.miGrafoDeStrings.addEdge("v1", "v2");
        assertFalse(result);
    }

    @Test
    public void addEdgeFail2(){
        boolean result = this.miGrafoDeStrings.addEdge("v1", "v2");
        assertFalse(result);
    }

    @Test
    public void obtainAdjacentsOk1() throws Exception{
        this.miGrafoDeStrings.addVertex("v1");
        boolean result = this.miGrafoDeStrings.obtainAdjacents("v1").isEmpty();
        assertTrue(result);
    }

    @Test
    public void obtainAdjacentsOk2() throws Exception{
        this.miGrafoDeStrings.addVertex("v1");
        this.miGrafoDeStrings.addVertex("v2");
        this.miGrafoDeStrings.addEdge("v1", "v2");
        boolean result = this.miGrafoDeStrings.obtainAdjacents("v1").contains("v2");
        assertTrue(result);
    }

    @Test
    public void obtainAdjacentsFail1() throws Exception{
        boolean result = this.miGrafoDeStrings.obtainAdjacents("v1") != null;
        assertFalse(result);  //Usar assertNotNull?
    }
    
    @Test
    public void obtainAdjacentsFail2() throws Exception{
        this.miGrafoDeStrings.addVertex("v1");
        this.miGrafoDeStrings.addVertex("v2");
        boolean result = this.miGrafoDeStrings.obtainAdjacents("v1").contains("v2");
        assertFalse(result);
    }

    @Test
    public void containsVertexOk(){
        this.miGrafoDeStrings.addVertex("v1");
        boolean result = this.miGrafoDeStrings.containsVertex("v1");
        assertTrue(result);
    }

    @Test
    public void containsVertexFail(){
        boolean result = this.miGrafoDeStrings.containsVertex("v1");
        assertFalse(result);
    }

    @Test
    public void toStringSingleVertexOK(){
        String expectedString = "\n------GRAFO-------\nClave\tValor\nv1\t\n";
        this.miGrafoDeStrings.addVertex("v1");
        String actual = miGrafoDeStrings.toString();
        assertEquals( expectedString, actual);
    }
    @Test
    public void toStringTwoVertexOK(){
        String expectedString = "\n------GRAFO-------\nClave\tValor\nv1\tv2 \nv2\tv1 \n";
        this.miGrafoDeStrings.addVertex("v1");
        this.miGrafoDeStrings.addVertex("v2");
        this.miGrafoDeStrings.addEdge("v1", "v2"); 
        String actual = miGrafoDeStrings.toString();
        assertEquals( expectedString, actual);
    }

    @Test
    public void onePathFindsAPath(){
        System.out.println("\nTest onePathFindsAPath");
        System.out.println("----------------------");
        // Se construye el grafo.
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        // Se construye el camino esperado.
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);
        System.out.println(g.toString());
        System.out.println("Camino: " + g.onePath(1, 4));
        //Se comprueba si el camino devuelto es igual al esperado.
        assertEquals(expectedPath, g.onePath(1, 4));
    }

}
