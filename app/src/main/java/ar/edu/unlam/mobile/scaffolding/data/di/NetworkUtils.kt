package ar.edu.unlam.mobile.scaffolding.data.di

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkUtils @Inject constructor(private val connectivityManager: ConnectivityManager) {

    fun isInternetAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}