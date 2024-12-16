package com.example.GymManagement.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.GymManagement.data.AuthViewModel
import com.example.GymManagement.R
import com.example.GymManagement.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavController){
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()
    var email by remember {
        mutableStateOf(value = "")
    }
    var password by remember {
        mutableStateOf(value = "")
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gali),
            contentDescription = "signup background",
            contentScale = ContentScale.FillBounds)
    }

    Column (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(start = 50.dp, top = 50.dp, end = 50.dp, bottom = 50.dp)) {

        Text(
            text = "Please Login Here",
            fontSize = 27.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 50.dp, top = 50.dp, end = 50.dp, bottom = 50.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .height(80.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo")

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {newEmail -> email = newEmail},
            label = { Text(text = "Enter Email",modifier = Modifier, color = Color.Black, fontWeight = FontWeight.Bold) },
            placeholder = { Text(text = "Please enter Email") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password ,
            onValueChange = {newPassword ->password = newPassword},
            label = { Text(text = "Enter Password",modifier = Modifier, color = Color.Black, fontWeight = FontWeight.Bold) },
            placeholder = { Text(text = "Please enter Password") },
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { authViewModel.login(email,password,navController,context)},
            colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text(
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                text = "LOGIN HERE")

        }
        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(text = AnnotatedString("Don't have an Account here"),
            onClick = {
                navController.navigate(ROUTE_REGISTER)
            },
            style = TextStyle(color = Color.Blue,
                fontSize = 16.sp,
                textAlign = TextAlign.Center)
        )

    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}