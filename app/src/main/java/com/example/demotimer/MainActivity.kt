package com.example.demotimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.demotimer.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding

    //variable for timer which will be initialized later
    private lateinit var  countDownTimer: CountDownTimer
    //Duration of time in milliseconds
    private var  timerDuration :Long =120000
    //pauseEffect =timeDuration -time left
    private var pauseOffset :Long =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar()

        binding.tvTimer.text = "${(timerDuration/1000).toString()}"
        binding.btnStart.setOnClickListener {
            startTimer(pauseOffset)
        }
        binding.btnPause.setOnClickListener {
            pauseTimer()
        }
        binding.btnReset.setOnClickListener {
            resetTimer()
        }





    }
    private fun startTimer(pauseOffsetL:Long){
        countDownTimer = object :CountDownTimer(timerDuration -pauseOffsetL ,1000){
            override fun onTick(millisUnitilFinished: Long) {
                pauseOffset =timerDuration - millisUnitilFinished
                binding.tvTimer.text =(millisUnitilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Timer is finished", Toast.LENGTH_SHORT).show()
            }
        }.start()


    }
    private fun pauseTimer(){
        if (countDownTimer != null){
            countDownTimer.cancel()
        }
    }
    private fun resetTimer(){
        if (countDownTimer !=null){
            countDownTimer.cancel()
            binding.tvTimer.text = "${(timerDuration/1000).toString()}"
//            countDownTimer =null
            pauseOffset =0
        }
    }
}