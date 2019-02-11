package jakubmajchrzak.setlistmanager.viewServiceClasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import jakubmajchrzak.setlistmanager.R;
import jakubmajchrzak.setlistmanager.otherInterfaces.AppConstantsInterface;
import jakubmajchrzak.setlistmanager.otherInterfaces.CommuniquesInterface;

public class AddBandDialog extends AppCompatDialogFragment implements CommuniquesInterface, AppConstantsInterface {

    private EditText newBandNameEditText;
    private AddBandEditListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_band_dialog_layout, null);

        newBandNameEditText = view.findViewById(R.id.new_band_name);

        builder.setView(view);
        builder.setTitle(ADDING_NEW_BAND_WINDOW_TITLE);

        builder.setNegativeButton(CANCEL_BUTTON_TITLE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton(ADD_BAND_BUTTON_TITLE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newBandName = newBandNameEditText.getText().toString();
                listener.applyAddingBand(newBandName);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddBandEditListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + MY_CLASS_CAST_EXCEPTION_COMMUNIQUE);
        }
    }

    public interface AddBandEditListener {

        void applyAddingBand(String newBandName);
    }
}
