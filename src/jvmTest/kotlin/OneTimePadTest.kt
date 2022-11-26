import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class OneTimePadTest {

    private lateinit var cryptography: Cryptography

    @BeforeTest
    fun setUp() {
        cryptography = OneTimePad()
    }

    @Test
    fun testEncryptDecrypt() = runTest {

        val expected = "aku ingin dienkripsi".uppercase()
        val cipherKey = "aku ingin dienkripsi".uppercase()

        val encryptedResult = cryptography.encrypt(expected, cipherKey).first()
        val actual = cryptography.decrypt(encryptedResult, cipherKey).first()

        assertEquals(expected, actual)
    }
}