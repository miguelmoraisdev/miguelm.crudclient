# Aplicação web com dashboard de vendas

O projeto consiste em uma aplicação Backend contendo um CRUD completo de web services REST para acessar um recurso de clientes contendo as cinco operações básicas:

- Busca paginada de recursos;
- Busca de recurso por id;
- Inserir novo recurso;
- Atualizar recurso;
- Deletar recurso


A aplicação foi estruturada no padrão camadas da segunte forma: 

:black_circle: Entidades e Camada de Acesso a Dados;

:black_circle: Camada de Serviços :heavy_plus_sign: DTO(Data Transfer Objects);

:black_circle: Controladores Rest.

O framework Spring foi utilzado para o desenvolvimento do backend da aplicação com as ferramentas:

:heavy_check_mark: Spring JPA para mapeamento de objeto relacional;

:heavy_check_mark: Spring H2 Database para simular banco de dados relacional durante o desenvolvimento;

O Postman foi utilizado para testar as consultas de clientes.
