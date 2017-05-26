package br.com.examples.kotlinappandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_chat.view.*

class ChatActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var adapter: FirebaseListAdapter<ChatModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mAuth = FirebaseAuth.getInstance()

        requestMessagesFirebase()
    }

    private fun requestMessagesFirebase() {

        adapter = object : FirebaseListAdapter<ChatModel>(
                this@ChatActivity, ChatModel::class.java, R.layout.item_chat, FirebaseDatabase.getInstance().reference) {
            override fun populateView(v: View?, model: ChatModel?, position: Int) {

                val messageText = v?.findViewById(R.id.item_text_msg) as TextView
                messageText.text = model?.message


            }
        }

        list_of_messages.adapter = adapter


    }


    fun buttonSendMessageWithFirebase(view: View) {

        val chat = ChatModel()
        chat.message = text_msg.text.toString()

        FirebaseDatabase
                .getInstance()
                .reference.push()
                .setValue(chat)

        text_msg.setText("")

        list_of_messages.smoothScrollToPositionFromTop(adapter?.count!! - 1, 0, 0)
        adapter?.notifyDataSetChanged()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.logout -> {
                mAuth?.signOut()
                startActivity(Intent(this@ChatActivity, LoginActivity::class.java))
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }
}
