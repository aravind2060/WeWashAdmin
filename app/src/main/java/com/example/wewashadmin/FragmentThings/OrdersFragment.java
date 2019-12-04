package com.example.wewashadmin.FragmentThings;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wewashadmin.Adapters.DisplayOrders;
import com.example.wewashadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OrdersFragment extends Fragment{

    private String UKey;
   private String proorderId;
    private String proname;
    private String proaddress;
    private String proarea;
    private String prophone;
    private String prodate;
    private String protime;
    private String proclothes;
    private Integer proprogress;
    private String prostatus;



    public OrdersFragment() {
    }
    private ArrayList<DisplayOrders> arrayListOrders=new ArrayList<>();

    private MyAdapterForOrdersDisplay myAdapterForOrdersDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_orders, container, false);




       setRecyclerView(rootView);
       getArrayListData();

        return rootView;

    }
    private void setRecyclerView(View view) {
        RecyclerView recyclerView=view.findViewById(R.id.recycle_orders);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        myAdapterForOrdersDisplay=new MyAdapterForOrdersDisplay(arrayListOrders);
        recyclerView.setAdapter(myAdapterForOrdersDisplay);
    }

    private void getArrayListData()
    {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Admin").child("Orders").child("CurrentOrders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListOrders.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Log.e("ArrayList","going in");
                    if (dataSnapshot1.exists())
                    {
                                    String profilename = dataSnapshot1.child("name").getValue().toString();
                                    String profileaddress = dataSnapshot1.child("address").getValue().toString();
                                    String profilephone = dataSnapshot1.child("phone").getValue().toString();
                                    String profiledate = dataSnapshot1.child("dateBooked").getValue().toString();
                                    String profiletime = dataSnapshot1.child("expectedPickupTime").getValue().toString();
                                    String profileclothes = dataSnapshot1.child("noOfClothes").getValue().toString();
                                    String profilearea = dataSnapshot1.child("area").getValue().toString();
                                    String profileorderId = dataSnapshot1.child("orderId").getValue().toString();
                                    String profilestatus = dataSnapshot1.child("status").getValue().toString();
                                    String profilekey = dataSnapshot1.child("key").getValue().toString();
                                    Integer profileprogress = dataSnapshot1.child("progress").getValue(Integer.class);
                                    UKey=profilekey;
                                    proorderId=profileorderId;
                                    proname=profilename;
                                    proaddress=profileaddress;
                                    proarea=profilearea;
                                    prophone=profilephone;
                                    prodate=profiledate;
                                    protime=profiletime;
                                    prostatus=profilestatus;
                                    proclothes=profileclothes;
                                    proprogress=profileprogress;






                        DisplayOrders displayOrders=new DisplayOrders(profileorderId,profilename,profileaddress,profilearea,profilephone,profiledate,profiletime,profileclothes,profilestatus,profileprogress,profilekey);
                        arrayListOrders.add(displayOrders);
                    }
                    else
                    {

                    }
                }
                myAdapterForOrdersDisplay.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    class MyAdapterForOrdersDisplay extends RecyclerView.Adapter<MyAdapterForOrdersDisplay.ViewHolderClass>
    {

        ArrayList<DisplayOrders> arrayList;


        MyAdapterForOrdersDisplay(ArrayList<DisplayOrders> arrayList1)
        {
            this.arrayList=arrayList1;
        }
        @NonNull
        @Override
        public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view=LayoutInflater.from(getContext()).inflate(R.layout.card_layout,parent,false);
            return new ViewHolderClass(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderClass holder, final int position) {

            holder.name.setText(arrayList.get(position).name);
            holder.address.setText(arrayList.get(position).address);
            holder.phone.setText(arrayList.get(position).phone);
            holder.date.setText(arrayList.get(position).dateBooked);
            holder.time.setText(arrayList.get(position).expectedPickupTime);
            holder.clothes.setText(arrayList.get(position).noOfClothes);
            holder.area.setText(arrayList.get(position).area);
            holder.id.setText(arrayList.get(position).orderId);

            holder.orders_btn.setText("Picked");
            holder.orders_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Admin").child("Orders").child("PickedOrders");
                    DisplayOrders displayOrders = new DisplayOrders(proorderId,proname,proaddress,proarea,prophone,prodate,protime,proclothes,prostatus,proprogress,UKey);
                    //  orderId, name,address,area,phone,dateBooked,expectedPickupTime,noOfClothes,status,progress,key;
                    databaseReference.child(UKey).setValue(displayOrders);



                    Map<String, Object> taskMap = new HashMap<String, Object>();
                    taskMap.put("progress", 25);
                    FirebaseDatabase.getInstance().getReference("Admin").child("Orders").child("DisplayOrders").child(UKey).updateChildren(taskMap);

                    FirebaseDatabase.getInstance().getReference("Admin").child("Orders").child("CurrentOrders").child(UKey).removeValue();

                }
            });

        }


        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolderClass extends RecyclerView.ViewHolder
        {

            CardView cardView;
        TextView name, address, area, phone, clothes, date, time, id;
        Button orders_btn;


            ViewHolderClass(@NonNull View itemView)
            {
                super(itemView);
                cardView = itemView.findViewById(R.id.cardView);
            name = itemView.findViewById(R.id.tv_name);
            address = itemView.findViewById(R.id.tv_address);
            area = itemView.findViewById(R.id.tv_area);
            phone = itemView.findViewById(R.id.tv_phone);
            clothes = itemView.findViewById(R.id.tv_clothes);
            date = itemView.findViewById(R.id.tv_date);
            time = itemView.findViewById(R.id.tv_time);
            id = itemView.findViewById(R.id.tv_order);
            orders_btn=itemView.findViewById(R.id.orders_btn);



            }
        }
    }
}