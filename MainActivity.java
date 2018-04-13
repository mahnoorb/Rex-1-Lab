package eecs.rex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void generate(View v)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.letter);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.digit);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.anchor);

        RexModel rex = new RexModel();

        rex.setDigit(checkBox1.isChecked());
        rex.setAnchor(checkBox2.isChecked());
        rex.setLetter(checkBox.isChecked());

        rex.generate(2);
        ((TextView) findViewById(R.id.regex)).setText(rex.getRex());







    }

    public void check(View v)
    {
        String check = ((EditText)findViewById(R.id.string)).getText().toString();

        CheckBox checkBox = (CheckBox) findViewById(R.id.letter);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.digit);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.anchor);

        RexModel rex = new RexModel();

        rex.setDigit(checkBox1.isChecked());
        rex.setAnchor(checkBox2.isChecked());
        rex.setLetter(checkBox.isChecked());

        rex.generate(2);
        String output = "regex" + rex.getRex()+", string = " + check+"---->" + rex.doesMatch(check);

        ((TextView) findViewById(R.id.log)).setText(output);

    }
}
