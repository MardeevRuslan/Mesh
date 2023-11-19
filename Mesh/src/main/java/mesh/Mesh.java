package mesh;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Mesh {
    private int length;
    private int width;
    private int minStepLength;
    private int minStepWidth;


    public void init() {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("target/resources/conf.conf")) {
            properties.load(fileInputStream);

            length = Integer.parseInt(properties.getProperty("length"));
            width = Integer.parseInt(properties.getProperty("width"));
            minStepLength = Integer.parseInt(properties.getProperty("step_l"));
            minStepWidth = Integer.parseInt(properties.getProperty("step_w"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculationStepLength() {
        int minRemainderDivision = 100;
        int countStep = 0;
        int step = minStepLength;
        for (int i = 0; i < 100; i++) {
            int remainderDivision = length % minStepLength;
            int countStepCurrent = length / minStepLength + 1;
            if (remainderDivision < minRemainderDivision) {
                minRemainderDivision = remainderDivision;
                step = minStepLength;
                countStep = countStepCurrent;
            }
            if (minRemainderDivision == 0 ) {
                System.out.println("step length = " + step);
                System.out.println("count step length = " + countStep);
                System.out.println("remainder length = " + minRemainderDivision);
                System.out.println();
                return;
            }
            minStepLength++;
        }
        System.out.println("step length = " + step);
        System.out.println("count step length = " + countStep);
        System.out.println("remainder length = " + minRemainderDivision);
        System.out.println();
    }


    public void calculationStepWidth() {
        int minRemainderDivision = 100;
        int countStep = 0;
        int step = minStepWidth;
        for (int i = 0; i < 100; i++) {
            int remainderDivision = width % minStepWidth;
            int countStepCurrent = width / minStepWidth + 1;
            if (remainderDivision < minRemainderDivision) {
                minRemainderDivision = remainderDivision;
                step = minStepWidth;
                countStep = countStepCurrent;
            }
            if (minRemainderDivision == 0 ) {
                System.out.println("step length = " + step);
                System.out.println("count step length = " + countStep);
                System.out.println("remainder length = " + minRemainderDivision);
                return;
            }
            minStepWidth++;
        }
        System.out.println("step length = " + step);
        System.out.println("count step length = " + countStep);
        System.out.println("remainder length = " + minRemainderDivision);
    }


}
