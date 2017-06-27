package wallethub;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//uses trie to store phrases ans TreeMap to store top frequencies,
// TreeMap could have been replaced with custom bounded priority queue
//
// everything is stream based so file size won't be a problem
// there could be other options depending on phrases uniqueness and size - eg if only a small set of phrases then Map could be used directly
// we can also hash phrases and store all of them in memory in case it is OK to lose precision due to hash collisions
//
// there is a trade off in evicting existing frequency of a phrase(to update top frequency of a phrase)
// versus evicting lowest top frequency to free up space - ie Set vs List,
// I chose to optimize updates  and use Set, this choice should depend on data nature
//
public class TopKPhrasesInFile {
    private static final int TOP_PHRASES_AMOUNT = 5;
    private static final String PHRASE_SEPARATOR = "\\|";

    private NavigableMap<Integer, Set<String>> frequencies = new TreeMap<>();
    private Trie trie = new Trie();
    private Map<String, Integer> frequencyByPhrase = new HashMap<>();
    private BufferedReader reader;
    private int currentFrequenciesSize;

    //method to be used as an entry point with FileReader or StringReader
    public TopKPhrasesInFile(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    //we store map of lists as phrases could have equal frequencies
    public Collection<String> getTopKPhrasesInFile() {
        reader.lines().
                flatMap(line -> Stream.of(line.split(PHRASE_SEPARATOR))).
                map(String::trim).
                filter(item -> !item.isEmpty()).
                forEach(this::updateFrequency);
        List<String> result = frequencies.values().stream().
                flatMap(Collection::stream).
                collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }

    private void updateFrequency(String phrase) {
        int frequency = trie.insert(phrase);
        Map.Entry<Integer, Set<String>> lowestEntry = frequencies.firstEntry();
        int lowestTopFrequency = lowestEntry != null ? lowestEntry.getKey() : 0;
        if (currentFrequenciesSize < TOP_PHRASES_AMOUNT || lowestTopFrequency < frequency) {
            //evict existing top frequency
            if (frequencyByPhrase.containsKey(phrase)) {
                int previousFrequency = frequencyByPhrase.remove(phrase);
                Set<String> listForPreviousFrequency = frequencies.get(previousFrequency);
                listForPreviousFrequency.remove(phrase);
                currentFrequenciesSize--;
            }
            //evict lowest top frequency
            else if (currentFrequenciesSize == TOP_PHRASES_AMOUNT) {
                Set<String> smallestTopFrequencyPhrases = frequencies.get(lowestTopFrequency);

                //remove first phrase in a list, this avoids list scan but breaks phrases order for the same frequency
                String evictedPhrase = smallestTopFrequencyPhrases.iterator().next();
                smallestTopFrequencyPhrases.remove(evictedPhrase);
                frequencyByPhrase.remove(evictedPhrase);
                if (smallestTopFrequencyPhrases.size() == 0) {
                    frequencies.remove(lowestTopFrequency);
                }
                currentFrequenciesSize--;
            }

            //add newly calculated frequency to top frequencies
            Set<String> listToAdd = frequencies.get(frequency);
            if (listToAdd == null) {
                listToAdd = new HashSet<>();
                frequencies.put(frequency, listToAdd);
            }
            listToAdd.add(phrase);
            frequencyByPhrase.put(phrase, frequency);
            currentFrequenciesSize++;

        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * @param word
         * @return current frequency of inserted word
         */
        public int insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                byte index = (byte) word.charAt(i);
                if (p.arr[index] == null) {
                    TrieNode temp = new TrieNode();
                    p.arr[index] = temp;
                    p = temp;
                } else {
                    p = p.arr[index];
                }
            }
            p.isEnd = true;
            p.frequency = p.frequency + 1;
            return p.frequency;
        }

        class TrieNode {
            TrieNode[] arr;
            boolean isEnd;
            int frequency;

            public TrieNode() {
                this.arr = new TrieNode[128];
            }
        }
    }
}
