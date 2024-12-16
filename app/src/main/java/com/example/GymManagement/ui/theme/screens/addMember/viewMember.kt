package com.example.GymManagement.ui.theme.screens.addMember

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.GymManagement.R
import com.example.GymManagement.data.MemberViewModel
import com.example.GymManagement.models.Member
import com.example.GymManagement.navigation.ROUTE_UPDATE_MEMBER


@Composable
fun ViewMembers(navController: NavHostController) {
    val context = LocalContext.current
    val memberRepository = MemberViewModel()

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.member),
            contentDescription = "signup background",
            contentScale = ContentScale.FillBounds)
    }
    val emptyUploadState = remember {
        mutableStateOf(
            Member("", "", "", "", "", "",)
        )
    }
    val emptyUploadListState = remember {
        mutableStateListOf<Member>()
    }
    val members = memberRepository.viewMember(
        emptyUploadState, emptyUploadListState, context,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .padding(top = 50.dp))
            Text(
                text = "All Members",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color.White)

            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn() {
                items(members) {
                    MemberItem(
                        firstname = it.firstname,
                        lastname = it.lastname,
                        gender = it.gender,
                        health = it.health,
                        age = it.age,
                        id = it.id,
                        navController = navController,
                        memberRepository = memberRepository)
                }

            }
        }
    }
}

@Composable
fun MemberItem(firstname:String,
               lastname:String,
               gender:String,
               age:String,
               health:String,
               id: String,
               navController: NavHostController,
               memberRepository:MemberViewModel) {
    val context = LocalContext.current




    Column(modifier = Modifier
        .fillMaxWidth(1.0f)
        .padding(10.dp)
        .wrapContentHeight(align = Alignment.Top)
        .border(1.dp, Color.LightGray)) {
        Card(
            modifier = Modifier
                .padding(5.dp)
                .height(150.dp)
                .width(400.dp),

            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors
                (containerColor = Color.LightGray)
        ) {
            Row {
                Column {



                Button(
                    onClick = {
                        val memberRepo = MemberViewModel()
                        memberRepo.deleteMember(context, id, navController)

                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "REMOVE",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp


                    )
                }
                Button(
                    onClick = {
                        navController.navigate("$ROUTE_UPDATE_MEMBER/$id")
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "UPDATE",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp

                    )
                  }

                }










                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .weight(0.8f)
                        .verticalScroll(rememberScrollState())
                        .padding(start = 15.dp)

                )
                {


                    Text(
                        text = "FIRSTNAME",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = firstname,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "LASTNAME",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = lastname,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "GENDER",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = gender,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "AGE",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = age,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "HEALTH",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = health,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
          }
         }
     }



        @Preview(showBackground = true, showSystemUi = true)
        @Composable
        fun ViewMembersPreview() {
            ViewMembers(rememberNavController())
        }

