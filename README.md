# 🚗 Sistema Automotivo — Gestão de Estoque de Veículos

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos (UniFECAF).

---

## 📁 Estrutura do Projeto

```
vehicle-system/
├── backend/
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/concessionaria/veiculos/
│       │   ├── model/         ← Entidades JPA (Veiculo, Marca, Modelo)
│       │   ├── repository/    ← Interfaces JpaRepository
│       │   ├── service/       ← Regras de negócio
│       │   └── controller/    ← Endpoints REST API
│       └── resources/
│           ├── application.properties
│           └── static/
│               └── index.html ← Frontend da aplicação
├── database/
│   └── schema.sql             ← Script MySQL (tabelas + dados de exemplo)
└── README.md
```

---

## ⚙️ Como executar

### 1. Banco de Dados (MySQL)
Abra o terminal e execute:
```bash
mysql -u root -p
```
Depois:
```sql
source /caminho/completo/para/database/schema.sql
```

### 2. Configurar a senha do banco
Abra o arquivo `backend/src/main/resources/application.properties` e edite:
```properties
spring.datasource.password=sua_senha_aqui
```

### 3. Rodar o Backend (Spring Boot)
Abra a pasta `backend/` no IntelliJ IDEA e rode o arquivo `VeiculosApplication.java`.

Ou pelo terminal:
```bash
cd backend
mvn spring-boot:run
```

### 4. Acessar o sistema
Com o backend rodando, abra o navegador e acesse:
```
http://localhost:8080
```

O frontend já está embutido no backend — não é necessário nenhum servidor adicional.

---

## 🔌 Endpoints da API REST

### Veículos
| Método | Endpoint                   | Descrição       |
|--------|----------------------------|-----------------|
| GET    | /api/veiculos              | Listar todos    |
| GET    | /api/veiculos/{id}         | Buscar por ID   |
| POST   | /api/veiculos              | Cadastrar       |
| PUT    | /api/veiculos/{id}         | Atualizar       |
| DELETE | /api/veiculos/{id}         | Remover         |
| GET    | /api/veiculos/filtrar      | Filtro avançado |
| GET    | /api/veiculos/estatisticas | Estatísticas    |

### Marcas
| Método | Endpoint         | Descrição    |
|--------|------------------|--------------|
| GET    | /api/marcas      | Listar todas |
| POST   | /api/marcas      | Cadastrar    |
| PUT    | /api/marcas/{id} | Atualizar    |
| DELETE | /api/marcas/{id} | Remover      |

### Modelos
| Método | Endpoint                     | Descrição    |
|--------|------------------------------|--------------|
| GET    | /api/modelos                 | Listar todos |
| GET    | /api/modelos/marca/{marcaId} | Por marca    |
| POST   | /api/modelos                 | Cadastrar    |
| PUT    | /api/modelos/{id}            | Atualizar    |
| DELETE | /api/modelos/{id}            | Remover      |

---

## 🧠 Conceitos de POO Aplicados

| Conceito          | Onde foi aplicado                                              |
|-------------------|----------------------------------------------------------------|
| **Classe**        | `Veiculo`, `Marca`, `Modelo` — entidades do mundo real         |
| **Encapsulamento**| Atributos privados com getters/setters                        |
| **Herança**       | Repositories herdam de `JpaRepository<T, ID>`                 |
| **Abstração**     | Interfaces `Repository` e camada `Service`                    |
| **Enum**          | `StatusVeiculo` — tipo seguro para status do veículo          |
| **Associação**    | `Veiculo` → `Modelo` → `Marca` (relacionamentos JPA)          |

---

## 🛠️ Tecnologias

- **Java 21** + **Spring Boot 3.2**
- **Spring Data JPA** (Hibernate)
- **MySQL 8**
- **HTML5 + CSS3 + JavaScript** (Frontend embutido no backend)
