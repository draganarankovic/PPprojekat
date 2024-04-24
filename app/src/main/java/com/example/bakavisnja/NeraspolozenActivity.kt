package com.example.bakavisnja

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NeraspolozenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_neraspolozen)

        val btnVoltUrl: Button=findViewById(R.id.volt)
        btnVoltUrl.setOnClickListener {
            val openUrl = Intent(android.content.Intent.ACTION_VIEW)
            openUrl.data = Uri.parse("https://wolt.com/sr/discovery/restaurants?pid%5B0%5D=google&pid%5B1%5D=googleadwords_int&c=SRB_Web_FTU_Search_Brand_All&af_adset=SRB_All_Brand_Search&af_keywords=wolt&keyword_match_type=e&matchtype=e&device=c&utm_source=google&utm_medium=cpc&utm_campaign=SRB_Web_FTU_Search_Brand_All&utm_adgroup=SRB_All_Brand_Search&utm_keyword=wolt&utm_term=wolt&gad_source=1&gclid=CjwKCAjw26KxBhBDEiwAu6KXtw4sSlHVYW-vgkkTJfOB8erYHGDGkHZACQxpkXDKpoS0t8d4-nzmXBoCRaEQAvD_BwE")

            startActivity(openUrl)
        }

        val btnGlovoUrl: Button=findViewById(R.id.glovo)
        btnGlovoUrl.setOnClickListener {
            val openUrl2 = Intent(android.content.Intent.ACTION_VIEW)
            openUrl2.data = Uri.parse("https://glovoapp.com/rs/sr/?utm_source=google&utm_medium=cpc&utm_campaign=google_search_brandprotection_newusers_RS_Exact_digitalbudget_serbian&utm_campaignid=12071799529&utm_adgroupid=116436790036&utm_term=glovo&utm_matchtype=e&utm_device=c&gad_source=1&gclid=CjwKCAjw26KxBhBDEiwAu6KXtyfvwNrq3cvuUdQUbsS-lTZzNCO3uxjaV0MtxLjdwZ1qUGaJNrIp8hoCPXwQAvD_BwE")

            startActivity(openUrl2)
        }

        val btnBVisnjaUrl: Button=findViewById(R.id.kiflice)
        btnBVisnjaUrl.setOnClickListener {
            val openUrl3 = Intent(android.content.Intent.ACTION_VIEW)
            openUrl3.data = Uri.parse("https://kiflice.rs/")

            startActivity(openUrl3)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}