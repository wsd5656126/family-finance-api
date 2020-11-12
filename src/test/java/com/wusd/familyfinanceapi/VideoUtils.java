package com.wusd.familyfinanceapi;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class VideoUtils {
    /**
     * 截取视频获得指定帧的图片
     * @param picPath 截图存放路径
     */
    public static InputStream getVideoPic(File file, String picPath) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
        try {
            ff.start();

            // 截取中间帧图片(具体依实际情况而定)
            int i = 0;
            int length = ff.getLengthInFrames();
            int middleFrame = length / 2;
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > middleFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = 480;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);


            InputStream imageStream = getImageStream(thumbnailImage);

            ff.stop();
            return imageStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static InputStream getImageStream(BufferedImage bimage){
        InputStream is = null;
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        try {
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bimage, "jpg",imOut);
            is= new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    public static void main(String[] args) {
        String videoPath = ResourceUtils.CLASSPATH_URL_PREFIX + "1.mp4";
        File video = null;
        try {
            video = ResourceUtils.getFile(videoPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String picPath = "video.jpg";
        InputStream videoPic = getVideoPic(video, picPath);
        System.out.println();

    }
}
