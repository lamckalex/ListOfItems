package io.github.lamckalex.listofitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alex on 1/7/2015.
 */
public class CustomAdapter extends BaseAdapter{

    List<CustomDataObject> cdoList;
    private Context context;

    public CustomAdapter(Context context)
    {
        this.context = context;
    }

    public void populateList(List<CustomDataObject> foo)
    {
        cdoList = foo;
    }

    @Override
    /**
     * Informs listview number of rows it will require
     */
    public int getCount(){
        return cdoList.size();
    }

    @Override
    /**
     * Returns object(the data for each row)
     */
    public Object getItem(int arg0){
        return cdoList.get(arg0);
    }

    @Override
    /**
     * useless?
     */
    public long getItemId(int arg0)
    {
        return arg0;
    }

    @Override
    /**
     * Called to get view for each row
     * Use custom listitem and bind with data
     * @arg0 row number
     * @arg1 recycled view reference
     * @arg2 parent to which this view will get attached to
     */
    public View getView(int arg0, View arg1, ViewGroup arg2)
    {
        if(arg1 == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.list_item, arg2, false);
        }
        TextView t1 = (TextView)arg1.findViewById(R.id.textView);
        TextView t2 = (TextView)arg1.findViewById(R.id.textView2);

        CustomDataObject cdo = cdoList.get(arg0);
        t1.setText(cdo.text1);
        t2.setText(cdo.text2);

        return arg1;
    }

}
