package io.github.lamckalex.listofitems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddNoteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
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

    public void saveOnClick(View view)
    {
        String newTitle, newContent;
        newTitle = ((EditText)findViewById(R.id.newNoteTitle)).getText().toString();
        newContent = ((EditText)findViewById(R.id.newNoteContent)).getText().toString();

        if(newTitle.length() > 0 && newContent.length() > 0)
        {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("newTitle", newTitle);
            returnIntent.putExtra("newContent", newContent);
            setResult(RESULT_OK,returnIntent);
            finish();
        }
    }

    public void cancelOnClick(View view)
    {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
