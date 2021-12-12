package com.course.androidshopping;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class ShopRecyclerAdapter extends RecyclerView.Adapter<ShopRecyclerAdapter.ShopViewHolder> {
    private ArrayList<ProductBean> data;
    private com.course.androidshopping.ItemClickListener listener;
    String name;
    String Price;
    byte[] image;
    public ShopRecyclerAdapter(ArrayList<ProductBean> data, com.course.androidshopping.ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_card, viewGroup, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder shopViewHolder, @SuppressLint("RecyclerView") int i) {
        ProductBean productBean = data.get(i);
        shopViewHolder.productImage.setImageDrawable(getImage(productBean.getImage()));
        shopViewHolder.productName.setText(productBean.getName());
        shopViewHolder.productPrice.setText(String.valueOf(productBean.getPrice()));
        shopViewHolder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                name = String.valueOf(productBean.getName());
                Price = String.valueOf(productBean.getPrice());
                image = productBean.getImage();

                Intent intent = new Intent(context,showItem.class);

                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                intent.putExtra("image",byteArray);
                intent.putExtra("name", name);
                intent.putExtra("Price", Price);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.size();
    }

    public void updateData(ArrayList<ProductBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public Drawable getImage(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Drawable drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        private Context context;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.productNameTv);
            productPrice = itemView.findViewById(R.id.productPriceTv);
            context = itemView.getContext();

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mname = "";
                    String nname = "";
                    Intent intent = new Intent(context,showItem.class);
                    intent.putExtra("mname", mname);
                    intent.putExtra("nname", nname);
                    context.startActivity(intent);
                    //BottomSheetDialog bottomSheetDialog = BottomSheetDialog.getInsetance();
                   //bottomSheetDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "bottomSheet");
                }
            });*/
        }
    }
}
