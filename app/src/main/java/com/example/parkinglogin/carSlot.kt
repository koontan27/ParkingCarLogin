package com.example.parkinglogin


import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.parkinglogin.databinding.FragmentCarSlotBinding
import com.example.parkinglogin.ui.CarInfo

/**
 * A simple [Fragment] subclass.
 */
class carSlot : Fragment() {

    private lateinit var binding:FragmentCarSlotBinding
    private val booking: ArrayList<CarInfo> = ArrayList()
    private var car: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_slot, container, false)
        for(x in 0..2){
            booking.add(x, CarInfo("","",""))
        }

        binding.apply {
            empty1.setBackgroundResource(android.R.drawable.btn_default);
            empty2.setBackgroundResource(android.R.drawable.btn_default);
            empty3.setBackgroundResource(android.R.drawable.btn_default);
        }
        if (car == 0) {
            binding.empty1.setText("empty")
            booking.removeAt(0)
            booking.add(0,CarInfo("","",""));
        } else if (car == 1) {
            binding.empty2.setText("empty")
            booking.removeAt(1)
            booking.add(1,CarInfo("","",""));
        } else if (car == 2) {
            binding.empty3.setText("empty")
            booking.removeAt(2)
            booking.add(2,CarInfo("","",""));
        }
        set()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


    private fun set(){
        binding.apply {
            empty1.setOnClickListener {
                car = 0;
                empty1.setBackgroundColor(Color.GRAY)
                empty2.setBackgroundResource(android.R.drawable.btn_default);
                empty3.setBackgroundResource(android.R.drawable.btn_default);
                showValue(car);
            }
            empty2.setOnClickListener {
                empty2.setBackgroundColor(Color.GRAY)
                empty1.setBackgroundResource(android.R.drawable.btn_default);
                empty3.setBackgroundResource(android.R.drawable.btn_default);
                car = 1;
                showValue(car);
            }
            empty3.setOnClickListener {
                empty3.setBackgroundColor(Color.GRAY)
                empty1.setBackgroundResource(android.R.drawable.btn_default);
                empty2.setBackgroundResource(android.R.drawable.btn_default);
                car = 2;
                showValue(car);

            }
            submit.setOnClickListener {
                setValue(it)
            }
        }
    }
    private fun showValue(slot: Int) {
        binding.licenseplate.setText(booking.get(car).licenseplate)
        binding.brand.setText(booking.get(car).brand)
        binding.owner.setText(booking.get(car).owner)
    }

    private fun setValue(view:View) {
        val registrationnumber = binding.licenseplate.text.toString()
        val brand = binding.brand.text.toString()
        val name = binding.owner.text.toString()
        booking.add(car , CarInfo(registrationnumber, brand, name))
        if (car == 0) {
            binding.empty1.setText("เต็ม")
        } else if (car == 1) {
            binding.empty2.setText("เต็ม")
        } else {
            binding.empty3.setText("เต็ม")
        }

    }
}
