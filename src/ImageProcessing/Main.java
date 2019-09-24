package ImageProcessing;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	private static ImageLoader[] loader;
	private static File[] images;
	
	public static void main(String[] args) {
		
		FileDialog dialog1 = new FileDialog(new Frame(),"Select images");
		dialog1.setMode(FileDialog.LOAD);
		dialog1.setMultipleMode(true);
	    dialog1.setVisible(true);
	    
	    images = dialog1.getFiles();
	    
	    for(File file:images) {
	    	System.out.println("File : " + file.getName());
	    }
	    
	    loader = new ImageLoader[images.length];
	    
	    for(int i=0;i<images.length;i++) {
	    	loader[i] = new ImageLoader(images[i].getPath());
	    }
		
		
		
		BufferedImage image = new BufferedImage(loader[0].getWidth(),loader[0].getHeight(),BufferedImage.TYPE_INT_RGB);
		
		int width = image.getWidth();
		int height = image.getHeight();
		int a = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		
		 for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				for(int x=0;x<loader.length;x++) {
					
					//a +=  ((loader[x].getPixel(j, i)&0xff000000)<<24) / loader.length;
					r +=  (loader[x].getPixel(j, i) & 0xff0000)>>16 ;
					g +=  (loader[x].getPixel(j, i) & 0xff00)>>8 ;
					b +=  loader[x].getPixel(j, i) & 0xff ;
					
					
				}
				image.setRGB(j, i, (((r/loader.length)<<16)+((g/loader.length)<<8)+(b/loader.length)));
				//System.out.println("-"+r+"-"+g+"-"+b);
				a=0;
				r=0;
				g=0;
				b=0;
			}
		}
		
		//System.out.print(Integer.toHexString(loader[loader.length-1].getPixel(width-1,height-1)));
		
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
