package registerView

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.app_name_color),
                shape = RoundedCornerShape(25.dp)
            )
            .border(width = 5.dp, color = Color(0xFF6200EE))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(1f) // Image chiếm 1 phần không gian
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                Modifier
                    .background(colorResource(R.color.white))
                    .padding(5.dp)
                    .weight(2f) // Box chiếm 2 phần không gian
                    .clip(RoundedCornerShape(25.dp))
            ) {
                Text(
                    text = "Thông tin",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            placeholder = "Họ và tên của bạn",
            value = hoTen,
            onValueChange = { hoTen = it },
            focusManager = focusManager
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Giới tính",
            value = gioiTinh,
            onValueChange = { gioiTinh = it },
            focusManager = focusManager
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Ngày sinh",
            value = ngaySinh,
            onValueChange = { ngaySinh = it },
            focusManager = focusManager
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Số điện thoại",
            value = soDienThoai,
            onValueChange = { soDienThoai = it },
            keyboardType = KeyboardType.Phone,
            focusManager = focusManager
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Số căn cước công dân",
            value = soCanCuoc,
            onValueChange = { soCanCuoc = it },
            keyboardType = KeyboardType.Number,
            focusManager = focusManager
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Handle registration */ },
            modifier = Modifier

                .border(
                    width = 2.dp, // Độ dày border
                    color = colorResource(R.color.text_button), // Màu border (màu hồng)
                    shape = RoundedCornerShape(25.dp) // Bo góc 25dp
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White // Màu nền trắng
            ),
            shape = RoundedCornerShape(25.dp) // Bo góc 25dp cho button
        ) {
            Text(
                "Đăng ký",
                color = colorResource(R.color.text_button) // Màu chữ (màu hồng)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Chúng tôi sẽ không bao giờ chia sẻ bất cứ điều gì nếu không có sự cho phép của bạn",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.wrapContentSize(Alignment.Center) // Căn giữa
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Bằng cách Đăng ký, bạn đồng ý với Điều khoản và Điều kiện của chúng tôi. Tìm hiểu cách chúng tôi sử dụng dữ liệu của bạn trong Chính sách bảo mật của chúng tôi",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.wrapContentSize(Alignment.Center) // Căn giữa
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    focusManager: androidx.compose.ui.focus.FocusManager
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.white),
            focusedContainerColor = colorResource(id = R.color.container_background)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrationScreen()
}