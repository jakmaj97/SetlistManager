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

public class EditBandDataDialog extends AppCompatDialogFragment implements CommuniquesInterface, AppConstantsInterface {

    private EditText editedBandNameEditText;
    private EditBandDataListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_band_data_layout, null);

        editedBandNameEditText = view.findViewById(R.id.edited_band_name);

        builder.setView(view);
        builder.setTitle(EDITING_NEW_BAND_WINDOW_TITLE);

        builder.setNegativeButton(CANCEL_BUTTON_TITLE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton(CONFIRM_BUTTON_TITLE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editedBandName = editedBandNameEditText.getText().toString();

                int position = getArguments().getInt(POSITION_BUNDLE_ID);

                listener.applyEditingBand(editedBandName, position);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (EditBandDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + MY_CLASS_CAST_EXCEPTION_COMMUNIQUE);
        }
    }

    public interface EditBandDataListener {

        void applyEditingBand(String editedBandName, int position);
    }
}
