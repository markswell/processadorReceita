# processadorReceita
#### Projeto que visa processamento de dados em larga escala com Spring Batch.

#### Esse projeto visa processar um volume massivo de dados provenientes de um arquivo csv no seguinte formato:

agencia;conta;saldo;status 

0101;12225-6;100,00;A

### O arquivo é processado e gerado uma arquivo de saida no seguinte formato: 

agencia;conta;saldo;status;resultado

0101;12225-6;100,00;A;processado

## Execução

* Faça o download do porjeto no sua máquina e abra um terminal na pasta raiz do projeto.
* Execute o seguintes comandos: 
* ./mvnw clean package
* cd target/
* java -jar processadorReceita.jar CAMINHO_SEU_ARQUIVO_CSV


