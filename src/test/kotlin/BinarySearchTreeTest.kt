import com.example.BinarySearchTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    private lateinit var bst: BinarySearchTree<Int, String>

    @BeforeEach
    fun setup() {
        bst = BinarySearchTree()
    }

    @Test
    fun `test empty tree`() {
        assertTrue(bst.isEmpty())
        assertNull(bst.search(5))
    }

    @Test
    fun `test insert and search single element`() {
        bst.insert(10, "Value10")
        assertFalse(bst.isEmpty())
        assertEquals("Value10", bst.search(10))
        assertNull(bst.search(5))   // 없는 키 검색해보기
    }
}