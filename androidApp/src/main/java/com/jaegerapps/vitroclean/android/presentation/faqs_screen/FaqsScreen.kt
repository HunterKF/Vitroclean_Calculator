package com.jaegerapps.vitroclean.android.presentation.faqs_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.faqs_screen.components.FaqContainer
import com.jaegerapps.vitroclean.shared.domain.models.Faq
import com.jaegerapps.vitroclean.android.R

/*FAQ screen to display FAQs gotten from remote db */
@Composable
fun FaqsScreen(
    modifier: Modifier = Modifier,
    faqsList: List<Faq>,
    onBack: () -> Unit
) {
    /*Expand used to allow only one FAQ to be opened at a time*/
    var expand by remember {
        mutableStateOf(-1)
    }
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            /*Screen Title and back button*/
            Column {
                Box(Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { onBack() },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.content_desc_back_button),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.faqs),
                    style = MaterialTheme.typography.h1.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
        }
        /*FAQ list*/
        itemsIndexed(faqsList) { index, item ->
            FaqContainer(
                title = item.question,
                contentText = item.answer,
                isOpen = index == expand
            ) {
                expand = if (expand != index) {
                    index
                } else {
                    -1
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun FAQsScreenPreview() {

    val list = listOf(
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
    )
    TrivitroTheme {
        FaqsScreen(faqsList = list) {

        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FAQsScreenPreview_DARK() {

    val list = listOf(
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
        Faq(
            question = "How do I do this",
            answer = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut"
        ),
    )
    TrivitroTheme {
        FaqsScreen(faqsList = list) {

        }
    }
}