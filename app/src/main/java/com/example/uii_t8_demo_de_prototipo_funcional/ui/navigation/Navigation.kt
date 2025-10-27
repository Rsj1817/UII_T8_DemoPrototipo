package com.example.uii_t8_demo_de_prototipo_funcional.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.uii_t8_demo_de_prototipo_funcional.ui.screens.*
import com.example.uii_t8_demo_de_prototipo_funcional.viewmodel.MainViewModel

// Rutas de navegación
object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot_password"
    const val HOME = "home"
    const val ADD_ITEM = "add_item"
    const val EDIT_ITEM = "edit_item/{itemId}"
    const val DETAIL_ITEM = "detail_item/{itemId}"
    
    // Funciones para construir rutas con parámetros
    fun editItem(itemId: Int) = "edit_item/$itemId"
    fun detailItem(itemId: Int) = "detail_item/$itemId"
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        // Pantalla de Login
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { navController.navigate(Routes.HOME) },
                onRegisterClick = { navController.navigate(Routes.REGISTER) },
                onForgotPasswordClick = { navController.navigate(Routes.FORGOT_PASSWORD) }
            )
        }
        
        // Pantalla de Registro
        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterClick = { navController.navigate(Routes.LOGIN) },
                onBackClick = { navController.popBackStack() },
                onForgotPasswordClick = { navController.navigate(Routes.FORGOT_PASSWORD) }
            )
        }
        
        // Pantalla de Recuperación de Contraseña
        composable(Routes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                onSubmitClick = { navController.navigate(Routes.LOGIN) },
                onBackClick = { navController.popBackStack() }
            )
        }
        
        // Pantalla Principal (Home)
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onItemClick = { itemId -> navController.navigate(Routes.detailItem(itemId)) },
                onAddClick = { navController.navigate(Routes.ADD_ITEM) },
                onLogoutClick = {
                    // Navegar de vuelta al login
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        
        // Pantalla para Agregar Item
        composable(Routes.ADD_ITEM) {
            AddPetScreen(
                onSaveClick = { navController.navigate(Routes.HOME) },
                onCancelClick = { navController.popBackStack() }
            )
        }
        
        // Pantalla para Editar Item
        composable(
            route = Routes.EDIT_ITEM,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            
            EditPetScreen(
                onAcceptClick = { navController.navigate(Routes.HOME) },
                onCancelClick = { navController.popBackStack() }
            )
        }
        
        // Pantalla de Detalle de Item
        composable(
            route = Routes.DETAIL_ITEM,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            
            DetailScreen(
                viewModel = viewModel,
                itemId = itemId,
                onEditClick = { navController.navigate(Routes.editItem(itemId)) },
                onDeleteClick = {
                    // Desactivado: no eliminar, solo volver atrás
                    navController.popBackStack()
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}