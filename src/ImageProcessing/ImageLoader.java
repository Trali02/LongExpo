package ImageProcessing;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;


public class ImageLoader {
	
	private String path;
	private static BufferedImage image;
	private boolean hasAlpha = false;
	
	public ImageLoader(String path) {
		this.path = path;
		try {
			image = ImageIO.read(new File(this.path));
			if(image.getAlphaRaster()!=null) this.hasAlpha = true;
		} catch (IOException e) {
			throw new Error(e);
		}
		
	}
	public ImageLoader(File file) {
		try {
			image = ImageIO.read(file);
			if(image.getAlphaRaster()!=null) this.hasAlpha = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPixel(int x,int y) {
		return image.getRGB(x,y);
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	
	
	
}
