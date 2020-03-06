package pe.solera.demopracticaltest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object AppUtils {

    fun isConnected(context: Context): Boolean {
       val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }



}