package com.example.dz2022

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent


class MyAdapter(private var array: ArrayList<Data>,val context: FragmentActivity
) : RecyclerView.Adapter<MyAdapter.MyVieholder>() {
    class MyVieholder(view: View) : RecyclerView.ViewHolder(view) {
      var Name: TextView = view.findViewById(R.id.tvName)
      var fDesc: TextView = view.findViewById(R.id.tvDesc)
      var sDesc: TextView = view.findViewById(R.id.tvDesc2)
      var fDate: TextView = view.findViewById(R.id.tvFirstDate)
      var sDate: TextView = view.findViewById(R.id.tvSecDate)
      var trud: TextView = view.findViewById(R.id.trud)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVieholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyVieholder(view)
    }

    override fun onBindViewHolder(holder: MyVieholder, position: Int) {
        val item = array[position]
        val ind = array.indexOf(item)

        holder.Name.text = "Название: ${item.Name}"
        holder.fDesc.text = "Описание: ${item.firstDesc}"
        holder.sDesc.text = "Описание2: ${item.secDesc}"
        holder.fDate.text = "Дата:${item.firstDate}"
        holder.sDate.text = "Дата2:${item.secDate}"
        holder.trud.text = "Трудности:${item.Trud}"



        holder.itemView.setOnClickListener {

            val i = Intent (context, SecondActivity::class.java)
            i.putExtra("key", item)
            i.putExtra("key2", ind)
            context.startActivity(i)
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return array.size
    }


//fun update(){
//    notifyDataSetChanged()
//}
fun bindActors(newData: ArrayList<Data>) {
    array = newData
    notifyDataSetChanged()
}

}