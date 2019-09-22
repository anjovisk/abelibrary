# abelibrary
Exercício de arquitetura de backend e microsserviços

### 11 Regras utilizadas para desenho da API
##### APIs organizadas ao longo de recursos
As APIs foram organizadas com base nos recursos: livros, carrinho de compras e pedidos.
##### APIs padronizadas
As APIs foram padronizadas, utilizando substrantivos no plural para URIs que fazem referência à coleções e singular para as demais. Exemplo:
- /v1/public/books
- /v1/public/orders/{orderId}/delivery
##### APIs projetadas para mapear entidades de negócio e suas operações
- /v1/public/books/{isbn}/comments
- /v1/public/baskets/{userId}/payment
##### APIs simples
coleção/item/coleção: 
- /v1/public/books/{isbn}/comments
##### Atualização em lote para operações complexas
A API de pagamentos recebe diversas informações para, em uma única operação, realizar o pagamento, criar o pedido e configurar a entrega.
##### Padrão ISO 8601 utilizado para os atributos data/hora
Os recursos que possuem atributos de data/hora (pagamento, pedido e entrega) utilizam o formato yyyy-MM-ddTHH:mm:ss.
##### APIs documentadas
As APIs foram devidamente documentadas utilizando o Swagger. Para consultar a documentação acesse http://x.x.x.x:porta/swagger-ui.html
##### Utilizar o protocolo HTTPS/SSL
O servidor de aplicações deve ser configurado com um certificado válido para permitir acesso às APIs utilizando o protocolo HTTPS.
##### APIs versionadas
As APIs foram versionadas. Todas elas ainda se encontram na versão v1. Quando necessário, a nova versão ficará disponível em uma nova URI (Ex.: /v1/public/books -> /v2/public/books). A versão anterior ainda continuará acessível por tempo indeterminado.
##### Estabelecer paginação para as coleções com grandes volumes de dados
 coleções de livros e resenhar são obtidas parcialmente através de páginas. Além dos resultados, a API retorna a página atual, a quantidade total de itens, a quantidade de itens retornados e a quantidade de itens ignorados (offset).
##### Utilização dos códigos HTTP
 códigos abaixo foram utilizados:
- 200 (OK) Quando os recursos são encontrados com sucesso.
  - GET /v1/public/books
  - GET /v1/public/books/{isbn}
- 201 (Created) A solicitação foi bem sucedida e um novo recurso foi criado.
  - POST /v1/public/books
- 404 (Not Found) O arquivo requisitado não pode ser encontrado.
  - POST /v1/public/books/{isbn}
- 405 (Method Not Allowed) O método especificado na requisição não é permitido.
  - DELETE /v1/public/baskets/{userId}/payment
