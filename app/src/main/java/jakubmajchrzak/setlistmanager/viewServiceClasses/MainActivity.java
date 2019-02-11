package jakubmajchrzak.setlistmanager.viewServiceClasses;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jakubmajchrzak.setlistmanager.R;
import jakubmajchrzak.setlistmanager.dao.DatabaseHelper;
import jakubmajchrzak.setlistmanager.otherInterfaces.AppConstantsInterface;
import jakubmajchrzak.setlistmanager.otherInterfaces.CommuniquesInterface;

public class MainActivity extends AppCompatActivity implements AddBandDialog.AddBandEditListener, EditBandDataDialog.EditBandDataListener, CommuniquesInterface, AppConstantsInterface, PopupMenu.OnMenuItemClickListener {

    DatabaseHelper myDb;
    Button addBandButton;
    ListView bandsListView;

    ArrayList<Integer> bandsIdsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bandsIdsList = new ArrayList<>();

        //Ta linijka jest żeby się kontrolki nie rozjeżdzały po wjechaniu klawiatury
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        myDb = new DatabaseHelper(this);

        addBandButton = (Button) findViewById(R.id.addBandButton);
        bandsListView = (ListView) findViewById(R.id.bandsListView);

        fillTableWithBands();
        makeBandListViewClickable();

        addBand();
    }

    public void fillTableWithBands() {

        Cursor cursor = myDb.getAllData();

        List<String> bandsList = new ArrayList<>();

        bandsIdsList.clear();

        while (cursor.moveToNext()) {
            bandsList.add(cursor.getString(BAND_NAME_COLUMN_INDEX));
            bandsIdsList.add(Integer.valueOf(cursor.getString(BAND_ID_COLUMN_INDEX)));
        }

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bandsList);

        bandsListView.setAdapter(listAdapter);
        cursor.moveToFirst();
    }

    public void makeBandListViewClickable() {

        bandsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopup(view, position);
                return false;
            }
        });
    }

    //Popup window to delete, editining or showing details of band
    public void showPopup(final View view, final int position) {

        PopupMenu bandPopupMenu = new PopupMenu(this, view);
        bandPopupMenu.setOnMenuItemClickListener(this);
        bandPopupMenu.inflate(R.menu.popup_band_menu);

        bandPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.deleteBand) :
                        deleteBand(position);
                        return true;
                    case (R.id.detailsBand) :
                        openBandDetailsDataDialog(position);
                        return true;
                    case (R.id.editBand) :
                        openEditBandDataDialog(position);
                        return true;
                }
                return false;
            }
        });

        bandPopupMenu.show();
    }

    public void addBand() {

        addBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBandDialog();
                fillTableWithBands();
            }
        });
    }

    public void openAddBandDialog() {

        AddBandDialog addBandDialog = new AddBandDialog();
        addBandDialog.show(getSupportFragmentManager(), ADD_BAND_EDIT_DIALOG_TAG);
    }

    @Override
    public void applyAddingBand(String newBandName) {

        boolean isInserted = myDb.insertData(newBandName);

        if(isInserted) {
            Toast.makeText(MainActivity.this, BAND_ADDED_COMMUNIQUE, Toast.LENGTH_LONG).show();
            fillTableWithBands();
        }
        else
            Toast.makeText(MainActivity.this, BAND_NOT_ADDED_COMMUNIQUE, Toast.LENGTH_LONG).show();
    }

    public void openEditBandDataDialog(int position) {

        EditBandDataDialog editBandDataDialog = editBandDataDialogNewInstance(position);
        editBandDataDialog.show(getSupportFragmentManager(), EDIT_BAND_DATA_EDIT_DIALOG_TAG);
    }

    //Way to pass data to Dialog kind of class (this case EditBandDataDialog)
    public static EditBandDataDialog editBandDataDialogNewInstance(int position) {
        EditBandDataDialog editBandDataDialog = new EditBandDataDialog();

        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_BUNDLE_ID, position);
        editBandDataDialog.setArguments(bundle);

        return editBandDataDialog;
    }

    @Override
    public void applyEditingBand(String editedBandName, int position) {
        boolean isUpdated = myDb.updateData(bandsIdsList.get(position).toString(), editedBandName);

        if(isUpdated) {
            Toast.makeText(MainActivity.this, UPDATED_DATA_COMMUNIQUE, Toast.LENGTH_LONG).show();
            fillTableWithBands();
        }
        else
            Toast.makeText(MainActivity.this, BAND_NOT_ADDED_COMMUNIQUE, Toast.LENGTH_LONG).show();
    }

    public void deleteBand(int position) {

        myDb.deleteData(bandsIdsList.get(position).toString());
        bandsIdsList.remove(position);
        fillTableWithBands();
        Toast.makeText(MainActivity.this, DELETED_DATA_COMMUNIQUE, Toast.LENGTH_LONG).show();
    }

    public void openBandDetailsDataDialog(int position) {

        Cursor cursor = myDb.getAllData();
        cursor.move(position+BAND_NAME_COLUMN_INDEX);
        String bandName = cursor.getString(BAND_NAME_COLUMN_INDEX);

        BandDetailsDialog bandDetailsDialog = bandDetailsEditDialogNewInstance(bandName);
        bandDetailsDialog.show(getSupportFragmentManager(), BAND_DETAILS_EDIT_DIALOG_TAG);
    }

    public static BandDetailsDialog bandDetailsEditDialogNewInstance(String bandName) {

        BandDetailsDialog bandDetailsDialog = new BandDetailsDialog();

        Bundle bundle = new Bundle();
        bundle.putString(BAND_NAME_BUNDLE_ID, bandName);
        bandDetailsDialog.setArguments(bundle);

        return bandDetailsDialog;
    }

    //Ma być puste = wymagane przez implementacje interfejsu
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

}
