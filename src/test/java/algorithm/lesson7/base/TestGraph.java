package algorithm.lesson7.base;

import algorithm.lesson7.Graph;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)       //определяем порядок запуска тестов
@RunWith(Parameterized.class)                       //параметризируем конструктор
public class TestGraph extends Assert {

    private int count;          //количество вершин графа
    private boolean bool;       //признак существования графа
    private Graph graph;        //объект графа

    public TestGraph(final int count, final boolean bool){
        this.bool = bool;
        this.count = count;
        if (bool) graph = new Graph(count);
    }

    //test constructor Graph
    @Test
    public void test01(){

        try {
            Graph graph = new Graph(count);
            assertTrue(graph != null);
            assertTrue(bool);
        } catch (IllegalArgumentException exp){
            assertFalse(bool);
        } catch (Exception exp){
            fail("Ошибка которая не предусмотрена");
        }
    }

    //test field Graph
    @Test
    public void test02(){

        if(bool){
            assertEquals(graph.vertexCount(),count);
            assertEquals(graph.edgeCount(),0);
            for (int i = 0; i < count; i++) {
                assertNotNull(graph.adjList(i));
            }
        }
    }

    //test add Edge (Not Exception)
    @Test
    public void test03(){

        if(bool){
            try{
                graph.addEdge(1,2);
                graph.addEdge(1,3);
                graph.addEdge(2,4);
                assertEquals(graph.edgeCount(), 3);
                assertEquals(graph.adjList(1).size(),2);
                assertEquals(graph.adjList(2).size(),2);
                assertEquals(graph.adjList(3).size(),1);
                assertEquals(graph.adjList(4).size(),1);
            } catch (Exception exp){
                fail("Ошибка которая не предусмотрена");
            }
        }
    }

    //test add Edge (with Exception)
    @Test(expected = IllegalArgumentException.class)
    public void test04(){

        if(bool){
            graph.addEdge(-1,-1);
        } else {
            throw new IllegalArgumentException();
        }
    }

    //test add Edge (with Exception)
    @Test(expected = IndexOutOfBoundsException.class)
    public void test05(){

        if(bool){

            int countException = 0;

            try{
                graph.addEdge(graph.vertexCount(),1);
            } catch (IndexOutOfBoundsException exp1){
                countException++;
            }

            try{
                graph.addEdge(1,graph.vertexCount());
            } catch (IndexOutOfBoundsException exp1){
                countException++;
            }

            try{
                graph.addEdge(graph.vertexCount(),graph.vertexCount());
            } catch (IndexOutOfBoundsException exp1){
                countException++;
            }

            if(countException == 3) {
                throw new IndexOutOfBoundsException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Parameterized.Parameters
    public static List<Object[]> isData(){
        return Arrays.asList(new Object[][]{
                {5, true},
                {-2, false},
                {-1, false},
                {10, true}
        });
    }

}
