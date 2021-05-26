package com.example.calculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View){
        binding.tvndis.append((view as Button).text)
        lastNumeric = true

    }
    fun AC(view: View){
        binding.tvndis.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimal(view: View){
        if(isOperatorAdded(binding.tvndis.text.toString())){
            lastDot =false
            if(!lastDot && lastNumeric){
                binding.tvndis.append(".")
                lastNumeric = false
                lastDot = true
            }
        }else if(lastNumeric && !lastDot){
            binding.tvndis.append(".")
            lastNumeric = false
            lastDot = true
        }




    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = binding.tvndis.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitn = tvValue.split("-")
                    var one = splitn[0]
                    var two = splitn[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    binding.tvndis.text = (removeZero((one.toDouble() - two.toDouble()).toString()))
                } else if(tvValue.contains("+")){
                    val splitn = tvValue.split("+")
                    var one = splitn[0]
                    var two = splitn[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    binding.tvndis.text = removeZero((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("*")){
                    val splitn = tvValue.split("*")
                    var one = splitn[0]
                    var two = splitn[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    binding.tvndis.text = removeZero((one.toDouble() * two.toDouble()).toString())
                } else if(tvValue.contains("/")){
                    val splitn = tvValue.split("/")
                    var one = splitn[0]
                    var two = splitn[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    binding.tvndis.text = (one.toDouble() / two.toDouble()).toString()
                } else if(tvValue.contains("%")){
                    val splitn = tvValue.split("%")
                    var one = splitn[0]
                    var two = splitn[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    binding.tvndis.text = removeZero(((one.toDouble() * two.toDouble())/100).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    fun onInteger(view: View){
        var tvValue = binding.tvndis.text.toString()
        if(tvValue.startsWith("-")){
            tvValue = tvValue.substring(1)
        }else{
            tvValue = "-" + tvValue
        }
        binding.tvndis.text = tvValue
    }
    private fun removeZero(result: String): String{
        var value = result
        if(result.contains("0"))
            value = result.substring(0,result.length-2)
        return value

    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||value.contains("*")||value.contains("%")
                    || value.contains("+")||value.contains("-")
        }
    }
    fun operator(view: View){
        if(lastNumeric && !isOperatorAdded(binding.tvndis.text.toString())) {
            binding.tvndis.append((view as Button).text)
            lastNumeric = true
        }



    }
}