import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Zipper {
	
	public Zipper(ArrayList<File> a, HashMap<File, String> h){
	
		zip(a, h);
	
	}
	
	private void zip(ArrayList<File> al, HashMap<File, String> hm){
		
		try{
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream("./MyFile.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for (File temp : al) {
				
				FileInputStream in = new FileInputStream(temp);
				zos.putNextEntry(new ZipEntry(hm.get(temp)));
				
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				
				in.close();
				zos.closeEntry();				
			}
			
			//remember close it
			zos.close();
			
			System.out.println("Done");
			
		}	
		catch(IOException ex){
				ex.printStackTrace();
		}
	}
}