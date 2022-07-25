# MyCryptoTrader

## Sobre o projeto
Este projeto foi criado a partir do projeto base The Stock Trader desenvolvido no curso de VueJS 2 da Cod3r. Transformei o projeto base que era um simulador de compra e venda de ações em um gerenciador de portfólio de criptomoedas. 

## Por que?
Acredito que a melhor forma de aprender e aperfeiçoar novas tecnologias é desenvolvendo algo próprio. A partir de um projeto simples em VueJS criado no curso da Cod3r, decidi ampliar a ideia do frontend para um projeto maior e mais complexo, além de também desenvolver minha própria api rest.  

## Funcionalidades

- Criar uma nova conta ou fazer login com uma conta já existente. 

- Listagem das top 100 moedas da Coinmarketcap para consulta. 

- Criar e editar portfólio (nome)

- Comprar criptomoeda

- Vender criptomoeda

- Resumo de todas as operações com informações sobre: dinheiro investido, saldo atual de acordo com o preço das moedas, lucro em portcentagem, lucro em dinheiro. 

- Resumo do portfolio filtrado por moeda contendo informações como: saldo atual, quantidade de moeda comprada, valor investido, lucro em porcentagem, lucro em dinheiro, entre outras. 

- Configurações
  - Atualizar lista das top 100 moedas da CoinmarketCap
  - Depositar fundos para conseguir simular as compras e vendas de criptomoedas
  - Deslogar

## Começando

- Para executar o frontend deste projeto na sua máquina local, você precisará ter um ambiente básico para executar apps em VueJS, [veja aqui](https://vuejs.org/guide/quick-start.html#without-build-tools)

- Para o backend você pode ler as instruções aqui (em breve)

### Instalando

**Cloning the Repository**

```
$ git clone https://github.com/leonardoamaral92/mycryptotrader.git

$ cd mycryptotrader
```

**Installing dependencies**

```
$ npm install
```

### Executando


```
$ npm serve run
```

## Tecnologias e conhecimentos utilizados

### Backend
- Java + [Spring Framework](https://spring.io)
- [PostgreSQL](https://www.postgresql.org)
- [Redis](https://redis.io)
- [Flyway](https://flywaydb.org)
- [auth0/java-Jwt](https://github.com/auth0/java-jwt)
- [OpenAPI](https://springdoc.org)

### Frontend
- [VueJS](https://vuejs.org)
- [vuex](https://vuex.vuejs.org)
- [axios](https://axios-http.com/ptbr/docs/intro)
- [vuetify](https://vuetifyjs.com/en/)
- [vue-toasted](https://www.npmjs.com/package/vue-toasted#install-using-npm)
- [vue-router](https://router.vuejs.org)
