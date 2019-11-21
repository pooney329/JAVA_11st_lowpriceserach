package lowpriceserach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class OpenApiNaver {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, ParseException {
		String clientId = "JTKGjInKqoITCH4Kxq8Q";
		String clientSecret = "tSs9rYoZci";
		String json = null;
		try {
			String text = URLEncoder.encode("삼성전자 860 EVO (250GB)", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + text + "&sort=sim&display=20";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			json = response.toString();
		} catch (Exception e) {
			System.out.println(e);
		}

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(json);

		JSONObject jsonObj = (JSONObject) obj;
		JSONArray jsonArray = (JSONArray) jsonObj.get("items");

		List<OpenApi11stDTO> list = new ArrayList<OpenApi11stDTO>();
		int sum = 0;
		for (int i = 0; i < jsonArray.size(); i++) {

			JSONObject jsonObj2 = (JSONObject) jsonArray.get(i);
			int price = Integer.parseInt((String) jsonObj2.get("lprice"));
			String title = (String) jsonObj2.get("title");
			String url = (String) jsonObj2.get("link");
			String delivery = null;
			String mallname = (String) jsonObj2.get("mallName");
			String producttype = (String) jsonObj2.get("productType");
			System.out.println(producttype);
			System.out.println(title);
			sum += price;

			list.add(new OpenApi11stDTO(price, delivery, url, mallname));

		}
		System.out.println(sum / jsonArray.size() + "원");

		OpenApi11stDTO[] api11stdtolist = new OpenApi11stDTO[list.size()]; // 배열로 리소스최대한 적게 잡아먹기

		for (int i = 0; i < list.size(); i++) { // List<>에있는 값을 api11stdtolist에 넣어주기
			api11stdtolist[i] = list.get(i);

		}

		OpenApi11stDTO temp = null;

		// 순서정렬 알고리즘
		for (int i = 0; i < api11stdtolist.length; i++) {
			System.out.println(api11stdtolist[i].getPrice());
		}
		System.out.println("------------------------------------------------");
		for (int i = 0; i < api11stdtolist.length; i++) {
			for (int j = 0; j < api11stdtolist.length - 1; j++) {
				if (api11stdtolist[j].getPrice() > api11stdtolist[j + 1].getPrice()) {
					temp = api11stdtolist[j];
					api11stdtolist[j] = api11stdtolist[j + 1];
					api11stdtolist[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < api11stdtolist.length; i++) {
			System.out.println(api11stdtolist[i].getMallname() + api11stdtolist[i].getPrice());
		}
	}

}
