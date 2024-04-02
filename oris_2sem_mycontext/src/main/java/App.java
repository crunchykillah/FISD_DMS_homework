import component.TestClassOne;
import component.TestClassTwo;

public class App {
    public static void main(String[] args) {
        ComponentScan componentScan = new ComponentScan();
        componentScan.scan();

        componentScan.initializeComponents();

        TestClassOne testClassOne =  componentScan.getComponent(TestClassOne.class);
        TestClassTwo testClassTwo =  componentScan.getComponent(TestClassTwo.class);

        System.out.println(testClassOne.getClass().getName() + " - its working!");
        System.out.println(testClassTwo.getTestClassOne() != null ? "Dependency injection worked!" : "Dependency injection failed.");

    }
}
