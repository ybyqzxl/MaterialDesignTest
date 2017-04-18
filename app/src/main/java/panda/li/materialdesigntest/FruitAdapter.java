package panda.li.materialdesigntest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xueli on 2016/12/11.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitHolder> {
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    private Context mContext;
    private List<Fruit> mFruitList;

    @Override
    public FruitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final FruitHolder holder = new FruitHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(FruitHolder holder, int position) {

        Fruit fruit = mFruitList.get(position);
        holder.mFruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.mFruitImage);

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class FruitHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.fruit_image)
        ImageView mFruitImage;
        @Bind(R.id.fruit_name)
        TextView mFruitName;
        @Bind(R.id.cardView)
        CardView mCardView;

        public FruitHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
