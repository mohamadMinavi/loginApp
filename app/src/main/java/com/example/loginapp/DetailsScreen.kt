package com.example.loginapp


import MyPreferencesManager
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity

@Composable
fun DetailsScreen(myName: String?, myDate: String?, myCode: String?) {

    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    val data = MyPreferencesManager(context)
    var orgName = myName
    var orgDate = myDate
    var orgCode = myCode

    orgName = data.getData("spName", "test")
    orgDate = data.getData("spDate", "test")
    orgCode = data.getData("spCode", "test")

    Card(
        modifier = Modifier
            .padding(30.dp),
        elevation =
        CardDefaults.elevatedCardElevation(10.dp)
    ) {

        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.myDarkWhite))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "پروفایل من",
                modifier = Modifier
                    .padding(end = 20.dp, top = 20.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    color = colorResource(id = R.color.myBlue),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(
                        Font(R.font.b_titr)
                    )
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "null",
                modifier = Modifier
                    .size(200.dp),

                )
            TitleText("نام و نام خانوادگی")

            InfoText(orgName)

            TitleText("تاریخ تولد")

            InfoText(orgDate)

            TitleText("کد ملی")

            InfoText(orgCode)

            Button(
                onClick = {
                    activity?.finish()
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .width(280.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.myBlue)
                )
            ) {
                Text(
                    text = "خروج",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }



}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(end = 20.dp, bottom = 10.dp)
            .fillMaxWidth(),
        style = TextStyle(
            textDirection = TextDirection.Rtl,
            color = Color.Gray,
            fontSize = 18.sp,
            fontFamily = FontFamily(
                Font(R.font.b_titr)
            )
        )
    )
}

@Composable
fun InfoText(text: String?) {
    Surface(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .padding(horizontal = 20.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text!!,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            style = TextStyle(
                textDirection = TextDirection.Rtl,
                color = colorResource(id = R.color.myLightBlue),
                fontSize = 22.sp,
                fontFamily = FontFamily(
                    Font(R.font.b_roya)
                )
            )
        )
    }
}


