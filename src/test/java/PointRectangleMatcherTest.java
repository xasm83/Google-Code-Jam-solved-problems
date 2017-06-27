import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointRectangleMatcherTest {
    private PointRectangleMatcher matcher;

    @Test
    public void shouldReturnEmptyOnMiss() {
        matcher = new PointRectangleMatcher();
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(1, 0, 5, 5, 0));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(2, 30, 30, 40, 20));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(3, 0, 20, 5, 15));

        Set<Integer> ids = matcher.getMatchedBoundingRectangles(100, 100);
        assertTrue(ids.isEmpty());
    }

    @Test
    public void shouldMatchBoundingRect() {
        matcher = new PointRectangleMatcher();
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(1, 0, 5, 5, 0));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(2, 30, 30, 40, 20));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(3, 0, 20, 5, 15));

        Set<Integer> ids = matcher.getMatchedBoundingRectangles(2, 2);
        assertTrue(ids.contains(1));
        assertEquals(ids.size(), 1);
    }

    @Test
    public void shouldMatchWhenManyBoundingRect() {
        matcher = new PointRectangleMatcher();
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(1, 0, 5, 5, 0));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(2, 30, 30, 40, 20));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(3, 0, 20, 5, 15));
        matcher.addBoundingRectangle(new PointRectangleMatcher.BoundingRectangle(4, 2, 2, 4, 1));

        Set<Integer> ids = matcher.getMatchedBoundingRectangles(2, 2);
        assertTrue(ids.contains(1));
        assertTrue(ids.contains(4));
        assertEquals(ids.size(), 2);
    }


    @Test
    public void shouldMatchRect() {
        matcher = new PointRectangleMatcher();
        matcher.addRectangle(1, 3, 3, 1, 6, 4);
        int id = matcher.getMatchedRectangles(5, 4);
        assertEquals(id, 0);
    }

    @Test
    public void shouldMatchWhenTwoRect() {
        matcher = new PointRectangleMatcher();
        matcher.addRectangle(1, 3, 3, 1, 6, 4);
        matcher.addRectangle(0, 8, 2, 6, 5, 8);
        int id = matcher.getMatchedRectangles(5, 4);
        assertEquals(id, 0);
        id = matcher.getMatchedRectangles(3, 8);
        assertEquals(id, 1);
    }


    @Test
    public void shouldNotMatchWhenTwoRect() {
        matcher = new PointRectangleMatcher();
        matcher.addRectangle(1, 3, 3, 1, 6, 4);
        matcher.addRectangle(0, 8, 2, 6, 5, 8);
        int id = matcher.getMatchedRectangles(1, 1);
        assertEquals(id, -1);
    }

    @Test
    public void shouldNotMatchRect() {
        matcher = new PointRectangleMatcher();
        matcher.addRectangle(1, 3, 3, 1, 6, 4);
        int id = matcher.getMatchedRectangles(7, 4);
        assertEquals(id, -1);
    }
}

