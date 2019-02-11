package jakubmajchrzak.setlistmanager.viewServiceClasses;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import jakubmajchrzak.setlistmanager.R;
import jakubmajchrzak.setlistmanager.otherInterfaces.AppConstantsInterface;
import jakubmajchrzak.setlistmanager.otherInterfaces.CommuniquesInterface;

public class BandDetailsDialog extends AppCompatDialogFragment implements CommuniquesInterface, AppConstantsInterface {

    private TextView bandNameTextView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.bands_details_dialog_layout, null);

        bandNameTextView = view.findViewById(R.id.detailBandNameTextView);

        String bandName = getArguments().getString(BAND_NAME_BUNDLE_ID);

        bandNameTextView.setText(bandName);

        builder.setView(view);
        builder.setTitle(BAND_DETAILS_WINDOW_TITLE);

        builder.setPositiveButton(OK_BUTTON_TITLE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
