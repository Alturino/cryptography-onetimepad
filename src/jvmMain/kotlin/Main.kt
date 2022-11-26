// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App(viewModel: MainViewModel) {
    MaterialTheme {
        val encryptedText by viewModel.encryptedText.collectAsState()
        val decryptedText by viewModel.decryptedText.collectAsState()
        Column {
            Row {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    modifier = Modifier.padding(16.dp),
                    value = viewModel.textToEncrypt,
                    onValueChange = { viewModel.encryptText(it) },
                    label = { Text("Text to encrypt") },
                    placeholder = { Text("Text To Encrypt") },
                    shape = RoundedCornerShape(4.dp),
                )

                Column {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Encrypted Text"
                    )
                    SelectionContainer {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = encryptedText
                        )
                    }
                }

            }

            Row {
                Column {
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        modifier = Modifier.padding(16.dp),
                        value = viewModel.cipherText,
                        onValueChange = { viewModel.renewCipherText(it) },
                        label = { Text("Ciphertext to decrypt") },
                        placeholder = { Text("Ciphertext to decrypt") },
                        shape = RoundedCornerShape(4.dp),
                    )

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Cipher Key"
                    )

                    SelectionContainer {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = viewModel.textToEncrypt
                        )
                    }

                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                        modifier = Modifier.padding(16.dp),
                        value = viewModel.textToEncrypt,
                        onValueChange = { viewModel.encryptText(it) },
                        label = { Text("Cipherkey") },
                        placeholder = { Text("Cipherkey") },
                        shape = RoundedCornerShape(4.dp),
                    )
                }

                Column {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Decrypted Text"
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = decryptedText
                    )
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val cryptography: Cryptography = OneTimePad()
        App(viewModel = MainViewModel(cryptography))
    }
}
