package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;




public class VideoLoader {
	
	List<BufferedImage> frames;
	File video;
	public VideoLoader(File file) {
		if(file == null) throw new Error("Missing File!");
		frames = new ArrayList<BufferedImage>();
		setVideo(file);
	}
	
	public void setVideo(File video) {
		
		this.video = video;
		
		try {
			FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(video));
			Picture p;
			p = grab.getNativeFrame();
			frames.add(bridge.AWTUtil.toBufferedImage(p));
		} catch (IOException | JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public BufferedImage[] getImages() {
		BufferedImage[] x = new BufferedImage[frames.size()];
		for(int i=0;i<frames.size();i++) {
			x[i] = frames.remove(0);
		}
		return x;
	}
	

	
}
