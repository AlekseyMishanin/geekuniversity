package algorithm.lesson7.base;

import algorithm.lesson7.DepthFirstPaths;
import algorithm.lesson7.Graph;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.LinkedList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)       //определяем порядок запуска тестов
public class TestDepthFirstPaths extends Assert {

    private static Graph graph;
    private static DepthFirstPaths testObject;

    @BeforeClass
    public static void setUp(){

        graph = new Graph(10);
        graph.addEdge(0,1);
        graph.addEdge(0,4);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(2,6);
        graph.addEdge(2,7);
        graph.addEdge(3,7);
        graph.addEdge(6,8);
        graph.addEdge(6,9);
        graph.addEdge(3,9);
    }

    public void clear(){

        testObject = null;
    }

    //constructor with IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void test01(){
        clear();
        testObject = new DepthFirstPaths(graph, -1, true);
    }

    //constructor with IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class)
    public void test02(){
        clear();
        testObject = new DepthFirstPaths(graph, graph.vertexCount()+1, true);
    }

    //not null
    @Test()
    public void test03(){
        clear();
        testObject = new DepthFirstPaths(graph, 1, true);
        assertNotNull(testObject);
    }

    //hasPathTo with IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void test05(){
        clear();
        testObject = new DepthFirstPaths(graph, 0,true);
        testObject.hasPathTo(-1);
    }

    //hasPathTo with IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class)
    public void test06(){
        clear();
        testObject = new DepthFirstPaths(graph, 0, true);
        testObject.hasPathTo(graph.vertexCount()+1);
    }

    //method hasPathTo
    @Test()
    public void test07(){
        clear();
        testObject = new DepthFirstPaths(graph, 0,true);
        for (int i = 0; i < graph.vertexCount(); i++) {
            assertTrue(testObject.hasPathTo(i));
        }
    }

    //method pathTo
    @Test()
    public void test08(){
        clear();
        testObject = new DepthFirstPaths(graph, 0, true);
        assertTrue(testObject.pathTo(1) instanceof LinkedList);
        LinkedList<Integer> list1 = testObject.pathTo(5);
        LinkedList<Integer> list2 = testObject.pathTo(8);
        assertNotNull(list1);
        assertNotNull(list2);
        assertEquals(list1, Arrays.asList(1,2,6,5));
        assertEquals(list2, Arrays.asList(1,2,6,8));
    }

    @AfterClass
    public static void tearDown(){

        graph = null;
    }
}
