package com.example.wewashadmin.FragmentThings;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.example.wewashadmin.Adapters.DisplayOrders;
import com.example.wewashadmin.MainActivity;
import com.example.wewashadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;

    private DatabaseReference ordersReference;


    public OrdersFragment() {
        // Required empty public constructor
    }
    ArrayList<DisplayOrders> arrayListOrders=new ArrayList<>();
    Context context;
    MyAdapterForOrdersDisplay myAdapterForOrdersDisplay;
    OrdersFragment(Context context)
    {
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_orders, container, false);


        // Inflate the layout for this fragment

        recyclerView = rootView.findViewById(R.id.recycle_orders);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        myAdapterForOrdersDisplay=new MyAdapterForOrdersDisplay(arrayListOrders);
        recyclerView.setAdapter(myAdapterForOrdersDisplay);
      //  ordersReference = FirebaseDatabase.getInstance().getReference("Admin").child("Orders").child("CurrentOrders");
        return rootView;

    }
    public void setRecyclerView(View view) {
        RecyclerView recyclerView=view.findViewById(R.id.recycle_orders);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        myAdapterForOrdersDisplay=new MyAdapterForOrdersDisplay(arrayListOrders);
        recyclerView.setAdapter(myAdapterForOrdersDisplay);
    }

    public void getArrayListData()
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
                        DisplayOrders displayOrders=new DisplayOrders(profileorderId,profilename,profileaddress,profilearea,profilephone,profiledate,profiletime,profileclothes);
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


//    class AsyncTaskToFetchCasual extends AsyncTask<Void,Void,Void>
//    {
//
//        @Override
//        protected Void doInBackground(Void... strings) {
//            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("MenFootWear").child("Casual");
//            // arrayListCasual.clear();
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
//                        D_ShoesDataFromInternet dShoesDataFromInternet = dataSnapshot1.getValue(D_ShoesDataFromInternet.class);
//                        if (dShoesDataFromInternet != null) {
//                            arrayListCasual.add(dShoesDataFromInternet);
//                            Log.e("MenFragment","Going inside");
//                        }
//                        else
//                        {
//                            Log.e("MenFragment","Unable To go inside");
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//            return null;
//        }
//    }

    class MyAdapterForOrdersDisplay extends RecyclerView.Adapter<MyAdapterForOrdersDisplay.ViewHolderClass>
    {

        ArrayList<DisplayOrders> arrayList;

        public MyAdapterForOrdersDisplay(ArrayList<DisplayOrders> arrayList1)
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
        public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {

            holder.name.setText(arrayList.get(position).name);
            holder.address.setText(arrayList.get(position).address);
            holder.phone.setText(arrayList.get(position).phone);
            holder.date.setText(arrayList.get(position).date);
            holder.time.setText(arrayList.get(position).Time);
            holder.clothes.setText(arrayList.get(position).Clothes);
            holder.area.setText(arrayList.get(position).area);
            holder.id.setText(arrayList.get(position).orderId);

        }


        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class ViewHolderClass extends RecyclerView.ViewHolder
        {

            CardView cardView;
        TextView name, address, area, phone, clothes, date, time, id;
        Button orders_btn;
        String OrderPicked = "Order Picked Up";

            public ViewHolderClass(@NonNull View itemView)
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
            orders_btn.setText(OrderPicked);
            }
        }
    }
}


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.fragment_orders, container, false);
//
//
//        // Inflate the layout for this fragment
//
////        recyclerView = rootView.findViewById(R.id.recycle_orders);
////        LinearLayoutManager manager = new LinearLayoutManager(getContext());
////        manager.setOrientation(LinearLayoutManager.VERTICAL);
////        recyclerView.setLayoutManager(manager);
////        recyclerView.setHasFixedSize(true);
////
////        ordersReference = FirebaseDatabase.getInstance().getReference("Admin").child("Orders").child("CurrentOrders");
////        return rootView;
//
//    }
//    }
//
////    @Override
////    public void onStart() {
////        super.onStart();
////
////
////        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<DisplayOrders>()
////                .setQuery(ordersReference, DisplayOrders.class).build();
////
////        FirebaseRecyclerAdapter<DisplayOrders, OrdersViewHolder> adapter = new FirebaseRecyclerAdapter<DisplayOrders, OrdersViewHolder>(options) {
////            @Override
////            protected void onBindViewHolder(@NonNull final OrdersViewHolder holder, int position, @NonNull DisplayOrders model) {
////
////
////                ordersReference.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                        {
////
////                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
////
////                                Log.e("GOing In", "going in");
////                                if (dataSnapshot1.exists()) {
////
////                                    String profilename = dataSnapshot1.child("name").getValue().toString();
////                                    String profileaddress = dataSnapshot1.child("address").getValue().toString();
////                                    String profilephone = dataSnapshot1.child("phone").getValue().toString();
////                                    String profiledate = dataSnapshot1.child("dateBooked").getValue().toString();
////                                    String profiletime = dataSnapshot1.child("expectedPickupTime").getValue().toString();
////                                    String profileclothes = dataSnapshot1.child("noOfClothes").getValue().toString();
////                                    String profilearea = dataSnapshot1.child("area").getValue().toString();
////                                    String profileorderId = dataSnapshot1.child("orderId").getValue().toString();
////                                    Log.e("WENT", "Went");
////
////                                    holder.name.setText(profilename);
////                                    holder.address.setText(profileaddress);
////                                    holder.phone.setText(profilephone);
////                                    holder.date.setText(profiledate);
////                                    holder.time.setText(profiletime);
////                                    holder.clothes.setText(profileclothes);
////                                    holder.area.setText(profilearea);
////                                    holder.id.setText(profileorderId);
////
////                                    Log.e("DisplayOrder", "Went");
////
////
////                                } else {
////
////                                    Log.e("DIsplayOrders", "snapshot does not exist");
////                                }
////                            }
////                        }
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                    }
////                });
////
////            }
////
////            @NonNull
////            @Override
////            public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
////                OrdersViewHolder viewHolder = new OrdersViewHolder(view);
////                return viewHolder;
////
////
////            }
////        };
////        recyclerView.setAdapter(adapter);
////        adapter.startListening();
////
////    }
//
////    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
////
////        CardView cardView;
////        TextView name, address, area, phone, clothes, date, time, id;
////        Button orders_btn;
////        String OrderPicked = "Order Picked Up";
////
////        public OrdersViewHolder(@NonNull View itemView) {
////            super(itemView);
////
////            cardView = itemView.findViewById(R.id.cardView);
////            name = itemView.findViewById(R.id.tv_name);
////            address = itemView.findViewById(R.id.tv_address);
////            area = itemView.findViewById(R.id.tv_area);
////            phone = itemView.findViewById(R.id.tv_phone);
////            clothes = itemView.findViewById(R.id.tv_clothes);
////            date = itemView.findViewById(R.id.tv_date);
////            time = itemView.findViewById(R.id.tv_time);
////            id = itemView.findViewById(R.id.tv_order);
//////            orders_btn=itemView.findViewById(R.id.orders_btn);
//////            orders_btn.setText(OrderPicked);
////        }
////    }
//}
