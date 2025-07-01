package problems.word_dictionary;

class WordDictionary {

    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode() {
            this.children = new TrieNode[27];
            this.isLeaf = false;
        }
    }

    static class MatchingTrie {

        TrieNode root;

        public MatchingTrie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            var tmp = root;

            for (Character c : word.toCharArray()) {
                var next = tmp.children[c - 'a'] != null ? tmp.children[c - 'a'] : new TrieNode();
                tmp.children[c - 'a'] = next;
                tmp = next;
            }

            tmp.isLeaf = true;
        }

        private boolean search(TrieNode root, String word, int index) {
            if (index == word.length()) {
                return root.isLeaf;
            }

            if (word.charAt(index) != '.') {
                var nextChild = root.children[word.charAt(index) - 'a'];
                if (nextChild == null) {
                    return false;
                }

                return search(nextChild, word, index + 1);
            }

            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null) {

                    if (search(root.children[i], word, index + 1)) {
                        return true;
                    }
                }
            }

            return false;
        }

        public boolean search(String word) {
            return search(this.root, word, 0);
        }

    }

    private final MatchingTrie trie;

    public WordDictionary() {
        this.trie = new MatchingTrie();
    }

    public void addWord(String word) {
        this.trie.insert(word);
    }

    public boolean search(String word) {
        var res = this.trie.search(word);
        return res;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");

        wordDictionary.search("a");
        wordDictionary.search(".ac");

        wordDictionary.addWord("bat");
        wordDictionary.search(".at");
        wordDictionary.search("an.");
        wordDictionary.search("a.d.");
        wordDictionary.search("b.");
        wordDictionary.search("a.d");
        wordDictionary.search(".");

    }
}