package io.github.lamckalex.listofitems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    DatabaseHandler db;
    CustomAdapter ca;
    List<CustomDataObject> cdoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        ca = new CustomAdapter(this);
        ca.populateList(getDataForListView());
        ListView mylistview = (ListView)findViewById(R.id.listView);
        mylistview.setAdapter(ca);
        registerForContextMenu(mylistview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<CustomDataObject> getDataForListView()
    {
        cdoList = db.getAllNotes();
        return cdoList;
    }

    public void btnAddOnClick(View view)
    {
        Intent i = new Intent(this, AddNoteActivity.class);
        startActivityForResult(i,1);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK){
                String newTitle =data.getStringExtra("newTitle");
                String newContent =data.getStringExtra("newContent");
                db.addNote(new CustomDataObject(newTitle, newContent));
                resetList();
            }
            //if (resultCode == RESULT_CANCELED) {}
        }
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.listView)
        {
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_item_context_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            /*
            case R.id.edit:
                editNote(info.id);
                return true;
             */
            case R.id.delete:
                Log.d("HELP", "DELETE INFO.ID"+info.id);
                Log.d("HELP", "DELETE INFO.POSITION"+info.position);
                db.deleteNote(cdoList.get((int)info.id));
                resetList();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    void resetList()
    {
        ca.notifyDataSetChanged();
        ca.populateList(getDataForListView());
    }
}

