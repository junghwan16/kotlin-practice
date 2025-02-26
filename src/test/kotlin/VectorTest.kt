import com.example.Vector
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class VectorTest {

    // lateinit 키워드를 사용하여 나중에 초기화
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
}