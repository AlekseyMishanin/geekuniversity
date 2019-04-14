package algorithm.lesson7;

import algorithm.lesson7.base.TestBredthFirstPaths;
import algorithm.lesson7.base.TestDepthFirstPaths;
import algorithm.lesson7.base.TestGraph;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestGraph.class,
        TestBredthFirstPaths.class,
        TestDepthFirstPaths.class
})
public class TestAllClass {
}
