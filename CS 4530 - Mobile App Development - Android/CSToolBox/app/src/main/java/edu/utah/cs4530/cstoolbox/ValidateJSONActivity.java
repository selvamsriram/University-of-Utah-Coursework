package edu.utah.cs4530.cstoolbox;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Robert on 12/3/2016.
 */

public class ValidateJSONActivity extends AppCompatActivity implements Button.OnClickListener{

    EditText input;
    EditText output;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        TextView mainView = new TextView(this);
        mainView.append("JSON Validator");
        mainView.setTextSize(20.0f);

        LinearLayout inputLayout = new LinearLayout(this);
        inputLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView inputView = new TextView(this);
        inputView.append("Input: ");
        inputView.setTextSize(20.0f);
        input = new EditText(this);
        //inputLayout.addView(inputView);
        inputLayout.addView(input);

        LinearLayout outputLayout = new LinearLayout(this);
        outputLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView outputView = new TextView(this);
        outputView.append("Output: ");
        outputView.setTextSize(20.0f);
        output = new EditText(this);
        //outputLayout.addView(outputView);
        outputLayout.addView(output);

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button clearButton = new Button(this);
        clearButton.setOnClickListener(this);
        clearButton.setTag("clear");
        clearButton.setText("CLEAR");
        Button convertButton = new Button(this);
        convertButton.setOnClickListener(this);
        convertButton.setTag("validate");
        convertButton.setText("VALIDATE");
        Button helpButton = new Button(this);
        helpButton.setOnClickListener(this);
        helpButton.setTag("help");
        helpButton.setText("HELP");
        buttonLayout.addView(convertButton);
        buttonLayout.addView(clearButton);
        buttonLayout.addView(helpButton);

        rootLayout.addView(mainView);
        rootLayout.addView(inputView);
        rootLayout.addView(inputLayout);
        rootLayout.addView(outputView);
        rootLayout.addView(outputLayout);
        rootLayout.addView(buttonLayout);

        setContentView(rootLayout);
    }

    @Override
    public void onClick(View view) {
        if(view.getTag().toString().equals("validate")){
            boolean valid = false;
            try {
                final JsonParser parser = new ObjectMapper().getJsonFactory().createJsonParser(input.getText().toString());
                while (parser.nextToken() != null) {
                }
                valid = true;
            } catch (JsonParseException exc1) {
                exc1.printStackTrace();
            } catch (IOException exc2) {
                exc2.printStackTrace();
            }

            if(valid){
                output.setText("JSON is valid.");
            }
            else{
                output.setText("JSON is invalid.");
            }
        }
        else if(view.getTag().toString().equals("help")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Input the JSON into the text field below the Input label and then press the VALIDATE button. " +
                    "Space and tabs will not effect " +
                    "the validity of the inputed JSON. The CLEAR button will clear all text from both the input and output fields.");
            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.setCancelable(true);
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            input.setText("");
            output.setText("");
        }
    }
}