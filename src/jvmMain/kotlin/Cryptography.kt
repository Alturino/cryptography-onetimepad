import kotlinx.coroutines.flow.Flow

interface Cryptography {
    fun encrypt(textToEncrypt: String, cipherKey: String): Flow<String>
    fun decrypt(cipherText: String, cipherKey: String): Flow<String>
}