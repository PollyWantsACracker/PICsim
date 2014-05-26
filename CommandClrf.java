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
public class CommandClrf extends Command {
    private DataStorage dataStorage;
    
    public CommandClrf (int aLineNumber, DataStorage aStack) {
        super (aLineNumber,1);
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
        dataStorage.setValue(parameters[0], 0);
        return -1;
    }
}
