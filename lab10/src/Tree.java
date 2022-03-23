import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree {
    List<XmasShape> branches = new ArrayList<>();

    Tree() {
        //BACKGROUND
        Background background = new Background();
        background.x = 0;
        background.y = 380;
        background.scale = 1;
        background.firstColor = new Color(171, 171, 171);
        background.secondColor = new Color(221, 221, 221);
        this.branches.add(background);

        //TRUNK
        Trunk trunk = new Trunk();
        trunk.x = 365;
        trunk.y = 390;
        trunk.scaleX = 0.5;
        trunk.scaleY = 0.25;
        this.branches.add(trunk);


        //BRANCHES
        for(double i = 1 ; i <= 3; i++){
            Branch branch = new Branch();
            branch.x = 435 - (1 - i /10 * 1.5) * 400 / 2;
            if(i != 3){
                branch.y = 290 - i * 50;
            } else {
                branch.y = 300 - i * 50;
            }
            branch.scale = 1 - i /10 * 2;
            branch.rotate = 0;
            branch.color = "#388E3C";
            this.branches.add(branch);

            Branch branch1 = new Branch();
            if(i == 1){
                branch1.y = 290 - i * 50;
                branch1.x = 435 - (0.80 - i /10 * 1.5) * 400 / 2 + i * 7;
            } else if (i == 2) {
                branch1.y = 290 - i * 50;
                branch1.x = 435 - (0.80 - i /10 * 1.5) * 400 / 2 + i * 4;
            }
            else {
                branch1.y = 300 - i * 50;
                branch1.x = 435 - (0.80 - i /10 * 1.5) * 400 / 2 + i * 3;
            }
            branch1.scale = 0.70 - i /10 * 2;
            branch1.rotate = 0;
            branch1.color = "#1B5E20";
            this.branches.add(branch1);
        }
    }
}