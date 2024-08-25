
# Design Patterns com Java: Dos Clássicos (GoF) ao Spring Framework

## Documentação do Projeto

### Sobre
Neste projeto vamos explorar o conceito de Padrões de Projeto na prática, 
implementando soluções que utilizem os padrões clássicos do GoF e as abstrações 
fornecidas pelo Spring Framework.

### Funcionalidades
Cadastro de Clientes: O projeto permite o registro de clientes com informações 
detalhadas. Ele integra a API ViaCEP para preencher automaticamente os campos 
de endereço com base no CEP fornecido, facilitando e agilizando o processo 
de cadastro.

---

### Tecnologias Usadas
### Projeto Spring Boot gerado via Spring Initializr.

Este projeto contém as seguintes dependências:    
  - Spring Data JPA
  - Validation
  - Spring web
  - H2 Database
  - OpenFeign

---

#### Utilização do jsonschema2pojo
Este projeto utiliza a ferramenta jsonschema2pojo para gerar classes Java à partir de arquivos JSON Schema.

Como utilizar:

 - Entre no site https://www.jsonschema2pojo.org/
 - Copie  e cole o Json
````bash
  Ex:Json API-ViaCep
     {
      "cep": "01001-000",
      "logradouro": "Praça da Sé",
      "complemento": "lado ímpar",
      "unidade": "",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP",
      "ibge": "3550308",
      "gia": "1004",
      "ddd": "11",
      "siafi": "7107"
     }
````
 - Coloque o nome do Package e o Class name
 - Mude o Source type para Json
 - Click em Preview
```bash
-----------------------------------com.example.Example.java-----------------------------------

package com.example;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Example {

private String cep;
private String logradouro;
private String complemento;
private String unidade;
private String bairro;
private String localidade;
private String uf;
private String ibge;
private String gia;
private String ddd;
private String siafi;
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

public String getCep() {
return cep;
}

public void setCep(String cep) {
this.cep = cep;
}

public String getLogradouro() {
return logradouro;
}

public void setLogradouro(String logradouro) {
this.logradouro = logradouro;
}

public String getComplemento() {
return complemento;
}

public void setComplemento(String complemento) {
this.complemento = complemento;
}

public String getUnidade() {
return unidade;
}

public void setUnidade(String unidade) {
this.unidade = unidade;
}

public String getBairro() {
return bairro;
}

public void setBairro(String bairro) {
this.bairro = bairro;
}

public String getLocalidade() {
return localidade;
}

public void setLocalidade(String localidade) {
this.localidade = localidade;
}

public String getUf() {
return uf;
}

public void setUf(String uf) {
this.uf = uf;
}

public String getIbge() {
return ibge;
}

public void setIbge(String ibge) {
this.ibge = ibge;
}

public String getGia() {
return gia;
}

public void setGia(String gia) {
this.gia = gia;
}

public String getDdd() {
return ddd;
}

public void setDdd(String ddd) {
this.ddd = ddd;
}

public String getSiafi() {
return siafi;
}

public void setSiafi(String siafi) {
this.siafi = siafi;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
```

#### Swagger

1. SwaggerConfig
- Esta classe define como o Swagger deve gerar e exibir a documentação da API.
- Inclui configurações como título, descrição, versão e outros detalhes
  que ajudam a documentar as APIs e fornecer uma interface gráfica (Swagger UI) para interagir com elas.
- O Swagger documenta todos os endpoints expostos pela aplicação, incluindo informações sobre os métodos
  HTTP (GET, POST, PUT, DELETE), parâmetros, respostas e códigos de status.
- Essa documentação é gerada automaticamente com base nas anotações em seus controladores e
  nas configurações definidas na classe SwaggerConfig.

#### Acesso ao Swagger UI

- A documentação da API está disponível através do Swagger UI.
- Você pode acessar a interface interativa para explorar e testar os endpoints da API usando o seguinte link:

```bash
  http://localhost:8080/swagger-ui/index.html
```
1. **Certifique-se de que a aplicação está em execução**: O Swagger UI está disponível somente quando a aplicação está em execução.
2. Se a aplicação estiver rodando em uma URL diferente ou porta diferente, ajuste o endereço conforme necessário.

###  Utilizando o Swagger

*Exemplos de Requisição*

- Abaixo estão exemplos de como enviar dados no corpo da requisição
  para os endpoints da API usando o Swagger UI.

  - Endpoint
    - **Descrição**: Cria um cadastro de clientes no banco de dados, utilizando a API ViaCEP 
      para buscar e preencher o endereço.
    - **Método**: POST /clientes.
    - **Corpo da Requisição:**
      ```json
      {
      "nome": "Silva Maria",
        "endereco": {
          "cep": "14801788"
        }
      }
      ```
  - Endpoint
    - **Descrição**: Atualiza um cadastro de clientes no banco de dados, através do ID do clientes.
    - **Método**: PUT /clientes.
    - **Corpo da Requisição:**
      ```json
      {
      "id": 2,
      "nome": "Silva Duarte Maria",
        "endereco": {
          "cep": "14835000"
        }
      }
      ```



### Utilizando do Postman

Para facilitar a integração e o teste das APIs, incluímos um arquivo de configuração do Postman (`postman-config.json`) no projeto. Este arquivo contém as coleções e requisições necessárias para testar a API.

#### Passos para Utilizar o Arquivo de Configuração do Postman

1. **Localizar o Arquivo JSON**

   O arquivo `postman-config.json` pode ser encontrado no diretório `src/main/resources`.


2. **Importar o Arquivo no Postman**


- Para importar o arquivo de configuração no Postman, siga estes passos:

  - **Abra o Postman**.

    1. Na barra lateral esquerda, clique em **"Import"**.

    2. Na janela de importação, você pode arrastar e soltar o arquivo `postman-config.json` diretamente na área indicada ou clicar em **"Upload Files"** para selecionar o arquivo a partir do seu sistema de arquivos.

    3. Após selecionar o arquivo, o Postman carregará as coleções e requisições definidas no arquivo. Você verá uma mensagem confirmando que o arquivo foi importado com sucesso.

    4. Acesse a aba **"Collections"** no Postman para ver as coleções importadas. Você pode agora executar as requisições e testar a API conforme necessário.

---
