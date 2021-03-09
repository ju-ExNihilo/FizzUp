package com.jgdeveloppement.fizzup.networking

import java.io.IOException

object ConnexionInternet {

    fun isConnected(callback : (ok: Boolean)->Unit){
        val command = "ping -c 1 google.com"
        val ping = Runtime.getRuntime().exec(command).waitFor()
        if (ping == 0) callback(true) else callback(false)
    }
}