package mesh.mesh;

import java.awt.*;

public class Mesh {

    private List input;
    private int length;
    private int width;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    private int NUMBER_ITERATIONS = 100;
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

    public Mesh(List input) {
        this.input = input;
    }


    private boolean init() {
        try {
            length = Integer.parseInt(input.getItem(0));
            width = Integer.parseInt(input.getItem(1));
            minStepLength = Integer.parseInt(input.getItem(2));
            minStepWidth = Integer.parseInt(input.getItem(3));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public String calculationMesh() {
        if (!init()) {
            return "Неккоректные значения";
        }
        String resWidth = calculationStepWidth();
        String resLength = calculationStepLength();
        return "По длине:\n" + resLength + "По ширине:\n" + resWidth;
    }

    private String calculationStepLength() {
        int minRemainderDivision = length;
        stepLength = minStepLength;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < NUMBER_ITERATIONS; i++) {
            int remainderDivision = length % minStepLength;
            int countStepCurrent = length / minStepLength + 1;

            if (remainderDivision < minRemainderDivision) {
                minRemainderDivision = remainderDivision;
                stepLength = minStepLength;
                countStepLength = countStepCurrent;
            }
            if (minRemainderDivision == 0) {
                appendResult(result, stepLength, countStepLength, minRemainderDivision);
                return result.toString();
            }
            minStepLength++;
        }
        appendResult(result, stepLength, countStepLength, minRemainderDivision);
        return result.toString();
    }

    private void appendResult(StringBuilder result, int step, int countStep, int minRemainderDivision) {
        result.append("длина шага = ").append(step).append("\n");
        result.append("количество шагов = ").append(countStep).append("\n");
        result.append("остаток стола = ").append(minRemainderDivision).append("\n");
        result.append("\n");
    }


    private String calculationStepWidth() {
        int minRemainderDivision = width;
        stepWidth = minStepWidth;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < NUMBER_ITERATIONS; i++) {
            int remainderDivision = width % minStepWidth;
            int countStepCurrent = width / minStepWidth + 1;
            if (remainderDivision < minRemainderDivision) {
                minRemainderDivision = remainderDivision;
                stepWidth = minStepWidth;
                countStepWidth = countStepCurrent;
            }
            if (minRemainderDivision == 0) {
                appendResult(result, stepWidth, countStepWidth, minRemainderDivision);
                return result.toString();
            }
            minStepWidth++;
        }
        appendResult(result, stepWidth, countStepWidth, minRemainderDivision);
        return result.toString();
    }


}
