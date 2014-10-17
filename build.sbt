import android.Keys._

android.Plugin.androidBuild

name := "FindLost"

scalaVersion := "2.10.4"

organization := "com.example.scala"


platformTarget in Android := "android-19"

zipalignPath in Android := "/usr/local/bin/zipalign"

run <<= run in Android

install <<= install in Android

