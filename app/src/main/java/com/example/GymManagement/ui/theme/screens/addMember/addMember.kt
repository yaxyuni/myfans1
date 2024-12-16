package com.example.GymManagement.ui.theme.screens.addMember

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.GymManagement.R
import com.example.GymManagement.data.MemberViewModel


@Composable
fun AddMember( navController: NavController) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.gali),
            contentDescription = "signup background",
            contentScale = ContentScale.FillBounds)
    }
        var firstname by remember {
            mutableStateOf(value = "")
        }
        var lastname by remember {
            mutableStateOf(value = "")
        }
        var gender by remember {
            mutableStateOf(value = "")

        }
        var age by remember {
            mutableStateOf(value = "")

        }
        var health by remember {
            mutableStateOf(value = "")

        }


    Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Settings"
                            )

                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            androidx.compose.material3.Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share"
                            )
                        }

                    },


                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {},
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                        }

                    }

                )
            }




        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(
                    text = "ENTER NEW MEMBER",
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)


                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {}) {
                        Text(text = "BACK")
                    }
                    Button(onClick = {
                        val memberRepositors = MemberViewModel()
                        memberRepositors.saveMember(
                            firstname = firstname,
                            lastname = lastname,
                            gender = gender,
                            age = age,
                            health = health,
                            context = context
                        )
                    }) {
                        Text(text = "SAVE")
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                }

                OutlinedTextField(
                    value = firstname,
                    onValueChange = { newFirstname -> firstname = newFirstname },
                    placeholder = { Text(text = "Enter you First name") },
                    label = { Text(text = "Enter First name") },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = lastname,
                    onValueChange = { newLastname -> lastname = newLastname },
                    placeholder = { Text(text = "Enter you Last name") },
                    label = { Text(text = "Enter Last name") },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = gender,
                    onValueChange = { newGender -> gender = newGender },
                    placeholder = { Text(text = "Enter you Gender Status") },
                    label = { Text(text = "Enter Gender Status") },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = age,
                    onValueChange = { Age -> age = Age },
                    placeholder = { Text(text = "Enter you Age") },
                    label = { Text(text = "Enter Age") },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = health,
                    onValueChange = { Health -> health = Health },
                    placeholder = { Text(text = "Enter you Health Status") },
                    label = { Text(text = "Enter Health status") },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddClientScreenPreview(){
    AddMember(rememberNavController())
}