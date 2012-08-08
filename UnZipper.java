import java.io.*;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.*;

public class UnZipper {
	
	public static void unZip(File fileToUnZip, File destFolder) throws IOException{
		
		try{
			if(!destFolder.exists()){
				destFolder.mkdir();
			}
			
			ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToUnZip));
			ZipEntry ze = zis.getNextEntry();
			
			while(ze!=null){
				
				String fileName = ze.getName();
				File newFile = new File(destFolder + File.separator + fileName);
				
				System.out.println("file unzip : "+ newFile.getAbsoluteFile());
				
				//create all non exists folders
				//else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				
				FileOutputStream fos = new FileOutputStream(newFile);             
				
				/*int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}*/
				IOUtils.copy(zis, fos);
				
				fos.close();   
				ze = zis.getNextEntry();
			}
			
			zis.closeEntry();
			zis.close();
			
		}	
		catch(IOException ex){
			throw ex;
		}
	}
}