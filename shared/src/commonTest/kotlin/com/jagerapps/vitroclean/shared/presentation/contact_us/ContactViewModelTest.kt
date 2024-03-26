package com.jagerapps.vitroclean.shared.presentation.contact_us

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactError
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactState
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactUiEvent
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactViewModel
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class ContactViewModelTest {

    private lateinit var viewModel: ContactViewModel
    @BeforeTest
    fun setup() {
        viewModel = ContactViewModel()
    }

    @Test
    fun `OnContentChange - Change content to hello - Expect Hello`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            viewModel.onEvent(ContactUiEvent.OnContentChange("Hello"))
            val newState = awaitItem()

            assertThat(newState.content).isEqualTo("Hello")
            assertThat(newState.error).isNull()

        }
    }

    @Test
    fun `OnContentChange - Change content with 502 chars - Expect take 500 and error`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas rutrum dolor vitae urna accumsan euismod. Phasellus non mi volutpat sem suscipit egestas. Ut mattis ante quis neque facilisis auctor. Donec accumsan eu mauris in interdum. Curabitur feugiat nisi tincidunt hendrerit pellentesque. Vestibulum eleifend, nunc eu ornare pulvinar, odio ipsum ultricies nulla, id consequat magna justo et erat. Fusce lectus erat, pulvinar vel aliquam ut, pellentesque in velit. Nullam vel tincidunt orci justo."
            viewModel.onEvent(ContactUiEvent.OnContentChange(loremIpsum))
            val newState = awaitItem()
            val expectedIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas rutrum dolor vitae urna accumsan euismod. Phasellus non mi volutpat sem suscipit egestas. Ut mattis ante quis neque facilisis auctor. Donec accumsan eu mauris in interdum. Curabitur feugiat nisi tincidunt hendrerit pellentesque. Vestibulum eleifend, nunc eu ornare pulvinar, odio ipsum ultricies nulla, id consequat magna justo et erat. Fusce lectus erat, pulvinar vel aliquam ut, pellentesque in velit. Nullam vel tincidunt orci just"

            assertThat(newState.content).isEqualTo(expectedIpsum)
            assertThat(newState.error).isNotNull()
            assertThat(newState.error).isEqualTo(ContactError.LENGTH_TOO_LONG)

        }
    }
    @Test
    fun `OnSubjectChange - Change subject with greetings - Expect take greetings`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            viewModel.onEvent(ContactUiEvent.OnContentChange("Greetings"))
            val newState = awaitItem()

            assertThat(newState.content).isEqualTo("Greetings")
            assertThat(newState.error).isNull()

        }
    }
    @Test
    fun `OnSubjectChange - Change subject with 125 chars - Expect take 120 and error`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue purus ullamcorper, pulvinar risus blandit, rutrum ante. Donec suscipit vitae eu."
            viewModel.onEvent(ContactUiEvent.OnSubjectChange(loremIpsum))
            val newState = awaitItem()
            val expectedIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue purus ullamcorper, pulvinar risus blandit, rutrum "
            assertThat(newState.subject).isEqualTo(expectedIpsum)
            assertThat(newState.error).isNotNull()
            assertThat(newState.error).isEqualTo(ContactError.LENGTH_TOO_LONG)
        }
    }
    @Test
    fun `OnSubjectChange and OnContentChange - Expect Subject Hello - Content lorem ipsum`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            val subject = "Hello!"
            viewModel.onEvent(ContactUiEvent.OnSubjectChange(subject))
            val subjectState = awaitItem()
            assertThat(subjectState.subject).isEqualTo("Hello!")
            val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras scelerisque, est eget tincidunt dictum, nisl sapien iaculis elit, ac laoreet odio ante quis leo. Phasellus luctus sapien id odio tincidunt venenatis vitae vel justo. Sed vel convallis mauris, vel maximus sem. Praesent dignissim est vitae elementum congue. Aenean eget dictum augue velit."
            viewModel.onEvent(ContactUiEvent.OnContentChange(loremIpsum))
            val newState = awaitItem()
            assertThat(newState.subject).isEqualTo("Hello!")
            assertThat(newState.content).isEqualTo(loremIpsum)
            assertThat(newState.error).isNull()
        }
    }
    @Test
    fun `OnSubjectChange and OnContentChange and Send - Expect Subject Hello - Content lorem ipsum`() = runBlocking {
        viewModel.state.test {
            var sent = false
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            val subject = "Hello!"
            viewModel.onEvent(ContactUiEvent.OnSubjectChange(subject))
            val subjectState = awaitItem()
            assertThat(subjectState.subject).isEqualTo("Hello!")
            val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras scelerisque, est eget tincidunt dictum, nisl sapien iaculis elit, ac laoreet odio ante quis leo. Phasellus luctus sapien id odio tincidunt venenatis vitae vel justo. Sed vel convallis mauris, vel maximus sem. Praesent dignissim est vitae elementum congue. Aenean eget dictum augue velit."
            viewModel.onEvent(ContactUiEvent.OnContentChange(loremIpsum))
            val newState = awaitItem()
            assertThat(newState.subject).isEqualTo("Hello!")
            assertThat(newState.content).isEqualTo(loremIpsum)
            assertThat(newState.error).isNull()
            viewModel.onEvent(ContactUiEvent.AttemptToSend { sent = true })
            assertThat(sent).isTrue()
        }
    }

    @Test
    fun `ClearSnackbar - Expect error null`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue purus ullamcorper, pulvinar risus blandit, rutrum ante. Donec suscipit vitae eu."
            viewModel.onEvent(ContactUiEvent.OnSubjectChange(loremIpsum))
            val newState = awaitItem()
            val expectedIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue purus ullamcorper, pulvinar risus blandit, rutrum "
            assertThat(newState.subject).isEqualTo(expectedIpsum)
            assertThat(newState.error).isNotNull()
            assertThat(newState.error).isEqualTo(ContactError.LENGTH_TOO_LONG)
            viewModel.onEvent(ContactUiEvent.ClearSnackbar)
            val clearedError = awaitItem()
            assertThat(clearedError.error).isNull()
        }
    }

    @Test
    fun `SendError - Send ContactError - Expect LENGTH_TOO_LONG`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(ContactState())
            viewModel.onEvent(ContactUiEvent.SendError(ContactError.LENGTH_TOO_LONG))
            val updatedErrorState = awaitItem()
            assertThat(updatedErrorState.error).isNotNull()
            assertThat(updatedErrorState.error).isEqualTo(ContactError.LENGTH_TOO_LONG)
        }
    }
}