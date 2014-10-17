package com.example.scala

import java.util.Date

import android.content.{CursorLoader, Context}
import android.database.Cursor
import android.text.format.DateFormat
import android.util.Log
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.{CursorAdapter, TextView}

/**
 * @author: nikolayivanov
 */
class ThreadAdapter(context: Context,cursor: Cursor) extends CursorAdapter(context, cursor, true) {

  val dateFormat = DateFormat.getDateFormat(context)

  val bodyIndex = cursor.getColumnIndexOrThrow("body")

  val dateIndex = cursor.getColumnIndexOrThrow("date")

  val tidIndex = cursor.getColumnIndexOrThrow("tid")

  class ViewHolder(view:View){
    val snippet : TextView = view.findViewById(R.id.snippet).asInstanceOf[TextView]
    val dateTime : TextView =  view.findViewById(R.id.date_time).asInstanceOf[TextView]
  }

  override def newView(context: Context, cursor: Cursor, parent: ViewGroup): View = {
    val view: View = LayoutInflater.from(context).inflate(R.layout.element,parent,false)
    view.setTag(new ViewHolder(view))
    view
  }

  override def bindView(view: View, context: Context, cursor: Cursor): Unit = {
    val holder = view.getTag().asInstanceOf[ViewHolder]
    holder.snippet.setText(cursor.getString(bodyIndex))
    holder.dateTime.setText(dateFormat.format(new Date(cursor.getLong(dateIndex))))
    Log.i("Adapater","ids=" + cursor.getString(3))
  }
}

object AdapterModel {

  val PROJECTION = Array("*")

  def initLoader(loader:CursorLoader) = {
      loader.setUri(Uris.threadsUri)
      loader.setProjection(PROJECTION)
      loader.setSortOrder("normalized_date desc")
  }
  def createLoader(context: Context) = {
    val loader = new CursorLoader(context)
    initLoader(loader)
    loader
  }
}
