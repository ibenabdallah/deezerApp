package com.smartdevservice.deezerapp.utils

import android.content.Context
import android.util.DisplayMetrics
import timber.log.Timber

object Utils {

    const val KEY_ALBUM = "KEY_ALBUM"

    private fun getDensityDpiOfDevice(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.densityDpi
    }

    fun getEnumDensityOfDevice(context: Context): EnumDensity {
        val densityDpi = getDensityDpiOfDevice(context)
        return when {
            densityDpi < DisplayMetrics.DENSITY_LOW -> {
                Timber.d("Smaller ldpi: < 120")
                EnumDensity.DENSITY_MDPI
            }
            densityDpi == DisplayMetrics.DENSITY_LOW -> {
                Timber.d("ldpi (120)")
                EnumDensity.DENSITY_MDPI
            }
            densityDpi < DisplayMetrics.DENSITY_MEDIUM -> {
                Timber.d("Between ldpi (120) and mdpi (160)")
                EnumDensity.DENSITY_MDPI
            }
            densityDpi == DisplayMetrics.DENSITY_MEDIUM -> {
                Timber.d("mdpi (160)")
                EnumDensity.DENSITY_MDPI
            }
            densityDpi < DisplayMetrics.DENSITY_HIGH -> {
                Timber.d("Between mdpi (160) and hdpi (240)")
                EnumDensity.DENSITY_HDPI
            }
            densityDpi == DisplayMetrics.DENSITY_XHIGH -> {
                Timber.d("xhdpi (320)")
                EnumDensity.DENSITY_XHDPI
            }
            densityDpi < DisplayMetrics.DENSITY_XXHIGH -> {
                Timber.d("Between xhdpi (320) and xxhdpi (480)")
                EnumDensity.DENSITY_XXHDPI
            }
            densityDpi == DisplayMetrics.DENSITY_XXHIGH -> {
                Timber.d("xxhdpi (480)")
                EnumDensity.DENSITY_XXHDPI
            }
            densityDpi < DisplayMetrics.DENSITY_XXXHIGH -> {
                Timber.d("Between xxhdpi (480) and xxxhdpi (640)")
                EnumDensity.DENSITY_XXXHDPI
            }
            densityDpi == DisplayMetrics.DENSITY_XXXHIGH -> {
                Timber.d("xxxhdpi (640)")
                EnumDensity.DENSITY_XXXHDPI
            }
            else -> {
                Timber.d("Bigger xxxhdpi: > 640")
                EnumDensity.DENSITY_XXXHDPI
            }
        }
    }
}