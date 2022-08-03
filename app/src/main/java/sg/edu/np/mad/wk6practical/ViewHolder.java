package sg.edu.np.mad.wk6practical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name, desc, id, follow;

    public ViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        desc = itemView.findViewById(R.id.desc);
        id = itemView.findViewById(R.id.id);
        follow = itemView.findViewById(R.id.follow);
    }
}
