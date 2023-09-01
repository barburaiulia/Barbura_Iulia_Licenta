package com.example.prorate;

import static com.example.prorate.MainActivity.getCurrentUser;
import static com.example.prorate.MainActivity.setCurrentUser;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;

public class EnrollmentActivity extends AppCompatActivity {
    private DBUsersHander handler;
    private DBMateriiHandler materiiHandler;
    private Users currentUser;
    public static final String TAG = "ListViewExample";
    SearchView searchView;
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment);

        searchView = findViewById(R.id.searchView);
        this.listView = (ListView) findViewById(R.id.listView);
        this.button = (Button) findViewById(R.id.enrollButton);

        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " + position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();

            }
        });
        //

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMaterii();
            }
        });

        this.initListViewData();

    }

    private void initListViewData() {
        materiiHandler= new DBMateriiHandler(EnrollmentActivity.this);
        ArrayList<Classes> classesArray= materiiHandler.readClasses();

        Resources res = getResources();
        ArrayList<String> materii_list= new ArrayList<String>();
        for (int i = 0; i < classesArray.size(); i++) {
            String aug=classesArray.get(i).getMaterie();
            /*String currUser=getCurrentUser();
            if(event_aug.getParticipants().contains(currUser)){*/
            materii_list.add(aug);

        }

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, materii_list);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                arrayAdapter.getFilter().filter(s);

                return false;
            }
        });
        this.listView.setAdapter(arrayAdapter);

        for (int i = 0; i < arrayAdapter.getCount(); i++) {
            this.listView.setItemChecked(i, false);
        }
    }

    public void updateMaterii()  {

        SparseBooleanArray sba = listView.getCheckedItemPositions();

        handler=new DBUsersHander(EnrollmentActivity.this);

        ArrayList<String> materii= new ArrayList<String>();

        int ok;
        String currentUser=getCurrentUser();

        Users user= new Users();
        System.out.println(currentUser);
        user=handler.getUserDB(currentUser);

        materii=user.getMaterii();
        for(int i=0;i<sba.size();i++){
            if(sba.valueAt(i)==true){
                //UserAccount user= (UserAccount) listView.getItemAtPosition(i);

                if (!materii.contains(listView.getItemAtPosition(i).toString())) {
                    // Adding the element to the ArrayList if it
                    // is not present
                    materii.add(listView.getItemAtPosition(i).toString());
                }


                handler.updateUser(user);
            }
        }
        Toast.makeText(this, "Enrolled ", Toast.LENGTH_LONG).show();
    }
}

    // When user click "Print Selected Items".




