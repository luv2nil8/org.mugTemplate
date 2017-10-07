package org.mugTemplate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONObject;

public abstract class CanvasImage {
	
	protected int x, y;
	protected int Width, Height;
	protected Image image;
	protected Image scaled;
	protected ID id;
	public boolean imageLocal;
	
	public CanvasImage(int x, int y, int Width, int Height, ID id){
		this.x = x;
		this.y = y;
		this.Width = Width;
		this.Height = Height;
		this.id= id;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public void clear(){
		image = null;
		scaled = null;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
	public void setWidth(int Width){
		this.Width = Width;
	}
	public void setHeight(int Height){
		this.Height = Height;
	}

	public void setID(ID id){
		this.id = id;
	}
	public ID getID(){
		return this.id;
	}
	
	public Image getImage() {
		return this.image;
	}
		
	public double getScaleFactor(int base, int height) {

        double dScale = 1;
        if (base > height | base < height ) {

            dScale = (double) height / (double) base;

        } else {
        	//Scale is 1 to 1 already
        }

        return dScale;

    }
	public void addAgent(File file){
		
		String dir = Paths.get(".").toAbsolutePath().normalize().toString();
		JTextField first = new JTextField();
		JTextField last = new JTextField();
		final JComponent[] inputBox = new JComponent[] {
				new JLabel("Enter the Agent's name here. \n Make sure It's spelled 100% Correct!"),
				new JLabel("First"),
				first,
				new JLabel("Last"),
				last
		};
		
		CanvasImage agent = this;
		agent.image = null;
		while (agent.image == null)
		{
			//if an agent pic hasn't been picked, open a new file chooser
		    //open a Dialogue to ask for the Agent name
			int result = JOptionPane.showConfirmDialog(null,  inputBox, "Agent Name", JOptionPane.PLAIN_MESSAGE);

		    if (result == JOptionPane.OK_OPTION) {
			    System.out.println("You entered " +
			            first.getText() + ", " +
			            last.getText());
			} else {
			    System.out.println("User canceled / closed the dialog, result = " + result);
			    return;
			}
		    
		    if(file == null){
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & PNG Images", "jpg","JPG","jpeg" ,"PNG", "png");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showDialog(null, "Choose Agent Picture");
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			       
				file = chooser.getSelectedFile();

			    }else if( returnVal == JFileChooser.CANCEL_OPTION){
			    	//cancel is hit, nothing is done.
			    	return;
			    }
		
		    }

			    //copy it to the project directory
		    try {
				this.fitImage(ImageIO.read(file));
		    	new File(dir+"\\agentPics\\").mkdirs();
				Files.copy(file.toPath(), Paths.get(dir+"\\agentPics\\"+first.getText() + " " +
			            last.getText()+"."+getFileExtension(file)));
				System.out.println(Paths.get(dir+"\\agentPics\\"+file.getName().toString()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    

		    //update the properties file
		    Properties prop = new Properties();
			prop.setProperty(first.getText()+" "+last.getText(), file.toString());
			try {
				updateProps(prop);
				agent.imageLocal = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			    //TODO

		}
	}
	public static void updateProps(Properties prop) throws IOException
	{
        OutputStream out = null;

		Properties loaded = loadProp();
		loaded.putAll(prop);
        File f = new File("agents.properties");
        out = new FileOutputStream( f );
        loaded.store(out, null);

		if (out != null) {
				out.close();
		}
		
	}
	public static Properties loadProp() throws IOException {
		FileInputStream file;
		file = new FileInputStream("agents.properties");
		Properties prop = new Properties();
		prop.load(file);
		
		return prop;
	}
	public static BufferedImage getImageFromKey(String key) throws IOException {
		Properties agents= loadProp();
		File f = new File(agents.getProperty(key));
		BufferedImage photo = ImageIO.read(f);
		return photo;
	}

	static ComboBoxModel<String> getAgentNames()
	{
		JSONObject JSON = null;
		// load a properties file
		try 
		{
			//Tryto find the property file and parse as a JSON for easy use.
			JSON = new JSONObject(loadProp());
			System.out.println("Prop Found");
		}
		catch (IOException e1) 
		{
	        Properties props = new Properties();
	        OutputStream out = null;
			System.out.println("Not Found");
			File f = new File("agents.properties");
	        try {
				out = new FileOutputStream( f );
		        props.store(out, null);
				JSON = new JSONObject(loadProp());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// get the property value and print it out
        String[] string = {"Add an Agent"};
        DefaultComboBoxModel<String> contingency = new DefaultComboBoxModel<String>(string);
		if(JSONObject.getNames(JSON) == null || JSONObject.getNames(JSON).equals(null) )
			{return contingency;}
		String[] sortedNames = JSONObject.getNames(JSON);
		Arrays.sort(sortedNames);
		return new DefaultComboBoxModel<String>(sortedNames);

	}


    protected void fitImage(Image image){
    	this.image = image;
    	int imgWidth =image.getWidth(null);
    	System.out.println(imgWidth);
    	int imgHeight =image.getHeight(null);
    	System.out.println(imgHeight);
    	BufferedImage tempImage;
    	double containerAspect = getScaleFactor(Width, Height);
    	System.out.println(containerAspect);
    	double imageAspect = getScaleFactor(imgWidth, imgHeight);
    	System.out.println(imageAspect);
    	System.out.println("Height= "+Height);
    	System.out.println("Width= "+Width);

    	if (imageAspect < containerAspect ){
    		//image is proportionally wider than container fill to height
        	System.out.println("Src is Wider");

    		tempImage = toBufferedImage(image.getScaledInstance((int)( Height / imageAspect),Height, Image.SCALE_SMOOTH));
        	scaled = tempImage.getSubimage((tempImage.getWidth() - Width )/2 ,  0, Width, Height);

    	} else if (imageAspect > containerAspect){
    		//image is proportionally taller than containter fill to width
        	System.out.println("Src is Taller");

    		tempImage = toBufferedImage(image.getScaledInstance(Width,(int) ( Width * imageAspect),Image.SCALE_SMOOTH));
        	scaled = tempImage.getSubimage( 0 , (tempImage.getHeight()-Height )/2 , Width, Height);

    	}else{ 
    		scaled  = toBufferedImage(image.getScaledInstance(Width,(int) ( Width * imageAspect),Image.SCALE_SMOOTH));;
    	}	

    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    
}
    


