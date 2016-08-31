package hh.hh;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;
import java.io.OutputStream;

import java.io.File;

import java.io.InputStream;


public class SomeClass {

	public static void main(String... args) throws Exception {
	        try {
	            InputStream in = null;
	            File fileOut = null;
	            String osName = System.getProperty("os.name");
	            System.out.println(osName);
	            if(osName.startsWith("Windows")){
	                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
	                if(bitness == 32){
	                    System.out.println("32 bit detected");
	                    in = SomeClass.class.getResourceAsStream("/opencv/x86/opencv_java245.dll");
	                    fileOut = File.createTempFile("lib", ".dll");
	                }
	                else if (bitness == 64){
	                    System.out.println("64 bit detected");
	                    in = SomeClass.class.getResourceAsStream("/opencv/x64/opencv_java245.dll");
	                    fileOut = File.createTempFile("lib", ".dll");
	                }
	                else{
	                    System.out.println("Unknown bit detected - trying with 32 bit");
	                    in = SomeClass.class.getResourceAsStream("/opencv/x86/opencv_java245.dll");
	                    fileOut = File.createTempFile("lib", ".dll");
	                }
	            }
	            else if(osName.equals("Mac OS X")){
	                in = SomeClass.class.getResourceAsStream("/opencv/mac/libopencv_java245.dylib");
	                fileOut = File.createTempFile("lib", ".dylib");
	            }


	            OutputStream out = FileUtils.openOutputStream(fileOut);
	            IOUtils.copy(in, out);
	            in.close();
	            out.close();
	            System.load(fileOut.toString());
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to load opencv native library", e);
	        }
		
		
		
	
		
	}

}
