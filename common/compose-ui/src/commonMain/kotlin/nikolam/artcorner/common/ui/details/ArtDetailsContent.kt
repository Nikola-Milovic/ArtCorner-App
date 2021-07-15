package nikolam.artcorner.common.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import nikolam.artcorner.common.details.ArtDetails
import nikolam.artcorner.common.ui.utils.KMPIcon
import nikolam.artcorner.common.ui.utils.copyToClipboard

@Composable
fun ArtDetailsContent(component: ArtDetails) {
    val model by component.models.subscribeAsState()

    Column() {
        TopAppBar(title = { Text(text = "Group X") }, navigationIcon = {
            // navigation icon is use
            // for drawer icon.
            IconButton(onClick = { component.closeDetails() }) {
                // below line is use to
                // specify navigation icon.
                Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back Button")
            }
        }
        )
        if (model.isOwner) {
            Spacer(modifier = Modifier.size(40.dp))
            GroupOwnerContent(model)
        }
    }
}

@Composable
fun GroupOwnerContent(model: ArtDetails.Model) {
    val uriHandler = LocalUriHandler.current

    val copyClipboard = copyToClipboard("Discord Command", "!register ${model.gid}")

    val instructionString = createInstructionText()

    val explanationString = buildAnnotatedString {
        pushStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 20.sp))
        append("Explanation\n")
        pop()
        append("Complete the previous two steps in the instructions. After all users join the group, they will get an invitation link to the server")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        ClickableText(
            modifier = Modifier
                .fillMaxWidth(),
            text = instructionString,
            onClick = {
                instructionString
                    .getStringAnnotations("URL", it, it)
                    .firstOrNull()?.let { stringAnnotation ->
                        uriHandler.openUri(stringAnnotation.item)
                    }
            }
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            copyClipboard()
        }) {
            Text("!register ${model.gid}")
            Spacer(Modifier.size(20.dp))

            KMPIcon(
                "clipboard", Modifier,
                null, "Copy command"
            )

        }
        Spacer(modifier = Modifier.size(40.dp))
        Text(explanationString)
    }
}

fun createInstructionText(): AnnotatedString {
    return buildAnnotatedString {

        pushStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 20.sp))
        append("Instructions")
        pop()

        append("\n\n")

        val s1 = "1) First click this link to create a discord server\n\n"
        append(s1)
        val s1Start = this.length - s1.length + s1.indexOf("this")
        val s1End = this.length

        addStringAnnotation(
            tag = "URL",
            annotation = "https://developer.android.com/jetpack/compose",
            start = s1Start,
            end = s1End
        )

        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                textDecoration = TextDecoration.Underline
            ), start = s1Start, end = s1End
        )


        val s2 = "2) Click this link and select the server you just created\n\n"
        append(s2)
        val s2Start = this.length - s2.length + s2.indexOf("this")
        val s2End = this.length - "you just created\n".length

        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                textDecoration = TextDecoration.Underline
            ), start = s2Start, end = s2End
        )

        addStringAnnotation(
            tag = "URL",
            annotation = "https://developer.android.com/jetpack/community",
            start = s2Start,
            end = s2End
        )

        append("3) Click on the command to copy it, and then paste it into the discord server you just created")
    }
}