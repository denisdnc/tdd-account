package com.example.account.controllers.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ContaResponseBody {
        private String id;
        private double saldo;

        @JsonCreator
        public ContaResponseBody(String id, double saldo) {
            this.id = id;
            this.saldo = saldo;
        }

        public String getId() {
            return this.id;
        }

        public double getSaldo() {
            return this.saldo;
        }
}
