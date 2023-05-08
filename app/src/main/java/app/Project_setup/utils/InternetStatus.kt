package app.Project_setup.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Socket
import java.net.URL


object InternetStatus {

    /**
     * Fast and Accurate, can Run on UI Thread
     * */
    @TargetApi(Build.VERSION_CODES.M)
    fun isNetworkOnline(context: Context): Boolean {
        var isOnline = false
        try {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = manager.getNetworkCapabilities(manager.activeNetwork) // need ACCESS_NETWORK_STATE permission
            isOnline =
                capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return isOnline
    }

    fun isNetworkOnline2(): Boolean {
        var isOnline = false
        try {
            val runtime = Runtime.getRuntime()
            val p = runtime.exec("ping -c 1 8.8.8.8")
            val waitFor = p.waitFor()
            isOnline =
                waitFor == 0 // only when the waitFor value is zero, the network is online indeed

            // BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            // String str;
            // while ((str = br.readLine()) != null) {
            //     System.out.println(str);     // you can get the ping detail info from Process.getInputStream()
            // }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return isOnline
    }

    fun isNetworkOnline3(): Boolean {
        var isOnline = false
        try {
            val url = URL("http://www.google.com") // or your server address
            // URL url = new URL("http://www.baidu.com");
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.setRequestProperty("Connection", "close")
            conn.setConnectTimeout(3000)
            isOnline = conn.getResponseCode() === 200
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return isOnline
    }

    fun isNetworkOnline4(): Boolean {
        var isOnline = false
        try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 3000)
            // socket.connect(new InetSocketAddress("114.114.114.114", 53), 3000);
            isOnline = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return isOnline
    }

    var connectivityManager: ConnectivityManager? = null
    var wifiInfo: NetworkInfo? = null
    var mobileInfo:NetworkInfo? = null
    var connected = false


    fun isOnline(context: Context): Boolean {
        try {
            connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val networkInfo = connectivityManager!!.activeNetworkInfo
            connected = networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
            return connected
        } catch (e: Exception) {
            println("CheckConnectivity Exception: " + e.message)
            Log.v("connectivity", e.toString())
        }
        return connected
    }

    private fun haveNetworkConnection(context: Context): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm!!.allNetworkInfo
        for (ni in netInfo) {
            if (ni.typeName
                    .equals("WIFI", ignoreCase = true)
            ) if (ni.isConnected) haveConnectedWifi = true
            if (ni.typeName
                    .equals("MOBILE", ignoreCase = true)
            ) if (ni.isConnected) haveConnectedMobile = true
        }
        return haveConnectedWifi || haveConnectedMobile
    }

}