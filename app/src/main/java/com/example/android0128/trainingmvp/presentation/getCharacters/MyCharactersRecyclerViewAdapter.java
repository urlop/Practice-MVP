package com.example.android0128.trainingmvp.presentation.getCharacters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.presentation.models.Character;
import com.example.android0128.trainingmvp.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCharactersRecyclerViewAdapter extends RecyclerView.Adapter<MyCharactersRecyclerViewAdapter.ViewHolder> {

    private final List<Character> mValues;
    private final CharactersFragment.OnListFragmentInteractionListener mListener;
    private final Context context;
    private LayoutInflater layoutInflater;

    public MyCharactersRecyclerViewAdapter(LayoutInflater layoutInflater, Context context, List<Character> items, CharactersFragment.OnListFragmentInteractionListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater
                .inflate(R.layout.row_characters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Character item = mValues.get(position);
        holder.mItem = item;
        holder.mIdView.setText(String.valueOf(item.getId()));
        holder.mContentView.setText(item.getName());
        Picasso.with(context).load(Utils.getCharacterImagePath(item)).into(holder.iv_thumbnail);
        holder.iv_favorite.setVisibility(item.isFavorite() ? View.VISIBLE : View.INVISIBLE);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnListFragmentInteraction(holder.mItem, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addData(List<Character> data){
        mValues.addAll(data);
        notifyDataSetChanged();
    }

    public List<Character> getData() {
        return mValues;
    }

    public void setItem(Character item, int position){
        mValues.set(position, item);
        notifyItemChanged(position);
    }

    public void removeItem(int position){
        mValues.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Character item){
        mValues.add(item);
        notifyItemChanged(mValues.size()-1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView iv_thumbnail;
        public final ImageView iv_favorite;
        public Character mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tv_id);
            mContentView = (TextView) view.findViewById(R.id.tv_content);
            iv_thumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
            iv_favorite = (ImageView) view.findViewById(R.id.iv_favorite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
