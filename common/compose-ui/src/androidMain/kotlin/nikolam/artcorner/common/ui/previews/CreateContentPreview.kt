package nikolam.artcorner.common.ui.previews

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.ui.create.ArtCreateContent

@Preview(widthDp = 480, heightDp = 600, backgroundColor = 0xffffff, showBackground = true)
@Composable
fun CreateContentPreview() {
    MaterialTheme () {
        ArtCreateContent(ArtCreateComponentStub())
    }
}

class ArtCreateComponentStub() : ArtCreate{
    override val models: Value<ArtCreate.Model> = MutableValue(ArtCreate.Model("Test"))


    override fun closeCreation() {
        TODO("Not yet implemented")
    }

    override fun finishCreation(maxMembers: Int) {
        TODO("Not yet implemented")
    }
}