package com.example.lesson19time

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lesson19time.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btTime.setOnClickListener(){
                getMinskTime()
            }
        }
    }

    private fun getMinskTime() {
        val queue = Volley.newRequestQueue(this@MainActivity)
        val stringTime = StringRequest(Request.Method.GET, url,
            {
              response ->
                timeToMink(response.toString())
            },
            {
                binding.textTime.text = "Error!".toString()
            })
        queue.add(stringTime)

    }

    private fun timeToMink(response: String) {
        val format = java.text.SimpleDateFormat("HH:mm:ss")
        val timeMink = java.util.Date((response.toLong()+10800) * 1000)
        binding.textTime.text = format.format(timeMink)
    }

    companion object {
        const val url = "https://time.akamai.com/xml"
    }
}