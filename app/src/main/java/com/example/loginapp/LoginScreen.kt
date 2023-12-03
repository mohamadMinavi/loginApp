package com.example.loginapp


import MyPreferencesManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


private val name = mutableStateOf(TextFieldValue())
private val birthDate = mutableStateOf(TextFieldValue())
private val nationalID = mutableStateOf(TextFieldValue())

private val nameError = mutableStateOf(false)
private val birthDateError = mutableStateOf(false)
private val nationalIDError = mutableStateOf(false)


@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "null",
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 35.dp, bottomStart = 35.dp))
                .width(300.dp)
                .height(265.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
        )

        Text(
            text = "ورود",
            modifier = Modifier
                .padding(end = 30.dp)
                .fillMaxWidth(),
            style = TextStyle(
                textDirection = TextDirection.Rtl,
                color = colorResource(id = R.color.myBlue),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )
        )

        MyTextField("نام و نام خانوادگی",name, nameError,false)
        MyTextField("تاریخ تولد",birthDate, birthDateError,false)
        MyTextField("کد ملی",nationalID, nationalIDError,true)


        Button(
            onClick = {
                nameError.value = name.value.text.isEmpty()
                birthDateError.value = birthDate.value.text.isEmpty()
                nationalIDError.value = nationalID.value.text.length < 10

                if(!nameError.value && !birthDateError.value && !nationalIDError.value)
                {
                    navController.navigate(
                        "Details?name=${name.value.text}&date=${birthDate.value.text}&code=${nationalID.value.text}"
                    )

                    val data = MyPreferencesManager(context)
                    data.saveData("spName", name.value.text)
                    data.saveData("spDate", birthDate.value.text)
                    data.saveData("spCode", nationalID.value.text)
                }

            },
            modifier = Modifier
                .width(280.dp)
                .padding(top = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.myBlue)
            )

        ) {
            Text(
                text = "ثبت اطلاعات",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MyTextField(
    label : String,
    valueText: MutableState<TextFieldValue>,
    valueError: MutableState<Boolean>,
    isNumber: Boolean
) {

    var text by remember{valueText}
    val error by remember{valueError}

    OutlinedTextField(
        value = text,
        onValueChange = {
            if(it.text.length < 11 || !isNumber){
                text = it
            }
        },
        singleLine = true,
        label = {
            Text(
                text = label,
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    color = colorResource(id = R.color.myBlue),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        keyboardOptions = if (isNumber)
            KeyboardOptions(keyboardType = KeyboardType.Number)
        else
            KeyboardOptions(keyboardType = KeyboardType.Text)
        ,
        textStyle = TextStyle(
            fontSize = 20.sp,
            textDirection = TextDirection.Rtl
        ),
        modifier = Modifier
            .padding(top = 10.dp)
            .width(280.dp)

        ,
        isError = error,
        supportingText = {
            if (error)
                Text(
                    text = "لطفا فیلد را پر کنید",
                    fontSize = 18.sp
                )
        }
    )

}



