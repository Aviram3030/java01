package experis.ds.userinterface.output;

import experis.ds.logic.Observer;

public class DisplayObserverConsole implements Display<Observer> {
    @Override
    public void getOutput(Observer observer) {
        System.out.println(observer.toString());
    }
}
