package stepsDefinition;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DSL;

public class stepsDefinition_APILeads extends DSL {

	@Given("que sejam enviados todos os campos do request body preenchidos e preenchido o campo confidence_score com valor igual a {int}")
	public void que_sejam_enviados_todos_os_campos_do_request_body_preenchidos_e_preenchido_o_campo_confidence_score_com_valor_igual_a(
			Integer int1) {

		setBody("{\r\n  \"email\": \"" + gerarAleatorio(8, "email") + "@hotmail.com\",\r\n  \"first_name\": \""
				+ gerarAleatorio(6, "nome") + "\",\r\n  \"last_name\""
				+ ": \"Silva\",\r\n  \"position\": \"Analyst\",\r\n  \"company\": \"Accenture\",\r\n  \"company_industry\""
				+ ": \"Internet and Telecom\",\r\n  \"company_size\": \"1,000,000 employees\",\r\n  \"confidence_score\": "
				+ int1 + ",\r\n  \"website\""
				+ ": \"reddit.com\",\r\n  \"custom_attributes\": {\r\n    \"customer_id\": \"608\"\r\n  }\r\n}");
	}

	@When("executar uma solicitacao {string}")
	public void executar_uma_solicitacao(String metodo) {
		if (metodo.toUpperCase().contains("POST")) {
			DSL.response = DSL.request.body(getBody()).when().post(DSL.urlBase + DSL.recurso + "?" + DSL.autorizacao).andReturn();
		} else if (metodo.toUpperCase().contains("PUT")) {
			DSL.response = DSL.request.body(getBody()).when().put(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao)
					.andReturn();
		} else if (metodo.toUpperCase().contains("GET")) {
			DSL.response = DSL.request.body(getBody()).when().get(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao + "&" + getParam()).andReturn();
		} else if (metodo.toUpperCase().contains("PATCH")) {
			DSL.response = DSL.request.body(getBody()).when().patch(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao).andReturn();
		} else if (metodo.toUpperCase().contains("DELETE")) {
			DSL.response = DSL.request.body(getBody()).when().delete(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao).andReturn();
		} else {
			System.out.println("O valor selecionado nao corresponde a um metodo valido para a API");
			Assert.fail("O valor selecionado nao corresponde a um metodo valido para a API");
		}
	}

	@Then("deve ser recebido o codigo de retorno HTTP {int}")
	public void deve_ser_recebido_o_codigo_de_retorno_http_status_code(int codigo) {
		try {
			Assert.assertEquals(codigo, DSL.response.getStatusCode());
		} catch (Exception e) {
			System.out.println("O status code retornado nao esta correto. Esperado: "+codigo+" Retornado: "+ DSL.response.getStatusCode());
			Assert.fail("O status code retornado nao esta correto. Esperado: " + codigo + " Retornado: " + DSL.response.getStatusCode());
		}
	}

	@And("o tempo de resposta deve ser menor que {long} segundos")
	public void o_tempo_de_resposta_deve_ser_menor_que_segundos(long long1) {
		try {
			DSL.response.then().time(Matchers.lessThan(long1), TimeUnit.SECONDS);
		} catch (Exception e) {
			Assert.fail("O tempo de resposta foi maior que " + long1 + " segundos");
		}
	}

	@And("a estrutura de response body esteja conforme a documentacao")
	public void a_estrutura_de_response_body_esteja_conforme_a_documentacao() {
		JSONObject responseBody = new JSONObject(DSL.response.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		
		try {
			
		String id = Integer.toString(nodeData.getInt("id"));
		Assert.assertTrue("Campo id nao esta presente no body",id.matches("^\\d+$"));
		
		String email = nodeData.getString("email");
		Assert.assertTrue("Campo email nao esta presente no body",email.matches("^\\S+@\\S+\\.\\S+$"));

		Object firstName = nodeData.get("first_name");
		Assert.assertTrue("Campo first_name nao esta presente no body",firstName.toString().matches("^\\S*|\\B$"));

		Object lastName = nodeData.get("last_name");
		Assert.assertTrue("Campo last_name nao esta presente no body",lastName.toString().matches("^\\S+|\\B$"));
		
		Object position = nodeData.get("position");
		Assert.assertTrue("Campo position nao esta presente no body",position.toString().matches("^\\S+|\\B$"));

		Object company = nodeData.get("company");
		Assert.assertTrue("Campo company nao esta presente no body",company.toString().matches("^\\S+|\\B$"));
		
		Object companyIndustry = nodeData.get("company_industry");
		Assert.assertTrue("Campo company_industry nao esta presente no body",companyIndustry.toString().matches("^.*|\\B$"));
		
		Object companySize = nodeData.get("company_size");
		Assert.assertTrue("Campo company_size nao esta presente no body",companySize.toString().matches("^.*|\\B$"));
		
		Object confidence_score = Integer.toString(nodeData.getInt("confidence_score"));
		Assert.assertTrue("Campo confidence_score nao esta presente no body",confidence_score.toString().matches("^\\d+$"));

		Object website = nodeData.get("website");
		Assert.assertTrue("Campo website nao esta presente no body",website.toString().matches("^\\S+|\\B$"));
		
		Object countryCode = nodeData.get("country_code");
		Assert.assertTrue("Campo country_code nao esta presente no body",countryCode.toString().matches("^\\S+|\\B$"));
		
		Object source = nodeData.get("source");
		Assert.assertTrue("Campo source nao esta presente no body",source.toString().matches("^\\S+|\\B$"));

		Object linkedinUrl = nodeData.get("linkedin_url");
		Assert.assertTrue("Campo linkedin_url nao esta presente no body",linkedinUrl.toString().matches("^\\S+|\\B$"));
		
		Object phoneNumber = nodeData.get("phone_number");
		Assert.assertTrue("Campo phone_number nao esta presente no body",phoneNumber.toString().matches("^\\S+|\\B$"));
		
		Object twitter = nodeData.get("twitter");
		Assert.assertTrue("Campo twitter nao esta presente no body",twitter.toString().matches("^\\S+|\\B$"));
		
		Object notes = nodeData.get("notes");
		Assert.assertTrue("Campo notes nao esta presente no body",notes.toString().matches("^\\S+|\\B$"));
		
		Object sendingStatus = nodeData.get("sending_status");
		Assert.assertTrue("Campo sending_status nao esta presente no body",sendingStatus.toString().matches("^\\S+|\\B$"));
		
		Object lastActivityAt = nodeData.get("last_activity_at");
		Assert.assertTrue("Campo last_activity_at nao esta presente no body",lastActivityAt.toString().matches("^\\S+|\\B$"));
		
		Object lastContactedAt = nodeData.get("last_contacted_at");
		Assert.assertTrue("Campo last_contacted_at nao esta presente no body",lastContactedAt.toString().matches("^\\S+|\\B$"));
		
		Object verification = nodeData.get("verification");
		Assert.assertTrue("Campo verification nao esta presente no body",verification.toString().matches("^\\S+|\\B$"));
		

		JSONObject nodeLeadsList = nodeData.getJSONObject("leads_list");
		
		String leadsListId = Integer.toString(nodeLeadsList.getInt("id"));
		Assert.assertTrue("Campo id do leads_list nao esta presente no body",leadsListId.matches("^\\d+"));

		String name = nodeLeadsList.getString("name");
		Assert.assertTrue("Campo name nao esta presente no body",name.matches("^.*|\\B$"));
						
		Object leadsCount = nodeLeadsList.get("leads_count");
		Assert.assertTrue("Campo leads_count nao esta presente no body",leadsCount.toString().matches("^\\d+"));

		} catch (Exception e) {
			 Assert.fail("O response body nao esta de acordo com a documentacao");
		}

	}
	
	@Given("que sejam enviados apenas os campos obrigatorios do request body preenchidos e preenchido o campo confidence_score com valor igual a {int}")
	public void que_sejam_enviados_apenas_os_campos_obrigatorios_do_request_body_preenchidos_e_preenchido_o_campo_confidence_score_com_valor_igual_a(Integer int1) {

		setBody("{\r\n  \"email\": \""+gerarAleatorio(8,"email")+"@yahoo.com\",\r\n  \"confidence_score\": "+int1+"\r\n}");
	}
	
	@Given("que sejam enviados apenas os campos obrigatorios do request body preenchidos e o campo position com valor igual a {string}")
	public void que_sejam_enviados_apenas_os_campos_obrigatorios_do_request_body_preenchidos_e_o_campo_position_com_valor_igual_a(String position) {

		setBody("{\r\n    \"position\": \""+position+"\"\r\n}");
	}
	@When("executar uma solicitacao {string} para um id valido")
	public void executar_uma_solicitacao_para_um_id_valido(String metodo) {
		
		DSL.response = DSL.request.body(getBody()).when().get(DSL.urlBase + DSL.recurso + "?" + DSL.autorizacao).andReturn();
		JSONObject responseBody = new JSONObject(response.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		JSONArray leads = nodeData.getJSONArray("leads");
		JSONObject nodeLeads = leads.getJSONObject(leads.length() - 1);
		int lastId = nodeLeads.getInt("id");
		
		if (metodo.toUpperCase().contains("POST")) {
			DSL.response = DSL.request.body(getBody()).when().post(DSL.urlBase + DSL.recurso + "?" + DSL.autorizacao).andReturn();
		} else if (metodo.toUpperCase().contains("PUT")) {
			DSL.response = DSL.request.body(getBody()).when().put(DSL.urlBase + DSL.recurso + "/" + lastId + "?" + DSL.autorizacao)
					.andReturn();
		} else if (metodo.toUpperCase().contains("GET")) {
			DSL.response = DSL.request.body(getBody()).when().get(DSL.urlBase + DSL.recurso + "/" + lastId + "?" + DSL.autorizacao + "&" + getParam()).andReturn();
		} else if (metodo.toUpperCase().contains("PATCH")) {
			DSL.response = DSL.request.body(getBody()).when().patch(DSL.urlBase + DSL.recurso + "/" + lastId + "?" + DSL.autorizacao).andReturn();
		} else if (metodo.toUpperCase().contains("DELETE")) {
			DSL.response = DSL.request.body(getBody()).when().delete(DSL.urlBase + DSL.recurso + "/" + lastId + "?" + DSL.autorizacao).andReturn();
		} else {
			System.out.println("O valor selecionado nao corresponde a um metodo valido para a API");
			Assert.fail("O valor selecionado nao corresponde a um metodo valido para a API");
		}
	}
	
	@And("o registro deve ser atualizado")
	public void o_registro_deve_ser_atualizado() {

		response = request.body(getBody()).when().get(urlBase + recurso + "?" + autorizacao + "&" + getParam())
				.andReturn();
		JSONObject responseBody = new JSONObject(response.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		JSONArray leads = nodeData.getJSONArray("leads");
		JSONObject nodeLeads = leads.getJSONObject(leads.length() - 1);
		int lastId = nodeLeads.getInt("id");
		
		
		response = request.body(getBody()).when().get(urlBase + recurso + "/" + lastId + "?" + autorizacao + "&" + getParam())
				.andReturn();
		
		
		String id = Integer.toString(nodeLeads.getInt("id"));
		Assert.assertTrue("Campo id nao esta presente no body",id.matches("^\\d+$"));
		
		
	}
	
	

}
