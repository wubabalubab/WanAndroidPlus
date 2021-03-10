package com.example.kotlintestdemo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintestdemo.R
import com.example.kotlintestdemo.adapter.MainAdapter
import com.example.kotlintestdemo.mvp.view.*
import com.example.kotlintestdemo.mvp.view.fragments.BaseAdapterHelperDemo
import com.example.kotlintestdemo.view.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter: MainAdapter
    //    var msList: List<String> = mutableListOf()
    var mList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mList.add(0, "asdf")
        mList.add(1, "2222")
        mList.add(2, "3333")
        mList.add(3, "侧滑adapter")
        mList.add(4, "adapterhelper")
        mList.add(5, "权限banner")
        mList.add(6, "new侧滑adapter")
        mList.add(7,"handlertest")
        mList.add(8,"main2act")
        mList.add(9,"knife")
        mAdapter = MainAdapter(this, mList)
        mAdapter.addMineListener { position ->
            when (position) {
                0 -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Toast.makeText(this,"now",Toast.LENGTH_SHORT).show()
                }
                1 -> startActivity(Intent(this, SwipeActivity::class.java))
                2 -> startActivity(Intent(this, Swipe2Activity::class.java))
                3 -> startActivity(Intent(this, RecyclerActivity::class.java))
                4 -> startActivity(Intent(this, BaseAdapterHelperDemo::class.java))
                5 -> startActivity(Intent(this, XBannerActivity::class.java))
                6 -> startActivity(Intent(this, DeleteActivity::class.java))
                7 -> startActivity(Intent(this,HandlerActivity::class.java))
                8 -> startActivity(Intent(this,Main2Activity::class.java))
//                9 -> startActivity(Intent(this,MineActivity::class.java))
                9 -> startActivity(Intent(this,KnifeActivity::class.java))
            }
        }
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = mAdapter;
        test1(0)
    }

    private fun test1(a: Int): Int {
        //类型后面加?表示可为空
        var age: String? = "12"
//抛出空指针异常
        val ages = age!!.toInt()
//不做处理返回 null
        val ages1 = age?.toInt()
//age为空返回-1
        val ages2 = age?.toInt() ?: -1
        if (age is String) {
//            println("asdf")
        }
        for (age in 1..19) {
//            println(age)
            var age1 = age
//            println(++age1)
        }
        return a
    }
}
