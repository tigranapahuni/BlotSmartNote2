package com.narsli.blotsmartnote.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.narsli.blotsmartnote.MainActivity;
import com.narsli.blotsmartnote.Model.RecyclerViewItem;
import com.narsli.blotsmartnote.R;

import java.util.ArrayList;

//ays clasn mer arraylistic tvyalnern dnum e RecyclerView-i mej jarangum e zavaskoy clasi
//ayn mec masov haskanal@ partadir che uxaki iran main-i mejic kanchenq!!

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<RecyclerViewItem> RecyclerView_arrayList;
    private MainActivity mainActivity;

    //------------------------------------------------------------------------------
//konstruktorn e, vorin talis enq mainActivity-in ev RecyclerViewItem clasi ArrayListn
// (mainActivity mijocov dimelu enq nra metodneric mekin),
    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> RecyclerView_arrayList,
                               MainActivity mainActivity) {
        this.RecyclerView_arrayList = RecyclerView_arrayList;
        this.mainActivity = mainActivity;
    }

    //mer koxmic stexcvac rametken vorn menq sarqel enq (recicler_view_item.xml -failn)
// kapum enq adapteri het, stanum e metodn viewGroup, vorn kapum e
// recicler_view_item-layouti het, ays file-i mej sarqac MyViewHolder-clas e
// veradarcnum
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.hashiv_item,
                        viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    //---------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------
// ays metod@ shat karevor zavaskoy metod e, vori mijocov dnum enq RecyclerView-i amen
// itemi(toxi)` MyViewHolder-mej 3 textView ev 1 nkar,
//vortex position-n` useri koxmic sexmvac RecyclerViewI-i toxi hamarn e, erb usern sexmum e
//vorev e toxi vra ayd toxi hamarn poxancum enq mainActivity-in
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        final RecyclerViewItem recyclerViewItem = RecyclerView_arrayList.get(position);
        myViewHolder.imageView.setImageResource(recyclerViewItem.getImageResource());
        myViewHolder.txtView_order.setText(recyclerViewItem.getZakaz());
//____________________________________________
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//RecyclerView view-i amen andami vra OnClick-i realizacian e
//kanchum enq mainActivity-i editHashiv() metodn, vorn poxancum enq nshvac toxi hamarn
//editHashiv-i mej el irakancnum enqkom1 ev kom2-i hashivneri nermucumn
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.editHashiv(position);
            }
        });
    }

    //-----------------------------------------------------------------------------
//veradarcnum enq arrayList-i qanakn, anhrajesht e iren korekt ashxatelu hamar
    @Override
    public int getItemCount() {
        return RecyclerView_arrayList.size();
    }

    //-----------------------------------------------------------------------------
//ays cnox clasn e, vorin jarangum e mer RecyclerViewAdapter-clasn
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView
                txtView_order;
        public ImageView imageView;
        CardView cardView;

        //construktorin trvum e yuraqanchyur RecyclerView-n, vorn ays metodi mej
// kochvum e itemView
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.Img_mast);
            txtView_order = itemView.findViewById(R.id.txtView_order);

            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}

