package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.CalculatorModel;

import static com.example.myapplication.model.CalculatorModel.Operation;

public class CalculatorViewModel extends ViewModel {

    private final MutableLiveData<String> screenData = new MutableLiveData<>();
    private final CalculatorModel model;
    private boolean isContinueInput = false;

    public CalculatorViewModel(){
        this(new CalculatorModel());
    }

    public CalculatorViewModel(final CalculatorModel model){
        screenData.setValue("0");
        this.model = model;
    }

    public LiveData<String> getScreenLiveData() {
        return screenData;
    }

    public void onDigitButtonClicked(char digit) {
        if (isContinueInput){
            screenData.setValue(screenData.getValue() + digit);
        } else {
            isContinueInput = true;
            screenData.setValue(String.valueOf(digit));
        }
    }

    public void onPlusButtonClicked() {
        if (screenData.getValue() == null){
            return;
        }
        isContinueInput = false;
        model.setOperation(Operation.PLUS);
        model.setData(Integer.parseInt(screenData.getValue()));
    }

    public void onMinusButtonClicked() {
        if (screenData.getValue() == null){
            return;
        }
        isContinueInput = false;
        model.setOperation(Operation.MINUS);
        model.setData(Integer.parseInt(screenData.getValue()));
    }

    public void onEqualsButtonClicked() {
        if (screenData.getValue() == null){
            return;
        }
        isContinueInput = false;
        int arg = Integer.parseInt(screenData.getValue());
        int result = model.doOperation(arg);
        screenData.setValue(String.valueOf(result));
    }

    public void onAcButtonClicked() {
        if (model != null){
            model.clear();
        }
        screenData.setValue("0");
        isContinueInput = false;
    }
}
