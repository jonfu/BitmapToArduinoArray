
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;






public class ImageToRgb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		BufferedImage bi = null;
		try {
			bi = ImageIO.read( new File( "D:/pov/39/twohearts.bmp" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int imgWidth = bi.getWidth();
		int imgHeight = bi.getHeight();
		

		System.out.println("###### bi.getRaster().getDataBuffer().getDataType() is " + bi.getRaster().getDataBuffer().getDataType());
		
		System.out.println("###### bi.getRaster().getDataBuffer().getSize() is " + bi.getRaster().getDataBuffer().getSize());
		
		
		byte[] data = ( (DataBufferByte) bi.getRaster().getDataBuffer() ).getData();
		


		
		System.out.println("//########## start here, image dimension = " + imgWidth + "x" + imgHeight);
		
		for (int j=0; j<imgWidth; j++) {
			System.out.print("{ ");
			for (int i=0; i<imgHeight; i++) {
				
				int value = data[i*imgWidth*3+j*3+2];
				if (value < 0) {
					value += 256;
				}
				
				System.out.print("{ 0x" + Integer.toHexString(value) + ", " );
				
				value = data[i*imgWidth*3+j*3+1];
				if (value < 0) {
					value += 256;
				}
				System.out.print("0x" + Integer.toHexString(value) + ", " );
				
				value = data[i*imgWidth*3+j*3];
				if (value < 0) {
					value += 256;
				}
				System.out.print("0x" + Integer.toHexString(value) + " }" );
				
				
				if (i==(imgHeight-1)) {
					if (j==(imgWidth-1)) {
						System.out.println(" }");
					}
					else {
						System.out.println(" },");
					}
				}
				else {
					System.out.print(", ");
				}
			}
		}
		
		
		
	}
	

}
