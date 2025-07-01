package problems.min_stack;

class MinStack {

    private static final int DEFAULT_STACK_SIZE = 11;
    private Integer[] elems;
    private Integer[] mins;

    int pointer;

    public MinStack() {
        this.elems = new Integer[DEFAULT_STACK_SIZE];
        this.mins = new Integer[DEFAULT_STACK_SIZE];

        this.pointer = -1;
    }

    private void expand() {
        var newElems = new Integer[elems.length * 2];
        var newMins = new Integer[mins.length * 2];

        System.arraycopy(elems, 0, newElems, 0, elems.length);
        System.arraycopy(mins, 0, newMins, 0, mins.length);

        this.elems = newElems;
        this.mins = newMins;
    }

    public void push(int val) {
        if (pointer == elems.length - 1) {
            expand();
        }

        this.elems[++pointer] = val;

        int min = pointer >= 1 ? this.mins[pointer - 1] : Integer.MAX_VALUE;
        this.mins[pointer] = Math.min(min, val);
    }

    public void pop() {
        if (pointer == -1) {
            throw new IndexOutOfBoundsException();
        }

        this.elems[pointer--] = null;
    }

    public int top() {
        if (pointer == -1) {
            throw new RuntimeException();
        }

        return this.elems[pointer];
    }

    public int getMin() {
        return this.mins[pointer];
    }

    public static void main(String[] args) {
        var ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);

        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.getMin());
    }
}