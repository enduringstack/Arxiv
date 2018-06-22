package com.chen.arxiv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.arxiv.R;
import com.chen.arxiv.model.Author;
import com.chen.arxiv.model.Feed;

import java.util.List;

import view.SelectableTextView;

/**
 * Created by chen on 18-3-3.
 */

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperItemViewHolder>{
    private static final String TAG = PaperAdapter.class.getSimpleName();
    private Feed feed;

    private Context context;

    public PaperAdapter(Feed feed, Context context){
        this.context = context;
        this.feed=feed;
    }

    @Override
    public PaperItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_item,parent,false);

        return new PaperItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaperItemViewHolder holder, int position) {
        holder.tvTitle.setText(feed.getEntry().get(position).getTitle());
        //holder.tvTitle.setText(feed.getEntry().get(position).getTitle().replace('\n', ' '));

        List<Author> authors = feed.getEntry().get(position).getAuthor();


        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < authors.size(); i++) {
            if (i == authors.size() - 1) {
                stringBuilder.append(authors.get(i).getName());
            }else{
                stringBuilder.append(authors.get(i).getName()).append(",");
            }
        }

        //holder.tvAuthors.setText(stringBuilder.toString().replace('\n', ' '));
        holder.tvAuthors.setText(stringBuilder.toString());

        String summary = feed.getEntry().get(position).getSummary();

        String strAbstract = null;
        if (summary.charAt(0) == ' '){
            strAbstract = summary.substring(1, summary.length());
        }
        holder.tvAbstract.setText(strAbstract.replace('\n', ' '));
    }

    @Override
    public int getItemCount() {
        return feed.getEntry().size();
    }

    public class PaperItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvAuthors;
        SelectableTextView tvAbstract;

        public PaperItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthors = itemView.findViewById(R.id.tv_authors);
            tvAbstract = itemView.findViewById(R.id.tv_abstract);
        }
    }

}
