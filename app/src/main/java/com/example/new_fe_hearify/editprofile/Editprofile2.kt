//package com.example.new_fe_hearify.editprofile
//
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil3.compose.AsyncImage
//import coil.request.ImageRequest
//import com.example.new_fe_hearify.R
//
//// ... (MessageScreen và MessageItem từ câu trả lời trước)
//
//@Composable
//fun ProfileEditingScreen() {
//    var name by remember { mutableStateOf("Minh") }
//    var age by remember { mutableStateOf(20) }
//    var bio by remember { mutableStateOf("Thích hải sản thì phải ăn ghẹ\nThích làm mẹ thì hãy ăn anh") }
//    var selectedImages by remember { mutableStateOf<List<Uri>>(emptyList()) }
//
//    val context = LocalContext.current
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetMultipleContents()
//    ) { uris: List<Uri>? ->
//        uris?.let { selectedImages = it }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Chỉnh sửa hồ sơ",
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Ảnh và gợi ý
//        PhotosAndSuggestions(selectedImages, launcher)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Giới thiệu
//        OutlinedTextField(
//            value = bio,
//            onValueChange = { bio = it },
//            label = { Text("Bio") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Thông tin cơ bản
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Tên") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        OutlinedTextField(
//            value = age.toString(),
//            onValueChange = { age = it.toIntOrNull() ?: 0 },
//            label = { Text("Độ tuổi") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // ... (Thêm các trường thông tin khác)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Nút lưu
//        Button(
//            onClick = { /* Xử lý lưu thông tin */ },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Lưu")
//        }
//    }
//}
//
//@Composable
//fun PhotosAndSuggestions(
//    selectedImages: List<Uri>,
//    launcher: androidx.activity.result.ActivityResultLauncher<String>
//) {
//    Column {
//        Text(
//            text = "Ảnh và gợi ý",
//            fontWeight = FontWeight.Bold,
//            fontSize = 16.sp
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = "Kéo rồi thả ảnh và gợi ý theo thứ tự mà bạn muốn xuất hiện.",
//            fontSize = 12.sp,
//            color = Color.Gray
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(selectedImages) { imageUri ->
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(imageUri)
//                        .crossfade(true)
//                        .build(),
//                    contentDescription = "Selected image",
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(RoundedCornerShape(8.dp)),
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//            item {
//                // Nút thêm ảnh
//                Box(
//                    modifier = Modifier
//                        .size(80.dp)
//                        .background(Color.LightGray, RoundedCornerShape(8.dp))
//                        .clickable { launcher.launch("image/*") }
//                        .padding(16.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.edit1), // Thay thế bằng icon thực tế
//                        contentDescription = "Add photo",
//                        tint = Color.Gray
//                    )
//                }
//            }
//        }
//    }
//}