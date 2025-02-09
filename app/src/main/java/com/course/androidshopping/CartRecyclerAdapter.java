package com.course.androidshopping;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder>{
    private ArrayList<ProductBean> data;
    private ItemClickListener listener;

    public CartRecyclerAdapter(ArrayList<ProductBean> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_cartitem_card, viewGroup, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        ProductBean productBean = data.get(i);

        cartViewHolder.productImage.setImageDrawable(getImage(productBean.getImage()));
        cartViewHolder.productName.setText(productBean.getName());
        cartViewHolder.productPrice.setText(String.valueOf(productBean.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.size();
    }

    public Drawable getImage(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Drawable drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.productNameTv);
            productPrice = itemView.findViewById(R.id.productPriceTv);
        }
    }
}
