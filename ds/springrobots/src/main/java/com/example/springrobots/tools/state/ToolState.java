package com.example.springrobots.tools.state;

import com.example.springrobots.tools.Tool;
import com.example.springrobots.tools.actions.ToolSelfDiagnostics;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum ToolState implements IToolState {
    READY {
        private final ExecutorService executor = Executors.newFixedThreadPool(4);

        @Override
        public void repair(Tool tool) {
            executor.submit(new ToolSelfDiagnostics(tool));
        }

        @Override
        public void reboot(Tool tool) {
        }

        @Override
        public void work(Tool tool) {
            Random random = new Random();
            if(random.nextInt(10) > 7){
                tool.setState(ToolState.MALFUNCTION);
            }
        }

        @Override
        public boolean isBroken() {
            return true;
        }
    },
    MALFUNCTION {
        @Override
        public void repair(Tool tool) {
            Random random = new Random();
            if(random.nextInt(10) > 2){
                tool.setState(ToolState.READY);
            }
        }

        @Override
        public void work(Tool tool) {
            throw new ArithmeticException();
        }

        @Override
        public boolean isBroken() {
            return false;
        }

        @Override
        public void reboot(Tool tool) {
            tool.setState(ToolState.READY);
        }
    }
}
