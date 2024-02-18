<h1 align="center">mongoteste</h1>

<p align="center">Aplicação estudo de mongoDB em cluster</p>

Sobre
=================

	Apenas implementações de teste para exercício de crud em mongoDB;


Tabela de conteúdo
=================
<!--ts-->
   * [Sobre](#Sobre)
   * [Tabela de Conteúdo](#tabela-de-conteúdo)
   * [Iniciar App](#iniciar-app)
   * [Utilização/documentação](#utilização-documentação)
   * [Testes](#testes)
   * [Arquitetura](#arquitetura)
<!--te-->

Iniciar app
=================

	Iniciar banco de dados com docker-compose
		no dockerdb
		- executar docker-compose -f dc-svr-srd.yml up
		- após subir os servidores, em outro terminal, executar sh initsvrsrd.sh para configurar os servidores e partições
		- executar docker-compose -f dc-router.yml up
		- após subir o router, em outro terminal, executar sh initrouter.sh para configurar o roteador e inciar o banco
		
	Iniciar aplicação
		- executar mvn spring-boot:run no diretório da aplicação (java 17)
		- ou startar pela IDE


Utilização/documentação
=================

	- Após subir a aplicação, pode ser cadastrado um parquímetro
		curl --location 'http://localhost:8011/parkingmeter' \
		--header 'Content-Type: application/json' \
		--data '{
			"street": "Rua Teodoro Sampaio",
			"number": 567,
			"zipCode": "05405-000",
			"city": "São Paulo",
			"uf": "SP"
		}'
		
	- Cadatrar uma reserva de estacionamento
		curl --location 'http://localhost:8011/parking/order' \
		--header 'Content-Type: application/json' \
		--data '{
			"carPlate": "GAL2C20",
			"parkingTime": 120,
			"payment": "DEBIT",
			"parkingmeter":{
				"code": ${codigoDoParquuimetroResponseDaRequestAnterior},
				"street": "Rua Teodoro Sampaio",
				"number": 567,
				"zipCode": "05405-000",
				"city": "São Paulo",
				"uf": "SP"
			}
		}'
		
	- Busca todas as reservas de estacionamento
		curl --location 'http://localhost:8011/parking'
	
	- Busca as reservas por id de parquímetro e data de expiração da reserva maior igual
		curl --location 'http://localhost:8011/parking/order/65d25c1972f6f90f0ece74aa?dateFinal=2024-02-17T15%3A20%3A00'
		
	- Busca reservas de uma placa de veículo e data de expiração da reserva maior igual
		curl --location 'http://localhost:8011/parking/order/active/GAL2C20?dateFinal=2024-02-17T18%3A15%3A17'
		
	
Testes
=================
	Não foram implementados testes unitários
	

Arquitetura
=================

<img src="arq_mongoteste.jpg">
