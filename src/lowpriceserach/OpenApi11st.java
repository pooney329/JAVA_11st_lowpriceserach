package lowpriceserach;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OpenApi11st {

	public static void main(String[] args) {
		try{
			
			// parsing할 url 지정(API 키 포함해서)
			String text = URLEncoder.encode("SAPPHIRE 라데온 RX 570 PULSE Optimized OC D5 4GB Dual-X", "UTF-8");
			String url = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=9f970fb1c7502e1184f90c361c3b9277&apiCode=ProductSearch&keyword="+text+"&sortCd=A";
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			// root tag 
			 Element root = doc.getDocumentElement();
			 NodeList n1 = root.getElementsByTagName("Product");
			
			// 파싱할 tag
			 List<OpenApi11stDTO> list = new ArrayList<OpenApi11stDTO>();			    	
	               for(int i=0; i<n1.getLength(); i++)
	               {	
	            	   if(i==20) {
	            		   break;
	            	   }
	                   Node bNode = n1.item(i);
	                   Element bElement = (Element)bNode;
	                   
	                   String code = bElement.getElementsByTagName("ProductCode").item(0).getTextContent();
	                   String name = bElement.getElementsByTagName("ProductName").item(0).getTextContent();
	                   String img = bElement.getElementsByTagName("ProductImage").item(0).getTextContent();
	                   int price = Integer.parseInt(bElement.getElementsByTagName("SalePrice").item(0).getTextContent());
	                   String delivery = bElement.getElementsByTagName("Delivery").item(0).getTextContent();
	                   String detailpageurl = bElement.getElementsByTagName("DetailPageUrl").item(0).getTextContent();
	                   String mallname ="11번가";
	                   list.add(new OpenApi11stDTO(price,delivery,detailpageurl,mallname));
	               }
	               OpenApi11stDTO [] api11stdtolist  = new OpenApi11stDTO[list.size()];
	               for(int i=0; i<list.size(); i++) {
	            	   api11stdtolist[i] = list.get(i);
	            	   
	               }
	              OpenApi11stDTO temp=null;
	              for(int i=0; i< api11stdtolist.length; i++) {
	            	  System.out.println(api11stdtolist[i].getPrice());
	              }
	              
	              for(int i=0; i< api11stdtolist.length; i++) {
	            	  for(int j=0; j<api11stdtolist.length-1; j++) {
	            		  if(api11stdtolist[j].getPrice() > api11stdtolist[j+1].getPrice()) {
	            			  temp=api11stdtolist[j];
		            		  api11stdtolist[j]=api11stdtolist[j+1];
		            		  api11stdtolist[j+1]=temp;
		            	  }
	            	  }
	              }
	              for(int i=0; i< api11stdtolist.length; i++) {
	            	  System.out.println(api11stdtolist[i].getPrice());
	              }
		}catch (Exception e){	
			e.printStackTrace();
		}	
		
		
	}
	public static void aa(String a ) {
		System.out.println(a);
	}

}
