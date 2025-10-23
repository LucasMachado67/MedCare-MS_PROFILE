package com.ms.patient.events;


import com.fasterxml.jackson.annotation.JsonProperty;

// Person Service (e Auth Service)
// Evento que contém os dados mínimos para criar credenciais no auth service
public class UserCreationEvent {


        @JsonProperty("person_id")
        private Long person_id;
        private String username;
        private String role;
        
        public Long getPerson_id() {
                return person_id;
        }
        public void setPerson_id(Long person_id) {
                this.person_id = person_id;
        }
        public String getUsername() {
                return username;
        }
        public void setUsername(String username) {
                this.username = username;
        }
        public String getRole() {
                return role;
        }
        public void setRole(String role) {
                this.role = role;
        }

        
}
