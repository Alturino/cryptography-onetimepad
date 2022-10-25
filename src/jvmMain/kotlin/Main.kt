// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {


    MaterialTheme {

        Column {
            Row {
                var textToEncrypt by remember { mutableStateOf("Input text to encrypt here") }
                OutlinedTextField(
                    modifier = Modifier.padding(16.dp),
                    value = textToEncrypt,
                    onValueChange = { textToEncrypt = it },
                    label = { Text("Text to encrypt") },
                    placeholder = { Text("Text To Encrypt") },
                    shape = RoundedCornerShape(4.dp),
                )

                Column {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Encrypted Text"
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = textToEncrypt
                    )
                }

            }

            var cipherText by remember { mutableStateOf("Input ciphertext in here") }
            Row {
                OutlinedTextField(
                    modifier = Modifier.padding(16.dp),
                    value = cipherText,
                    onValueChange = { cipherText = it },
                    label = { Text("Ciphertext to decrypt") },
                    placeholder = { Text("Ciphertext to decrypt") },
                    shape = RoundedCornerShape(4.dp),
                )

                Column {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Decrypted Text"
                    )
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = cipherText
                    )
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
