package com.example.scala.util

import android.util.Log

/**
 * @author: nikolayivanov
 */
trait Loggable {

  val TAG : String

  def i(msg:String,args:Any*) = Log.i(TAG,msg.format(args))

}
