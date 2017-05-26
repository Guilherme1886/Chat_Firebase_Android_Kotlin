package br.com.examples.kotlinappandroid

import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.stream.Stream

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configButton()
        configListView()
        configRecycler()



        loginFB.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)


        }


    }

    private fun configRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = ListAdapter(this, getLists())



    }

    private fun getLists(): ArrayList<String> {

        val lists = ArrayList<String>()
        lists.add("JAVA")
        lists.add("KOTLIN")
        lists.add("PHP")
        lists.add("SWIFT")
        lists.add("JAVA Script")
        lists.add("MYSQL")
        return lists


    }

    private fun configButton() {

        button.setOnClickListener {
            Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_LONG).show()
            teste("Hello Kotlin of the World")
        }
    }

    private fun configListView() {

        val items = ArrayList<String>()
        items.add("Item1")
        items.add("Item2")
        items.add("Item3")
        items.add("Item4")
        items.add("Item5")

        list_item.adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, items)


    }

    private fun teste(message: String) {

        Snackbar.make(button, message, Snackbar.LENGTH_LONG).show()

    }

}
