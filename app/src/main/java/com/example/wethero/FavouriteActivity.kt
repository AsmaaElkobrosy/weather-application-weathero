package com.example.wethero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FavouriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
    }
}

/*
public class MainActivity extends AppCompatActivity {
   SearchView searchView;
   ListView listView;
   ArrayList list;
   ArrayAdapter adapter;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      searchView = findViewById(R.id.searchView);
      listView = findViewById(R.id.listView);
      list = new ArrayList<>();
      list.add("Apple");
      list.add("Banana");
      list.add("Pineapple");
      list.add("Orange");
      list.add("Mango");
      list.add("Grapes");
      list.add("Lemon");
      list.add("Melon");
      list.add("Watermelon");
      list.add("Papaya");
      adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
      listView.setAdapter(adapter);
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
            if(list.contains(query)){
               adapter.getFilter().filter(query);
            }else{
               Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
            }
            return false;
         }
         @Override
         public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
         }
      });
   }
}

 */