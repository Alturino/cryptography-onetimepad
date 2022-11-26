import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlin.coroutines.EmptyCoroutineContext

class MainViewModel(private val cryptography: Cryptography) {
    private val coroutineScope = CoroutineScope(EmptyCoroutineContext)

    var textToEncrypt by mutableStateOf("")
        private set

    fun encryptText(text: String) {
        textToEncrypt = text.uppercase()
    }

    val encryptedText: StateFlow<String> = snapshotFlow { textToEncrypt }
        .debounce(2000)
        .flatMapLatest { cryptography.encrypt(it, it) }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    var cipherText by mutableStateOf("")
        private set

    fun renewCipherText(text: String) {
        cipherText = text.uppercase()
    }

    val decryptedText: StateFlow<String> = snapshotFlow { cipherText.uppercase() }
        .debounce(2000)
        .flatMapLatest { cryptography.decrypt(it, textToEncrypt) }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )
}