package com.soshi.listviewapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(private val context: Context,
                      private val userArrayList: ArrayList<User>): BaseAdapter() {

    private class ViewHolder(view: View) {
        val id: TextView = view.findViewById(R.id.item_id)
        val name: TextView  = view.findViewById(R.id.item_name)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                    as LayoutInflater
            view = inflater.inflate(R.layout.item, convertView)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val user = userArrayList[position]
        if (user.id != null) {
            viewHolder.id.text = user.id.toString()
            viewHolder.name.text = user.name
        } else {
            viewHolder.id.text = context.getString(R.string.no_data)
        }

        return view
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return userArrayList[position]
    }

    override fun getCount(): Int {
        return userArrayList.size
    }

    //ListViewを更新
    internal fun updateListView(userArrayList: ArrayList<User>) {
        this.userArrayList.clear()
        this.userArrayList.addAll(userArrayList)
        notifyDataSetChanged()
    }
}