package MovingBricks.QuestionEveryday;

import java.util.TreeMap;

public class Trie {
    /** Initialize your data structure here. */
    public static void main(String[] args){
        Trie root =new Trie();
        String[] strings = {"apple","app","apples"};
        for(String e:strings)
            root.insert(e);
        System.out.println(root.startsWith("apple"));
    }
    Trie[] dict;
    boolean isEnd;
    public Trie() {
        dict = new Trie[26];
        isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for(char c:word.toCharArray()){
            int index = c-'a';
            if(node.dict[index] == null){
                node.dict[index] = new Trie();
            }
            node = node.dict[index];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = startSearch(word);
        return node!=null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = startSearch(prefix);
        return node != null;
    }
    public Trie startSearch(String prefix){
        Trie node = this;
        for(char c:prefix.toCharArray()){
            node = node.dict[c-'a'];
            if(node == null)
                return null;
        }
        return node;
    }
}
