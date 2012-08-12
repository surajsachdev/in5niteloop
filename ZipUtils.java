package in.scribebook.utils

import java.io.*;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.*;

public class ZipUtils {
	
	public static void zip(ArrayList<File> srcFiles, HashMap<File, String> srcFileMap, File outputFile) throws IOException{
		
		try{
			FileOutputStream fos = new FileOutputStream(outputFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			for (File temp : srcFiles) {
				if (!temp.exists()) {
					throw new IOException("Error: The file '" + temp + "' does not exist");
				}
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
			System.out.println(ex.getMessage());
		}
	}
	
	public static void unZip(File fileToUnZip, File destFolder) throws IOException{
		
		try{
			if(!destFolder.exists()){
				throw new IOException("Error: The Folder '" + destFolder + "' does not exist");
			}
			
			ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToUnZip));
			ZipEntry ze = zis.getNextEntry();
			
			while(ze!=null){
				
				String fileName = ze.getName();
				File newFile = new File(destFolder + File.separator + fileName);
				
				//System.out.println("file unzip : "+ newFile.getAbsoluteFile());
				
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
			System.out.println(ex.getMessage());
		}
	}
}