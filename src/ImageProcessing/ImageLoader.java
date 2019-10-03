package ImageProcessing;


import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;


public class ImageLoader {
	
	
	private static BufferedImage[] images;
	//private boolean hasAlpha = false;
	
	public ImageLoader() {
		try {
			FileDialog dialog1 = new FileDialog(new Frame(),"Select Image Sequence");
			dialog1.setMode(FileDialog.LOAD);
			dialog1.setMultipleMode(true);
		    dialog1.setVisible(true);
			
		    images = new BufferedImage[dialog1.getFiles().length];
		    
		    for(int i=0;i<dialog1.getFiles().length;i++) {
		    	images[i] = ImageIO.read(dialog1.getFiles()[i]);	
		    }
		    
		    
		    
			//if(image.getAlphaRaster()!=null) this.hasAlpha = true;
		} catch (IOException e) {
			throw new Error(e);
		}
		
	}
	

	public BufferedImage[] getImages() {
		return images;
	}
	
	public int getHeight(int index) {
		return images[index].getHeight();
	}
	
	public int getWidth(int index) {
		return images[index].getWidth();
	}
	
	
	
	
}
