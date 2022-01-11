package com.example.dz2022

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.Transliterator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dz2022.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val res = intent.getSerializableExtra("key") as Data
        val res2 = intent.getIntExtra("key2",0)
        binding.tvName.text = res.Name
        binding.tvDesc.text = res.firstDesc
        binding.tvDesc2.text = res.secDesc
        binding.tvFirstDate.text = res.firstDate
        binding.tvSecDate.text = res.secDate
        binding.tvTrud.text = res.Trud

        adapter = MyAdapter(Constance.events,this)

        binding.btnBack.setOnClickListener{
            adapter.update(res2)
            onBackPressed()
            adapter.notifyItemChanged(res2)

        }

        binding.editBtn.setOnClickListener {
            val v = LayoutInflater.from(this).inflate(R.layout.alert_item, null)
            val name = v.findViewById<EditText>(R.id.etName)
            val fdesc = v.findViewById<EditText>(R.id.etFirstDesc)
            val sdesc = v.findViewById<EditText>(R.id.etSecDesc)
            val fdate = v.findViewById<EditText>(R.id.etFirstDate)
            val sdate= v.findViewById<EditText>(R.id.etSecDesc)
            val trud = v.findViewById<EditText>(R.id.etTrud)

            AlertDialog.Builder(this).setView(v)
                .setPositiveButton("Ok") { dialog, _ ->


                    Constance.events.set(res2,Data(name.text.toString(),fdesc.text.toString(),sdesc.text.toString(),
                    fdate.text.toString(),sdate.text.toString(),trud.text.toString()))
//                    res.Name = name.text.toString()
//                    res.firstDesc = fdesc.text.toString()
//                    res.secDesc = sdesc.text.toString()
                    binding.tvName.setText(name.text)
                    binding.tvDesc.setText(fdesc.text)
                    binding.tvDesc2.setText(sdesc.text)
                    binding.tvFirstDate.setText(fdate.text)
                    binding.tvSecDate.setText(sdate.text)
                    binding.tvTrud.setText(trud.text)
                    adapter.notifyDataSetChanged()
                    Log.d("MyTag","${Constance.events}")

                    Toast.makeText(this, "Edited", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()

                }
                .create().show()
        }
    }


}