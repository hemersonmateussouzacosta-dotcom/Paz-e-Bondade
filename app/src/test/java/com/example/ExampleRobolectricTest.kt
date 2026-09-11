package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Pax et Bonum", appName)
  }

  @Test
  fun `verify frei galvao novena structure`() {
    val days = com.example.data.FreiGalvaoData.novenaDays
    assertEquals(9, days.size)
    // Days 1, 5, and 9 are traditional pill days
    val pillDays = days.filter { it.isPillDay }.map { it.day }
    assertEquals(listOf(1, 5, 9), pillDays)
  }

  @Test
  fun `verify frei galvao prayers and antiphon`() {
    val prayers = com.example.data.FreiGalvaoData.prayers
    val official = prayers.find { it.id == "oracao_oficial" }
    org.junit.Assert.assertNotNull(official)
    org.junit.Assert.assertTrue(official!!.latinAntiphon.contains("Post partum"))
  }
}
