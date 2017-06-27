import java.util.*;
/*
        There are 1 million rectangles,
        create method to verify that a point is inside of one of the rectangles
        there could be millions of such requests.
        Two interval trees are used to match x and y point projections. Logarithmic complexity.
        There is a more optimal solution with R-trees.
        Less memory could be used in case a rectangle is defined by two points.
 */
public class PointRectangleMatcher {
    private int lastRectangleId;
    private Map<Integer, Rectangle> rectangles = new HashMap<>();
    private IntervalTree xTree = new IntervalTree();
    private IntervalTree yTree = new IntervalTree();

    public int getMatchedRectangles(int x, int y) {
        Set<Integer> matchedRectIds = getMatchedBoundingRectangles(x, y);
        return matchedRectIds.stream().
                filter(id -> checkIfPointMatchesRectangle(x, y, rectangles.get(id))).
                findFirst().orElseGet(() -> -1);
    }

    /*
    Assuming the rectangle is represented by three points A,B,C, with AB and BC perpendicular,
    to check if a point is inside the rectangle you  need to check the projections of the query point M on AB and BC:
        0 <= dot(AB,AM) <= dot(AB,AB) &&
        0 <= dot(BC,BM) <= dot(BC,BC)
    AB is vector AB, with coordinates (Bx-Ax,By-Ay), and dot(U,V) is the dot product of vectors U and V: Ux*Vx+Uy*Vy.
     */
    private boolean checkIfPointMatchesRectangle(int x, int y, Rectangle rect) {
        long abam = dotProduct(rect.getX2() - rect.getX1(), rect.getY2() - rect.getY1(), x - rect.getX1(), y - rect.getY1());
        long abab = dotProduct(rect.getX2() - rect.getX1(), rect.getY2() - rect.getY1(), rect.getX2() - rect.getX1(), rect.getY2() - rect.getY1());
        long bcbm = dotProduct(rect.getX3() - rect.getX2(), rect.getY3() - rect.getY2(), x - rect.getX2(), y - rect.getY2());
        long bcbc = dotProduct(rect.getX3() - rect.getX2(), rect.getY3() - rect.getY2(), rect.getX3() - rect.getX2(), rect.getY3() - rect.getY2());
        return abam >= 0 && abam <= abab && bcbm >= 0 && bcbc >= bcbm;
    }

    private long dotProduct(int x1, int y1, int x2, int y2) {
        return x1 * x2 + y1 * y2;
    }

    public Set<Integer> getMatchedBoundingRectangles(int x, int y) {
        Set<Integer> matchedRectIds = xTree.matchPointToInterval(x);
        matchedRectIds.retainAll(yTree.matchPointToInterval(y));
        return matchedRectIds;
    }

    public void addBoundingRectangle(BoundingRectangle boundingRectangle) {
        xTree.insertNode(new Interval(boundingRectangle.getxTopLeft(),
                boundingRectangle.getxBottomRight(),
                boundingRectangle.getRectId()));
        yTree.insertNode(new Interval(boundingRectangle.getyBottomRight(),
                boundingRectangle.getyTopLeft(),
                boundingRectangle.getRectId()));
    }

    public int addRectangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        int rectId = lastRectangleId++;
        Rectangle rect = new Rectangle(x1, y1, x2, y2, x3, y3);
        rectangles.put(rectId, rect);
        BoundingRectangle boundRect = getBoundingRect(rect, rectId);
        addBoundingRectangle(boundRect);
        return rectId;
    }

    private BoundingRectangle getBoundingRect(Rectangle rect, int rectId) {
        // x4 is always in x1-x3 interval
        int y4 = rect.getY3() + rect.getY1() - rect.getY2();
        return new BoundingRectangle(rectId, rect.getX1(), y4, rect.getX3(), rect.getY2());
    }

    /*
      3 points are used to define a rectangle
      just to avoid extra mangling with vectors it is assumed that:
         (x1y1,x2y2) is orthogonal to (x2y2,x3,y3)
         x1=<x2=<x3, y3<=y2<=y1
         x1<=x4<=x3, y4=>y1, y4>=y2, y4>=y3
    */
    class Rectangle {
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private int x3;
        private int y3;


        public Rectangle(int x1, int y1, int x2, int y2, int x3, int y3) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.x3 = x3;
            this.y3 = y3;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }

        public int getX3() {
            return x3;
        }

        public int getY3() {
            return y3;
        }
    }

    static class BoundingRectangle {
        private int rectId;
        private long xTopLeft;
        private long yTopLeft;
        private long xBottomRight;
        private long yBottomRight;

        public BoundingRectangle(int rectId, int xTopLeft, int yTopLeft, int xBottomRight, int yBottomRight) {
            this.rectId = rectId;
            this.xTopLeft = xTopLeft;
            this.yTopLeft = yTopLeft;
            this.xBottomRight = xBottomRight;
            this.yBottomRight = yBottomRight;
        }

        public int getRectId() {
            return rectId;
        }

        public long getxTopLeft() {
            return xTopLeft;
        }

        public long getyTopLeft() {
            return yTopLeft;
        }

        public long getxBottomRight() {
            return xBottomRight;
        }

        public long getyBottomRight() {
            return yBottomRight;
        }
    }

    class Interval implements Comparable<Interval> {
        private long start;
        private long end;
        private long max;
        private Interval left;
        private Interval right;
        private int rectId;

        public Interval(long start, long end, int rectId) {
            this(start, end);
            this.rectId = rectId;
        }

        public Interval(long start, long end) {
            this.start = start;
            this.end = end;
            this.max = end;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }

        public Interval getLeft() {
            return left;
        }

        public void setLeft(Interval left) {
            this.left = left;
        }

        public Interval getRight() {
            return right;
        }

        public void setRight(Interval right) {
            this.right = right;
        }

        @Override
        public int compareTo(Interval i) {
            if (this.start < i.start) {
                return -1;
            } else if (this.start == i.start) {
                return this.end <= i.end ? -1 : 1;
            } else {
                return 1;
            }
        }
    }

    class IntervalTree {
        Interval root;

        public void insertNode(Interval newNode) {
            if (root == null) {
                root = newNode;
            } else {
                insertNode(root, newNode);
            }
        }

        private void insertNode(Interval tmpNode, Interval newNode) {
            Deque<Interval> stack = new LinkedList<>();

            while (tmpNode != null) {
                if (newNode.getEnd() > tmpNode.getMax()) {
                    tmpNode.setMax(newNode.getEnd());
                }

                if (tmpNode.compareTo(newNode) <= 0) {
                    if (tmpNode.getRight() == null) {
                        tmpNode.setRight(newNode);
                    } else {
                        stack.push(tmpNode.getRight());
                    }
                } else {
                    if (tmpNode.getLeft() == null) {
                        tmpNode.setLeft(newNode);
                    } else {
                        stack.push(tmpNode.getLeft());
                    }
                }
                tmpNode = stack.poll();
            }
        }

        public Set<Integer> matchPointToInterval(int point) {
            Set<Integer> matchedRectIds = new HashSet<>();
            matchPointToInterval(root, point, matchedRectIds);
            return matchedRectIds;
        }

        private void matchPointToInterval(Interval target, int point, Set<Integer> res) {
            Deque<Interval> deque = new LinkedList<>();
            while (target != null) {
                if (!((target.getStart() > point) || (target.getEnd() < point))) {
                    res.add(target.rectId);
                }
                deque.push(target.getRight());

                if ((target.getLeft() != null) && (target.getLeft().getMax() >= point)) {
                    deque.push(target.getLeft());
                }
                target = deque.poll();
            }
        }
    }
}

