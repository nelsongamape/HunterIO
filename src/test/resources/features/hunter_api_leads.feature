@APILeads
Feature: HunterIO_APILeads
  API com CRUD para Leads da HunterIO

  @Tc01
  Scenario: Requisicao de criacao de leads com todos os campos preenchidos
    Given que sejam enviados todos os campos do request body preenchidos e preenchido o campo confidence_score com valor igual a <confidence_score>
    When executar uma solicitacao <metodo>
    Then deve ser recebido o codigo de retorno HTTP <codigo>
    And o tempo de resposta deve ser menor que <tempo> segundos
		And a estrutura de response body esteja conforme a documentacao

     Examples: 
      |confidence_score|metodo   |codigo   |tempo  |
      |0               |"POST"   | 201     |5      |
      
@Tc02
  Scenario: Requisicao de criacao de leads somente com os campos obrigatorios preenchidos
    Given que sejam enviados apenas os campos obrigatorios do request body preenchidos e preenchido o campo confidence_score com valor igual a <confidence_score>
    When executar uma solicitacao <metodo>
    Then deve ser recebido o codigo de retorno HTTP <codigo>
    And o tempo de resposta deve ser menor que <tempo> segundos
		And a estrutura de response body esteja conforme a documentacao

     Examples: 
      |confidence_score|metodo   |codigo   |tempo  |
      |100               |"POST" | 201     |5      |
      
  @Tc03
  Scenario: Requisicao de atualizacao de leads apenas com os campos obrigatorios preenchidos
    Given que sejam enviados apenas os campos obrigatorios do request body preenchidos e o campo position com valor igual a <position>
    When executar uma solicitacao <metodo>
    Then deve ser recebido o codigo de retorno HTTP <codigo>
    And o tempo de resposta deve ser menor que <tempo> segundos
    And o campo <position> deve ser atualizado
   		
     Examples: 
     
   |position  |metodo   |codigo   |tempo  |
   |"Manager" |"PUT"    | 204     |5      |

  @Tc04
  Scenario: Requisicao de atualizacao de leads apenas com os campos obrigatorios preenchidos
    When executar uma solicitacao <metodo>
    Then deve ser recebido o codigo de retorno HTTP <codigo>
    And o tempo de resposta deve ser menor que <tempo> segundos
    And o leads deve ser excluido
		
     Examples: 
     
    |metodo   |codigo   |tempo  |
    |"DELETE" | 204     |5      |
      