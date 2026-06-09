# Mini-SIRH Backend - API Test Plan

## 1. Project Information

Project: Mini-SIRH Backend  
Technology: Spring Boot, MySQL, Spring Security, Swagger  
Base URL: http://localhost:8080  
Swagger URL: http://localhost:8080/swagger-ui/index.html

---

## 2. Authentication

### Login - Success

POST /api/auth/login

Request:
{
"username": "admin",
"password": "admin123"
}

Expected:
200 OK

---

### Login - Wrong Password

POST /api/auth/login

Request:
{
"username": "admin",
"password": "wrongpass"
}

Expected:
400 Bad Request

---

## 3. Security Tests

### Access protected endpoint without authentication

GET /api/collaborateurs

Expected:
401 Unauthorized

---

### Access protected endpoint with ADMIN

GET /api/collaborateurs

Auth:
Basic Auth admin / admin123

Expected:
200 OK

---

### Access ADMIN endpoint with EMPLOYEE

GET /api/users

Auth:
Basic Auth employee1 / emp123

Expected:
403 Forbidden

---

## 4. Departement API

GET /api/departements  
POST /api/departements  
PUT /api/departements/{id}  
DELETE /api/departements/{id}

Expected:
Clean response with:
- id
- nom
- description
- nombreCollaborateurs

---

## 5. Collaborateur API

GET /api/collaborateurs  
POST /api/collaborateurs  
PUT /api/collaborateurs/{id}  
DELETE /api/collaborateurs/{id}

Expected:
Clean response with:
- id
- nom
- prenom
- email
- telephone
- cin
- poste
- rfidCode
- statut
- departementId
- departementNom

---

## 6. Formation API

GET /api/formations  
POST /api/formations  
PUT /api/formations/{id}  
DELETE /api/formations/{id}  
POST /api/formations/{formationId}/collaborateurs/{collaborateurId}

Expected:
Clean response with:
- id
- titre
- description
- formateur
- dateDebut
- dateFin
- statut
- nombreCollaborateurs

---

## 7. Conge API

GET /api/conges  
POST /api/conges  
PUT /api/conges/{id}  
PUT /api/conges/{id}/accepter  
PUT /api/conges/{id}/refuser  
DELETE /api/conges/{id}

Expected:
Clean response with:
- id
- dateDebut
- dateFin
- motif
- statut
- collaborateurId
- collaborateurNomComplet

---

## 8. Pointage IoT API

POST /api/pointages  
GET /api/pointages  
GET /api/pointages/today

Test scenario:
1. First scan saves heureEntree
2. Second scan saves heureSortie
3. Third scan returns duplicate error
4. Unknown RFID returns not found error

Expected:
Clean response with:
- id
- datePointage
- heureEntree
- heureSortie
- statut
- deviceId
- collaborateurId
- collaborateurNomComplet
- rfidCode

---

## 9. Dashboard API

GET /api/dashboard/stats

Expected:
Dashboard statistics:
- totalCollaborateurs
- totalDepartements
- totalFormations
- totalConges
- pointagesAujourdhui
- retardsAujourdhui
- congesEnAttente

---

## 10. Python RFID Simulation

File:
iot-simulation/simulate_rfid.py

Command:
python simulate_rfid.py

Expected:
Python script sends RFID code to Spring Boot backend and receives pointage response.

---

## 11. Validation Summary

Backend status:
- Authentication: OK
- Security roles: OK
- CRUD APIs: OK
- IoT pointage: OK
- Dashboard: OK
- Swagger documentation: OK
- Python simulation: OK