package com.example.havaman.api

import android.os.Message

sealed class networkrespose<out T> {
    data class Success<out T>(val data : T) : networkrespose<T>()
    data class Error(val message:  String) : networkrespose<Nothing>()
    object Loading : networkrespose<Nothing>()
}