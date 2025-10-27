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
    // Valores iniciales de ejemplo (sin funcionalidad real)
    var petName by remember { mutableStateOf("Rudy") }
    var species by remember { mutableStateOf("Perro") }
    var breed by remember { mutableStateOf("Labrador Retriever") }
    var age by remember { mutableStateOf("5 Años") }
    var gender by remember { mutableStateOf("Macho") }

    val speciesOptions = listOf("Perro", "Gato", "Ave", "Reptil", "Otro")
    val genderOptions = listOf("Macho", "Hembra", "Sin especificar")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Mascota Existente") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre de la mascota
            InputField(
                value = petName,
                onValueChange = { petName = it },
                label = "Nombre de la mascota",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Especie
            DropdownInput(
                selectedOption = species,
                options = speciesOptions,
                onOptionSelected = { species = it },
                label = "Especie",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Raza
            InputField(
                value = breed,
                onValueChange = { breed = it },
                label = "Raza",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Edad (Años)
            InputField(
                value = age,
                onValueChange = { age = it },
                label = "Edad (Años)",
                keyboardType = KeyboardType.Number,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Genero
            DropdownInput(
                selectedOption = gender,
                options = genderOptions,
                onOptionSelected = { gender = it },
                label = "Genero",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // Botones
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