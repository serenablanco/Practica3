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

    @Before
    public void setup() {
        this.miGrafoDeStrings = new Graph<String>();
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

}