/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logik.Command;

import Logik.DataStorage;
import Logik.WReg;

/**
 *
 * @author Christopher Heß
 */
public class CommandRrf extends Command{
    private WReg wReg;
    private DataStorage dataStorage;
    
    public CommandRrf (int aLineNumber, WReg aWReg, DataStorage aStack) {
        super (aLineNumber,1);
        this.wReg = aWReg;
        this.dataStorage = aStack;
    }
    
    @Override
    public void readParameters(int [] aParameters) {
        parameters [0] = aParameters [0];
    }
    
    @Override
    public int executeCommand() {
        if (parameters[0] == 0){
            parameters[0] = dataStorage.getValue(4);
            if (parameters[0] == 0) {
                return -1;
            }
        }
        int carry = (dataStorage.getValue(3) & 0x01);
        
        int value = dataStorage.getValue(parameters[0]);
        int result = (value >> 8);
        
        if (((value & 0x01) == 1) && (carry == 0)){
            dataStorage.setValue(3, dataStorage.getValue(3) + 1);
        } else if (((value & 0x01) == 0) && (carry == 1)){
            dataStorage.setValue(3, dataStorage.getValue(3) - 1);
        }
        
        if (carry == 1){
            result = result + 128;
        }
        
        if (parameters[1] == 0){
            wReg.setValue(result);
        } else {
            dataStorage.setValue(parameters[0], result);
        }
        
        return -1;
    }
}
