package wallethub;

import org.junit.Test;

import java.io.StringReader;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

public class TopKPhrasesTest {
    @Test
    public void whenPhrasesReturnFrequency() {
        String testString = "aaa| bbb| ccc|aaa\ngg|h| ffff| bbb| aaa";
        TopKPhrasesInFile processor = new TopKPhrasesInFile(new StringReader(testString));
        Collection<String> result = processor.getTopKPhrasesInFile();
        assertArrayEquals(new String[]{"aaa", "bbb", "h", "ccc", "gg"}, result.toArray());
    }

    @Test
    public void whenEmptyReturnEmptyFrequency() {
        String testString = "";
        TopKPhrasesInFile processor = new TopKPhrasesInFile(new StringReader(testString));
        Collection<String> result = processor.getTopKPhrasesInFile();
        assertArrayEquals(new String[]{}, result.toArray());
    }

    @Test
    public void whenAscendingFrequenciesReturnFrequency() {
        String testString = "a|b|c|d\ne|a|b|c|\nd|a|b|c|a|b|a";
        TopKPhrasesInFile processor = new TopKPhrasesInFile(new StringReader(testString));
        Collection<String> result = processor.getTopKPhrasesInFile();
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, result.toArray());
    }
}
