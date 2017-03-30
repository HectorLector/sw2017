package at.sw2017.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Calculator extends AppCompatActivity implements View.OnClickListener{

    private List<Button> buttons = new ArrayList<>();
    private TextView numberView;
    private int firstNumber;
    private State state = State.INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Setup Buttons
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
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
            case R.id.buttonAdd:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonMinus:
                clearNumberView();
                state = State.SUB;
                break;
            case R.id.buttonMult:
                clearNumberView();
                state = State.MUL;
                break;
            case R.id.buttonDiv:
                clearNumberView();
                state = State.DIV;
                break;
            case R.id.buttonEqu:
                calculateResult();
                state = State.INIT;
                break;
            case R.id.buttonC:
                clearTextView();
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if(state == State.INIT){
                    recentNumber = "";
                    state = State.NUM;
                }
                recentNumber += clicked.getText().toString();
                numberView.setText(recentNumber);
        }

    }

    private void clearTextView()
    {
        numberView.setText("0");
        firstNumber = 0;
        state = State.INIT;
    }

    private void clearNumberView(){
        String tmp = numberView.getText().toString();
        if(!tmp.equals("")) {
            firstNumber = Integer.valueOf(tmp);
        }
        numberView.setText("");
    }

    private void calculateResult(){
        int secondNumber = 0;

        String tmp = numberView.getText().toString();
        if(!tmp.equals("")){
            secondNumber = Integer.valueOf(tmp);
        }

        int result;
        switch(state)
        {
            case ADD:
                result = Calculations.doAddition(firstNumber, secondNumber);
                break;
            case SUB:
                result = Calculations.doSubstraction(firstNumber, secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber, secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber, secondNumber);
                break;
            default:
                result = secondNumber;
        }

        numberView.setText(Integer.toString(result));
    }
}