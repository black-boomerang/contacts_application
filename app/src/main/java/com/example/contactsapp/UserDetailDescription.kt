import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.contactsapp.R
import com.example.contactsapp.User
import java.text.SimpleDateFormat

@Composable
fun UserDetailContent(user: User) {
    val orientation = LocalConfiguration.current.orientation
    Surface(color = colorResource(R.color.backgroundPrimary)) {
        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        UserAvatar(user.imageUrl)
                    }
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        UserTextInfo(user)
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    UserAvatar(user.imageUrl)
                    Divider(color = colorResource(R.color.purple_500), thickness = 3.dp)
                    UserTextInfo(user)
                }
            }
        }
    }
}

@Composable
fun UserTextInfo(user: User) {
    Column {
        Spacer(modifier = Modifier.padding(4.dp))
        UserText(stringResource(R.string.login_label, user.login))
        UserText(stringResource(R.string.name_label, user.name))
        UserText(stringResource(R.string.email_label, user.email))
        UserText(stringResource(R.string.phone_label, user.phone))
        UserText(
            stringResource(
                R.string.dob_label,
                SimpleDateFormat.getDateInstance().format(user.dob.time)
            )
        )
        UserText(stringResource(R.string.address_label, user.address))
    }
}

@Composable
fun UserAvatar(avatarUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(avatarUrl),
        contentDescription = LocalContext.current.getString(R.string.image_content),
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).size(180.dp)
    )
}

@Composable
fun UserText(text: String) {
    val paddingHorizontal = when(LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> 0.dp
        else -> 16.dp
    }
    Text(
        text = text,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(horizontal = paddingHorizontal, vertical = 6.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}