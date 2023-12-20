package com.jaegerapps.trivitro_calculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jaegerapps.trivitro_calculator.android.core.presentation.Routes
import com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen.AndroidCalculatorViewModel
import com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen.CalculatorScreen
import com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.AndroidContactViewModel
import com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.ContactScreen
import com.jaegerapps.trivitro_calculator.android.presentation.faqs_screen.FaqsScreen
import com.jaegerapps.trivitro_calculator.android.presentation.home_screen.HomeScreen
import com.jaegerapps.trivitro_calculator.android.presentation.home_screen.AndroidHomeScreenViewModel
import com.jaegerapps.trivitro_calculator.android.presentation.loading_screen.LoadingScreen
import com.jaegerapps.trivitro_calculator.shared.presentation.calculator.CalculateUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.home.HomeUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.SharedUiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivitroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TrivitroRoot()
                }
            }
        }
    }
}

@Composable
fun TrivitroRoot() {
    val navController = rememberNavController()
    val sharedViewModel = hiltViewModel<AndroidSharedViewModel>()
    val sharedState by sharedViewModel.state.collectAsState()
    val androidHomeScreenViewModel = AndroidHomeScreenViewModel()

    NavHost(navController = navController, startDestination = Routes.LOADING) {
        composable(Routes.LOADING) {
            LaunchedEffect(key1 = Unit) {
                sharedViewModel.onEvent(SharedUiEvent.LoadData)
            }
            LaunchedEffect(key1 = sharedState.loaded) {
                navController.navigate(Routes.HOME)
            }
            LoadingScreen(isLoading = sharedState.isLoading, error = sharedState.error) {
                sharedViewModel.onEvent(SharedUiEvent.OnRetry)
            }
        }
        composable(Routes.HOME) {
            val homeState by androidHomeScreenViewModel.state.collectAsState()
            println("MainActivity is called")
            println("Current state: $homeState")
            HomeScreen(homeState = homeState, onUiEvent = { event ->
                when (event) {
                    is HomeUiEvent.OnNavigate -> {
                        if (!event.arguments.isNullOrBlank()) {
                            navController.navigate(event.route + "/${event.arguments}")
                        } else {
                            navController.navigate(event.route)
                        }
                    }
                    else -> androidHomeScreenViewModel.onEvent(event)
                }
            })
        }
        composable(route = Routes.CALCULATOR + "/{route}",
            arguments = listOf(
                navArgument("route") {
                    type = NavType.StringType
                    defaultValue = "by_filter"
                }
            )
        ) { backStackEntry ->
            val calculatorViewModel = hiltViewModel<AndroidCalculatorViewModel>()
            calculatorViewModel.onEvent(
                CalculateUiEvent.ChangeMode(
                    backStackEntry.arguments?.getString(
                        "route"
                    ) ?: "by_filter"
                )
            )
            val calculatorState by calculatorViewModel.state.collectAsState()
            LaunchedEffect(key1 = Unit, block = {
                if (sharedState.poolFilterList.isNotEmpty()) {
                    calculatorViewModel.onEvent(CalculateUiEvent.AddList(sharedState.poolFilterList))
                }
            })
            CalculatorScreen(
                calculatorState = calculatorState,
                onCalculatorEvent = { event ->
                    when (event) {
                        is CalculateUiEvent.OnNavigate -> {
                            navController.popBackStack()
                        }

                        else -> calculatorViewModel.onEvent(event)
                    }
                }
            )

        }
        composable(Routes.FAQS) {
            FaqsScreen(faqsList = sharedState.faqsList, onBack = { navController.popBackStack() })
        }
        composable(Routes.CONTACT_US) {
            val contactViewModel = hiltViewModel<AndroidContactViewModel>()
            val contactState by contactViewModel.state.collectAsState()
            ContactScreen(
                state = contactState,
                onEvent = { event -> contactViewModel.onEvent(event) },
                onBack = { navController.popBackStack() })
        }
    }
}

