package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    var lastNumeric = false
    var lastDot = false
    fun onDigit(view: View) {

        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvInput.setText("")
        lastNumeric = false
        lastDot = false

    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDot)
        {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View)
    {
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString()))
                tvInput.append((view as Button).text)
                lastNumeric = false
                lastDot = false

    }

    private fun isOperatorAdded(value : String) : Boolean
    {
        return if(value.contains("-"))
        {
            false
        }else
        {
            value.contains("/") || value.contains("*")
            || value.contains("+") || value.contains("-")
        }
    }

    fun removeZeroAfterDot(result : String) : String
    {
        var value = result
        if(value.contains(".0"))
        {
            value = value.substring(0,value.length-2)
        }
        return value

    }

    fun onEqual(view: View)
    {
            if(lastNumeric)
            {
                var tvValue = tvInput.text.toString()
                var prefix = ""
                if(tvValue.startsWith("-"))
                {
                       prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                try {

                    if (tvValue.contains("-"))
                    {
                        var splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if(prefix.isNotEmpty())
                        {
                            one = prefix + one
                        }

                        tvInput.setText(removeZeroAfterDot((one.toDouble() - two.toDouble()).toString()))

                    }else if(tvValue.contains("+"))
                    {
                        var splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if(prefix.isNotEmpty())
                        {
                            one = prefix + one
                        }

                        tvInput.setText(removeZeroAfterDot((one.toDouble() + two.toDouble()).toString()))
                    }else if(tvValue.contains("*"))
                    {
                        var splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if(prefix.isNotEmpty())
                        {
                            one = prefix + one
                        }

                        tvInput.setText(removeZeroAfterDot((one.toDouble() * two.toDouble()).toString()))
                    }else if(tvValue.contains("/"))
                    {
                        var splitValue = tvValue.split("/")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if(prefix.isNotEmpty())
                        {
                            one = prefix + one
                        }

                        tvInput.setText(removeZeroAfterDot((one.toDouble() / two.toDouble()).toString()))
                    }




                }catch (e : ArithmeticException)
                {
                    e.printStackTrace()
                }



            }



    }


}