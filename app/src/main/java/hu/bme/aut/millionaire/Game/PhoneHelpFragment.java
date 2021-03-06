package hu.bme.aut.millionaire.Game;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hu.bme.aut.millionaire.R;

/**
 * Created by Bence on 2017. 11. 22..
 */

public class PhoneHelpFragment extends DialogFragment {
    public static final String TAG = "PhoneHelpDialogFragment";

    private TextView phoneTextView;
    private EditText number;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = (FragmentActivity) getActivity();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.audience_help)
                .setView(getContentView())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uri = "tel:" + number.getText();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.phone_dialog_fragment, null);
        phoneTextView = (TextView) contentView.findViewById(R.id.tv_phone_help);
        number = (EditText) contentView.findViewById(R.id.phone_number);

        return contentView;
    }
}
