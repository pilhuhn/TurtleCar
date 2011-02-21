package de.bsd.turtlecar;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 *  TODO: Document this
 * @author Heiko W. Rupp
 */
public class EditRunActivity extends ListActivity {


    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> mStrings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_run);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);

        setListAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();    // TODO: Customise this generated block
    }


    public void button_left(View v) {
        mAdapter.add(getString(R.string.left));
    }

    public void button_straight(View v) {
        mAdapter.add(getString(R.string.straight));
    }

    public void button_right(View v) {
        mAdapter.add(getString(R.string.right));
    }

    public void button_delete(View v) {

    }

    public void button_clear(View v) {
        mAdapter.clear();
    }

    public void done(View v) {

        Intent intent = new Intent();
        intent.putExtra("data", mStrings);
        setResult(RESULT_OK,intent);

        finish();

    }
}
