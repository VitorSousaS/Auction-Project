## 🧾 Project Summary – Auction System

This project was developed as part of a selection process, with the goal of building a basic **auction system** using **Vue** for the frontend and **Spring Boot** for the backend. The database used was **PostgreSQL**.  
The entire development was done in one week, focusing on learning new tools and delivering a fully functional system.

**Client**: Vue
 
**Server**: Spring Boot. 

**Data Base**: PostgreSQL

---

### ✅ Step 1 – Database Structure (DDL)
Started by creating the database schema using a DDL script that included all tables, columns, and constraints (primary keys, foreign keys, and unique constraints), according to the provided diagram.  
All `NOT NULL` constraints and unique fields were correctly handled.

![alt Banco de dados][database]

---

### ✅ Step 2 – Initial Data Population (DML)
Created a DML script to populate the database with test data.  
At least 10 records were inserted into each table to simulate real-world scenarios and allow for complete testing of the system.

---

### ✅ Step 3 – RESTful API Implementation
Developed full CRUD (Create, Read, Update, Delete) endpoints for **all entities** in the system using REST principles:

- `GET /entity` – Retrieve all records
- `GET /entity/:id` – Retrieve a specific record
- `POST /entity` – Create a new record
- `PUT /entity/:id` – Update an existing record
- `DELETE /entity/:id` – Delete a record

The backend followed a layered architecture:
- **Controllers**: Route and handle requests
- **Services**: Business logic layer
- **Repositories**: Data access with Spring Data JPA
- **Entities**: Domain models

![alt_arquitetura][arquitetura]

---

### ✅ Step 4 – Front-End Routing and Navigation
Configured routing with Vue Router.  
The main screen (`App.vue`) included a navigation menu with links to:

- `#/unidades` → Units
- `#/empresas` → Companies
- `#/leiloes` → Auctions

Each view was created in its respective folder inside `views/`.

---

### ✅ Step 5 – CRUD UI for Units
Created a fully functional CRUD interface for the Unit entity using a `v-data-table`.  
All operations (create, edit, delete) could be performed directly from the UI and connected to the REST API.

- Page URL:                http://server/#/unidades  
- RESTful services URL:    http://server/unidades (GET | POST | PUT | DELETE)  
- HTML file:               views/unidade/Unidades.vue  
- COMPONENTS folder:       components/  

---

### ✅ Step 6 – Read-Only View for Companies
Developed a simple read-only view for Companies using a `v-data-table`.  
Displayed fields included: `CNPJ`, `Business Name`, `Phone`, and `Email`.

- Page URL:               http://server/#/empresas
- RESTful services URL:   http://server/empresas (GET)
- HTML:                   views/empresa/Empresas.vue
- COMPONENTS:             components/

---

### ✅ Step 7 – Read-Only View for Auctions
Built a read-only view to list Auctions with the following fields:

- Seller’s business name
- Expected start date
- Total auction value (calculated as the sum of item quantity × initial value)

- Page URL:               http://server/#/leiloes
- RESTful services URL:   http://server/leiloes (GET)
- HTML:                   views/leilao/Leiloes.vue
- COMPONENTS:             components/


---

### ✅ Step 8 – Company Form with Validation
Implemented a form to **create** and **edit** company records, with field validation including:

- Required fields
- Input masks for CNPJ, phone, and email
- Field length and format checks

Added links in the company list for editing and deleting records, using routes like `#/empresa/:id`.

- Page URL INCLUDE:        http://server/#/empresa
- Page URL EDIT:           http://server/#/empresa/:id
- RESTful services URL:    http://server/empresa (GET | POST | PUT | DELETE)
- HTML:                    views/empresa/Empresa.vue
- COMPONENTS:              components/


---

### ✅ Final Tests and Adjustments
Manually tested the application from end to end to ensure all routes, views, and services worked as expected.

---

💡 This project was a valuable learning experience, especially for quickly adapting to unfamiliar tools and delivering a complete full-stack solution within a limited timeframe.

[database]: leilao-api/src/main/resources/sql/database.png
[arquitetura]: leilao-api/src/main/resources/arquitetura.png













