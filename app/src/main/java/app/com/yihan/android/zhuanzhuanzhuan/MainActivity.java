package app.com.yihan.android.zhuanzhuanzhuan;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> placeList;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private Button btnStart;
    private Button btnAdd;
    View vvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomNum = (int) (Math.random() * placeList.size());
                showLocationDialog(placeList.get(randomNum).getPlaceName());
            }
        });
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vvv = view;
                showLocationDialog();
            }
        });

        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        buildList();
        adapter = new MyRecyclerAdapter(this, placeList);
        mRecyclerView.setAdapter(adapter);


        setTitle("Choice Maker");
    }

    private void addItem(int color, String lunchOrDinner, String place) {
        Item item = new Item(color, lunchOrDinner, place);
        placeList.add(item);
        adapter.notifyItemInserted(placeList.size() - 1);
    }

    private void buildList() {
        placeList = new ArrayList<>();
//        Item item = new Item(1, "Lunch", "Stony Brook");
//        placeList.add(item);
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

    private void showLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.ready_to_add_new_item));
        builder.setMessage(getString(R.string.please_enter_content));
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(30, 10, 30, 10);
        input.setLayoutParams(lp);
        input.setFocusable(true);
        builder.setView(input);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        String positiveText = getString(R.string.go_for_it);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        addItem((int)(Math.random() * 30), ":)", input.getText().toString());
                        hideKeyboard(input);
                    }
                });

        String negativeText = getString(R.string.give_up);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        Snackbar.make(vvv, "Cancel...", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        hideKeyboard(input);
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
        YoYo.with(Techniques.Landing).playOn(input);
        input.requestFocus();

        showKeyboard(input);
    }

    private void showKeyboard(final EditText input) {
        input.post(new Runnable() {

            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(input, 0);
            }
        });
    }

    private void hideKeyboard(EditText input) {
        input.post(new Runnable() {
            @Override
            public void run() {
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );

            }
        });
    }

    private void showLocationDialog(String itemName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.your_result));
        builder.setMessage("Here is your choice： " + itemName);

        String positiveText = getString(R.string.accept);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        Snackbar.make(vvv, "Problem resolved ...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }


}
