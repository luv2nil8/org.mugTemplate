package org.mugTemplate;

import java.nio.file.Paths;

import javax.swing.JComboBox;

import org.json.JSONArray;

public class AgentBox extends JComboBox<String>{
	

	private static final long serialVersionUID = 1L;
	private Handler handler;
	
	
	public AgentBox( Handler handler){
		this.handler = handler;
		
		System.out.println("Current Directory: " + Paths.get(".").toAbsolutePath().normalize().toString());
		String baseRestURL = "https://goisn.net/nookandkrannyhomeinspections/rest/";
		String username = "hgardner";
		String password= "nook1018";
		
		JSONService ISN = new JSONService(baseRestURL, username, password);
		JSONArray agentsJSON = ISN.getAgents().getJSONArray("agents");
		//System.out.println(agentsJSON.toString());
		
	
		int j = 0;	
		
		/*
		for(int i = 0; agentsJSON.length() > i; i++)
		{
			if(j<20){
				String agentID = new JSONObject(agentsJSON.get(i).toString()).get("id").toString();
				JSONObject agentInfo = ISN.getAgentByID(agentID).getJSONObject("agent");
				if (agentInfo.getString("img").equals("https://dlil96nns7nd5.cloudfront.net/images/missing-agent-photo.jpg")){
					System.out.println("No Image");
					
				}else{
					String first = agentInfo.getString("first");
					String last = agentInfo.getString("last");
					String img = agentInfo.getString("img");
					System.out.println(first+" "+last+"\n"+img);
					try{
					 URL url = new URL(img);
					 InputStream in = new BufferedInputStream(url.openStream());
					 ByteArrayOutputStream out = new ByteArrayOutputStream();
					 byte[] buf = new byte[1024];
					 int n = 0;
					 while (-1!=(n=in.read(buf)))
					 {
					    out.write(buf, 0, n);
					 }
					 out.close();
					 in.close();
					 byte[] response = out.toByteArray();
					 
					 FileOutputStream fos = new FileOutputStream(Paths.get(".").toAbsolutePath().normalize().toString()+"//agentPics//"+first+" "+last+".jpg");
					 fos.write(response);
					 fos.close();
					}catch(IOException e){
						
					}
					
					Properties prop = new Properties();
					prop.setProperty(first+" "+last, Paths.get(".").toAbsolutePath().normalize().toString()+"//agentPics//"+first+" "+last+".jpg");
					try {
						AgentButton.updateProps(prop);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//AgentBox.addItem(agentInfo.getString("first")+" "+agentInfo.getString("last"));
					j++;
				}
			}else{
				continue;
			}
		}
		*/
		System.out.println(CanvasImage.getAgentNames());
		this.setModel( CanvasImage.getAgentNames());

	}



}
