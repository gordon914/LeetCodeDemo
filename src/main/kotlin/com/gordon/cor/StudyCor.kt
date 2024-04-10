package org.example.com.gordon.cor

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun test1(){
    GlobalScope.launch {

    }

    GlobalScope.cancel()
}