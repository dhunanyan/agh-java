import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Decorations {
    List<XmasShape> decorations = new ArrayList<>();

    Decorations() {
        this.generateSnow();
        this.generateLights();
        this.generateBubbles();
        this.generateStar();
    }

    private void generateLights() {
        int[][] lightPositions = {
                {367, 140, 6},
                {343, 165, 11},
                {345, 210, 12},
                {320, 240, 14},
                {310, 300, 20},
                {285, 335, 11}
        };

        int [][] rgb = {
                {250,218,94},
                {252,209,42},
                {218,165,32},
                {248,228,115},
                {210,181,91},
                {255,195,11},
                {196,165,2}
        };

        for (int[] position : lightPositions) {
            for (int i = 0; i < position[2]; i++) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
                Light light = new Light();
                light.x = position[0] + 10 * i;
                light.y = position[1] + 40 +2 * i;
                light.fillColor = new Color(rgb[randomNum][0], rgb[randomNum][1], rgb[randomNum][2]);

                this.decorations.add(light);
            }
        }
    }

    private void generateBubbles() {
        int[][] bubblePositions = {
                {378, 192},
                {350, 275 - 15},
                {420, 285 - 15},
                {309, 365 + 10},
                {392, 333 + 10},
                {458, 352 + 10}
        };

        for (int[] position : bubblePositions) {
            Bubble bubble = new Bubble();
            bubble.firstColor = new Color(216, 152, 78);
            bubble.secondColor = new Color(216, 78, 78);
            bubble.x = position[0];
            bubble.y = position[1];
            bubble.scale = 0.25;

            this.decorations.add(bubble);
        }
    }

    private void generateStar() {
        StarShape star = new StarShape();
        star.firstColor = new Color(255, 229, 97);
        star.secondColor = new Color(255, 210, 76);
        star.x = 355;
        star.y = 102;
        star.scale = 0.35;

        this.decorations.add(star);
    }

    private void generateSnow() {
        int[][] snowPositions = new int[500][2];
        for(int i = 0; i < 500; i++){
            int randX = ThreadLocalRandom.current().nextInt(0, 800 + 1);
            int randY = ThreadLocalRandom.current().nextInt(0, 800 + 1);
            snowPositions[i][0] = randX;
            snowPositions[i][1] = randY;
        }

        for (int[] position : snowPositions) {
            Snow snow = new Snow();
            snow.firstColor = new Color(171, 171, 171);
            snow.secondColor = new Color(221, 221, 221);
            snow.x = position[0];
            snow.y = position[1];
            snow.scale = Math.random();
            int randSize = ThreadLocalRandom.current().nextInt(0, 18 + 1);
            snow.size = randSize;

            this.decorations.add(snow);
        }
    }
}