package com.galileo.tarea2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    String _Operador = "";
    String _ValueOne = "";
    String _ValueTwo = "";
    double _Value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonClick(View arg0){
        try {
            if(_Operador==""){
                _ValueOne = _ValueOne + ((Button) arg0).getText().toString();
            }
            else
            {
                _ValueTwo = _ValueTwo + ((Button) arg0).getText().toString();

            }
            ((TextView) findViewById(R.id.txtOperation)).append(((Button) arg0).getText().toString());
            OperationInt();

        }
        catch(Exception e) {
            ((TextView) findViewById(R.id.txtResult)).setHint(e.toString());
        }
    }

    public void BtnEqual(View arg0){
        try{
            ((TextView) findViewById(R.id.txtOperation)).setText(String.valueOf(String.format("%.2f", _Value)));
            ((TextView) findViewById(R.id.txtResult)).setText(String.valueOf(String.format("%.2f", _Value)));
            ((TextView) findViewById(R.id.txtResult)).setHint("");
            _Operador ="";
            _ValueTwo = "";
            _ValueOne = String.valueOf(_Value);
        }
        catch (Exception e){
            ((TextView) findViewById(R.id.txtResult)).setHint(e.toString());
        }
    }

    public void ButtonClickDot(View arg0){
        try {
            String _Dot = "0.";
            if(_Operador==""){
                if(!_ValueOne.contains(".")) {
                    if ( _ValueOne.length() > 0) {
                        _ValueOne = _ValueOne + ((Button) arg0).getText().toString();
                        _Dot = ".";
                    } else {
                        _ValueOne = "0" + _ValueOne + ((Button) arg0).getText().toString();
                        _Dot = "0.";
                    }
                }
             }
            else
            {
                if(!_ValueTwo.contains(".")) {
                    if ( _ValueTwo.length() > 0) {
                        _ValueTwo = _ValueTwo + ((Button) arg0).getText().toString();
                        _Dot = ".";
                    } else {
                        _ValueTwo = "0" + _ValueTwo + ((Button) arg0).getText().toString();
                        _Dot = "0.";
                    }
                }
            }



            if(_ValueTwo =="" || _ValueOne == ""){
                ((TextView) findViewById(R.id.txtOperation)).setText(_Dot);
            }
            else{
                ((TextView) findViewById(R.id.txtOperation)).append(_Dot);
            }



            OperationInt();

        }
        catch(Exception e) {
            ((TextView) findViewById(R.id.txtResult)).setHint(e.toString());
        }
    }

    public void btnClear(View arg0){

        ((TextView) findViewById(R.id.txtOperation)).setText("");
        ((TextView) findViewById(R.id.txtResult)).setText("");
        ((TextView) findViewById(R.id.txtResult)).setHint("0");
        _Operador ="";
        _Value = 0;
        _ValueTwo = "";
        _ValueOne = "";
    }


    public void OperationInt(){
        try {
                if (_Operador=="" || _ValueTwo== "") {
                    if (_ValueOne !=""){
                        _Value = Double.parseDouble(_ValueOne);
                        ((TextView) findViewById(R.id.txtResult)).setHint(String.valueOf(String.format("%.2f", _Value)));
                    }

                }
                else{

                    switch (_Operador) {
                        case "+":
                            _Value = Double.parseDouble(_ValueOne)+Double.parseDouble(_ValueTwo);
                            break;
                        case "-":
                            _Value = Double.parseDouble(_ValueOne)-Double.parseDouble(_ValueTwo);
                            break;
                        case "x":
                            _Value = Double.parseDouble(_ValueOne)*Double.parseDouble(_ValueTwo);
                            break;
                        case "÷":
                            _Value = Double.parseDouble(_ValueOne)/Double.parseDouble(_ValueTwo);
                            break;

                    }

                    //_ValueOne = String.valueOf(_Value);
                    ((TextView) findViewById(R.id.txtResult)).setText("");
                    ((TextView) findViewById(R.id.txtResult)).setHint(String.valueOf(String.format("%.2f", _Value)));
                }
            }
        catch(Exception e) {
            ((TextView) findViewById(R.id.txtResult)).setHint(e.toString());
        }
    }

    public void btnOperator(View arg0) {
        //Obtengo el valor del txt
         String vValue = ((TextView) findViewById(R.id.txtOperation)).getText().toString();
        //Si es mayor a 0 se agrega el simbolo
        if (vValue.length() > 0) {
            //Obtengo el la ultima posicion
            String vLastValue = vValue.substring(vValue.length() - 1);
            //Valido si la ultima posision es un simbolo, si lo es lo cambia
            if (vLastValue.contains("+") || vLastValue.contains("-") || vLastValue.contains("x") || vLastValue.contains("÷")) {
                //Obtengo solo el valor
                vValue = vValue.substring(0, vValue.length() - 1);
                //Agrego la cadena al texto
                vValue = vValue + ((Button) arg0).getText().toString();
                //Muestro la nueva cadena
                ((TextView) findViewById(R.id.txtOperation)).setText(vValue);
                _Operador = ((Button) arg0).getText().toString();
            } else {
                //Se asigna el operador el texto
                ((TextView) findViewById(R.id.txtOperation)).append(((Button) arg0).getText().toString());
                _Operador = ((Button) arg0).getText().toString();
            }
        }
        _ValueOne = String.valueOf(_Value);
        _ValueTwo = "";
        _Value = 0;
        OperationInt();
    }
}
