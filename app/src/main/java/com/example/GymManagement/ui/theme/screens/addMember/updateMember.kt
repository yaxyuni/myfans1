package com.example.GymManagement.ui.theme.screens.addMember

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.GymManagement.data.MemberViewModel
import com.example.GymManagement.models.Member
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



@Composable
fun UpdateMemberScreen(navController: NavController, id: String){

    val context = LocalContext.current
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
    val currentDataRef  = FirebaseDatabase.getInstance()
        .getReference().child("Member/$id")
    DisposableEffect(Unit){
        val listener = object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val member = snapshot.getValue(Member::class.java)
                member?.let {
                    firstname = it.firstname
                    lastname = it.lastname
                    gender = it.gender
                    health = it.health
                    age = it.age


                }
            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message, Toast.LENGTH_LONG).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }
    Scaffold (
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "settings")
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Email, contentDescription = "settings")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                    }
                }
            )


        }
    ){innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            Text(text = "UPDATE MEMBER",
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.Green)
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "BACK")
                }
                Button(onClick = {
                    val memberRepository = MemberViewModel()
                    memberRepository.updateMember(
                        context = context,
                        navController = navController,
                        firstname = firstname,
                        lastname = lastname,
                        gender = gender,
                        health = health,
                        age = age,
                        id = id
                    )
                }) {
                    Text(text = "UPDATE")
                }
            }

            OutlinedTextField(value = firstname,
                onValueChange = {newFirstname -> firstname = newFirstname},
                placeholder = { Text(text = "Enter First Name") },
                label = { Text(text = "Enter First Name") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = lastname,
                onValueChange = {newLastname -> lastname = newLastname},
                placeholder = { Text(text = "Enter Last Name") },
                label = { Text(text = "Enter Last Name") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = gender,
                onValueChange = {newGender -> gender = newGender},
                placeholder = { Text(text = "Enter Gender") },
                label = { Text(text = "Enter Your Gender") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = age,
                onValueChange = {newAge -> age = newAge},
                placeholder = { Text(text = "Enter Age") },
                label = { Text(text = "Enter Your Age") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}