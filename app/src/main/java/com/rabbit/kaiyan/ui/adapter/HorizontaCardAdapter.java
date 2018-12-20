package com.rabbit.kaiyan.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabbit.kaiyan.R;
import com.rabbit.kaiyan.component.ImageLoader;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

public class HorizontaCardAdapter extends RecyclerView.Adapter<HorizontaCardAdapter.Holder> {
    private static final String TAG = "HorizontaCardAdapter";
    Context context;
    List<ItemListBean> cardList ;

    public HorizontaCardAdapter(Context context,List<ItemListBean> itemListBeans) {
        this.context = context;
        this.cardList = itemListBeans;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_square_image,viewGroup,false);
        RecyclerView.ViewHolder holder = new Holder(view);
        return (Holder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.textView.setText(cardList.get(0).getData().getItemList().get(i).getData().getTitle());
        ImageLoader.loadSquare(context,cardList.get(0).getData().getItemList().get(i).getData().getImage(),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cardList.get(0).getData().getCount();
    }


    public static class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_square);
            textView = itemView.findViewById(R.id.tv_square);
        }
    }
}
