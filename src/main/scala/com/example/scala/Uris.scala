package com.example.scala

import android.net.Uri
import android.os.Build
import android.provider.Telephony

/**
 * @author: nikolayivanov
 */
object Uris {
  lazy val smsUri: Uri =
    if (isKitKat) {
      Telephony.Sms.CONTENT_URI
    }
    else {
      Uri.parse("content://sms/")
    }

  lazy val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT


  lazy val threadsUri: Uri = if (isKitKat) {
    Telephony.Threads.CONTENT_URI
  } else {
    smsUri.buildUpon().appendPath("threads").build()
  }
}
