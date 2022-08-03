package sg.edu.np.mad.wk6practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> userList;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);

        // CHALLENGE NOT ATTEMPTED
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = new DBHandler(this);

        // Create list for Users
        userList = new ArrayList<>();
        userList = db.getUsers();

        if (userList.size() == 0) {
            createUsers(db);
            userList = db.getUsers();
        }

        // Recycler View
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Adapter mAdapter = new Adapter(userList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void createUsers(DBHandler db) {
        for (int i = 0; i < 20; i++) {
            int id = randomInteger();
            int desc = randomInteger();
            db.addUser("Name " + id, "Description " + desc, i, false);
        }
    }

    // Create random number generator to create User objects
    private int randomInteger() {
        Random ran = new Random();
        return ran.nextInt(999999);
    }
}