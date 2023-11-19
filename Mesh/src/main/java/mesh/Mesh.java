package mesh;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Mesh {
    private int length;
    private int width;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    private int minStepLength;
    private int minStepWidth;
    private int countStepLength = 0;
    private int stepLength;

    public int getCountStepLength() {
        return countStepLength;
    }

    public int getStepLength() {
        return stepLength;
    }

    public int getCountStepWidth() {
        return countStepWidth;
    }

    public int getStepWidth() {
        return stepWidth;
    }

    private int countStepWidth = 0;
    private int stepWidth;

    public Mesh() {
        init();
    }


    private void init() {
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

    public void calculationMesh() {
        calculationStepWidth();
        calculationStepLength();

    }

    private void calculationStepLength() {
        int minRemainderDivision = 100;
        stepLength = minStepLength;
        for (int i = 0; i < 100; i++) {
            int remainderDivision = length % minStepLength;
            int countStepCurrent = length / minStepLength + 1;
            if (remainderDivision < minRemainderDivision) {
                minRemainderDivision = remainderDivision;
                stepLength = minStepLength;
                countStepLength = countStepCurrent;
            }
            if (minRemainderDivision == 0 ) {
                System.out.println("step length = " + stepLength);
                System.out.println("count step length = " + countStepLength);
                System.out.println("remainder length = " + minRemainderDivision);
                System.out.println();
                return;
            }
            minStepLength++;
        }
        System.out.println("step length = " + stepLength);
        System.out.println("count step length = " + countStepLength);
        System.out.println("remainder length = " + minRemainderDivision);
        System.out.println();
    }


    private void calculationStepWidth() {
        int minRemainderDivision = 100;
        stepWidth = minStepWidth;
        for (int i = 0; i < 100; i++) {
            int remainderDivision = width % minStepWidth;
            int countStepCurrent = width / minStepWidth + 1;
            if (remainderDivision < minRemainderDivision) {
                minRemainderDivision = remainderDivision;
                stepWidth = minStepWidth;
                countStepWidth = countStepCurrent;
            }
            if (minRemainderDivision == 0 ) {
                System.out.println("step length = " + stepWidth);
                System.out.println("count step length = " + countStepWidth);
                System.out.println("remainder length = " + minRemainderDivision);
                return;
            }
            minStepWidth++;
        }
        System.out.println("step length = " + stepWidth);
        System.out.println("count step length = " + countStepWidth);
        System.out.println("remainder length = " + minRemainderDivision);
    }


}
