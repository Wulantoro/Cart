package com.example.pos.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pos.R
import com.example.pos.Utils.UtilExtensions.openActivity
import kotlinx.android.synthetic.main.activity_menu_admin.*

class MenuAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)

        initView()
    }

    private fun initView() {
        btnBarang.setOnClickListener {
            openActivity(AdminActivity::class.java)
        }

        btnTrans.setOnClickListener {
//            openActivity()
        }
    }
}