package io.github.lamckalex.listofitems;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomAdapter ca = new CustomAdapter(this);
        ca.populateList(getDataForListView());
        ListView mylistview = (ListView)findViewById(R.id.listView);
        mylistview.setAdapter(ca);
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
        List<CustomDataObject> cdoList = new ArrayList<CustomDataObject>();

        cdoList.add(new CustomDataObject("Hello","World"));
        cdoList.add(new CustomDataObject("Goodbye","World"));
        cdoList.add(new CustomDataObject("Hello","World"));
        cdoList.add(new CustomDataObject("Goodbye","World"));
        cdoList.add(new CustomDataObject("Hello","World"));
        cdoList.add(new CustomDataObject("Goodbye","World"));
        cdoList.add(new CustomDataObject("Hello","World"));
        cdoList.add(new CustomDataObject("Goodbye","World"));
        cdoList.add(new CustomDataObject("Hello","World"));
        cdoList.add(new CustomDataObject("Goodbye","World"));

        return cdoList;
    }

}

