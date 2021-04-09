package com.example.pos.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pos.R
import com.example.pos.Utils.UtilExtensions.openActivity

class MainActivity : AppCompatActivity() {


//    private lateinit var binding: ActivityMainBinding
    private lateinit var btnAdm: Button
    private lateinit var btnkasir : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdm = findViewById(R.id.btnAdm)
        btnkasir = findViewById(R.id.btnkasir)

        initView()
    }

    private fun initView() {
        btnAdm.setOnClickListener {
            openActivity(MenuAdminActivity::class.java)
        }

        btnkasir.setOnClickListener {
            openActivity(KasirActivity::class.java)
        }
    }


}