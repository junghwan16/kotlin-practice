import com.example.Vector
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class VectorTest {

    private lateinit var vector: Vector<Int>

    @BeforeEach
    fun setUp() {
        vector = Vector() //초기 용량 설정, 필요에 따라 변경
    }

    @Test
    fun `test empty vector`() {
        assertEquals(0, vector.size())
        assertTrue(vector.isEmpty())
    }

    @Test
    fun `test pushBack and size`() {
        vector.pushBack(1)
        assertEquals(1, vector.size())
        vector.pushBack(2)
        assertEquals(2, vector.size())
    }

    @Test
    fun `test get and set`() {
        vector.pushBack(10)
        vector.pushBack(20)
        assertEquals(10, vector.get(0))
        assertEquals(20, vector.get(1))
    }

    @Test
    fun `test get out of bounds`() {
        assertThrows<IndexOutOfBoundsException> {
            vector.get(0)   // 빈 벡터에서 get() 호출
        }
        vector.pushBack(1)
        assertThrows<IndexOutOfBoundsException> {
            vector.get(1)   // 범위를 벗어난 인덱스 호출
        }
    }

    @Test
    fun `test set out of bounds`() {
        assertThrows<IndexOutOfBoundsException> {
            vector.set(0, 1)   // 빈 벡터에서 set() 호출
        }
        vector.pushBack(1)
        assertThrows<IndexOutOfBoundsException> {
            vector.set(1, 2)   // 범위를 벗어난 인덱스 호출
        }
    }

    @Test
    fun `test pushBack with capacity increase`() {
        val initialCapacity = 3 // 초기 용량을 3으로 설정
        val testVector = Vector<Int>(initialCapacity)
        for (i in 0 until initialCapacity) {
            testVector.pushBack(i)
        }
        assertEquals(initialCapacity, testVector.size())

        // capacity 넘어가도록 pushBack
        testVector.pushBack(3)
        assertEquals(initialCapacity + 1, testVector.size())
        assertEquals(3, testVector.get(initialCapacity))
    }

    @Test
    fun `test removeAt`() {
        vector.pushBack(1)
        vector.pushBack(2)
        vector.pushBack(3)

        val removed = vector.removeAt(1)
        assertEquals(2, removed)
        assertEquals(2, vector.size())
        assertEquals(1, vector.get(0))
        assertEquals(3, vector.get(1))
    }

    @Test
    fun `test removeAt out of bounds`(){
        vector.pushBack(1)
        assertThrows<IndexOutOfBoundsException> {
            vector.removeAt(1) // 범위를 벗어난 removeAt
        }
    }

    @Test
    fun `test removeAt from empty vector`(){
        assertThrows<IndexOutOfBoundsException> {
            vector.removeAt(0)  // 빈 벡터에서 removeAt
        }
    }

    @Test
    fun `test insertAt()`() {
        vector.pushBack(1)
        vector.pushBack(3)

        vector.insertAt(1, 2)   // 3이 있는 위치에 2 삽입
        assertEquals(3, vector.size())
        assertEquals(1, vector.get(0))
        assertEquals(2, vector.get(1))
        assertEquals(3, vector.get(2))

        vector.insertAt(0, 0)   // 1이 있는 위치에 0 삽입
        assertEquals(4, vector.size())
        assertEquals(0, vector.get(0))
        assertEquals(1, vector.get(1))

        vector.insertAt(4, 4)   // 벡터의 끝에 4 삽입
        assertEquals(5, vector.size())
        assertEquals(4, vector.get(4))
    }

    @Test
    fun `test insertAt out of bounds`(){
        assertThrows<IndexOutOfBoundsException> {
            vector.insertAt(1, 1) // 빈 vector에 insertAt
        }

        vector.pushBack(1)
        assertThrows<IndexOutOfBoundsException> {
            vector.insertAt(2, 2) // 범위를 벗어나는 인덱스에 insertAt
        }
    }

    @Test
    fun `test isEmpty`() {
        assertTrue(vector.isEmpty())
        vector.pushBack(1)
        assertFalse(vector.isEmpty())
    }

    @Test
    fun `test clear`() {
        vector.pushBack(1)
        vector.pushBack(2)
        vector.clear()
        assertTrue(vector.isEmpty())
        assertEquals(0, vector.size())
        assertThrows<IndexOutOfBoundsException> {
            vector.get(0)
        }
    }


    @Test
    fun `test contains`() {
        vector.pushBack(10)
        vector.pushBack(20)
        vector.pushBack(30)

        assertTrue(vector.contains(20))
        assertFalse(vector.contains(40))
    }

    @Test
    fun `test iterator`(){
        vector.pushBack(1)
        vector.pushBack(2)
        vector.pushBack(3)

        val iterator = vector.iterator()
        assertTrue(iterator.hasNext())
        assertEquals(1, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(2, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(3, iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun `test forEach loop`() {
        vector.pushBack(1)
        vector.pushBack(2)
        vector.pushBack(3)

        val result = mutableListOf<Int>()
        for (item in vector) {
            result.add(item)
        }

        assertEquals(listOf(1, 2, 3), result)
    }
}