package com.example.GymManagement.navigation

import SignupScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.GymManagement.ui.theme.screens.SplashScreen
import com.example.GymManagement.ui.theme.screens.login.LoginScreen
import com.example.androidapp.DashBoard
import com.example.GymManagement.ui.theme.screens.addMember.AddMember
import com.example.GymManagement.ui.theme.screens.addMember.UpdateMemberScreen
import com.example.GymManagement.ui.theme.screens.addMember.ViewMembers

@Composable
fun AppNavHost(navController:NavHostController = rememberNavController(),
               startDestination:String = ROUTE_SPLASH){
NavHost(navController = navController,
    startDestination = startDestination){
   composable(ROUTE_SPLASH) { SplashScreen {
       navController.navigate(ROUTE_LOGIN){
           popUpTo(ROUTE_SPLASH){
               inclusive = true }


       }
   }

   }

    composable(ROUTE_REGISTER){SignupScreen(navController)}
    composable(ROUTE_LOGIN){LoginScreen(navController)}
    composable(ROUTE_HOME){ DashBoard(navController) }
    composable(ROUTE_VIEW_MEMBER){ ViewMembers(navController) }
    composable(ROUTE_ADD_MEMBER){ AddMember (navController) }
    composable("$ROUTE_UPDATE_MEMBER/{id}") {
            passedData -> UpdateMemberScreen(
        navController, passedData.arguments?.getString("id")!! )
    }


}

 }

