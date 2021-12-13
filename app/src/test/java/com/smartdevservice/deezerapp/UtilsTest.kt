package com.smartdevservice.deezerapp

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import com.smartdevservice.deezerapp.utils.EnumDensity
import com.smartdevservice.deezerapp.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception


class UtilsTest {

    private val mockContext: Context = mock(Context::class.java)
    private val mockResources: Resources = mock(Resources::class.java)
    private val displayMetrics: DisplayMetrics = mock(DisplayMetrics::class.java)

    @Throws(Exception::class)
    @Test
    fun `test getDensityDpiOfDevice when densityDpi = 240`() {

        displayMetrics.densityDpi = 240
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getDensityDpiOfDevice(mockContext), 240)
    }

    @Test
    fun `test getDensityDpiOfDevice when densityDpi = 640`() {

        displayMetrics.densityDpi = 640
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getDensityDpiOfDevice(mockContext), 640)
    }


    @Test
    fun `test getEnumDensityOfDevice when densityDpi = 160`() {

        displayMetrics.densityDpi = 160
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getEnumDensityOfDevice(mockContext), EnumDensity.DENSITY_MDPI)
    }

    @Test
    fun `test getEnumDensityOfDevice when densityDpi = 240`() {

        displayMetrics.densityDpi = 240
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getEnumDensityOfDevice(mockContext), EnumDensity.DENSITY_HDPI)
    }

    @Test
    fun `test getEnumDensityOfDevice when densityDpi = 320`() {

        displayMetrics.densityDpi = 320
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getEnumDensityOfDevice(mockContext), EnumDensity.DENSITY_XHDPI)
    }

    @Test
    fun `test getEnumDensityOfDevice when densityDpi = 480`() {

        displayMetrics.densityDpi = 480
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getEnumDensityOfDevice(mockContext), EnumDensity.DENSITY_XXHDPI)
    }

    @Test
    fun `test getEnumDensityOfDevice when densityDpi = 640`() {

        displayMetrics.densityDpi = 640
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getEnumDensityOfDevice(mockContext), EnumDensity.DENSITY_XXXHDPI)
    }

    @Test
    fun `test getEnumDensityOfDevice when densityDpi = 800`() {

        displayMetrics.densityDpi = 800
        `when`(mockResources.displayMetrics).thenReturn(displayMetrics)
        `when`(mockContext.resources).thenReturn(mockResources)

        assertEquals(Utils.getEnumDensityOfDevice(mockContext), EnumDensity.DENSITY_XXXHDPI)
    }
}