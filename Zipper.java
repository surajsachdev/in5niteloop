import java.io.*;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.*;

public class Zipper {
	
	public Zipper(){
	
	}
	
	public static void zip(ArrayList<File> srcFiles, HashMap<File, String> srcFileMap, File outputFile) throws IOException{
		
		try{
			FileOutputStream fos = new FileOutputStream(outputFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for (File temp : srcFiles) {
				
				InputStream in = new FileInputStream(temp);
				ZipEntry ze= new ZipEntry(srcFileMap.get(temp));
				zos.putNextEntry(ze);
				
				/*int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}*/
				IOUtils.copy(in, zos);
				
				in.close();
				zos.closeEntry();				
			}
			
			//remember close it
			zos.close();
			
			//System.out.println("Done");
			
		}	
		catch(IOException ex){
			throw ex;
		}
	}	
}