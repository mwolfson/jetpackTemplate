package com.wolfsoft.demo

import android.content.res.Resources
import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wolfsoft.demo.ui.settings.*
import com.wolfsoft.demo.ui.theme.ComposeTemplateTheme
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsComponentsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var res: Resources
    var isComplete = false

    val large_20 = "largevalue1234567890"
    val large_21 = "123456789012345678901"
    val large_30 = "largevalue12345678901234567890"
    val large_31 = "largevalue123456789012345678901"

    @Before
    fun setup() {
        res = InstrumentationRegistry.getInstrumentation().targetContext.resources
        isComplete = false
    }

    @Test
    fun NameTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        val input = "RandomName123"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                NameTextInput(
                    name = input,
                    onNameInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_name))
        val semanticsConfig = root.fetchSemanticsNode().config
        // Prints all Semantics to log
        composeTestRule.onRoot().printToLog("MSW")
        // Tests Complete Value and Label
        assertEquals(isComplete, true)
        composeTestRule.onNodeWithText(res.getString(R.string.name)).assertIsDisplayed()
        // Test Semantics info of node
        for ((key, value) in semanticsConfig) {
            Log.d("MSW", "$key = $value") // access and print all config
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
            println("$key = $value")
        }
    }

    @Test
    fun NameTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        val input = "Samp"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                NameTextInput(
                    name = input,
                    onNameInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_name))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.name_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun NameTextField_LongInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                NameTextInput(
                    name = "",
                    onNameInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_name))
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_name))
            .performTextInput(large_30)
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_name))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_name))
            .assertTextContains(res.getString(R.string.name))
        val semanticsConfig = root.fetchSemanticsNode().config
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(large_30, value.toString())
            }
        }
    }

    @Test
    fun PasswordTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PasswordTextInput(
                    password = "inputpass",
                    onPasswordInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithContentDescription(res.getString(R.string.visibility_on))
            .performClick()
        composeTestRule.onNodeWithText(res.getString(R.string.password)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun PasswordTextField_ValidInput_CompleteStatusAndLabelCorrectPasswordHidden() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PasswordTextInput(
                    password = "inputpass",
                    onPasswordInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.password)).assertIsDisplayed()
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals("•••••••••", value.toString())
            }
        }
    }

    @Test
    fun PasswordTextField_SetVisibilityOn_PasswordValueIsVisible() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PasswordTextInput(
                    password = "icanseethis",
                    onPasswordInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithContentDescription(res.getString(R.string.visibility_on))
            .performClick()
        composeTestRule.onNodeWithText("icanseethis").assertIsDisplayed()
    }

    @Test
    fun PasswordTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PasswordTextInput(
                    password = "passwor",
                    onPasswordInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
        val semanticsConfig = root.fetchSemanticsNode().config
        isErrorDisplayed(semanticsConfig)

// composeTestRule.onNodeWithText("*******").assertIsDisplayed()
        composeTestRule.onNodeWithText(res.getString(R.string.password_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
    }

    @Test
    fun PasswordTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "Password1234"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PasswordTextInput(
                    password = input,
                    onPasswordInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_password))
            .assertTextContains(res.getString(R.string.password))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals("••••••••••••", value.toString())
            }
        }
    }

    @Test
    fun PhoneTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PhoneTextInput(
                    phone = "3035551212",
                    onPhoneInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_phone))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.phone)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun PhoneTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PhoneTextInput(
                    phone = "303555121",
                    onPhoneInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_phone))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.phone_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun PhoneTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "3035551212"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                PhoneTextInput(
                    phone = input,
                    onPhoneInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_phone))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_phone))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_phone))
            .assertTextContains(res.getString(R.string.phone))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
        }
    }

    @Test
    fun EmailTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                EmailTextInput(
                    email = "my@email.com",
                    onEmailInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.email)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun EmailTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                EmailTextInput(
                    email = "m@.l",
                    onEmailInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.email_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun EmailTextField_NoAtSymbol_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                EmailTextInput(
                    email = "myemail.com",
                    onEmailInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.email_error)).assertIsDisplayed()
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun EmailTextField_NoDotSymbol_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                EmailTextInput(
                    email = "my@email",
                    onEmailInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.email_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun EmailTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "my@email.c12345678901234567890"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                EmailTextInput(
                    email = input,
                    onEmailInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_email))
            .assertTextContains(res.getString(R.string.email))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
        }
    }

    @Test
    fun StreetTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                StreetTextInput(
                    street = "1235678 E Main St",
                    onStreetInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_street))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.street)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun StreetTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                StreetTextInput(
                    street = "123 E Mai",
                    onStreetInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_street))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.street_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun StreetTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "1235678012345678901"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                StreetTextInput(
                    street = input,
                    onStreetInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_street))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_street))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_street))
            .assertTextContains(res.getString(R.string.street))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
        }
    }

    @Test
    fun CityTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CityTextInput(
                    city = "Denver ",
                    onCityInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_city))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.city)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun CityTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CityTextInput(
                    city = "Ci",
                    onCityInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_city))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.city_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun CityTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "123456789012345678901234567890"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CityTextInput(
                    city = input,
                    onCityInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_city))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_city))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_city))
            .assertTextContains(res.getString(R.string.city))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
        }
    }

    @Test
    fun CreditCardTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        val input = "1234123412341234"
        val maskinput = "1234  1234  1234  1234"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcNumTextInput(
                    ccNum = input,
                    onCcNumInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccnum))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.cc_number)).assertIsDisplayed()
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(maskinput, value.toString())
            }
        }
    }

    @Test
    fun CreditCardTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcNumTextInput(
                    ccNum = "123412341234123",
                    onCcNumInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccnum))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.ccnum_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun CreditCardTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "1234123412341234"
        val maskinput = "1234  1234  1234  1234"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcNumTextInput(
                    ccNum = input,
                    onCcNumInfoValid = { isComplete = it }
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccnum))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccnum))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccnum))
            .assertTextContains(res.getString(R.string.cc_number))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(maskinput, value.toString())
            }
        }
    }

    @Test
    fun CcExpTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcExpTextInput(
                    ccExp = "1234",
                    onCcExpInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccexp))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.expiration)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun CcExpTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcExpTextInput(
                    ccExp = "123",
                    onCcExpInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccexp))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.exp_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun CcExpTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "1234"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcExpTextInput(
                    ccExp = input,
                    onCcExpInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccexp))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccexp))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_ccexp))
            .assertTextContains(res.getString(R.string.expiration))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
        }
    }

    @Test
    fun CcCvvTextField_ValidInput_CompleteStatusAndLabelCorrect() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcCvvTextInput(
                    ccCvv = "123",
                    onCcCvvInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_cvv))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.cvv)).assertIsDisplayed()
        assertEquals(isComplete, true)
    }

    @Test
    fun CcCvvTextField_ShortInput_InCompleteStatusAndErrorLabel() {
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcCvvTextInput(
                    ccCvv = "12",
                    onCcCvvInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_cvv))
        val semanticsConfig = root.fetchSemanticsNode().config

        composeTestRule.onNodeWithText(res.getString(R.string.cvv_error)).assertIsDisplayed()
        assertEquals(isComplete, false)
        isErrorDisplayed(semanticsConfig = semanticsConfig)
    }

    @Test
    fun CcCvvTextField_LongInput_CompleteStatusAndLabelCorrect() {
        val input = "123"
        composeTestRule.setContent {
            ComposeTemplateTheme {
                CcCvvTextInput(
                    ccCvv = input,
                    onCcCvvInfoValid = { isComplete = it },
                    modifier = Modifier
                )
            }
        }
        val root = composeTestRule.onNodeWithTag(res.getString(R.string.testtag_cvv))
        val semanticsConfig = root.fetchSemanticsNode().config
        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_cvv))
            .performTextInput("more")

        composeTestRule.onNodeWithTag(res.getString(R.string.testtag_cvv))
            .assertTextContains(res.getString(R.string.cvv))
        assertEquals(isComplete, true)
        for ((key, value) in semanticsConfig) {
            if (key.name == "EditableText") {
                assertEquals(input, value.toString())
            }
        }
    }

    fun isErrorDisplayed(semanticsConfig: SemanticsConfiguration) {
        for ((key, value) in semanticsConfig) {
            if (key.name == "Error") {
                assertEquals("Invalid input", value.toString())
            }
        }
    }
}
