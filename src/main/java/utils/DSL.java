package utils;

import java.util.Random;

import org.json.JSONObject;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DSL {

	private String body;
	private String param;
	private int id;
	public static String urlBase = "https://api.hunter.io";
	public static String recurso = "/v2/leads";
	public static String autorizacao = "api_key=04a087df62a592296cad8ad4b37f895986c88a47";
	public static Response response;
	public static Response firstResponse;

	public static RequestSpecification request;

	public int getId() {
		JSONObject responseBody = new JSONObject(response.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		return nodeData.getInt("id");
	}

	public int getFirstResponseId() {
		JSONObject responseBody = new JSONObject(firstResponse.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		return nodeData.getInt("id");
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public static Response getFirstResponse() {
		return firstResponse;
	}

	public static void setFirstResponse(Response firstResponse) {
		DSL.firstResponse = firstResponse;
	}

	public static String gerarAleatorio(int tam, String tipo) {
		char[] elementosMisto = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
				'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		char[] elementoAlfa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] conteudoGerado = new char[tam];
		int tamanhoArray = elementosMisto.length;
		int tamanhoArrayDominio = elementoAlfa.length;

		Random random = new Random();

		if (tipo == "nome") {
			for (int i = 0; i < tam; i++) {
				conteudoGerado[i] = elementoAlfa[random.nextInt(tamanhoArrayDominio)];
			}
		} else if (tipo == "email") {
			for (int i = 0; i < tam; i++) {
				conteudoGerado[i] = elementosMisto[random.nextInt(tamanhoArray)];
			}
		}

		return new String(conteudoGerado);
	}

}