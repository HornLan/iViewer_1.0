package model;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureFile {
	
	private String imageName;
	private File imageFile;
	private URL imageURL;
	
	public PictureFile(File imageFile) {
		this.imageFile = imageFile;
		imageName = imageFile.getName();
//		try {
//			imageURL = imageFile.toURI().toURL();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		if(imageName.equals("")) {
			imageName = imageFile.getPath();
		}
	}
	
    public PictureFile(String imagePath) {
    	this(new File(imagePath));
    }
    
    public PictureFile[] listPictures() {
		File[] files = this.imageFile.listFiles();
		if(files == null||files.length == 0) {
			return null;
		}
		PictureFile[] pictureFiles = new PictureFile[files.length];
		for(int i = 0; i<files.length;i++) {
			pictureFiles[i] = new PictureFile(files[i]);
		}
		return pictureFiles;
	}
    
    public URL toURL() {
    	return imageURL;
    }
    
    public boolean isPicture() {
    	if(imageName.toLowerCase().endsWith(".jpg")||
    	   imageName.toLowerCase().endsWith(".jpge")||
    	   imageName.toLowerCase().endsWith(".png")||
    	   imageName.toLowerCase().endsWith(".bmp")||
    	   imageName.toLowerCase().endsWith(".gif")	) {
    		return true;
    	}
    	return false;
    }
    
    public boolean isDirectory() {
    	return imageFile.isDirectory();
    }
    
    public boolean isFile() {
    	return imageFile.isFile();
    }
    
    public boolean isHidden() {
    	return imageFile.isHidden();
    }
    
    public long length() {
    	return imageFile.length();
    }
    
    public String toString() {
		return imageName;
	}   
    
 //------------------getting and setting-----------

    public File getImageFile() {
		return imageFile;
	}
    public String getImageName() {
		return imageName;
	}
}

