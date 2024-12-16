package com.example.androidapp

import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.GymManagement.data.AuthViewModel
import com.example.GymManagement.R
import com.example.GymManagement.navigation.ROUTE_ADD_MEMBER
import com.example.GymManagement.navigation.ROUTE_VIEW_MEMBER

import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(navController: NavController){

    val user  = FirebaseAuth.getInstance().currentUser
    val userName = user?.displayName ?: "Unknown"

    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.dashboardi),
            contentDescription = "dashboard background",
            contentScale = ContentScale.FillBounds)
    }
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        TopAppBar(
            title = { Text(text = "User:$userName") },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Home,
                        contentDescription = "Home")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Cyan,
                titleContentColor = Color.Blue,
                navigationIconContentColor = Color.Red
            ),
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Person,
                        contentDescription = "My Profile")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search,
                        contentDescription = "Search Here")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu")
                }
                IconButton(onClick = {
                    val authRepository = AuthViewModel()
                    authRepository.logout(navController, context)
                }) {
                    Icon(imageVector = Icons.Filled.ExitToApp,
                        contentDescription = "LogOut")
                }
            })
        }


        Row (modifier = Modifier.wrapContentWidth()){
            Card (modifier = Modifier
                .height(500.dp)
                .padding(start = 20.dp, end = 20.dp, bottom = 1.dp, top = 3.dp)
                .padding(top = 270.dp, bottom = 1.dp)
                .clickable {
                    navController.navigate(ROUTE_ADD_MEMBER)

                },
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(50.dp),
                colors = CardDefaults.cardColors
                    (containerColor = Color.LightGray)


            ){
                Box(modifier = Modifier.height(370.dp).width(150.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.addmember), contentScale = ContentScale.Inside, alignment = Alignment.Center,
                        contentDescription = "Add Member")
                    Box(modifier = Modifier
                        .matchParentSize()
                        .padding(top = 155.dp,),
                        contentAlignment = Alignment.Center){
                        Text(color = Color.Red,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            text = "ADD MEMBER")
                    }
                }
            }


            Card (modifier = Modifier
                .height(500.dp)
                .padding(start = 20.dp, end = 20.dp, bottom = 1.dp, top = 3.dp)
                .padding(top = 270.dp, bottom = 1.dp)

                .clickable {
                    navController.navigate(ROUTE_VIEW_MEMBER)
                },
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(50.dp),
                colors = CardDefaults.cardColors
                    (containerColor = Color.LightGray)


            ){
                Box(modifier = Modifier
                    .height(370.dp)
                    .width(155.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.activemembers), contentScale = ContentScale.Crop, alignment = Alignment.Center,
                        contentDescription = "New Member")
                    Box(modifier = Modifier
                        .matchParentSize()
                        .padding(top = 159.dp),
                        contentAlignment = Alignment.Center){
                        Text(color = Color.Red,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            text = "VIEW MEMBER")
                    }
                }
            }
        }


    }




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashBoardPreview(){
    DashBoard(rememberNavController())
}