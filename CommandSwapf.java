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
public class CommandSwapf extends Command {
    private WReg wReg;
    private DataStorage dataStorage;
    
    public CommandSwapf (int aLineNumber, WReg aWReg, DataStorage aStack) {
        super (aLineNumber,2);
        this.wReg = aWReg;
        this.dataStorage = aStack;
    }
    
    @Override
    public void readParameters(int [] aParameters) {
        parameters [0] = aParameters [0];
        parameters [1] = aParameters [1];
    }
    
    @Override
    public int executeCommand() {
        
        if (parameters[0] == 0){
            parameters[0] = dataStorage.getValue(4);
            if (parameters[0] == 0) {
                return -1;
            }
        }
        
        int value = dataStorage.getValue(parameters [0]);
        int result = (((value & 0x0F) << 4) + (((value & 0xF0) >> 4) & 0x0F));
        
        if (parameters[1] == 0){
                wReg.setValue(result);
            } else {
                dataStorage.setValue(parameters[0], result);
            }
        return -1;
    }
}