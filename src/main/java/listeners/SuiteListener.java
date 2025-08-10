package listeners;

import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

   public  void onStart(org.testng.ISuite suite) {
       System.out.println("Suite started: " + suite.getName());
       System.out.println("All invoked methods: " + suite.getAllInvokedMethods());
    }

    public void onFinish(org.testng.ISuite suite) {
        System.out.println("Suite finished: " + suite.getName());
        System.out.println("Suite state: " + suite.getSuiteState());
    }
    }

