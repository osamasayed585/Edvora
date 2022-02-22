package com.edvora.edvora.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edvora.edvora.R
import com.edvora.edvora.pojo.Ride
import com.edvora.edvora.pojo.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GenerallyAdapter : RecyclerView.Adapter<GenerallyAdapter.MyViewHolder>() {

    private var list: List<Ride> = emptyList()
    private var user: User? = null
    var itemListener: OnClickGenerallyItemListener? = null
    var stateNameListener: OnClickStateNameListener? = null
    var cityNameListener: OnClickCityNameListener? = null

    fun setData(dataList: List<Ride>) {
        this.list = dataList
        notifyDataSetChanged()
    }

    fun setUser(user: User) {
        this.user = user
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var rideId: TextView = itemView.findViewById(R.id.rideId)
        private var originStation: TextView = itemView.findViewById(R.id.originStation)
        private var stationPath: TextView = itemView.findViewById(R.id.StationPath)
        private var date: TextView = itemView.findViewById(R.id.date)
        private var distance: TextView = itemView.findViewById(R.id.distance)
        private var cityName: TextView = itemView.findViewById(R.id.cityName)
        private var stateName: TextView = itemView.findViewById(R.id.stateName)


        fun bind(ride: Ride) {

            //  Picasso.get().load(book.image).into(image)

            stateName.text = ride.state

            cityName.text = ride.city

            rideId.text = ride.id.toString()

            originStation.text = ride.origin_station_code.toString()

            stationPath.text = ride.station_path.toString()

            date.text = convert(ride.date)

            distance.text = String.format("%s", ride.destination_station_code - user!!.station_code)

            itemView.setOnClickListener {
                val itemSelected: Ride = list[adapterPosition]
                itemListener?.onItemClicked(itemSelected)
            }
            cityName.setOnClickListener {
                cityNameListener?.onCityClicked(adapterPosition)
            }

            stateName.setOnClickListener {
                stateNameListener?.onStateClicked(adapterPosition)
            }
        }

        fun convert(strDate1: Long): String {
            var date = Date(strDate1)
            try {
                var f =  SimpleDateFormat("dd MMM yyyy HH:mm",Locale.ENGLISH)
                return f.format(date)
            } catch (ex: ParseException) {
                ex.printStackTrace();
            }
            return "null"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_nearest_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickGenerallyItemListener {
        fun onItemClicked(ride: Ride)
    }

    interface OnClickCityNameListener {
        fun onCityClicked(id: Int)
    }

    interface OnClickStateNameListener {
        fun onStateClicked(id: Int)
    }
}