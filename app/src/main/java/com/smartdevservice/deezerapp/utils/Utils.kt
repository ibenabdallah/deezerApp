package com.smartdevservice.deezerapp.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.VisibleForTesting
import timber.log.Timber

object Utils {

    const val KEY_ALBUM = "KEY_ALBUM"

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public fun getDensityDpiOfDevice(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.densityDpi
    }

    fun getEnumDensityOfDevice(context: Context): EnumDensity {
        val densityDpi = getDensityDpiOfDevice(context)
        return when {
            densityDpi <= DisplayMetrics.DENSITY_MEDIUM -> {
                Timber.d("less than or equals of mdpi (160)")
                EnumDensity.DENSITY_MDPI
            }
            densityDpi <= DisplayMetrics.DENSITY_HIGH -> {
                Timber.d("Between mdpi (160) and hdpi (240)")
                EnumDensity.DENSITY_HDPI
            }
            densityDpi <= DisplayMetrics.DENSITY_XHIGH -> {
                Timber.d("Between hdpi (240) and xhdpi (320)")
                EnumDensity.DENSITY_XHDPI
            }
            densityDpi <= DisplayMetrics.DENSITY_XXHIGH -> {
                Timber.d("Between xhdpi (320) and xxhdpi (480)")
                EnumDensity.DENSITY_XXHDPI
            }
            densityDpi <= DisplayMetrics.DENSITY_XXXHIGH -> {
                Timber.d("Between xxhdpi (480) and xxxhdpi (640)")
                EnumDensity.DENSITY_XXXHDPI
            }
            else -> {
                Timber.d("Bigger xxxhdpi: > 640")
                EnumDensity.DENSITY_XXXHDPI
            }
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    fun convertDpToPixel(dp: Int, context: Context): Int {
        return (dp * (getDensityDpiOfDevice(context) / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }
}