package pl.dziobas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MyListAdapter extends ArrayAdapter<SimpleBean> {
    private static final String  TAG      = "MyListAdapter";
    private final LayoutInflater inflater;

    public MyListAdapter(Context context, int textViewResourceId, List<SimpleBean> objects) {
        super(context, textViewResourceId, objects);
        inflater                          = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View             view;
        final ViewHolder holder;

        if(convertView == null) {
            view          = inflater.inflate(R.layout.row, null);
            holder        = new ViewHolder();
            holder.text   = (TextView) view.findViewById(R.id.name);
            holder.button = (Button) view.findViewById(R.id.button);
            holder.text.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.textClicked();
                    }
                });
            holder.button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.buttonClicked();
                    }
                });
            view.setTag(holder);
        } else {
            view   = convertView;
            holder = (ViewHolder) view.getTag();
        }

        SimpleBean item = getItem(position);
        holder.position = position;
        holder.text.setText(item.getName());
        holder.button.setText(item.isState() ? "On" : "Off");

        return view;
    }

    private class ViewHolder {
        int      position;
        TextView text;
        Button   button;

        private void textClicked() {
            SimpleBean item = getItem(position);
            item.setName(item.getName() + "!");
            notifyDataSetChanged();
        }

        private void buttonClicked() {
            SimpleBean item = getItem(position);
            item.setState(!item.isState());
            notifyDataSetChanged();
        }
    }
}
