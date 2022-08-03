package sg.edu.np.mad.wk6practical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DBHandler(this);

        // Receive intent
        Intent i = getIntent();

        // Find TextViews
        TextView profileName = findViewById(R.id.profileName);
        TextView profileDesc = findViewById(R.id.profileDesc);

        String name = i.getStringExtra("name");
        String desc = i.getStringExtra("desc");

        // Set texts
        profileName.setText(name);
        profileDesc.setText(desc);

        // Find buttons
        Button follow = findViewById(R.id.profileFollowButton);
        Button message = findViewById(R.id.profileFollowButton);

        User u = db.findUser(name);

        if (u.isFollowed() == true) { follow.setText("UNFOLLOW"); }
        else { follow.setText("FOLLOW"); }

        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (follow.getText().equals("FOLLOW")) {
                    u.setFollowed(true);
                    follow.setText("UNFOLLOW");
                }
                else {
                    u.setFollowed(false);
                    follow.setText("FOLLOW");
                }

                db.updateUser(name, u.isFollowed());
                finish();
            }
        });
    }
}