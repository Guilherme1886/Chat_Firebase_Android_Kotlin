package br.com.examples.kotlinappandroid

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_layout.view.*


class ListAdapter(var c: Context, var lists: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        (holder as Item).bindData(lists[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(c).inflate(R.layout.list_layout, parent, false)

        return Item(v)

    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(_list: String) {
            itemView.textView.text = _list
        }
    }
}