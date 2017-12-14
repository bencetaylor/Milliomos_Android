package hu.bme.aut.millionaire.Game;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import hu.bme.aut.millionaire.R;

/**
 * Mindig a j valaszt adja meg
 */

public class AudienceHelpFragment extends DialogFragment {

    public static final String TAG = "AudienceHelpDialogFragment";

    private TextView helpText;
    private String message;

    static AudienceHelpFragment newInstance(String ans) {
        AudienceHelpFragment f = new AudienceHelpFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("ans", ans);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = (FragmentActivity)getActivity();
        Intent intent = activity.getIntent();

        message = getArguments().getString("ans");
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

                    }
                })
                .create();
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.audience_dialog_fragment, null);

        helpText = (TextView) contentView.findViewById(R.id.tv_audience_help);
        helpText.setText("A helyes valasz a " + message);

        return contentView;
    }
}
