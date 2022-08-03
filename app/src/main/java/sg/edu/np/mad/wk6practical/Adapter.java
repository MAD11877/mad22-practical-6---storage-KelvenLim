package sg.edu.np.mad.wk6practical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<User> data;
    Context context;

    public Adapter (ArrayList<User> input, Context context) {
        data = input;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User u = data.get(position);
        holder.id.setText(String.valueOf(u.getId()));
        holder.image.setImageResource(R.mipmap.ic_launcher_round);
        holder.name.setText(u.getName());
        holder.desc.setText(u.getDescription());
        holder.follow.setText(String.valueOf(u.isFollowed()));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Profile.class);
                intent.putExtra("name", u.getName());
                intent.putExtra("desc", u.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
