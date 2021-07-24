package MovingBricks.QuestionEveryday;

class Node {
    public int val = 0;
    public Node left = null;
    public Node right = null;
    public Node next = null;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
    public Node(int _val, Node _left, Node _right)
    {
        val = _val;
        left = _left;
        right = _right;
        next = null;
    }
};