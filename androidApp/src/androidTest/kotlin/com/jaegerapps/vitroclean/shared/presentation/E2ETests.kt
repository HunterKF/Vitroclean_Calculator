package com.jaegerapps.vitroclean.shared.presentation

import android.content.Context
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.jaegerapps.vitroclean.android.MainActivity
import com.jaegerapps.vitroclean.android.R
import com.jaegerapps.vitroclean.android.di.AppModule
import com.jaegerapps.vitroclean.shared.domain.VitrocleanRepo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
class E2ETests {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeRepo: VitrocleanRepo
    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun goToFiltersAndSelect() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_vitroclean_logo))
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_filter)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.dropdown_select_manufacturer)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.dropdown_select_manufacturer)).performClick()
        composeRule.onNodeWithText("Pentair").performClick()
        composeRule.onNodeWithText(context.getString(R.string.dropdown_select_model)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.dropdown_select_model)).performClick()
        composeRule.onNodeWithText("TR40").performClick()
        composeRule.onNodeWithText(context.getString(R.string.recommended_sand_load_lbs)).assertExists()
    }
    @Test
    fun goToFiltersAndSelectModelFirst() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_vitroclean_logo))
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_filter)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.dropdown_select_model)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.dropdown_select_model)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.prompt_select_manufacturer_first)).assertExists()
    }

    @Test
    fun goToBySandTypeInNumber() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_vitroclean_logo))
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_sand)).performClick()
        composeRule.onNodeWithTag("number display").performClick()
        composeRule.onNodeWithTag("number display").performTextInput("50")
        composeRule.onNodeWithTag("number display").assertTextContains("50")
        composeRule.onNodeWithTag("number display").performImeAction()
        composeRule.onNodeWithText(context.getString(R.string.recommended_sand_load_lbs)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.recommended_vitroclean_vfa_load_lbs)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.recommended_vitroclean_pebble_vf8_load_lbs)).assertExists()
        composeRule.onNodeWithTag("stat display").performScrollToIndex(5)
        composeRule.onNodeWithText(context.getString(R.string.recommended_50_lb_bags_of_vitroclean_vfa)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.recommended_50_lb_bags_of_pebble_vf8)).assertExists()
    }
    @Test
    fun goToBySandTypeInNumberClearInput() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_vitroclean_logo))
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_sand)).performClick()
        composeRule.onNodeWithTag("number display").performClick()
        composeRule.onNodeWithTag("number display").performTextInput("50")
        composeRule.onNodeWithTag("number display").assertTextContains("50")
        composeRule.onNodeWithTag("number display").performImeAction()
        composeRule.onNodeWithText(context.getString(R.string.recommended_sand_load_lbs)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.recommended_vitroclean_vfa_load_lbs)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.recommended_vitroclean_pebble_vf8_load_lbs)).assertExists()
        composeRule.onNodeWithTag("stat display").performScrollToIndex(5)
        composeRule.onNodeWithText(context.getString(R.string.recommended_50_lb_bags_of_vitroclean_vfa)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.recommended_50_lb_bags_of_pebble_vf8)).assertExists()
        composeRule.onNodeWithTag("number display").performClick()
        composeRule.onNodeWithTag("number display").performTextClearance()
        composeRule.onNodeWithTag("number display").assertTextContains("")
        composeRule.onNodeWithText(context.getString(R.string.recommended_sand_load_lbs)).assertDoesNotExist()
        composeRule.onNodeWithText(context.getString(R.string.recommended_vitroclean_vfa_load_lbs)).assertDoesNotExist()
        composeRule.onNodeWithText(context.getString(R.string.recommended_vitroclean_pebble_vf8_load_lbs)).assertDoesNotExist()
        composeRule.onNodeWithText(context.getString(R.string.recommended_50_lb_bags_of_vitroclean_vfa)).assertDoesNotExist()
        composeRule.onNodeWithText(context.getString(R.string.recommended_50_lb_bags_of_pebble_vf8)).assertDoesNotExist()
        composeRule.onNodeWithText(context.getString(R.string.prompt_start_calculating)).assertExists()
    }

    @Test
    fun goToFaqsAndClickOnFaqs() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_vitroclean_logo))
        composeRule.onNodeWithText(context.getString(R.string.faqs)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.faqs)).performClick()
        composeRule.onNodeWithText("How do I install this?").performClick()
        composeRule.onNodeWithText("Why is this good").performClick()
        composeRule.onNodeWithText("Why is this good").performClick()
    }

    @Test
    fun goToContactScreenEnterEmail() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_vitroclean_logo))
        composeRule.onNodeWithContentDescription(context.getString(R.string.contact_us)).performClick()

        composeRule.onNodeWithText(context.getString(R.string.contact_us)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.action_send)).assertIsNotEnabled()
        composeRule.onNodeWithText(context.getString(R.string.email_subject)).performTextInput("This is a test subject")
        composeRule.onNodeWithText(context.getString(R.string.email_subject)).assertTextContains("This is a test subject")
        composeRule.onNodeWithText(context.getString(R.string.email_subject)).performImeAction()
        composeRule.onNodeWithText(context.getString(R.string.action_send)).assertIsNotEnabled()

        composeRule.onNodeWithText(context.getString(R.string.email_content)).performTextInput("This is a test message content.")
        composeRule.onNodeWithText(context.getString(R.string.email_content)).assertTextContains("This is a test message content.")
        composeRule.onNodeWithText(context.getString(R.string.email_content)).performImeAction()

        composeRule.onNodeWithText(context.getString(R.string.action_send)).assertIsEnabled()
        composeRule.onNodeWithText(context.getString(R.string.action_send)).performClick()

    }

    @Test
    fun clickToLeaveAppToTrivitroSite() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithText(context.getString(R.string.prompt_link_to_site)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.prompt_link_to_site)).performClick()
        //Pop up opened
        composeRule.onNodeWithText(context.getString(R.string.title_navigate_out_of_app)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.explanation_navigate_out_of_app)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.no)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.yes)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.no)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.title_navigate_out_of_app)).assertDoesNotExist()
    }
    @Test
    fun goThroughEntireApp() = runBlocking<Unit> {
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Home screen
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_filter)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_filter)).performClick()
        //ByFilter screen
        composeRule.onNodeWithTag(context.getString(R.string.dropdown_select_manufacturer)).assertExists()
        composeRule.onNodeWithTag(context.getString(R.string.dropdown_select_model)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).performClick()
        //Back to home
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_cubic_feet)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_cubic_feet)).performClick()
        //ByNumber - Cubic Feet
        composeRule.onNodeWithTag("number display").assertExists()
        composeRule.onNodeWithText(context.getString(R.string.subtext_cubic_feet)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).performClick()
        //Back to home
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_sand)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.calculate_by_sand)).performClick()
        //ByNumber - Sand
        composeRule.onNodeWithTag("number display").assertExists()
        composeRule.onNodeWithText(context.getString(R.string.subtext_sand_needed)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).performClick()
        //Back to home
        composeRule.onNodeWithText(context.getString(R.string.faqs)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.faqs)).performClick()
        //FAQs
        composeRule.onNodeWithText(context.getString(R.string.faqs)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).performClick()
        //Back to home
        composeRule.onNodeWithText(context.getString(R.string.contact_us)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.contact_us)).performClick()
        //Contact Us
        composeRule.onNodeWithText(context.getString(R.string.contact_us)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.email_subject)).assertExists()
        composeRule.onNodeWithText(context.getString(R.string.email_content)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).assertExists()
        composeRule.onNodeWithContentDescription(context.getString(R.string.content_desc_back_button)).performClick()
        //Back to home


    }

}