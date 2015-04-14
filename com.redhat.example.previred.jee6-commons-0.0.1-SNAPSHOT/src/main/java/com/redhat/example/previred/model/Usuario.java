/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.example.previred.model;

import java.io.Serializable;

import javax.validation.constraints.*;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 7, max = 8, message = "El RUT debe contener entre 7 y 8 números sin contar el dígito verificador")
    @Digits(fraction = 0, integer = 8, message ="El RUT solo puede estar formado de un número entero de hasta 8 dígitos")
    @NotNull
    private String run;

    @NotNull
    @Size(min = 1, max = 50, message = "El nombre no puede sobrepasar los 50 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "El nombre no puede contener números")
    private String nombre;

    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "El correo debe tener el formato <nombre>@<empresa>.<dominio>")
    private String email;

    @Size(min = 8, max = 10 , message =" El teléfono debe contener entre 8 y 10 dígitos")
    @Digits(fraction = 0, integer = 10, message="El teléfono solo puede estar formado de un número entero de hasta 10 dígitos")
    private String telefono;

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String phoneNumber) {
        this.telefono = phoneNumber;
    }
}
