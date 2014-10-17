package com.example.scala

import android.app.{Activity, LoaderManager}
import android.content.{CursorLoader, Loader}
import android.database.Cursor
import android.os.Bundle
import android.provider.BaseColumns
import android.provider.Telephony.{TextBasedSmsColumns, ThreadsColumns}
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.{RelativeLayout, Button, ListView}
import com.example.scala.Uris._
import com.example.scala.util.Loggable
import com.example.scala.TR._
import com.example.scala.TypedResource._

/**
 * @author: nikolayivanov
 */
class ScalaActivity extends Activity with LoaderManager.LoaderCallbacks[Cursor] with Loggable {

  val LOADER_ID = 1;

  val TAG = "Scala"

  implicit def toClickListener(f: View => Unit): View.OnClickListener = new OnClickListener {
    override def onClick(view: View): Unit = f(view)
  }

  val PROJECTION = Array(
    BaseColumns._ID,
    TextBasedSmsColumns.BODY,
    ThreadsColumns.DATE,
    ThreadsColumns.RECIPIENT_IDS
  )

  override def onCreateLoader(loaderId: Int, args: Bundle): Loader[Cursor] = AdapterModel.createLoader(this)


  override def onLoaderReset(loader: Loader[Cursor]): Unit = {}

  override def onLoadFinished(loader: Loader[Cursor], cursor: Cursor): Unit = {
    val list: ListView = this.findView(TR.list)

    for (columnName <- cursor.getColumnNames) {
      Log.i("Cursor", "name=" + columnName)
    }
    list.setAdapter(new ThreadAdapter(this, cursor))
  }


  override def onCreate(savedInstanceState: Bundle): Unit = {

    super.onCreate(savedInstanceState)

    setContentView(R.layout.main)

    val button: Button = getView(R.id.button)

    val listener: View => Unit = (x: View) => {
    }

    button.setOnClickListener((x: View) => {
    })
    getLoaderManager.restartLoader(LOADER_ID, null, this);
  }

  def getView[T <: View](id: Int): T = findViewById(id).asInstanceOf[T]

}
