package org.firstinspires.ftc.teamcode.robot.vision;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera {
    // Initialize USB Camera
    public OpenCvWebcam webcam;
    private int cameraMonitorViewId;
    private CameraPipeline pipeline;
    private int average;
    //private FtcDashboard dashboard;
    // Initialize bounding boxes
    // Initialize Thresholds
    public Camera(HardwareMap hardwareMap){
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        pipeline = new CameraPipeline();
        webcam.setPipeline(pipeline);
        //dashboard = FtcDashboard.getInstance();
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240);

            }

            @Override
            public void onError(int errorCode) {

            }



        });
    }

    public void detect(){

        average = pipeline.getAnalysis();
        //dashboard.startCameraStream(webcam, 30);

    }
    public int getAverage(){
        return average;
    }

}
