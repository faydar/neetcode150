package problems.prefix_tree;

public class Trie {

    private TrieNode root;

    class TrieNode {
        private TrieNode[] children;
        boolean isLeaf;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isLeaf = false;
        }
    }

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        var it = root;

        for (Character c : word.toCharArray()) {

            TrieNode children = it.children[c - 'a'];
            if (children == null) {
                children = new TrieNode();
                it.children[c - 'a'] = children;
            }

            it = it.children[c - 'a'];
        }

        it.isLeaf = true;
    }

    public boolean search(String word) {
        var it = root;

        for (Character c : word.toCharArray()) {
            if (it == null) {
                return false;
            }

            it = it.children[c - 'a'];
        }

        return it != null && it.isLeaf;
    }

    public boolean startsWith(String prefix) {
        var it = root;

        for (Character c : prefix.toCharArray()) {
            if (it == null) {
                return false;
            }

            it = it.children[c - 'a'];
        }

        return it != null && true;
    }

    public static void main(String[] args) {
        var t = new Trie();

        t.search("a");
    }
}
