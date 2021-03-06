package com.lmx.yolo;

import com.lmx.yolo.beans.YoloVideo;
import com.lmx.yolo.controller.YoloImageController;
import com.lmx.yolo.controller.YoloVideoController;
import com.lmx.yolo.service.FileService;
import com.lmx.yolo.task.YoloTask;
import com.lmx.yolo.utils.CameraUtil;
import com.lmx.yolo.utils.FfmpegUtil;
import com.lmx.yolo.utils.YoloUtil;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@EnableAsync
@SpringBootTest
class YoloApplicationTests {
    @Autowired
    CameraUtil cameraUtil;
    @Autowired
    YoloTask yoloTask;
    @Autowired
    FileService fileService;
    @Autowired
    YoloImageController yoloImageController;
    @Autowired
    YoloVideoController videoController;

    @Autowired
    FfmpegUtil ffmpegUtil;
    @Autowired
    YoloUtil yoloUtil;
    @Test
    void contextLoads() throws IOException, InterruptedException {

        new Thread(new Runnable(){
            @Override
            public void run() {

            }
        }).start();String line;
        while(true) {
            try {
                if (yoloUtil.br!=null){

                    line = yoloUtil.br.readLine();
                    System.out.println("======================"+line);

                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
//        yoloUtil.yoloImage("C:\\mask_detecting-master\\image\\2.jpg");
//        yoloUtil.yoloImage("C:\\mask_detecting-master\\image\\3.jpg");


    }



    @Test
    public void thread() throws ExecutionException, InterruptedException, IOException {

//        YoloImage yoloImage = yoloUtil.yoloImage("C:\\mask_detecting-master\\image\\2.jpg");
//        System.out.println(yoloImage);
//        YoloImage yoloImage1 = yoloUtil.yoloImage("C:\\mask_detecting-master\\image\\3.jpg");
//        System.out.println(yoloImage1);
    }


    @Test
    public void Ffmpeg() throws Exception {
        Integer integer = ffmpegUtil.videoToImage("D:\\yolo\\storeVideo\\WeChat_20200117234311.mp4", "D:\\yolo\\outputImage", "WeChat_20200117234311.mp4");
        System.out.println(integer);
    }


    @Test
    public void FfmpegVideo() throws Exception {
//      ffmpegUtil.imageToVideo("WeChat_20200117234311.mp4","D:\\yolo\\storeVideo\\test.mp4","D:\\yolo\\outputImage");
    }

    public static void main(String[] args) {
        FfmpegUtil ffmpegUtil = new FfmpegUtil();
        //ffmpegUtil.imageToVideo("","C:\\Users\\Legion\\Desktop\\video\\","test2","C:\\Users\\Legion\\Desktop\\outputPath\\outputImage2\\");
        try {
            ffmpegUtil.videoToImage("C:\\Users\\Legion\\Desktop\\VID_20210517_182141.mp4","C:\\Users\\Legion\\Desktop\\outputPath\\outputCamera","temp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCamera() throws InterruptedException, FrameGrabber.Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();   //???????????????????????????
        CanvasFrame canvas = new CanvasFrame("?????????");//??????????????????
        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        while (true) {
            if (!canvas.isDisplayable()) {//??????????????????
                grabber.stop();//????????????
                System.exit(-1);//??????
            }

            Frame frame = grabber.grab();

            canvas.showImage(frame);//???????????????????????????????????????????????? ?????????Frame frame=grabber.grab(); frame?????????????????????
            Thread.sleep(50);//50????????????????????????
        }
    }

    @Test
    public void testCamera1() throws FrameGrabber.Exception, InterruptedException {
        VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
        grabber.start();
        CanvasFrame canvasFrame = new CanvasFrame("?????????");
        canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvasFrame.setAlwaysOnTop(true);
        while (true) {
            if (!canvasFrame.isDisplayable()) {
                grabber.stop();
                System.exit(-1);
            }
            Frame frame = grabber.grab();
            canvasFrame.showImage(frame);
            Thread.sleep(30);
        }
    }
    @Test
    public void testVideoToImage(){
        YoloVideo yoloVideo = new YoloVideo();
        yoloVideo.setVideoName("WeChat_20200117234311.mp4");
        yoloVideo.setStorePath("D:\\yolo\\storeVideo\\WeChat_20200117234311.mp4");

        fileService.videoToImage(yoloVideo);
    }

    @Test
    public void cameralImg() throws InterruptedException, FrameGrabber.Exception {
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();   //???????????????????????????
        CanvasFrame canvas = new CanvasFrame("?????????");//??????????????????
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        int ex = 0;
        while(true)
        {
            if(!canvas.isDisplayable())
            {//??????????????????
                grabber.stop();//????????????
                System.exit(2);//??????
                break;
            }
            canvas.showImage(grabber.grab());//???????????????????????????????????????????????? ?????????Frame frame=grabber.grab(); frame?????????????????????
            opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
            ex++;
            opencv_imgcodecs.imwrite("C:\\mask_detecting-master\\uploadImage\\" + ex + ".jpg", mat);
            Thread.sleep(200);//50????????????????????????
        }

    }

    /*@Test
    public void testCameralUtil() throws Exception {

        YoloImage yoloImage1 =new YoloImage();
        yoloImage1.setImageUrl("C:\\mask_detecting-master\\image\\2.jpg");
        yoloImage1.setImageName("2.jpg");
       yoloImageController.analysisImage(yoloImage1);
       for (int i=1;i<=100;i++){
           YoloImage yoloImage = videoController.analysisRunTime(true);
       }
    }*/


}
