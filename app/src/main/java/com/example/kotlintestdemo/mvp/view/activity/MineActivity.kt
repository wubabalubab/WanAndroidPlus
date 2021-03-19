//package com.example.kotlintestdemo.mvp.view
//
//import android.content.Context
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.kotlintestdemo.R
//import com.example.kotlintestdemo.databinding.ActivityMineBinding
//import okio.buffer
//import okio.sink
//import okio.source
//import java.io.File
//import java.nio.charset.Charset
//
//class MineActivity : AppCompatActivity() {
//
//    private lateinit var binding:ActivityMineBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMineBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.bindviewtv.text="bindview"
//        binding.bindviewtv.setTextColor(getColor(R.color.black))
//        binding.tvbindview2.text="bindview"
//        binding.tvbindview2.setTextColor(getColor(R.color.black))
//
//        getName()
//
//
//    }
//    open fun getFile(){
//         lateinit var file:File
//        if (this.filesDir.exists()) {
//            file.delete()
//            file=File(this.filesDir.path.toString()+"MyTest.txt")
//        }else{
//
//        }
//        if (file.exists()) {
//            file.delete()
//        }else{
//            file.mkdir()
//        }
//        file.sink().buffer().writeString("my file test context test", Charset.forName("utf-8")).close()
//        var fileContent=file.source().buffer().readString(Charset.forName("utf-8"))
//        println(fileContent)
//    }
//
//
//    fun getName():String{
//        val arrs =Array(3){Array(2){IntArray(1)} }
//        var str:String ="hello"
//        var str1 ="word"
//        var str2:String?=null
//        when(str){
//            "hello" -> println(str)
//        }
//        val list= arrayListOf("asdf","asdf","werwer")
//        for (s in list.indices) { //for 递增循环
//            print(list[s])
//        }
//        for (i in 2 until list.size-1){
//            print(list[i])
//        }
//        for ((i,item)in list.withIndex()){
//            print(i.toString()+ item)
//        }
//        return list[0]
//    }
//
//}
