public class ConstructQuadTree {

    // QuadTree Node definition
    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public static Node construct(int[][] grid) {
        return helper(grid, 0, 0, grid.length);
    }

    private static Node helper(int[][] grid, int r, int c, int size) {
        if (isUniform(grid, r, c, size)) {
            return new Node(grid[r][c] == 1, true);
        }

        int half = size / 2;
        Node topLeft = helper(grid, r, c, half);
        Node topRight = helper(grid, r, c + half, half);
        Node bottomLeft = helper(grid, r + half, c, half);
        Node bottomRight = helper(grid, r + half, c + half, half);

        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    private static boolean isUniform(int[][] grid, int r, int c, int size) {
        int val = grid[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (grid[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 1, 0, 0}
        };

        Node root = construct(grid);
        System.out.println("QuadTree Constructed successfully. Root is Leaf: " + root.isLeaf);
    }
}