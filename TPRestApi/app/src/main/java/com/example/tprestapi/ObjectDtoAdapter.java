package com.example.tprestapi;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ObjectDtoAdapter extends  RecyclerView.Adapter<ObjectDtoAdapter.ObjectDtoViewHolder> {


    private List<ObjectDto> mData;
    LayoutInflater inflater;
    Context _context;

    public ObjectDtoAdapter(Context context, List<ObjectDto> data) {
        inflater = LayoutInflater.from(context);
        this.mData = data;
        _context=context;
    }


    @Override
    public ObjectDtoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.objectdto_listview, parent, false);
        ObjectDtoViewHolder holder = new ObjectDtoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ObjectDtoViewHolder holder, int position) {
        ObjectDto selected = mData.get(position);
        holder.setData(selected, position);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ObjectDtoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mmPhotoDescription;
        TextView mmPhotoUrl;
        ImageView mmPhotoImg;

        public ObjectDtoViewHolder(View itemView) {
            super(itemView);
            mmPhotoDescription = (TextView) itemView.findViewById(R.id.photo_description);
            mmPhotoUrl = (TextView) itemView.findViewById(R.id.photo_url);
            mmPhotoImg = (ImageView) itemView.findViewById(R.id.photo_img);
        }

        public void setData(ObjectDto selectedProduct, int position) {

            this.mmPhotoDescription.setText(mData.get(position).title);
            this.mmPhotoUrl.setText(mData.get(position).url);
            UrlToBitmapAsyc urlToBitmapAsyc = new UrlToBitmapAsyc(_context, mData.get(position).thumbnailUrl,this.mmPhotoImg);
            Thread thread = new Thread(urlToBitmapAsyc);
            thread.start();
            //try {
            //    thread.join();
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}
        }
        @Override
        public void onClick(View v) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tvUrl = v.findViewById(R.id.photo_url);
                    Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( tvUrl.getText().toString()) );
                    v.getContext().startActivity(browse);
                }
            });
            
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ObjectDtoViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.onClick(holder.itemView);
    }
}
