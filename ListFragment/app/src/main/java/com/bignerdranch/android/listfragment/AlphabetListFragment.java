package com.bignerdranch.android.listfragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.cp.android.R;
public class AlphabetListFragment extends ListFragment {
    String[] alphabet = getResources().getStringArray( R.array.alphabet );
    String[] word = new String[]{"Apple", "Boat", "Cat"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.listfragment_row, alphabet);
        setListAdapter(myListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfragment, null);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        OutputFragment txt = (OutputFragment)getFragmentManager().findFragmentById(R.id.output);
        txt.display(word[position]);
        getListView().setSelector(android.R.color.holo_red_dark);
    }

    public class MyListAdapter extends ArrayAdapter<String> {

        private Context mContext;
        public MyListAdapter(Context context, int textViewResourceId,
                             String[] objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_row, parent,
                    false);
            TextView catNameTextView = (TextView) row.findViewById(R.id.textViewName);
            catNameTextView.setText(alphabet[position]);
            ImageView iconImageView = (ImageView) row.findViewById(R.id.imageViewIcon);

            // Присваиваем значок
            iconImageView.setImageResource(R.drawable.ic_launcher_foreground);

            return row;
        }
    }
}
