package registerView

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.new_fe_hearify.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistrationScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen() {
    var hoTen by remember { mutableStateOf("") }
    var gioiTinh by remember { mutableStateOf("") }
    var ngaySinh by remember { mutableStateOf("") }
    var soDienThoai by remember { mutableStateOf("") }
    var soCanCuoc by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.login_background), shape = RectangleShape
            )
            .border(width = 5.dp, color = Color(0xFF6200EE))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) { // Canh giữa theo chiều dọc
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp)) // Thêm khoảng cách giữa Image và Box
            Box(
                Modifier
                    .background(colorResource(R.color.white))
                    .padding(8.dp) // Thêm padding để Box lớn hơn text
            ) {
                Text(
                    text = "Thông tin",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.align(Alignment.Center) // Canh giữa text trong Box
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Sử dụng hàm CustomTextField
        CustomTextField(
            placeholder = "Họ và tên của bạn",
            value = hoTen,
            onValueChange = { hoTen = it })

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Giới tính",
            value = gioiTinh,
            onValueChange = { gioiTinh = it })

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Ngày sinh",
            value = ngaySinh,
            onValueChange = { ngaySinh = it })

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Số điện thoại",
            value = soDienThoai,
            onValueChange = { soDienThoai = it },
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Số căn cước công dân",
            value = soCanCuoc,
            onValueChange = { soCanCuoc = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { /* TODO: Handle registration */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.app_name_color)
            )
        ) {
            Text("Đăng ký", color = White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.field_white),
            focusedContainerColor = colorResource(id = R.color.container_background)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { /* TODO: Focus on the next field */ }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrationScreen()
}