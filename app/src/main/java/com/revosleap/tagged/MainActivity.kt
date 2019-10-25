package com.revosleap.tagged

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.revosleap.tagcloud.entities.Tag
import com.revosleap.tagcloud.ui.TagCloudLinkView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),TagCloudLinkView.OnTagSelectListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        makeTags()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTagSelected(tag: Tag, position: Int) {
        Toast.makeText(this,tag.text,Toast.LENGTH_SHORT).show()
    }

    private fun makeTags(){

        tagView.apply {
            setOnTagSelectListener(this@MainActivity)
            add(Tag(1,"Viper"))
            add(Tag(1,"Venom"))
            add(Tag(1,"Plano"))
            add(Tag(1,"Payin"))
            add(Tag(1,"Valerian"))
            add(Tag(1,"Scooby"))
            add(Tag(1,"Pewin"))
            add(Tag(1,"Drac"))
            add(Tag(1,"Cartoons"))
            add(Tag(1,"America"))
            add(Tag(1,"Beard"))
            add(Tag(1,"Yellow Ghost"))
            add(Tag(1,"Monkey Bar"))
        }
    }
}
