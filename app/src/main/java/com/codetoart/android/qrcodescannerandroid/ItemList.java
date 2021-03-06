package com.codetoart.android.qrcodescannerandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.codetoart.android.qrcodescannerandroid.Transaction;

public class ItemList extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Item1", "Item2", "Item3", "Item4","Item5"};
    String mDescription[] = {"Item1 Des", "Item2 Des", "Item3 Des", "Item4 Des", "Item5 Des"};
    int images[] = {R.drawable.groc, R.drawable.groc, R.drawable.groc, R.drawable.groc, R.drawable.groc};

    List<String> titles = new ArrayList<String>();
    List<String> desc = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        listView = findViewById(R.id.listView);


        for (Transaction t: Transaction.getTransactions()){
            titles.add(t.name);
            desc.add("Quantity:" + t.quantity);
        }

        int img[] = new int[Transaction.getTransactions().size()];

        for(int i = 0; i < img.length; i++) {
            img[i] = R.drawable.groc;
        }

        MyAdapter adapter = new MyAdapter(this, titles.toArray(new String[0]), desc.toArray(new String[0]), img);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]){
            super(c, R.layout.row, R.id.name, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.name);
            TextView myDescription = row.findViewById(R.id.desc);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return row;
        }
    }
}