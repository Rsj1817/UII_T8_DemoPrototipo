package com.example.uii_t8_demo_de_prototipo_funcional.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.uii_t8_demo_de_prototipo_funcional.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPetScreen(
    onAcceptClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var petName by remember { mutableStateOf("Rudy") }
    var species by remember { mutableStateOf("Perro") }
    var breed by remember { mutableStateOf("Labrador Retriever") }
    var age by remember { mutableStateOf("5") }
    var gender by remember { mutableStateOf("Macho") }

    val speciesOptions = listOf("Perro", "Gato", "Ave", "Reptil", "Otro")
    val genderOptions = listOf("Macho", "Hembra", "Sin especificar")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Editar Mascota Existente") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputField(
                value = petName,
                onValueChange = { petName = it },
                label = "Nombre de la mascota",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            DropdownInput(
                selectedOption = species,
                options = speciesOptions,
                onOptionSelected = { species = it },
                label = "Especie",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            InputField(
                value = breed,
                onValueChange = { breed = it },
                label = "Raza",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            InputField(
                value = age,
                onValueChange = { age = it },
                label = "Edad (Años)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            DropdownInput(
                selectedOption = gender,
                options = genderOptions,
                onOptionSelected = { gender = it },
                label = "Género",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SecondaryButton(
                    text = "Cancelar",
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                PrimaryButton(
                    text = "Aceptar",
                    onClick = { onAcceptClick() },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
