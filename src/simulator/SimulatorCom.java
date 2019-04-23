package simulator;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class SimulatorCom {
	private ConcurrentHashMap<String, Double> varMap;
	private String[] paths;
	private Thread ds = null;
	private ServerSocket srv = null;
	private Socket clientSock = null;
	private Socket connectSock = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private final int NULL_BUFFER_SIZE = 1024;
	private byte[] nullBytes = new byte[NULL_BUFFER_SIZE]; 

	
	public SimulatorCom(String[] paths) {
		this.paths = paths;
		this.varMap = new ConcurrentHashMap<String, Double>();
		for(String s : this.paths)
			this.varMap.put(s, 0.0);
	}
	public void openDataServer(int port, int freq) throws IOException {
		this.srv = new ServerSocket(port);
		this.clientSock = srv.accept();
		this.br = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
		this.ds = new Thread(()-> {
			try {
				String line = getLineFromServer();
				while(line != null) {
					String[] vals = line.split(",");
					if(vals.length == this.paths.length) {
						for(int i = 0; i < vals.length; i++) {
							this.varMap.put(this.paths[i], Double.parseDouble(vals[i]));
						}
					}
					Thread.sleep((long)(1000/freq));
					line = getLineFromServer();
				}
			} catch(IOException | InterruptedException e) {
				
			}
				
		});
		ds.start();
	}
	public void connect(String ip, int port) throws IOException {
		this.connectSock = new Socket(ip, port);
		this.pw = new PrintWriter(this.connectSock.getOutputStream());
	}
	
	private synchronized String getLineFromServer() throws IOException {
		if (this.br != null)
			return this.br.readLine();
		return null;
	}
	
	public synchronized void disconnect() {
		if(this.pw != null) {
			this.pw.print("bye\r\n");
			this.pw.flush();
			closeResource(this.pw);
			this.pw = null;
		}
		if(this.br != null) {
			closeResource(this.br);
			br = null;
		}
		if(this.clientSock != null) {
			closeResource(this.clientSock);
			this.clientSock = null;
		}
		if(this.connectSock != null) {
			closeResource(this.connectSock);
			this.connectSock = null;
		}
		if(this.srv != null) {
			closeResource(this.srv);
			this.srv = null;
		}
			
	}
	public Double getVal(String s) {
		return this.varMap.get(s);
	}
	public void setVal(String path, Double d) {
		this.varMap.put(path, d);
		this.pw.print("set " + path + " " + d.toString() + "\r\n");
		this.pw.flush();
		try {
			while (this.connectSock.getInputStream().available() > 0)
				this.connectSock.getInputStream().read(this.nullBytes);
		} catch (IOException e) {}
		
	}
	public boolean containsVal(String path) {
		return this.varMap.containsKey(path);
	}
	private void closeResource(Closeable c) {
		try {
			c.close();
		} catch (IOException e) {}
	}
}
