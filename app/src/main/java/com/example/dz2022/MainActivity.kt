package com.example.dz2022

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dz2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MyAdapter
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MyAdapter(Constance.events, this)
        recycler = findViewById(R.id.recyclerView)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(Constance.events, this)

        binding.plusBtn.setOnClickListener {
            addInfo()
        }

    }
    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MyAdapter)?.apply {
            bindActors(Constance.events)
        }
    }

    private fun addInfo() {
        val infl = LayoutInflater.from(this)
        val view = infl.inflate(R.layout.alert_item, null)
        val name = view.findViewById<EditText>(R.id.etName)
        val fdesc = view.findViewById<EditText>(R.id.etFirstDesc)
        val sdesc = view.findViewById<EditText>(R.id.etSecDesc)
        val fdate = view.findViewById<EditText>(R.id.etFirstDate)
        val sdate = view.findViewById<EditText>(R.id.etSecDesc)
        val trud = view.findViewById<EditText>(R.id.etTrud)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(view)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val nam = name.text.toString()
            val firstdesc = fdesc.text.toString()
            val secdesc = sdesc.text.toString()
            val firstdate = fdate.text.toString()
            val seconddate = sdate.text.toString()
            val trudn = trud.text.toString()

            Constance.events.add(
                Data("$nam", "$firstdesc", "$secdesc", "$firstdate", "$seconddate", "$trudn")
            )
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}