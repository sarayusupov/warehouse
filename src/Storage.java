import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage {
	private String path;
	private String clients = "clients.csv";
	private String products = "products.csv";
	private HashMap<String, Double> productPrices;
	private static Storage storage_instance = null;
	
	
	public static Storage getInstance(String path) {
		if (storage_instance == null) {
			storage_instance = new Storage(path);
		}
		return storage_instance;
	}
	
	private Storage(String path) {
		this.path = path;
	}
	
	public int insertClient(Client client) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        String line = "";
	        File file = new File(this.path + "/" + this.clients);
	        if (file.exists()) {
	        	reader = new FileReader(this.path + "/" + this.clients);
		        br = new BufferedReader(reader);
		        String[] clientDetails;
		        String id;
		        
		        while ((line = br.readLine()) != null) {
		        	clientDetails = line.split(",");
		        	id = clientDetails[3].replaceAll("^\"|\"$", "");
		        	if (id.compareTo(client.getId()) == 0) {
		        		this.closeBuffersAndFiles(br, writer, reader);
		        		return 0;
		        	}
		        }
	        }

	        writer = new FileWriter(file, true);
	        writer.append(client.toString() + "\r\n");
	        this.closeBuffersAndFiles(br, writer, reader);
	        return 1;
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return -1;
		}
	}
	
	public boolean checkIfIDExists(String id) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        String line = "";
	        File file = new File(this.path + "/" + this.clients);
	        if (file.exists()) {
	        	reader = new FileReader(this.path + "/" + this.clients);
		        br = new BufferedReader(reader);
		        String[] clientDetails;
		        String clientId;
		        
		        while ((line = br.readLine()) != null) {
		        	clientDetails = line.split(",");
		        	clientId = clientDetails[3].replaceAll("^\"|\"$", "");
		        	if (id.compareTo(clientId) == 0) {
		        		this.closeBuffersAndFiles(br, writer, reader);
		        		return true;
		        	}
		        }
	        }
	        this.closeBuffersAndFiles(br, writer, reader);
	        return false;
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return false;
		}

	}
	
	public String getCompanyNameAndPhoneNumber(String id) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        String line = "";
	        File file = new File(this.path + "/" + this.clients);
	        if (file.exists()) {
	        	reader = new FileReader(this.path + "/" + this.clients);
		        br = new BufferedReader(reader);
		        String[] clientDetails;
		        String clientId;
		        
		        while ((line = br.readLine()) != null) {
		        	clientDetails = line.split(",");
		        	clientId = clientDetails[3].replaceAll("^\"|\"$", "");
		        	if (id.compareTo(clientId) == 0) {
		        		this.closeBuffersAndFiles(br, writer, reader);
		        		return clientDetails[4].replaceAll("^\"|\"$", "") + "," +  clientDetails[1].replaceAll("^\"|\"$", "");
		        	}
		        }
	        }
	        this.closeBuffersAndFiles(br, writer, reader);
	        return "";
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return "";
		}

	}
	

	public int updateClientCreditApproval(String id, boolean approval) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        String line = "";
	        reader = new FileReader(this.path + "/" + this.clients);
	        br = new BufferedReader(reader);
	        String[] clientDetails;
	        ArrayList<String> clients = new ArrayList<String>();
	        
	        while ((line = br.readLine()) != null) {
	        	line = this.removeQuotes(line);
	        	if (id.compareTo(line.split(",")[3]) == 0) {
	        		if (approval) {
	        			line += ",Credit Approved"; 
	        		} else {
	        			line += ",Credit Not Approved";
	        		}
	        	}
	        	clients.add(line);
	        }
	        br.close();
	        reader.close();
	        File file = new File(this.path + "/" + this.clients);
	        file.delete();
	        file = new File(this.path + "/" + this.clients);
	        writer = new FileWriter(file, true);
	        
	        for (String client: clients) {
	        	writer.append(client + "\r\n");
	        }
	        this.closeBuffersAndFiles(br, writer, reader);
	        return 1;
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return -1;
		}

	}
	
	public int insertPO(PO po) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        File file = new File(this.path + "/" + po.getPOID() + ".csv");
	        file.createNewFile();
	        writer = new FileWriter(file, true);
	        
	        writer.write("Status,Created" + "\r\n");
	        writer.write("Company Name," + po.getCompanyName() + "\r\n");
	        writer.write("Phone Number," + po.getPhoneNumber() + "\r\n");
	        
	        for (Order order: po.getOrders()) {
	        	writer.write(order.toString() + "\r\n");
	        }
	        this.closeBuffersAndFiles(br, writer, reader);
	        return 1;
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return -1;
		}

	}


	public int updatePOPrice(String POID, double price) {
		FileWriter writer = null;
		try {
	        File file = new File(this.path + "/" + POID + ".csv");
	        writer = new FileWriter(file, true);
	        writer.append("Total Price," + Double.toString(price) +"\r\n");
	        writer.close();
	        return 1;
		} catch (Exception e) {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return -1;
		}
	}

	public int updatePOPayment(String POID, boolean hasPayed) {
		FileWriter writer = null;
		try {
	        File file = new File(this.path + "/" + POID);
	        writer = new FileWriter(file, true);
	        if (hasPayed) {
	        	writer.append("Payed" + "\n\r");
	        } else {
	        	writer.append("Not payed" + "\n\r");
	        }
	        writer.close();
	        return 1;
		} catch (Exception e) {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return -1;
		}
	}

	public String getPOStatus(String POID) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        String status = "";
	        String companyName;
	        String phoneNumber;
	        reader = new FileReader(this.path + "/" + POID + ".csv");
	        br = new BufferedReader(reader);
	        status = br.readLine().split(",")[1].replaceAll("^\"|\"$", "");
	        companyName = br.readLine().split(",")[1].replaceAll("^\"|\"$", "");
	        phoneNumber = br.readLine().split(",")[1].replaceAll("^\"|\"$", "");
	        this.closeBuffersAndFiles(br, writer, reader);
	        return status + "\n" + companyName + "\n" + phoneNumber;
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return "The product order does not exist";
		}
	}
	
	public int updatePOStatus(String POID, String status) {
		BufferedReader br = null;
		FileReader reader = null;
		FileWriter writer = null;
		try {
	        String line = "";
	        reader = new FileReader(this.path + "/" + POID + ".csv");
	        br = new BufferedReader(reader);
	        String[] poDetails;
	        ArrayList<String> po = new ArrayList<String>();
	        line = br.readLine();
	        po.add("Status," + status);
	        while ((line = br.readLine()) != null) {
	        	line = this.removeQuotes(line);
	        	po.add(line);
	        }
	        br.close();
	        reader.close();
	        File file = new File(this.path + "/" + POID + ".csv");
	        file.delete();
	        file = new File(this.path + "/" + POID + ".csv");
	        writer = new FileWriter(file, true);
	        
	        for (String order: po) {
	        	writer.append(order + "\n");
	        }
	        this.closeBuffersAndFiles(br, writer, reader);
	        return 1;
		} catch (Exception e) {
			this.closeBuffersAndFiles(br, writer, reader);
			return -1;
		}

	}
	
	
	
	
	
	private void closeBuffersAndFiles(BufferedReader br, FileWriter writer, FileReader reader) {
		try {
			if (br != null) {
				br.close();
			}
			if (writer != null) {
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String removeQuotes(String line) {
		String[] stringArray = line.split(",");
		String s = "";
		for (int i = 0; i < stringArray.length; i++) {
			s += stringArray[i].replaceAll("^\"|\"$", "");
			if (i + 1 != stringArray.length) {
				s += ",";
			}
		}
		return s;
	}
	
	public Map<String, Double> getProductPrices() {
		if (this.productPrices == null) {
			this.productPrices = new HashMap<String, Double>();
			BufferedReader br = null;
			FileReader reader = null;
			FileWriter writer = null;
			try {
		        String line = "";
		        reader = new FileReader(this.path + "/" + this.products);
		        br = new BufferedReader(reader);
		        String[] productDetails;
		        
		        line = br.readLine();
		        line = br.readLine();
		        while ((line = br.readLine()) != null) {
		        	line = this.removeQuotes(line);
		        	productDetails = line.split(",");
		        	productPrices.put(productDetails[0],
		        			Double.valueOf(productDetails[1]));
		        }
		        this.closeBuffersAndFiles(br, writer, reader);
			} catch (Exception e) {
				this.closeBuffersAndFiles(br, writer, reader);
				this.productPrices = null;
			}
		}
		return this.productPrices;
	}

	
}
