`http://localhost:8080/api/produtos`

JSON POST

`
{
"nome": "Produto Teste",
"preco": 10.99
}
`

Utilizado postgres, para configurar colocar em application.properties suas configurações 

`spring.datasource.url=jdbc:poshttp://localhost:8080/api/produtostgresql://localhost:5432/ecommerce`

`spring.datasource.username=postgres`

`spring.datasource.password=admin`

E na classe DatabaseInitializer também é necessario

    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private static final String DATABASE_NAME = "ecommerce";

