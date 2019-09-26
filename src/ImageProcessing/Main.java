package ImageProcessing;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	private static ImageLoader loader;
	private static BufferedImage[] images;
	
	public static void main(String[] args) {
		/*
		FileDialog dialog1 = new FileDialog(new Frame(),"Select video");
		dialog1.setMode(FileDialog.LOAD);
		dialog1.setMultipleMode(false);
	    dialog1.setVisible(true);
	    
	    System.out.println(dialog1.getDirectory()+dialog1.getFile());
	     
	    loader = new VideoLoader(dialog1.getFiles()[0]);
	    */
		loader = new ImageLoader();
		
	    images = loader.getImages();
	    
	    
		
		BufferedImage image = new BufferedImage(images[0].getWidth(),images[0].getHeight(),BufferedImage.TYPE_INT_RGB);
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		 for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				for(int x=0;x<images.length;x++) {
					
					//a +=  ((loader[x].getPixel(j, i)&0xff000000)<<24) / loader.length;
					r +=  (images[x].getRGB(j, i) & 0xff0000)>>16 ;
					g +=  (images[x].getRGB(j, i) & 0xff00)>>8 ;
					b +=  images[x].getRGB(j, i) & 0xff ;
					
					
				}
				image.setRGB(j, i, (((r/images.length)<<16)+((g/images.length)<<8)+(b/images.length)));
				//System.out.println("-"+r+"-"+g+"-"+b);
				
				r=0;
				g=0;
				b=0;
			}
		}
		
		
		File f;
		
		
		FileDialog dialog = new FileDialog(new Frame(),"choose save location");
	    dialog.setMode(FileDialog.SAVE);
	    dialog.setVisible(true);
	    f = dialog.getFiles()[0];
        
		
		try {
			ImageIO.write(image,"png",f);
			System.out.println("done!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
