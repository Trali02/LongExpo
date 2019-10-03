package ImageProcessing;

import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

public class VideoLoader {
	
	List<Frame> frames;
	File video;
	public VideoLoader() {
		
		frames = new ArrayList<Frame>();
		
		FileDialog dialog1 = new FileDialog(new java.awt.Frame(),"Select video");
		dialog1.setMode(FileDialog.LOAD);
		dialog1.setMultipleMode(false);
	    dialog1.setVisible(true);
	    
		if(dialog1.getFiles()[0] == null) throw new Error("Missing File!");
		
		setVideo(dialog1.getFiles()[0]);
	}
	
	public void setVideo(File video) {
		
		this.video = video;
		FFmpegFrameGrabber f = new FFmpegFrameGrabber(video);
		
		
		
		try {
			f.start();
			if(f.getFrameNumber()<60) {
				for(int i=0;i<f.getFrameNumber();i++) {
				
					frames.add(null);
				}
			
			} else {
				for(int i=0;i<60;i++) {
					frames.add(null);
				}
			}
		
			f.stop();
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public BufferedImage[] getImages() {
		BufferedImage[] x = new BufferedImage[frames.size()];
		for(int i=0;i<frames.size();i++) {
			x[i] = new Java2DFrameConverter().getBufferedImage(frames.remove(0));
		}
		return x;
	}
	

	
}
