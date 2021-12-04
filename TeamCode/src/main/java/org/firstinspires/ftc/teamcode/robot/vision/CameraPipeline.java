package org.firstinspires.ftc.teamcode.robot.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class CameraPipeline extends OpenCvPipeline {

    public enum DuckPosition {
        TWO,
        ONE,
        THREE,
        UNKNOWN
    }

    /*
     * Some color constants
     */
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);

    /*
     * The core values which define the location and size of the sample regions
     */
    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(10, 75);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(80, 75);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(150, 75);

    static final int REGION_WIDTH = 25;
    static final int REGION_HEIGHT = 25;

    Point region1_pointA = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x,
            REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    // Pos 2
    Point region2_pointA = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x,
            REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pointB = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    // Pos 3
    Point region3_pointA = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x,
            REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pointB = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    /*
     * Working variables
     */
    Mat region1_Cb;
    Mat region2_Cb;
    Mat region3_Cb;

    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int avg1;
    int avg2;
    int avg3;
    Point bestRegion;
    Point bestRegion2;


    // Volatile since accessed by OpMode thread w/o synchronization
    public static volatile DuckPosition position = DuckPosition.UNKNOWN;

    /*
     * This function takes the RGB frame, converts to YCrCb,
     * and extracts the Cb channel to the 'Cb' variable
     */
    void inputToCb(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 1);
    }

    @Override
    public void init(Mat firstFrame) {
        inputToCb(firstFrame);

        region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
        region3_Cb = Cb.submat(new Rect(region3_pointA, region3_pointB));

    }

    @Override
    public Mat processFrame(Mat input) {
        inputToCb(input);

        avg1 = (int) Core.mean(region1_Cb).val[0];
        avg2 = (int) Core.mean(region2_Cb).val[0];
        avg3 = (int) Core.mean(region3_Cb).val[0];


        Imgproc.rectangle( // Rect 1
                input, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                -1); // Thickness of the rectangle lines
        Imgproc.rectangle( // Rect 2
                input, // Buffer to draw on
                region2_pointA, // First point which defines the rectangle
                region2_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                -1); // Thickness of the rectangle lines
        Imgproc.rectangle( // Rect 3
                input, // Buffer to draw on
                region3_pointA, // First point which defines the rectangle
                region3_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                -1); // Thickness of the rectangle lines


        position = DuckPosition.UNKNOWN; // Record our analysis
        if(Math.max(Math.max(avg1, avg2), avg3) == avg1){
            position = DuckPosition.ONE;
            bestRegion = region1_pointA;
            bestRegion2 = region1_pointB;
        }
        else if(Math.max(Math.max(avg1, avg2), avg3) == avg2){
            position = DuckPosition.TWO;
            bestRegion = region2_pointA;
            bestRegion2 = region2_pointB;

        }
        else if(Math.max(Math.max(avg1, avg2), avg3) == avg3){
            position = DuckPosition.THREE;
            bestRegion = region3_pointA;
            bestRegion2 = region3_pointB;

        }
        // Redraw the 'best' region with green
        Imgproc.rectangle(
                input, // Buffer to draw on
                bestRegion, // First point which defines the rectangle
                bestRegion2, // Second point which defines the rectangle
                GREEN, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        return input;
    }

    public int getRegion1(){return avg1;}
    public int getRegion2(){return avg2;}
    public int getRegion3(){return avg3;}


}
