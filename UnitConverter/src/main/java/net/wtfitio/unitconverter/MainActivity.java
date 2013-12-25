package net.wtfitio.unitconverter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by plamend on 12/24/13.
 */
public class MainActivity extends Activity {

    Spinner conv_from;
    Spinner conv_to;
    EditText input;
    TextView output;
    Bundle table_values;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        this.context=this;
        this.table_values =new Bundle();
        this.conv_from = (Spinner)findViewById(R.id.conv_from);
        this.conv_to = (Spinner)findViewById(R.id.conv_to);
        this.input = (EditText)findViewById(R.id.input);
        this.output = (TextView)findViewById(R.id.output);
        Button convert = (Button)findViewById(R.id.convert);
        ArrayAdapter <CharSequence> units =ArrayAdapter.createFromResource(this,R.array.units,android.R.layout.simple_spinner_item);
        units.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.conv_from.setAdapter(units);
        this.conv_to.setAdapter(units);

        this.table_values.putFloat("cm/cm",Float.valueOf("1"));
        this.table_values.putFloat("cm/m",Float.valueOf("0.01"));
        this.table_values.putFloat("cm/km",Float.valueOf("0.00001"));
        this.table_values.putFloat("cm/foot",Float.valueOf("0.0328084"));
        this.table_values.putFloat("cm/In",Float.valueOf("0.393701"));
        this.table_values.putFloat("cm/yard",Float.valueOf("0.0109361"));
        this.table_values.putFloat("cm/mile",Float.valueOf("0.0000062"));
        this.table_values.putFloat("m/cm",Float.valueOf("100"));
        this.table_values.putFloat("m/m",Float.valueOf("1"));
        this.table_values.putFloat("m/km",Float.valueOf("0.001"));
        this.table_values.putFloat("m/foot",Float.valueOf("3.28084"));
        this.table_values.putFloat("m/In",Float.valueOf("39.3701"));
        this.table_values.putFloat("m/yard",Float.valueOf("1.09361"));
        this.table_values.putFloat("m/mile",Float.valueOf("0.000621371"));
        this.table_values.putFloat("km/cm",Float.valueOf("100000"));
        this.table_values.putFloat("km/m",Float.valueOf("1000"));
        this.table_values.putFloat("km/km",Float.valueOf("1"));
        this.table_values.putFloat("km/foot",Float.valueOf("3280.84"));
        this.table_values.putFloat("km/In",Float.valueOf("39370.1"));
        this.table_values.putFloat("km/yard",Float.valueOf("1093.61"));
        this.table_values.putFloat("km/mile",Float.valueOf("0.621371"));
        this.table_values.putFloat("foot/cm",Float.valueOf("30.48"));
        this.table_values.putFloat("foot/m",Float.valueOf("0.3048"));
        this.table_values.putFloat("foot/km",Float.valueOf("0.0003048"));
        this.table_values.putFloat("foot/foot",Float.valueOf("1"));
        this.table_values.putFloat("foot/In",Float.valueOf("12"));
        this.table_values.putFloat("foot/yard",Float.valueOf("0.333333"));
        this.table_values.putFloat("foot/mile",Float.valueOf("0.000189394"));
        this.table_values.putFloat("In/cm",Float.valueOf("2.54"));
        this.table_values.putFloat("In/m",Float.valueOf("0.0254"));
        this.table_values.putFloat("In/km",Float.valueOf("0.0000254"));
        this.table_values.putFloat("In/foot",Float.valueOf("0.0833333"));
        this.table_values.putFloat("In/In",Float.valueOf("1"));
        this.table_values.putFloat("In/yard",Float.valueOf("0.0277778"));
        this.table_values.putFloat("In/mile",Float.valueOf("0.0000578"));
        this.table_values.putFloat("yard/cm",Float.valueOf("91.44"));
        this.table_values.putFloat("yard/m",Float.valueOf("0.9144"));
        this.table_values.putFloat("yard/km",Float.valueOf("0.0009144"));
        this.table_values.putFloat("yard/foot",Float.valueOf("3"));
        this.table_values.putFloat("yard/In",Float.valueOf("36"));
        this.table_values.putFloat("yard/yard",Float.valueOf("1"));
        this.table_values.putFloat("yard/mile",Float.valueOf("0.000568182"));
        this.table_values.putFloat("mile/cm",Float.valueOf("160934"));
        this.table_values.putFloat("mile/m",Float.valueOf("1609.34"));
        this.table_values.putFloat("mile/km",Float.valueOf("1.60934"));
        this.table_values.putFloat("mile/foot",Float.valueOf("5280"));
        this.table_values.putFloat("mile/In",Float.valueOf("63360"));
        this.table_values.putFloat("mile/yard",Float.valueOf("1760"));
        this.table_values.putFloat("mile/mile",Float.valueOf("1"));


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_text = input.getText().toString();
                if (!input_text.equals("") || input_text.length() > 0) {
                    String convert_from = conv_from.getSelectedItem().toString();
                    String convert_to = conv_to.getSelectedItem().toString();
                    StringBuilder sb = new StringBuilder();
                    sb.append(convert_from);
                    sb.append("/");
                    sb.append(convert_to);
                    String output_text = calculate(sb.toString(), table_values, input_text);
                    output.setText(output_text);
                } else {
                    Toast.makeText(context, "Enter correct value to convert", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private String calculate(String search_for, Bundle table_values, String input_text) {
        Float constant = table_values.getFloat(search_for);
        Float input_number = Float.parseFloat(input_text);
        return String.format("%f",constant*input_number);
    }
}
