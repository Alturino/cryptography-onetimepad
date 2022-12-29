import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OneTimePad : Cryptography {

    override fun encrypt(textToEncrypt: String, cipherKey: String): Flow<String> = flow {
        if (textToEncrypt.isBlank() or textToEncrypt.isEmpty())
            return@flow

        val cipher = cipherKey.mapIndexed { index, char ->
            textToEncrypt[index].code - 'A'.code + char.code - 'A'.code
        }

        val key = cipher.map { char ->
            if (char > 25)
                char - 26
            else
                char
        }

        val cipherText = buildString {
            key.forEach {
                append((it + 'A'.code).toChar())
            }
        }

        emit(cipherText)
    }

    override fun decrypt(cipherText: String, cipherKey: String): Flow<String> = flow {
        if (cipherText.isBlank() or cipherText.isEmpty() or cipherKey.isBlank() or cipherKey.isEmpty())
            return@flow

        val plain = cipherKey.mapIndexed { index, char ->
            cipherText[index].code - 'A'.code - (char.code - 'A'.code)
        }

        val key = plain.map { char ->
            if (char < 0)
                char + 26
            else
                char
        }

        val plainText = buildString {
            key.forEach {
                append((it + 'A'.code).toChar())
            }
        }

        emit(plainText)
    }
}