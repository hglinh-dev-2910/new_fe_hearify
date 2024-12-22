import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_fe_hearify.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    name: String,
    age: Int,
    description: String,
    location: String,
    education: String,
    children: String,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.login_background))
    ) {
        // Image Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.messi),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Content below the image
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // "Quay lại" text with offset
            Text(
                text = "Quay lại",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .offset(y = 60.dp)
            )

            // Information boxes with spacing
            Spacer(modifier = Modifier.height(220.dp))
            InfoBox("$name, $age")
            Spacer(modifier = Modifier.height(16.dp))
            Line(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(16.dp))

            InfoBox(description)
            Spacer(modifier = Modifier.height(16.dp))
            Line(modifier = Modifier.height(1.dp))
            InfoBox(location)
            Spacer(modifier = Modifier.height(16.dp))
            Line(modifier = Modifier.height(1.dp))
            InfoBox(education)
            Spacer(modifier = Modifier.height(16.dp))
            Line(modifier = Modifier.height(1.dp))
            InfoBox(children)
            Spacer(modifier = Modifier.height(16.dp))
            Line(modifier = Modifier.height(1.dp))
            // "Xem thêm" button
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White.copy(alpha = 0.8f),
                    contentColor = Color.Black
                ),
                shape = CircleShape
            ) {
                Text("Xem thêm về $name")
            }
        }
    }
}

// Reusable composable for each information box
@Composable
fun InfoBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(16.dp)
    ) {
        Text(text, color = Color.Black)
    }
}

@Composable
fun Line(modifier: Modifier)
{
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black) ){}
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        name = "David",
        age = 20,
        description = "Vào một ngày đẹp trời, từ hai con người xa lạ - chúng ta sẽ hòa làm một <3",
        location = "Đang ở Thành phố Hồ Chí Minh",
        education = "Học tại Trường Đại học Khoa học tự nhiên, ĐHQG-HCM",
        children = "Chưa có con"
    )
}