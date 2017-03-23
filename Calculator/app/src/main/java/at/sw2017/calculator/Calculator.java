package at.sw2017.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Calculator extends AppCompatActivity implements View.OnClickListener{

    private List<Button> buttons = new ArrayList<>();
    private TextView numberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Setup Buttons
        Button buttonAdd = (Button) findViewById(R.id.buttonPlus);
        buttonAdd.setOnClickListener(this);
        buttons.add(buttonAdd);

        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(this);
        buttons.add(buttonMinus);

        Button buttonMult = (Button) findViewById(R.id.buttonMult);
        buttonMult.setOnClickListener(this);
        buttons.add(buttonMult);

        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(this);
        buttons.add(buttonDiv);

        Button buttonEqu = (Button) findViewById(R.id.buttonEqu);
        buttonEqu.setOnClickListener(this);
        buttons.add(buttonEqu);

        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        buttons.add(buttonC);

        setUpNumberButtons();

        numberView = (TextView)findViewById(R.id.textView);
    }

    public void setUpNumberButtons()
    {
        for(int i = 0; i <= 9; i++)
        {
            int id = getResources().getIdentifier("button" + i, "id", R.class.getPackage().getName());
            Button button = (Button) findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);
        }
    }

    @Override
    public void onClick(View v) {

        Button clicked = (Button) v;

        switch(clicked.getId())
        {
            case R.id.buttonPlus:
                break;
            case R.id.buttonMinus:
                break;
            case R.id.buttonMult:
                break;
            case R.id.buttonDiv:
                break;
            case R.id.buttonEqu:
                break;
            case R.id.buttonC:
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if(recentNumber.equals("0")){
                    recentNumber = "";
                }
                recentNumber += clicked.getText().toString();
                numberView.setText(recentNumber);
        }

    }
}
