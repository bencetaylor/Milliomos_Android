package hu.bme.aut.millionaire.Scoreboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.millionaire.R;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.ScoreboardViewHolder>{

    public List<ScoreboardData> items;

    public ScoreboardAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ScoreboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scoreboard, parent, false);
        ScoreboardViewHolder viewHolder = new ScoreboardViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreboardViewHolder holder, int position) {
        ScoreboardData item = items.get(position);
        holder.ScoreboardItemNameTextView.setText(item.playerName);
        holder.ScoreboardItemPointsTextView.setText(Integer.toString(item.scoredPoints));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ScoreboardData item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void update(List<ScoreboardData> scorboardItem) {
        items.clear();
        items.addAll(scorboardItem);
        notifyDataSetChanged();
    }


    public class ScoreboardViewHolder extends RecyclerView.ViewHolder {

        TextView ScoreboardItemNameTextView;
        TextView ScoreboardItemPointsTextView;


        public ScoreboardViewHolder(View itemView) {
            super(itemView);

            ScoreboardItemNameTextView = (TextView) itemView.findViewById(R.id.scoreboardItemNameTextView);
            ScoreboardItemPointsTextView = (TextView) itemView.findViewById(R.id.scoreboardItemPointsTextView);
        }
    }
}
