import org.example.exception.ListIndexOutOfBoundsException;
import org.example.MyArrayListImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {

    private MyArrayListImpl out = new MyArrayListImpl(5);


    @BeforeEach
    public void testList() {
        out.add(1);
        out.add(2);
        out.add(3);
        out.add(4);
        out.add(5);
    }

    @AfterEach
    public void clearList() {
        out.clear();
    }

    @Test
    public void simpleAdditionPositiveTest() {
        int size = out.size();
        assertEquals(6, out.add(6));
        assertEquals(size + 1, out.size());
    }

    @Test
    public void indexAdditionPositiveTest() {
        int size = out.size();
        int index = 1;
        assertEquals(6, out.add(index, 6));
        assertEquals(index, out.indexOf(6));
        assertEquals(size + 1, out.size());
    }

    @Test
    public void indexAdditionNegativeTest() {
        assertThrows(ListIndexOutOfBoundsException.class, () -> out.add(5, 6));
    }

    public static Stream<Arguments> argumentsForGetPositiveTest() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(4, 3),
                Arguments.of(5, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForGetPositiveTest")
    public void getPositiveTest(Integer i, int index) {
        assertEquals(i, out.get(index));
    }

    @Test
    public void getNegativeTest() {
        assertThrows(ListIndexOutOfBoundsException.class, () -> out.get(5));
    }

    @Test
    public void removeByIndexPositiveTest() {
        int size = out.size();
        assertEquals(1, out.remove(0));
        assertEquals(size - 1, out.size());
    }

    @Test
    public void removeByIndexNegativeTest() {
        assertThrows(ListIndexOutOfBoundsException.class, () -> out.remove(5));
    }
}
