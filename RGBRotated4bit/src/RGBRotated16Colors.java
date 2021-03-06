
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RGBRotated16Colors {

	//4 bit BMP (16 colors) = 2
	//8 bit BMP (256 colors) = 1
	final static byte pixelPerByte = 2;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
	  for (int y=0; y<args.length; y++) {
		
		String filename = args[y];


		BufferedImage bi = null;
		try {
			bi = ImageIO.read( new File( "E:/pov/20/4bit/" + filename + ".bmp" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int imgWidth = bi.getWidth();
		int imgHeight = bi.getHeight();
		
		int arrayWidth = imgWidth;
		if (imgWidth%2==1) {
			arrayWidth++;
		}
		
		arrayWidth /= pixelPerByte;
		
		
		IndexColorModel icm = (IndexColorModel) bi.getColorModel();
		

		
		/*
		
		
		System.out.println("###### indexed color model getMapSize() " + icm.getMapSize());


		for (int i=0; i<icm.getMapSize(); i++) {
			
			System.out.println("###### RED " + i + " : " + icm.getRed(i) + ", BLUE: " + icm.getBlue(i));
		}

		System.out.println("###### bi.getRaster().getDataBuffer().getDataType() is " + bi.getRaster().getDataBuffer().getDataType());
		
		System.out.println("###### bi.getRaster().getDataBuffer().getSize() is " + bi.getRaster().getDataBuffer().getSize());
		
		*/
		
		
		byte[] data = ( (DataBufferByte) bi.getRaster().getDataBuffer() ).getData();
		
		System.out.println("// WIDTH" + filename + " " + imgWidth + ", array size " + data.length);
		System.out.println("#define HEIGHT" + filename + " " + imgHeight);
		
		System.out.println("const byte " + filename + "Palette[" + icm.getMapSize() + "][3] = {");
		
		for (int i=0; i<icm.getMapSize(); i++) {
			/*
			System.out.print("{ 0x" + Integer.toHexString(icm.getRed(i)) + ", " );
			System.out.print("0x" + Integer.toHexString(icm.getGreen(i)) + ", " );
			System.out.print("0x" + Integer.toHexString(icm.getBlue(i)) );
			*/
			
			System.out.print("{" +icm.getRed(i) + ", " );
			System.out.print(icm.getGreen(i) + ", " );
			System.out.print(icm.getBlue(i) );
			
			if (i==(icm.getMapSize()-1)) {
				System.out.print("}");
			}
			else {
				System.out.print("}, ");
			}			
			
		}		
		System.out.println("\n};\nPROGMEM const byte " + filename + "[" + imgHeight +"][" + arrayWidth + "] = {");
		
		for (int i=0; i<data.length; i++) {
			
			if (i%arrayWidth==0) {
				System.out.print("{ ");
			}
			
			
			int value = data[i];
			if (value < 0) {
				value += 256;
			}
			System.out.print("0x" + Integer.toHexString(value));
			/*
			if (i!=data.length-1) {
				System.out.print(", " );
			}
			if (i>0 && i%200==0) {
				System.out.println("");
			}
			*/
			
			if (i==data.length-1) {
				System.out.println(" }");
			}
			else if ((i+1)%arrayWidth==0) {
				System.out.println(" },");
			}
			else {
				System.out.print(", " );
			}
		}
		System.out.println("\n};\n\n");

		
		
	}
	  
	  
	}
	

}
