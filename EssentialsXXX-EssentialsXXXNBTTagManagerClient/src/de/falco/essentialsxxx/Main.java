package de.falco.essentialsxxx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			Socket so = new Socket("localhost", 2233);
			
			if(so.isConnected()) {
				System.out.println("connect to server");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(so.getInputStream()));
				PrintWriter writer = new PrintWriter(so.getOutputStream());
				
				BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter w = new PrintWriter(System.out);
				
				w.write("select option: registertable or wantnumbers addoldnumbers\n");
				w.flush();

				String option = r.readLine();
				
				if(option.equals("registertable")) {
					
					writer.write("registertable\n");
					writer.write("localhost\n");
					writer.write("3306\n");
					writer.write("ec2\n");
					writer.write("root\n");
					writer.write("\n");
					writer.write("NBTTagCompound\n");
					
					writer.flush();
					
					System.out.println("send data");
					
					
				}else if(option.equals("wantnumbers")) {
					
					writer.write("wantnumbers\n");
					
					writer.write("NBTTagCompound\n");
					writer.write("100\n");
					writer.flush();
					
					String result = reader.readLine();
					
					w.write("result: " + result + "\n");
					w.flush();
					
					if(result != null) {
						for(String i : result.split("§")) {
							System.out.println(i + " ");
						}	
					}
					
				}else if(option.equals("addoldnumbers")) {
						
					writer.write("addoldnumbers\n");
					
					writer.write("NBTTagCompound\n");
					writer.write("3§4§5\n");
					writer.write("6§7§8\n");
					writer.flush();
					
				}
				
				System.out.println("stop programm");
				
				so.close();
				reader.close();
				writer.close();
				
			}else {
				System.out.println("couldnt connect");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
